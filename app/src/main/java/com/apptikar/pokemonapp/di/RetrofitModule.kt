package com.apptikar.pokemonapp.di

import android.content.Context
import androidx.room.Room
import com.apptikar.pokemonapp.network.RetrofitApi
import com.apptikar.pokemonapp.room.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun retrofitBuilder(): RetrofitApi = Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/").addConverterFactory(GsonConverterFactory.create()).build()
          .create(RetrofitApi::class.java)




}