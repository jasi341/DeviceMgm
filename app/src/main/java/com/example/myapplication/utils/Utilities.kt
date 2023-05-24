package com.example.myapplication.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.myapplication.R

object Utilities {


    private const val backgroundstring = "#07122B"
    val background = Color(backgroundstring.toColorInt())

    private const val backgroundstring2 = "#0D1D41"
    val background2 = Color(backgroundstring2.toColorInt())

    private const val backgroundstring3 = "#3A4765"
    val background3 = Color(backgroundstring3.toColorInt())

    val font = FontFamily(Font(R.font.acumin_pro_medium))

}