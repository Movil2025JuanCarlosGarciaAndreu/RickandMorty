package net.iessochoa.sergiocontreras.rickandmortyapi.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RickAndMortyResponse(
    @SerialName(value="info") val pageInfo: PageInfo,
    @SerialName(value="results") val results: List<RickAndMortyCharacterDto>
)


@Serializable
data class PageInfo(
    @SerialName(value="count") val count: Int,
    @SerialName(value="pages") val pages: Int,
    @SerialName(value="next") val next: String?,
    @SerialName(value="prev") val  prev: String?
)


@Serializable
data class RickAndMortyCharacterDto(
    @SerialName(value="id") val id: Int,
    @SerialName(value="name") val name: String,
    @SerialName(value="status") val status: String,
    @SerialName(value="species") val species: String
)