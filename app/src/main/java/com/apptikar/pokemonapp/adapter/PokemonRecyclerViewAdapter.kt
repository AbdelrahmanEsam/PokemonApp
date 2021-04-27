package com.apptikar.pokemonapp.adapter
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.apptikar.pokemonapp.R
import com.apptikar.pokemonapp.pojo.Pokemon
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.pokemon_item.view.*

class PokemonRecyclerViewAdapter : RecyclerView.Adapter<PokemonRecyclerViewAdapter.ViewHolder>() {
    var pokemonArrayList = arrayListOf<Pokemon>()
    private lateinit var mContext: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return  ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item,parent,false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         holder.itemView.apply {
             Log.d("abdo", pokemonArrayList.size.toString())
         pokemonName.text = pokemonArrayList[position].name
          Glide.with(mContext).load(pokemonArrayList[position].url).into(pokemonImage)
         }
    }

    override fun getItemCount(): Int {
        return  pokemonArrayList.size
    }

    fun setList(data: List<Pokemon>,  context: Context)
    {
     this.pokemonArrayList = data as ArrayList<Pokemon>
     mContext = context
        notifyDataSetChanged()
    }

    fun getPokemonAt(position: Int): Pokemon {
       return pokemonArrayList[position]
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {

    }

}


