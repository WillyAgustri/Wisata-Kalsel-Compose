package com.example.wisatakalsel.model

data class Tour(
    val id : Int,
    val name : String,
    val image : Int,
    val location : String,
    val description : String,
    var isFavorite : Boolean = false
)
