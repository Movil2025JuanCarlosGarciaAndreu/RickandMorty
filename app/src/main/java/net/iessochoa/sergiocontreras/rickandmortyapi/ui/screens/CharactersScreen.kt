package net.iessochoa.sergiocontreras.rickandmortyapi.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import net.iessochoa.sergiocontreras.rickandmortyapi.data.RickAndMortyRepository
import net.iessochoa.sergiocontreras.rickandmortyapi.network.RickAndMortyCharacter

@Composable
fun CharactersScreen(modifier: Modifier = Modifier) {



    Column(modifier = modifier) {

        val firstCharacter = getCharacters().first()
        Text(text = firstCharacter.name)

    }


}


private fun getCharacters(): List<RickAndMortyCharacter> {
    val scope = MainScope()
    var characters: List<RickAndMortyCharacter> = emptyList()
    scope.launch {
        characters = RickAndMortyRepository.getCharacters()
    }
    return characters

}