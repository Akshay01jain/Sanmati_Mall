package com.sanmati.mall.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import sanmatimall.composeapp.generated.resources.Res
import sanmatimall.composeapp.generated.resources.lato_bold
import sanmatimall.composeapp.generated.resources.lato_light
import sanmatimall.composeapp.generated.resources.lato_regular
import sanmatimall.composeapp.generated.resources.lato_semi_bold


val LatoFontFamily @Composable get() = FontFamily(

    Font(resource = Res.font.lato_regular, weight = FontWeight.Normal),
    Font(resource = Res.font.lato_light, weight = FontWeight.Light),
    Font(resource = Res.font.lato_semi_bold, weight = FontWeight.SemiBold),
    Font(resource = Res.font.lato_bold, weight = FontWeight.Bold)

)


val Typography: Typography @Composable get() = Typography(
    bodyLarge = TextStyle(
        fontFamily = LatoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = LatoFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    bodySmall = TextStyle(
        fontFamily = LatoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    titleLarge = TextStyle(
        fontFamily = LatoFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),
    titleMedium = TextStyle(
        fontFamily = LatoFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    titleSmall = TextStyle(
        fontFamily = LatoFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 22.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = LatoFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp
    )
)

