package com.apptikar.pokemonapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apptikar.pokemonapp.adapter.PokemonRecyclerViewAdapter
import com.apptikar.pokemonapp.viewModels.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
   private val viewModel: PokemonViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.getPokemons()
        val adapter = PokemonRecyclerViewAdapter()
        pokemonRecyclerView.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        viewModel.getNonMutableLiveData().observe(this,{
            adapter.setList(it,this)
        })
        goToFavorite.setOnClickListener {
            startActivity(Intent(this,Favorite::class.java))
        }
     swipeSetup(adapter)
    }
   private fun swipeSetup(adapter: PokemonRecyclerViewAdapter)
   {
       val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
           override fun onMove(
               recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
             return false
           }

           override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
               val adapterPosition = viewHolder.adapterPosition
               val pokemonAt = adapter.getPokemonAt(adapterPosition)
               viewModel.insert(pokemonAt)
               adapter.notifyDataSetChanged()
               Toast.makeText(this@MainActivity,"saved",Toast.LENGTH_LONG).show()
           }

       })
       itemTouchHelper.attachToRecyclerView(pokemonRecyclerView)



   }
}

