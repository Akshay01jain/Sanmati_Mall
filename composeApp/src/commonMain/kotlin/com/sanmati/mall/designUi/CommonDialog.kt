package com.sanmati.mall.designUi

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.ktor.websocket.Frame.Text

@Composable
expect fun CommonDialog(
    title: String,
    message: String,
    confirmText: String = "OK",
    dismissTextVisible: Boolean = true,
    dismissText: String = "Cancel",
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
)
