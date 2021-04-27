package com.apptikar.pokemonapp.pojo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject


@Entity(tableName = "PokemonTable")
 class  Pokemon @Inject constructor(

){
    @PrimaryKey(autoGenerate = true) @ColumnInfo (name = "id") var id : Int = 0
    @ColumnInfo (name = "name") var name: String? = null
    @ColumnInfo(name = "url")  var url: String? = null
}