package com.sanmati.mall.designUi

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sanmati.mall.theme.OnSurface
import com.sanmati.mall.theme.OnSurfaceVariant
import com.sanmati.mall.theme.Primary
import com.sanmati.mall.theme.PrimaryDark

@Composable
fun CustomIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    enabled: Boolean = true,
    containerColor: Color = Primary,            // Beige background
    contentColor: Color = OnSurface,            // Icon color (dark gray)
    disabledContainerColor: Color = PrimaryDark.copy(alpha = 0.4f),
    disabledContentColor: Color = OnSurfaceVariant.copy(alpha = 0.4f),
    size: Dp = 48.dp,
    shape: Shape = CircleShape,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    Surface(
        modifier = modifier.size(size),
        shape = shape,
        color = if (enabled) containerColor else disabledContainerColor,
        onClick = onClick,
        enabled = enabled,
        interactionSource = interactionSource,
        tonalElevation = if (enabled) 2.dp else 0.dp,
        shadowElevation = if (enabled) 4.dp else 0.dp
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CompositionLocalProvider(
                LocalContentColor provides if (enabled) contentColor else disabledContentColor
            ) {
                icon()
            }
        }
    }
}

@Composable
fun CustomButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    containerColor: Color = PrimaryDark,          // Default beige
    contentColor: Color = OnSurface,          // Text/icons in dark gray
    disabledContainerColor: Color = PrimaryDark.copy(alpha = 0.5f),
    disabledContentColor: Color = OnSurfaceVariant.copy(alpha = 0.5f),
    shape: Shape = CircleShape,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(
        fontWeight = FontWeight.SemiBold
    ),
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(48.dp),
        enabled = enabled && !isLoading,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp,
            pressedElevation = 2.dp,
            disabledElevation = 0.dp
        )
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = contentColor,
                modifier = Modifier.size(20.dp),
                strokeWidth = 2.dp
            )
        } else {
            Text(
                text = text,
                style = textStyle,
                color = contentColor
            )
        }
    }
}
