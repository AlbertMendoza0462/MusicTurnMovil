package edu.ucne.musicturnmovil.utils

sealed class Screen(val Route: String, val MostrarNav: Boolean = true) {
    object ElegirCancionListaScreen: Screen("ElegirCancionListaScreen")
    object ConsultaColaScreen: Screen("ConsultaColaScreen")
}