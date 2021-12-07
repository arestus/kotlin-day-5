package com.example.books_filter_app

import android.app.Application
import com.fasterxml.jackson.databind.ObjectMapper
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class MyApplication : Application() {

    public lateinit var httpApiService: HttpApiService

    override fun onCreate() {
        super.onCreate()

        httpApiService = initHttpApiService()
    }

    private fun initHttpApiService(): HttpApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://httpapibooks.mocklab.io")
            .addConverterFactory(JacksonConverterFactory.create(ObjectMapper()))
            .build()
        return retrofit.create(HttpApiService::class.java)
    }

}