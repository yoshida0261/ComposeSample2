package com.stah.githubcomposesample.model.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Inject
import okhttp3.OkHttpClient

import okhttp3.Interceptor
import okhttp3.Request


class ApiClientProvider @Inject constructor() {
    companion object {
        private const val API_END_POINT = "https://api.github.com/"
    }

    @ExperimentalSerializationApi
    fun provide(): ApiClient {

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(Interceptor { chain ->
            val original: Request = chain.request()

            //header設定
            val request: Request = original.newBuilder()
                .header("Accept", "application/json")
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        })
        //ログ出力設定
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        httpClient.addInterceptor(logging)
        val client: OkHttpClient = httpClient.build()


        return Retrofit.Builder()
            .baseUrl(API_END_POINT)
            .addConverterFactory(
                Json { ignoreUnknownKeys = true }.asConverterFactory(
                    "application/json".toMediaType()
                ),
            )
            .client(client)
            .build()
            .create(ApiClient::class.java)
    }
}
