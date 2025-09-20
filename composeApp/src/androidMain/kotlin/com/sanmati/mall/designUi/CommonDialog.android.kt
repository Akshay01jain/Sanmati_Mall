package com.sanmati.mall.designUi

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sanmati.mall.theme.Primary
import com.sanmati.mall.theme.PrimaryDark

@Composable
actual fun CommonDialog(
    title: String,
    message: String,
    confirmText: String,
    dismissTextVisible: Boolean,
    dismissText: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = { Text(message) },
        confirmButton = {
            Button(onClick = onConfirm, colors = ButtonDefaults.buttonColors(containerColor = PrimaryDark)) { Text(confirmText) }
        },

        dismissButton = {
            if(dismissTextVisible)
            {
                TextButton(onClick = onDismiss, colors = ButtonDefaults.buttonColors(contentColor = PrimaryDark)) { Text(dismissText) }
            }
        },
        containerColor = Primary,
    )
}

