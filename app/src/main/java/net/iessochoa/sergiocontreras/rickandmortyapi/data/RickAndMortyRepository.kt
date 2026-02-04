package net.iessochoa.sergiocontreras.rickandmortyapi.data

import kotlinx.serialization.json.Json
import net.iessochoa.sergiocontreras.rickandmortyapi.network.RickAndMortyApiService
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object RickAndMortyRepository {

    private val baseUrl = "https://rickandmortyapi.com/api/"

    //Empezamos a inicializar primero Retrofit, siempre lazy.
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }.asConverterFactory("application/json".toMediaType()))
            .baseUrl(baseUrl)
            .build()
    }

    private val retrofitService: RickAndMortyApiService by lazy {
        retrofit.create(RickAndMortyApiService::class.java)
    }

    suspend fun getCharacters() = retrofitService.getCharacters()
    // En RickAndMortyRepository.kt
    suspend fun getCharactersByPage(page: Int) = retrofitService.getCharactersByPage(page)

}