package com.apptikar.pokemonapp.di

import android.content.Context
import androidx.room.Room
import com.apptikar.pokemonapp.room.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext app : Context) = Room.databaseBuilder(app,PokemonDatabase::class.java,"Database").build()

    @Singleton
    @Provides
    fun provideRunDao(db: PokemonDatabase) = db.doa()
}