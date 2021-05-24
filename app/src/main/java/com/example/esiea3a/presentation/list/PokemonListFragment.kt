package com.example.esiea3a.presentation.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.esiea3a.R
import com.example.esiea3a.presentation.api.PokeApi
import com.example.esiea3a.presentation.api.PokemonListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PokemonListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    private val adapter = PokemonAdapter(listOf(), ::onClickedPokemon)

    //private val sharedPref = activity?.getSharedPreferences("app", Context.MODE_PRIVATE)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.pokemon_recyclerView)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@PokemonListFragment.adapter

        }

        Singleton.pokeApi.getPokemonList().enqueue(object: retrofit2.Callback<PokemonListResponse>{
            override fun onFailure(call: Call<PokemonListResponse>, t:Throwable){
                //TODO("not implemented")
            }

            override fun onResponse(call: Call<PokemonListResponse>, response: Response<PokemonListResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val pokemonResponse = response.body()!!
                    adapter.updateList(pokemonResponse.results)
                }
            }

        })
    }


        //val list = getListFromCache()
        //if(list.isEmpty()){
            //callApi()
        //} else {
            //showList(list)
        //}
    //}

    //private fun getListFromCache(): List<Pokemon> {
        //sharedPref.
        //TODO
    //}

    //private fun saveListIntoCache() {
        //TODO("Not yet implemented")
    //}


    //private fun callApi() {
        //Singleton.pokeApi.getPokemonList().enqueue(object : Callback<PokemonListResponse> {
            //override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                //TODO("Not yet implemented")
            //}



    //private fun showList(pokeList: List<Pokemon>) {
        //adapter.updateList(pokeList)
    //}

    private fun onClickedPokemon(id: Int) {
        findNavController().navigate(R.id.navigateToPokemonDetailFragment, bundleOf(
                "pokemonId" to (id +1)
        ))

    }
}

