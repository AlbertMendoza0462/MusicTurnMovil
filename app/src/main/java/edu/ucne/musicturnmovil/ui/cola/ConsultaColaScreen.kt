package edu.ucne.musicturnmovil

import android.os.Handler
import android.os.Looper
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.musicturnmovil.ui.colaCanciones.ColaViewModel
import edu.ucne.musicturnmovil.utils.Components.CardComponent
import edu.ucne.musicturnmovil.utils.Screen

@Composable
fun ConsultaColaScreen(
    navController: NavController,
    viewModel: ColaViewModel = hiltViewModel()
) {
    val myHandler = Handler(Looper.getMainLooper())

    myHandler.post(object : Runnable {
        override fun run() {
            viewModel.cargarColaCanciones()
            myHandler.postDelayed(this, 5000)
        }
    })
    val localContext = LocalContext.current
    Scaffold(
        floatingActionButton = {
            Button(onClick = { viewModel.cargarColaCanciones() }) {
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
                    selected = true,
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
                    selected = false,
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
                text = "Canciones Siguientes...",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                var count = 0
                items(viewModel.colaCanciones) {
                    CardComponent(
                        titulo = it.nombre,
                        primera = count == 0,
                        cuerpo = if (count == 0) "Reproducción en curso" else null
                    )
                    count++
                }
            }
        }
    }
}