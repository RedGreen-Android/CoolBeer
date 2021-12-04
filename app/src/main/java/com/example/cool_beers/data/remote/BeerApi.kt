package com.example.cool_beers.data.remote

import com.example.cool_beers.data.Constants
import com.example.cool_beers.data.model.BeerResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface BeerApi{
    @GET(Constants.END_POINT)
    suspend fun getAllBeers(): List<BeerResponse>

    @GET(Constants.END_POINT_PATH)
    suspend fun getBeerId(@Path("id") beerId: Int): List<BeerResponse>

    companion object{
        fun initRetrofit(): BeerApi{
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(BeerApi::class.java)
        }
    }
}