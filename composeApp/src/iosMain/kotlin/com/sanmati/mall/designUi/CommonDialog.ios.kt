package com.sanmati.mall.designUi

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

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
    Dialog(onCloseRequest = onDismiss) {
        Surface {
            Column(
                Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(title, style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(8.dp))
                Text(message)
                Spacer(Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (dismissTextVisible) {
                        TextButton(onClick = onDismiss) { Text(dismissText) }
                    }
                    Button(onClick = onConfirm) { Text(confirmText) }
                }
            }
        }
    }
}
