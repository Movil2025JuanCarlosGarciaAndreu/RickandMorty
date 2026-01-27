package net.iessochoa.sergiocontreras.rickandmortyapi.ui.screens

import net.iessochoa.sergiocontreras.rickandmortyapi.network.RickAndMortyCharacterDto

data class CharacterScreenUiState (
    val characters: List<RickAndMortyCharacterDto> = emptyList()
)