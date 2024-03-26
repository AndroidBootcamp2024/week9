package com.kodeco.android.countryinfo

import android.app.Application
import com.kodeco.android.countryinfo.network.CountryService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class CountryApp :Application(){
    lateinit var moshi: Moshi
    lateinit var retrofit: Retrofit
    lateinit var  service: CountryService
    override fun onCreate() {
        super.onCreate()
        moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        retrofit = Retrofit.Builder()
            .baseUrl("https://restcountries.com/v3.1/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        service = retrofit.create(CountryService::class.java)
    }

    fun getCountryService(): CountryService {
        return service
    }
}