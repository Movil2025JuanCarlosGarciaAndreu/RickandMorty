package net.iessochoa.sergiocontreras.rickandmortyapi.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import net.iessochoa.sergiocontreras.rickandmortyapi.data.RickAndMortyRepository

class CharacterScreenViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(CharacterScreenUiState())
    val uiState: StateFlow<CharacterScreenUiState> = _uiState.asStateFlow()

    init {fetchCharacters(1)}
        fun fetchCharacters(page: Int) {
        viewModelScope.launch {

            try {
                /*_uiState.update { it ->
                    it.copy(
                        currentState = RequestStatus.Loading
                    )
                }

                val response = RickAndMortyRepository.getCharacters()

                _uiState.update { it ->
                    it.copy(
                        currentState = RequestStatus.Success(response.results)
                    )
                }*/
                _uiState.update { it.copy(currentState = RequestStatus.Loading,currentPage = page) }

                // Llamamos a la nueva función de la API
                val response = RickAndMortyRepository.getCharactersByPage(page)

                _uiState.update {
                    it.copy(
                        currentState = RequestStatus.Success(response.results),
                        pageUI = response.pageInfo // Actualizamos info global (total de páginas)
                    )
                }

            } catch (e: Exception) {

                e.printStackTrace() // Esto te dirá la línea exacta del fallo
                _uiState.update { it ->
                    it.copy(
                        currentState = RequestStatus.ErrorState
                    )
                }
            }
        }
    }
}




