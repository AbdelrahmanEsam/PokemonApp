package com.apptikar.pokemonapp.network

import com.apptikar.pokemonapp.pojo.PokemonList
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitApi {

    @GET("pokemon")
  suspend fun   getPokemons() : Response<PokemonList>
}