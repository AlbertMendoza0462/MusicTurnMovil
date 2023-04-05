package edu.ucne.musicturnmovil.data.remote.api_repository

import edu.ucne.musicturnmovil.data.remote.api_dao.CancionApi
import edu.ucne.musicturnmovil.data.remote.dto.ColaCancionDto
import javax.inject.Inject

class CancionApiRepository @Inject constructor(
    private val api: CancionApi
) {
    suspend fun getCanciones(): List<ColaCancionDto>{
        try {
            val api = api.getAll();
            return api
        }catch (e: Exception){
            println(e)
            return emptyList()
        }
    }

    suspend fun getCancion(id:String?): ColaCancionDto? {
        try {
            return this.api.getById(id ?: "0")
        } catch (e: Exception) {
            println(e)
            return null
        }
    }


    suspend fun insertCancion(cancion: ColaCancionDto): ColaCancionDto {
        try {
            return this.api.insert(cancion)
        } catch (e: Exception) {
            println(e)
            return cancion
        }
    }

    suspend fun deleteCancion(id: String) : Boolean {
        try {
            val api = api.delete(id)
            return true // debe verificar si se elimino
        } catch (e: Exception) {
            println(e)
            return false
        }
    }
}