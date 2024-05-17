package com.barisaslankan.freedivingbreathing.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.unit.Dp

fun iconMaker(
    path : String,
    viewPortSize : Float,
    iconSize : Dp
): ImageVector {
    return ImageVector.Builder(
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = viewPortSize,
        viewportHeight = viewPortSize
    ).apply {
        addPath(
            pathData = PathParser().parsePathString(path).toNodes(),
            fill = SolidColor(Color.White)
        )
    }.build()
}