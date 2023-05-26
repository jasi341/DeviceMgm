package com.example.myapplication.utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.myapplication.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Utilities {


    private const val backgroundstring = "#07122B"
    val background = Color(backgroundstring.toColorInt())

    private const val backgroundstring2 = "#0D1D41"
    val background2 = Color(backgroundstring2.toColorInt())

    private const val backgroundstring3 = "#3A4765"
    val background3 = Color(backgroundstring3.toColorInt())

    val font = FontFamily(Font(R.font.acumin_pro_medium))


    @RequiresApi(Build.VERSION_CODES.O)
    fun convertTimestampToDate(timestamp: String): String {
        val inputFormat = DateTimeFormatter.ISO_DATE_TIME
        val outputFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy")

        val dateTime = LocalDateTime.parse(timestamp, inputFormat)
        return dateTime.format(outputFormat)
    }
}