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
    @SerialName(value="species") val species: String,
    @SerialName("location") val location

)

/*
* {
    "name": "bulbasaur",
    "height": 7,
    "weight": 69,
    "base_experience": 64,
    "is_default": true
}*/

/*
* @Serializable
data class PokemonDetailDto(
    val name: String,
    val height: Int,
    val weight: Int,
    @SerialName("base_experience") val experience: Int,
    @SerialName("is_default") val isDefault: Boolean
)*/

/*
* {
  "tienda": {
    "nombre": "TechZone",
    "almacen": {
      "seccion": "Periféricos",
      "producto": {
        "id": 101,
        "nombre": "Ratón Gaming RGB",
        "precio": 45.99
      }
    }
  }
}*/

/*
* @Serializable
data class TiendaResponse(
    val tienda: TiendaDto
)

@Serializable
data class TiendaDto(
    val nombre: String,
    val almacen: AlmacenDto // Entramos a la segunda caja
)

@Serializable
data class AlmacenDto(
    val seccion: String,
    val producto: ProductoDto // Entramos a la tercera caja
)

@Serializable
data class ProductoDto(
    val id: Int,
    val nombre: String,
    val precio: Double
)*/