package com.example.countrybook

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import androidx.lifecycle.LiveData




@Dao
interface CountryDAO {

    @Query("SELECT * FROM country_table ORDER BY name ASC")
    fun getAlphabetizedWords(): Flow<List<Country>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(name: Country)

    @Query("DELETE FROM country_table")
    suspend fun deleteAll()

    @Query("SELECT COUNT(*) FROM country_table")
    fun loadLastTask():Flow<Int>

}