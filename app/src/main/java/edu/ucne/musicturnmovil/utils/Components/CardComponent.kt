package edu.ucne.musicturnmovil.utils.Components

import android.content.res.Resources.Theme
import android.graphics.ColorSpace.Rgb
import androidx.compose.animation.VectorConverter
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpace
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CardComponent(
    titulo: String,
    cuerpo: String? = null,
    subtitulo: String? = null,
    btn1Nombre: String? = null,
    btn1OnClick: () -> Unit? = {},
    onClick: () -> Unit? = {},
    clickeable: Boolean = false,
    primera: Boolean = false
) {
//    var bgColor = MaterialTheme.colorScheme.surfaceVariant
//    if (selected) bgColor = MaterialTheme.colorScheme.primaryContainer
//    if (status == 2) bgColor = MaterialTheme.colorScheme.tertiaryContainer
//    if (status == 3) bgColor = MaterialTheme.colorScheme.errorContainer

    var modifier = Modifier.fillMaxWidth()
    if (clickeable) modifier = modifier.clickable { onClick() }

    Card(
        border = BorderStroke(1.dp, Color(0, 0, 0, 12)),
        modifier = modifier
    ) {
        var modifier2 = Modifier.background(color = if (primera) MaterialTheme.colors.secondary  else Color.White)
        Column(modifier = modifier2) {

        CardHeaderComponent(titulo, subtitulo, primera)

        if (cuerpo != null)
            CardBodyComponent(text = cuerpo)

        if (btn1Nombre != null)
            CardFooterComponent(
                btn1Nombre = btn1Nombre,
                btn1OnClick = btn1OnClick,
            )
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun CardHeaderComponent(titulo: String, subtitulo: String?, primera: Boolean) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = titulo,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colors.onSecondary
        )
        if (subtitulo != null && !subtitulo.isBlank())
            Text(
                text = subtitulo ?: "",
                fontSize = 14.sp
            )
    }
}

@Composable
fun CardBodyComponent(text: String) {
    Column(
        modifier = Modifier
            .padding(0.dp, 0.dp, 16.dp, 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.onSecondary
        )
    }
}

@Composable
fun CardFooterComponent(
    btn1Nombre: String?,
    btn1OnClick: () -> Unit?
) {
    Row(modifier = Modifier.padding(16.dp)) {
        if (btn1Nombre != null) {
            Button(onClick = { btn1OnClick() }) {
                Text(text = btn1Nombre)
            }
        }
    }
}