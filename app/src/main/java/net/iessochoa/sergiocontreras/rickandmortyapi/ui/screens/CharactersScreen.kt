package net.iessochoa.sergiocontreras.rickandmortyapi.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
@Composable
fun CharactersScreen(modifier: Modifier = Modifier) {

    val viewModel: CharacterScreenViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val characters = uiState.characters

    Column(modifier = modifier) {

        characters.forEach {
            Text(
                text = it.id.toString()
            )
            Text(
                text = it.name
            )
            Text(
                text = it.status
            )
            Text(
                text = it.species
            )
            Spacer(modifier = Modifier.padding(vertical = 16.dp))
        }


    }


}

