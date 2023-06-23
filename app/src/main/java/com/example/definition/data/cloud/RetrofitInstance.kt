package com.example.definition.data.cloud

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    // За допомогою бібліотеки Retrofitи підключення до api за базовим посиланням,
    //конвертація за допомогою GsonConverterFactory
    //та створення DictionaryApi
    private val client = OkHttpClient.Builder().build()

    private var gson = GsonBuilder()
        .setLenient()
        .create()

    val retrofit: DictionaryApi = Retrofit.Builder()
        .baseUrl("https://api.dictionaryapi.dev/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()
        .create(DictionaryApi::class.java)

}