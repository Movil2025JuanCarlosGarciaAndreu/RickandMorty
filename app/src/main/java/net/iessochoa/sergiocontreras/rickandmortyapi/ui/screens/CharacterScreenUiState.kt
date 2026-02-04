package net.iessochoa.sergiocontreras.rickandmortyapi.ui.screens

import net.iessochoa.sergiocontreras.rickandmortyapi.network.PageInfo
import net.iessochoa.sergiocontreras.rickandmortyapi.network.RickAndMortyCharacterDto

data class CharacterScreenUiState (
    val characters: List<RickAndMortyCharacterDto> = emptyList(),
    val pageUI : PageInfo= PageInfo(0, 0, null, null),
    val currentState: RequestStatus = RequestStatus.Idle
)

sealed interface RequestStatus{
    object Loading: RequestStatus
    data class Success(val characters: List<RickAndMortyCharacterDto>) : RequestStatus
    object ErrorState : RequestStatus
    object Idle: RequestStatus
}