package edu.ucne.musicturnmovil.data.remote.api_dao

import edu.ucne.musicturnmovil.data.remote.dto.ColaCancionDto
import retrofit2.http.*

interface CancionApi {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
        "User-Agent: MusicTurnMovil"
    )
    @GET("canciones")
    suspend fun getAll(): List<ColaCancionDto>

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
        "User-Agent: MusicTurnMovil"
    )
    @GET("canciones/{id}")
    suspend fun getById(@Path("id") id: String): ColaCancionDto

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
        "User-Agent: MusicTurnMovil"
    )
    @POST("canciones")
    suspend fun insert(@Body servicio: ColaCancionDto): ColaCancionDto

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
        "User-Agent: MusicTurnMovil"
    )
    @DELETE("canciones")
    suspend fun delete(@Path("id") id: String): ColaCancionDto

}