package com.apptikar.pokemonapp.repository

import androidx.lifecycle.LiveData
import com.apptikar.pokemonapp.network.RetrofitApi
import com.apptikar.pokemonapp.pojo.Pokemon
import com.apptikar.pokemonapp.pojo.PokemonList
import com.apptikar.pokemonapp.room.Dao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


class Repository @Inject constructor(private val retrofitApi : RetrofitApi,private val dao: Dao){



    suspend fun getPokemons() : Response<PokemonList>
    {
         return  retrofitApi.getPokemons()
    }

    fun insert(pokemon: Pokemon) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insert(pokemon)
        }
    }

    fun delete(pokemon: Pokemon) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.delete(pokemon)
        }
    }

    fun update(pokemon: Pokemon) {
        CoroutineScope(Dispatchers.IO).launch {
           dao.update(pokemon)
        }
    }

    fun deleteAll() {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteAll()
        }
    }

    fun selectAll() : LiveData<List<Pokemon>>
    {
          return   dao.selectAll()
    }
}