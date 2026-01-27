package net.iessochoa.sergiocontreras.rickandmortyapi.network

import retrofit2.http.GET

interface RickAndMortyApiService {

    @GET("character")
    suspend fun getCharacters(): List<RickAndMortyCharacter>

}