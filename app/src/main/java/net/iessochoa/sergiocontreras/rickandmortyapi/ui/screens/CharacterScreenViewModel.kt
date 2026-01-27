package net.iessochoa.sergiocontreras.rickandmortyapi.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import net.iessochoa.sergiocontreras.rickandmortyapi.data.RickAndMortyRepository

class CharacterScreenViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(CharacterScreenUiState())
    val uiState: StateFlow<CharacterScreenUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val response = RickAndMortyRepository.getCharacters()
            val characters = response.results

        }


    }



}

