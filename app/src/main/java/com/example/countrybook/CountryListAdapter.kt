package com.example.countrybook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class CountryListAdapter : ListAdapter<Country, CountryListAdapter.CountryViewHolder>(WordsComparator()) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
            return CountryViewHolder.create(parent)
        }

        override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
            val current = getItem(position)
            holder.bind(current)
        }

        class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val countryName: TextView = itemView.findViewById(R.id.country_name)
            private val flagView: ImageView = itemView.findViewById(R.id.flag_view)
            private val country_line2: TextView = itemView.findViewById(R.id.country_line2)
            private val country_line3: TextView = itemView.findViewById(R.id.country_line3)
            private val country_line4: TextView = itemView.findViewById(R.id.country_line4)
            private val country_line5: TextView = itemView.findViewById(R.id.country_line5)


            fun bind(item: Country) {
                countryName.text = item.name +"  "+ item.capital
                country_line2.text="Population: "+item.population
                country_line3.text="Region: "+item.region+" Sub-region: "+item.subregion
                country_line4.text = "Borders: "+item.borders
                country_line5.text= "Languages: "+item.languages

                val media = item.flag
                Glide.with(flagView.context)
                    .load(media)
                    .into(flagView)

            }

            companion object {
                fun create(parent: ViewGroup): CountryViewHolder {
                    val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.recyclerview_item, parent, false)
                    return CountryViewHolder(view)
                }
            }
        }

        class WordsComparator : DiffUtil.ItemCallback<Country>() {
            override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }