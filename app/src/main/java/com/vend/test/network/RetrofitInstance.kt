package com.vend.test.network

import com.vend.test.apiservice.MyAPIService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {

        fun getInstance(): MyAPIService? {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(MyAPIService::class.java)
        }
    }
}