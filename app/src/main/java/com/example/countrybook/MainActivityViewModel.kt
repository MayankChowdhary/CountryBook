package com.example.countrybook

import android.R.attr
import android.util.Log
import androidx.lifecycle.*
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.JsonArray

import android.app.ProgressDialog
import android.content.Context

import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.widget.Toast

class MainActivityViewModel(private val repository: CountryRepository) : ViewModel() {


    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allCountry: LiveData<List<Country>> = repository.allWords.asLiveData()
    val tableCount: LiveData<Int> = repository.tableCount.asLiveData()
    private lateinit var progressDialog: ProgressDialog;

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(country: Country) = viewModelScope.launch {
        repository.insert(country)

    }

    fun delete() = viewModelScope.launch {
        repository.delete()
    }




    fun getCurrentData(context: Context, continent: String) {
        setDialog(context)
        delete()
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val retrofit = Retrofit.Builder()
            .baseUrl(MainActivity.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(CountryService::class.java)
        val call = service.getCountryData(continent)

        call.enqueue(object : Callback<List<CountryResponse>> {
            override fun onResponse(
                call: Call<List<CountryResponse>>,
                response: Response<List<CountryResponse>>
            ) {
                if (response.code() == 200) {
                    val countryJson = response.body()

                    val call2 = service.getLanguageData(continent)
                    call2.enqueue(object : Callback<JsonArray> {
                        override fun onResponse(
                            call: Call<JsonArray>,
                            response: Response<JsonArray>
                        ) {
                            if (response.code() == 200) {
                                val languagesMap = hashMapOf<String, MutableList<String>>()
                                val languageJson = response.body()?.asJsonArray
                                if (languageJson != null) {
                                    for (i in 0 until languageJson.size()) {
                                        try {
                                            val row: JsonObject = languageJson[i] as JsonObject
                                            val countryObject = row.get("name") as JsonObject
                                            val countryName = countryObject.get("common").asString
                                            val dataResult: JsonObject =
                                                row.get("languages") as JsonObject
                                            val allLanguages = dataResult.keySet()
                                            val mutableListString: MutableList<String> =
                                                mutableListOf<String>()
                                            allLanguages.forEach {
                                                mutableListString.add(
                                                    0,
                                                    dataResult.get(it).toString()
                                                )
                                                // Log.d(countryName, "Languages: "+  dataResult.get(it))
                                            }
                                            languagesMap[countryName] = mutableListString
                                        } catch (e: Exception) {
                                            e.printStackTrace()
                                        }
                                    }


                                    countryJson?.forEach {
                                        //Log.d("Added country", " Country"+it.name)
                                        insert(
                                            Country(
                                                it.name?.common.toString(),
                                                it.capital.toString(),
                                                it.flags?.png.toString(),
                                                it.region.toString(),
                                                it.subregion.toString(),
                                                it.population.toString(),
                                                it.borders.toString(),
                                                languagesMap[it.name?.common].toString()
                                            )
                                        )

                                    }

                                    hideDialog(context)


                                }


                            }
                        }

                        override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                            Log.e("Retrofit Failure", "onFailure: " + t.message.toString())
                            hideDialog(context)
                        }
                    })

                }
            }

            override fun onFailure(call: Call<List<CountryResponse>>, t: Throwable) {
                Log.e("Retrofit Failure", "onFailure: " + t.message.toString())
                hideDialog(context)
            }
        })
    }

    private fun setDialog(context: Context) {

        progressDialog = ProgressDialog(context)
        progressDialog.setTitle("Loading CountryBook")
        progressDialog.setMessage("Please Wait...")
        progressDialog.setCancelable(false)
        progressDialog.show()
    }

    fun hideDialog(context: Context) {
        progressDialog.dismiss()
    }
}

class MainActivityViewModelFactory(private val repository: CountryRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainActivityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}