package com.apptikar.pokemonapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.apptikar.pokemonapp.pojo.Pokemon

@Dao
interface Dao {

    @Insert
    suspend  fun insert(pokemon:Pokemon)

    @Delete
    suspend  fun delete(pokemon:Pokemon)

    @Update
    suspend  fun update(pokemon:Pokemon)

    @Query("DELETE FROM PokemonTable")
    suspend  fun deleteAll()

    @Query("SELECT * FROM  PokemonTable")
    fun selectAll(): LiveData<List<Pokemon>>
}