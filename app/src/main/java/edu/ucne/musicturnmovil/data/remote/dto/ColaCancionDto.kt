package edu.ucne.musicturnmovil.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ColaCancionDto(
    val cancionId: Int,
    val nombre: String
)
