package com.sanmati.mall.designUi

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import io.ktor.websocket.Frame.Text
import javax.swing.JOptionPane

@Composable
actual fun ForceLogoutDialog(onLogout: () -> Unit) {
    JOptionPane.showMessageDialog(
        null,
        "Your session has expired. Please login again.",
        "Session Expired",
        JOptionPane.WARNING_MESSAGE
    )
    onLogout()
}