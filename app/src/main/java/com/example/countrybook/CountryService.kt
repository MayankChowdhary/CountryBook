package com.example.countrybook

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CountryService {
        @GET("v3.1/region/{id}")
        fun getCountryData(@Path("id") id:String): Call<List<CountryResponse>>

    @GET("v3.1/region/{id}")
    fun getLanguageData(@Path("id") id:String): Call<JsonArray>
    }