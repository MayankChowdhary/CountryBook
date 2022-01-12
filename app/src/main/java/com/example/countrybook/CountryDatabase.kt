package com.example.countrybook

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [Country::class], version = 1, exportSchema = false)
public abstract class CountryDatabase : RoomDatabase() {

    abstract fun countryDao(): CountryDAO


    private class CountryDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val countryDao = database.countryDao()

                    // Delete all content here.
                    countryDao.deleteAll()

                   // Log.d("Injecting Room", "onCreate: Injecting...")
                    // Add sample words.
                   /* var country = Country("India","Asia","uDDF2","Asia","AsiaContinent","1 Billion","china, pakistan","Hindi English")
                    countryDao.insert(country)
                    country = Country("China","Asia","uDDF2","Asia","AsiaContinent","1 Billion","china, pakistan","Hindi English")
                    countryDao.insert(country)*/

                }
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: CountryDatabase? = null

        fun getDatabase(context: Context, applicationScope: CoroutineScope): CountryDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CountryDatabase::class.java,
                    "country_database"
                ).addCallback(CountryDatabaseCallback(applicationScope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}