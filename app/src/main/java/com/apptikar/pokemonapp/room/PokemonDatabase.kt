package com.apptikar.pokemonapp.room
import androidx.room.Database
import androidx.room.RoomDatabase
import com.apptikar.pokemonapp.pojo.Pokemon

@Database(entities = [Pokemon::class], version = 1, exportSchema = false)
abstract  class PokemonDatabase : RoomDatabase()
{

    abstract fun  doa() : Dao
}