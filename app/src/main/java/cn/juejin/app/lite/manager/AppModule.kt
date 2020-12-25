@file:Suppress("MemberVisibilityCanBePrivate", "HasPlatformType")

package cn.juejin.app.lite.manager

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {

    private var gson: Gson? = null
    private var okHttpClient: OkHttpClient? = null
    private var retrofit: Retrofit? = null

    @JvmStatic
    fun providerGson(): Gson {
        if (null == gson) {
            gson = Gson()
        }
        return gson!!
    }

    @JvmStatic
    fun providerOkHttpClient(): OkHttpClient {
        if (null == okHttpClient) {
            okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
        }
        return okHttpClient!!
    }

    @JvmStatic
    fun providerRetrofit(): Retrofit {
        if (null == retrofit) {
            retrofit = Retrofit.Builder()
                .baseUrl("https://api.juejin.cn/")
                .addConverterFactory(GsonConverterFactory.create(providerGson()))
                .client(providerOkHttpClient())
                .build()
        }
        return retrofit!!
    }
}