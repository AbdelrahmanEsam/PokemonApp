package com.apptikar.pokemonapp.pojo
data class PokemonList constructor(
val count: Int,
val next: String,
val previous: Any,
val results: List<Pokemon>
)