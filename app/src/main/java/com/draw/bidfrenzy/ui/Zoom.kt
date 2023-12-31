package com.draw.bidfrenzy.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun Zoom(
    content: @Composable () -> Unit
) {

    var scale by remember {
        mutableStateOf(1f)
    }

    var angle by remember {
        mutableStateOf(0f)
    }

    var offset by remember {
        mutableStateOf(Offset.Zero)
    }

    val state = rememberTransformableState { scaleChange, offsetChange, rotationChange ->
        scale *= scaleChange
        angle += rotationChange
        offset += offsetChange
    }

    Box(
        modifier = Modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                rotationZ = angle
                translationX = offset.x
                translationY = offset.y
            }
            .transformable(state),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}