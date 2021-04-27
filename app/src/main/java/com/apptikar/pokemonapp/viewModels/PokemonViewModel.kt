package com.apptikar.pokemonapp.viewModels
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apptikar.pokemonapp.pojo.Pokemon
import com.apptikar.pokemonapp.pojo.PokemonList
import com.apptikar.pokemonapp.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class PokemonViewModel @ViewModelInject constructor(private val repository: Repository): ViewModel() {
 private  val  pokemons  = MutableLiveData<List<Pokemon>>()
    lateinit var    favoritePokemons  : LiveData<List<Pokemon>>
    fun getNonMutableLiveData(): LiveData<List<Pokemon>> = pokemons

     fun getPokemons()
    {

        viewModelScope.launch {
        val pokemons1 = repository.getPokemons()
            if (pokemons1.isSuccessful) {
                withContext(Dispatchers.Main)
                {
                pokemons.value = pokemons1.body()!!.results.map {
                it.apply {
                    val split = this.url!!.split("/")
                    this.url = "https://pokeres.bastionbot.org/images/pokemon/${split[split.lastIndex - 1]}.png"
                }
                }
            }
            }
        }




    }

    fun insert(pokemon: Pokemon) {
        repository.insert(pokemon)
    }

    fun delete(pokemon: Pokemon) {
        repository.delete(pokemon)
    }

    fun selectAll() {
        favoritePokemons = repository.selectAll()
    }


}