package net.iessochoa.sergiocontreras.rickandmortyapi.network

import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApiService {

    @GET("character")
    suspend fun getCharacters(): RickAndMortyResponse

    @GET("character")
    suspend fun getCharactersByPage(@Query("page") page: Int): RickAndMortyResponse

}