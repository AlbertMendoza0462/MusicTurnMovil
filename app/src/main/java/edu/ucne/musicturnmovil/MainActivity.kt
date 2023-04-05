package edu.ucne.musicturnmovil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.musicturnmovil.ConsultaColaScreen
import edu.ucne.musicturnmovil.utils.Screen
import edu.ucne.musicturnmovil.ui.theme.MusicTurnMovilTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicTurnMovilTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold() {
                        it

                        NavHost(
                            navController = navController,
                            startDestination = Screen.ConsultaColaScreen.Route
                        ) {
                            composable(route = Screen.ConsultaColaScreen.Route) {
                                ConsultaColaScreen(navController = navController)
                            }
                            composable(route = Screen.ElegirCancionListaScreen.Route) {
                                ElegirCancionListaScreen(navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MusicTurnMovilTheme {
        Greeting("Android")
    }
}