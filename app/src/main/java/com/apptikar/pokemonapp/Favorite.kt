package com.apptikar.pokemonapp

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
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class Favorite : AppCompatActivity() {
    private val viewModel: PokemonViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

       val adapter = PokemonRecyclerViewAdapter()
        favoritePokemonRecyclerView.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@Favorite) }

        viewModel.selectAll()
        viewModel.favoritePokemons.observe(this@Favorite, {
         adapter.setList(it,this)
        })
        goBack.setOnClickListener {
         onDestroy()
        }
        swipeSetup(adapter)
    }


    private fun swipeSetup(adapter: PokemonRecyclerViewAdapter) {
        val itemTouchHelper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean { return false }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapterPosition = viewHolder.adapterPosition
                val pokemonAt = adapter.getPokemonAt(adapterPosition)
                viewModel.delete(pokemonAt)
                adapter.notifyItemRemoved(adapterPosition)
                Toast.makeText(this@Favorite, "Deleted", Toast.LENGTH_LONG).show()
            }

        })
        itemTouchHelper.attachToRecyclerView(favoritePokemonRecyclerView)


    }
}
