package com.example.data.network

import android.content.Context
import com.example.data.mockserver.MockInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    internal fun provideApiservice(
        @ApplicationContext context: Context
    ): Apiservice {
        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(MockInterceptor(context))
            .build()

        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://kurly.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(Apiservice::class.java)
    }
}