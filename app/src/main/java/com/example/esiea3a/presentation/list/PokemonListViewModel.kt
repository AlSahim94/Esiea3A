package com.example.esiea3a.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.esiea3a.presentation.api.PokemonListResponse
import retrofit2.Call
import retrofit2.Response

class PokemonListViewModel : ViewModel(){

    val pokeList : MutableLiveData<List<Pokemon>> = MutableLiveData()

    init {
        callApi()

    }

    private fun callApi() {
        Singleton.pokeApi.getPokemonList().enqueue(object: retrofit2.Callback<PokemonListResponse>{
            override fun onFailure(call: Call<PokemonListResponse>, t:Throwable){
                //TODO("not implemented")
            }

            override fun onResponse(call: Call<PokemonListResponse>, response: Response<PokemonListResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val pokemonResponse = response.body()!!
                    pokeList.value = pokemonResponse.results


                }
            }

        })
    }
}