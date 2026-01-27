package net.iessochoa.sergiocontreras.rickandmortyapi

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.iessochoa.sergiocontreras.rickandmortyapi.ui.screens.CharactersScreen

@Composable
fun RickAndMortyApp(modifier:Modifier = Modifier) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        CharactersScreen(modifier = Modifier.padding(innerPadding))
    }
}