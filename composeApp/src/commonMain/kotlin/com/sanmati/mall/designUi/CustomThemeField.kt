package com.sanmati.mall.designUi

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sanmati.mall.theme.Primary
import com.sanmati.mall.theme.PrimaryDark

data class ErrorStyle(
    val errorTextStyle: TextStyle = TextStyle(
        color = Color.Red
    ),
    val errorStartPadding: Dp = 12.dp,
    val errorIconRes: ImageVector = Icons.Rounded.Info,
    val errorIconSize: Dp = 24.dp,
    val errorLabelColor: Color = Color.Red,
    val errorBorderColor: Color = Color.Red,
    val errorContainerColor: Color = Color(0xFFF2F2F2),
    val errorCursorColor: Color = Color.Red
)

data class BackgroundStyle(
    val focusedBackgroundColor: Color = Color(0xFFF2F2F2),
    val unfocusedBackgroundColor: Color = Color(0xFFF2F2F2),
    val disabledBackgroundColor: Color = Color.LightGray
)

data class BorderStyle(
    val focusedBorderColor: Color = PrimaryDark,
    val unfocusedBorderColor: Color = Primary,
    val disabledBorderColor: Color = Color.LightGray
)

data class CursorStyle(
    val focusedCursorColor: Color = Color.Black
)