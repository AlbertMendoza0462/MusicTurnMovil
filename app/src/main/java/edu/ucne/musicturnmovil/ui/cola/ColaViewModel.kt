package edu.ucne.musicturnmovil.ui.colaCanciones

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.musicturnmovil.data.remote.api_repository.CancionApiRepository
import edu.ucne.musicturnmovil.data.remote.api_repository.ColaCancionApiRepository
import edu.ucne.musicturnmovil.data.remote.dto.ColaCancionDto
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ColaViewModel @Inject constructor(
    private val apiColaCanciones: ColaCancionApiRepository,
    private val apiCanciones: CancionApiRepository
) : ViewModel() {
    var colaCanciones by mutableStateOf(emptyList<ColaCancionDto>())
    var canciones by mutableStateOf(emptyList<ColaCancionDto>())
    var dialogoAbierto by  mutableStateOf(false)
    var cancionSeleccionada by mutableStateOf(ColaCancionDto(0, ""))

    fun cargarColaCanciones() {
        viewModelScope.launch {
            colaCanciones = apiColaCanciones.getColaCanciones()
        }
    }

    fun cargarCanciones() {
        viewModelScope.launch {
            canciones = apiCanciones.getCanciones()
        }
    }

    fun abrirDialogo(cancion: ColaCancionDto) {
        viewModelScope.launch {
            cancionSeleccionada = cancion
            dialogoAbierto = true
        }
    }

    fun solicitar() {
        viewModelScope.launch {
            apiColaCanciones.insertColaCancion(cancionSeleccionada)
            dialogoAbierto = false
        }
    }

//    fun save(
//        localContext: Context,
//        navController: NavController,
//        navegacionViewModel: NavegacionViewModel,
//        salirAlTerminar: Boolean = true
//    ) {
//        enableSubmit = false
//
//        if (colaCancionId == 0) {
//            usuarioCreacionId = navegacionViewModel.cliente.clienteId
//            fechaCreacion = LocalDateTime.now().toString()
//        }
//
//        fechaModificacion = LocalDateTime.now().toString()
//        usuarioModificacionId = navegacionViewModel.cliente.clienteId
//
//
//        viewModelScope.launch {
//            if (validacion()) {
//                val intentoGuardar = if (colaCancionId == 0) api.insertColaCancion(
//                    ColaCancionDto(
//                        colaCancionId = colaCancionId,
//                        nombre = nombre,
//                        imagen = imagen,
//                        usuarioCreacionId = usuarioCreacionId,
//                        usuarioModificacionId = usuarioModificacionId,
//                        fechaCreacion = fechaCreacion,
//                        fechaModificacion = fechaModificacion,
//                        status = status
//                    )
//
//                ) else api.updateColaCancion(
//                    colaCancionId.toString(),
//                    ColaCancionDto(
//                        colaCancionId = colaCancionId,
//                        nombre = nombre,
//                        imagen = imagen,
//                        usuarioCreacionId = usuarioCreacionId,
//                        usuarioModificacionId = usuarioModificacionId,
//                        fechaCreacion = fechaCreacion,
//                        fechaModificacion = fechaModificacion,
//                        status = status
//                    )
//                )
//                if (
//                    intentoGuardar.colaCancionId > 0
//                ) {
//                    var colaCancionDb = ColaCancion(
//                        colaCancionId = intentoGuardar.colaCancionId,
//                        nombre = intentoGuardar.nombre,
//                        imagen = intentoGuardar.imagen,
//                        usuarioCreacionId = intentoGuardar.usuarioCreacionId,
//                        usuarioModificacionId = intentoGuardar.usuarioModificacionId,
//                        status = intentoGuardar.status
//                    )
//                    colaCancionRepository.insert(colaCancionDb)
//
//                    if (true) {
//                        navegacionViewModel.sincronizarColaCancionesApi()
//                        if (salirAlTerminar)
//                            navController.navigate(Screen.ConsultaColaCancionesScreen.Route)
//                    } else {
//                        Toast.makeText(localContext, "No se pudo guardar!", Toast.LENGTH_SHORT)
//                            .show()
//                    }
//                } else {
//                    Toast.makeText(localContext, msg, Toast.LENGTH_SHORT).show()
//                }
//            } else {
//                Toast.makeText(localContext, msg, Toast.LENGTH_SHORT).show()
//            }
//            enableSubmit = true
//        }
//    }

    fun searchById(id: String?) {
        viewModelScope.launch {
            apiColaCanciones.getColaCancion(
                id ?: ""
            ) // se debe igualar a las variables de los campos en el registro
        }
    }
}