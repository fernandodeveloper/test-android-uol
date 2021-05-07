package br.com.fernandodeveloper.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiBase {

    const val API_URL = "https://5f5a8f24d44d640016169133.mockapi.io/api/"

    fun provideHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .followRedirects(true)
            .followSslRedirects(true)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS).build()

    inline fun <reified T> createWebService(okHttpClient: OkHttpClient): T =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(API_URL)
            .build()
            .create(T::class.java)


}
