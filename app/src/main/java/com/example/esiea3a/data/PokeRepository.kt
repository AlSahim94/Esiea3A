package com.example.esiea3a.data

import com.example.esiea3a.presentation.list.Pokemon

interface PokeRepository {
    fun getPokeList() : List<Pokemon>
}