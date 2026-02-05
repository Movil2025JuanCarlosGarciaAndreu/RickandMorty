package net.iessochoa.sergiocontreras.rickandmortyapi.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import net.iessochoa.sergiocontreras.rickandmortyapi.network.RickAndMortyCharacterDto
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import net.iessochoa.sergiocontreras.rickandmortyapi.network.PageInfo

@Composable
fun CharactersScreen(modifier: Modifier = Modifier) {
    // Obtenemos el ViewModel y el estado
    val viewModel: CharacterScreenViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val totalPages = uiState.pageUI.pages // El número que viene de la API
    //var paginaActual by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título de la pantalla
        Text(
            text = "Rick & Morty Characters",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )


        DynamicSelectTextField(
            selectedValue = uiState.currentPage.toString(),
            options = (1..totalPages).map { it.toString() }, // Crea lista del 1 al 42
            label = "Selecciona Página",
            onValueChangedEvent = { nuevaPagina ->
                viewModel.fetchCharacters(nuevaPagina.toInt())
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Manejo de estados para mostrar la lista o el indicador de carga
        when (val status = uiState.currentState) {
            is RequestStatus.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Cargando personajes...")
                }
            }
            is RequestStatus.ErrorState -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Error al conectar con la API", color = Color.Red)
                }
            }
            is RequestStatus.Success -> {
                // Lista en cuadrícula de 2 columnas
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(status.characters) { character ->
                        CharacterItem(
                            character = character,
                            page = uiState.currentPage // <-- Así se añade el segundo parámetro
                        )
                    }
                }
            }
            else -> {}
        }
    }
}

@Composable
fun CharacterItem(character: RickAndMortyCharacterDto, page : Int) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            // Imagen del personaje usando su ID
            AsyncImage(

                model = "https://rickandmortyapi.com/api/character/avatar/${character.id}.jpeg",
                contentDescription = character.name,
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Nombre en negrita
            Text(
                text = character.name,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1
            )

            // Especie
            Text(
                text = character.species,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
            Text(
                text = page.toString(),
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            // Estado (Alive/Dead) con color condicional
            val statusColor = when(character.status.lowercase()) {
                "alive" -> Color(0xFF4CAF50) // Verde
                "dead" -> Color(0xFFF44336)  // Rojo
                else -> Color.Gray
            }

            Text(
                text = character.status,
                style = MaterialTheme.typography.labelMedium,
                color = statusColor
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DynamicSelectTextField(
    selectedValue: String,
    options: List<String>,
    label: String,
    onValueChangedEvent: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedValue,
            onValueChange = {},
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
            modifier = Modifier.menuAnchor().fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    onClick = {
                        onValueChangedEvent(selectionOption)
                        expanded = false
                    }
                )
            }
        }
    }
}

