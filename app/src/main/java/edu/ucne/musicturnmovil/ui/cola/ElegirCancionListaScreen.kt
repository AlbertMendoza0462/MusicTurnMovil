package edu.ucne.musicturnmovil

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import edu.ucne.musicturnmovil.data.remote.dto.ColaCancionDto
import edu.ucne.musicturnmovil.ui.colaCanciones.ColaViewModel
import edu.ucne.musicturnmovil.utils.Components.CardComponent
import edu.ucne.musicturnmovil.utils.Screen
import kotlinx.coroutines.launch

@Composable
fun ElegirCancionListaScreen(
    navController: NavController,
    viewModel: ColaViewModel = hiltViewModel()
) {
    viewModel.cargarCanciones()
    val localContext = LocalContext.current

    if (viewModel.dialogoAbierto) {
        AlertDialog(
            title = { Text(text = "Seguro que desea solicitar esta canción?") },
            text = { Text(text = viewModel.cancionSeleccionada.nombre) },
            buttons = {
                Row(modifier = Modifier.padding(20.dp)) {
                    Button(onClick = {
                        viewModel.solicitar()
                        navController.navigate(Screen.ConsultaColaScreen.Route)
                    }) {
                        Text(text = "Solicitar")
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Button(onClick = { viewModel.dialogoAbierto = false }) {
                        Text(text = "Cancelar")
                    }
                }
            },
            onDismissRequest = { viewModel.dialogoAbierto = false }
        )
    }

    Scaffold(
        floatingActionButton = {
            Button(onClick = { viewModel.cargarCanciones() }) {
                Text(text = "Recargar")
            }
        },
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    icon = {
                        Icon(
                            Icons.Filled.Star,
                            contentDescription = null
                        )
                    },
                    label = { Text("Siguientes") },
                    selected = false,
                    onClick = { navController.navigate(Screen.ConsultaColaScreen.Route) }
                )
                BottomNavigationItem(
                    icon = {
                        Icon(
                            Icons.Filled.List,
                            contentDescription = null
                        )
                    },
                    label = { Text("Solicitar") },
                    selected = true,
                    onClick = { navController.navigate(Screen.ElegirCancionListaScreen.Route) }
                )
            }
        }
    ) {
        it
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 28.dp, start = 28.dp, bottom = 70.dp, top = 68.dp)
        ) {
            Text(
                text = "Elija su Canción",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(viewModel.canciones) {
                    CardComponent(
                        titulo = it.nombre,
                        clickeable = true,
                        onClick = {
                            viewModel.abrirDialogo(it)
                        }
                    )
                }
            }
        }
    }
}