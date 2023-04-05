package edu.ucne.musicturnmovil.data.remote.api_dao

import edu.ucne.musicturnmovil.data.remote.dto.ColaCancionDto
import retrofit2.http.*

interface ColaCancionApi {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
        "User-Agent: MusicTurnMovil"
    )
    @GET("canciones/cola")
    suspend fun getAll(): List<ColaCancionDto>

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
        "User-Agent: MusicTurnMovil"
    )
    @GET("canciones/cola/{id}")
    suspend fun getById(@Path("id") id: String): ColaCancionDto

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
        "User-Agent: MusicTurnMovil"
    )
    @POST("canciones/cola")
    suspend fun insert(@Body servicio: ColaCancionDto): ColaCancionDto

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
        "User-Agent: MusicTurnMovil"
    )
    @DELETE("canciones/cola")
    suspend fun delete(@Path("id") id: String): ColaCancionDto

}