package com.hivian.lumapps_test.business.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitApiClient {

    private val okHttpClient by lazy { OkHttpClient() }

    private val retrofit: Retrofit by lazy {
        val builder = Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create())

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client: OkHttpClient = okHttpClient.newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(AuthInterceptor())
                .addInterceptor(loggingInterceptor)
                .build()
        builder.client(client).build()
    }

    fun <T> createService(tClass: Class<T>): T {
        return retrofit.create(tClass)
    }

}
