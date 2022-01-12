package com.example.countrybook

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast

import android.view.Menu
import android.view.MenuItem
import android.view.View

import android.widget.ArrayAdapter
import androidx.core.view.MenuItemCompat
import android.widget.Spinner
import android.widget.AdapterView

import android.widget.TextView


class MainActivity : AppCompatActivity() {

    var isFirstLaunch=true
    lateinit var sharedPreferences: SharedPreferences

    private val mainActivityViewModel: MainActivityViewModel by viewModels {
        MainActivityViewModelFactory((application as CountryApplication).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         sharedPreferences = this.getSharedPreferences("com.example.countrybook", Context.MODE_PRIVATE)
         region = sharedPreferences.getString("region","asia").toString()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = CountryListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.hasFixedSize()

        mainActivityViewModel.allCountry.observe(this, Observer { countries ->
            // Update the cached copy of the words in the adapter.
            countries?.let { adapter.submitList(it) }
        })

        mainActivityViewModel.tableCount.observe(this, Observer { count ->
            // Update the cached copy of the words in the adapter.
             if(count <= 0  && isFirstLaunch){
                 Log.d("Count", "onCreate: $count")
                 mainActivityViewModel.getCurrentData(this, region)
            }

        })

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        val spinner = MenuItemCompat.getActionView(menu.findItem(R.id.spinner)) as Spinner

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.continents, android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter
        spinner.onItemSelectedListener = listener;
        val spinnerPosition = adapter.getPosition(region)
        spinner.setSelection(spinnerPosition)

        return true
    }

    private val listener: AdapterView.OnItemSelectedListener =
        object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                (parent.getChildAt(0) as TextView).setTextColor(Color.WHITE)
                region = (view as TextView).text.toString()
                if(!isFirstLaunch) {
                    mainActivityViewModel.getCurrentData(this@MainActivity, region)
                    val editor:SharedPreferences.Editor =  sharedPreferences.edit()
                    editor.putString("region", region)
                    editor.apply()
                }else{
                    isFirstLaunch=false
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> {
                mainActivityViewModel.delete()
                Toast.makeText(this@MainActivity, "Successfully Deleted!", Toast.LENGTH_LONG).show()
            }
            R.id.action_reload -> {
                mainActivityViewModel.getCurrentData(this, region)
            }
            else -> super.onOptionsItemSelected(item)

        }
        return true
    }


    companion object {

        var BaseUrl = "https://restcountries.com/"
        var region = "asia"
    }
}