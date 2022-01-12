package com.example.countrybook

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "country_table")
class Country(
              @ColumnInfo(name = "name") val name: String,
              @ColumnInfo(name = "capital") val capital: String,
              @ColumnInfo(name = "flag") val flag: String,
              @ColumnInfo(name = "region") val region: String,
              @ColumnInfo(name = "subregion") val subregion: String,
              @ColumnInfo(name = "population") val population: String,
              @ColumnInfo(name = "borders") val borders: String,
              @ColumnInfo(name = "languages") val languages: String)
{

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}