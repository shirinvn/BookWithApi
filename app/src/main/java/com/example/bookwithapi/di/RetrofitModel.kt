package com.example.bookwithapi.di

import com.example.bookwithapi.BASE_URL
import com.example.bookwithapi.data.BookApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModel {



    @Singleton
    @Provides
    fun ProvideRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun ProvideApi(retrofit: Retrofit): BookApi{

        return retrofit.create(BookApi::class.java)

    }
}



