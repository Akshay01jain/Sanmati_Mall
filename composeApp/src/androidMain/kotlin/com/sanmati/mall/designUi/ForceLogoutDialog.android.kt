@file:OptIn(ExperimentalMaterial3Api::class)

package com.sanmati.mall.designUi

import android.app.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun ForceLogoutDialog(onLogout: () -> Unit) {
    val context = LocalContext.current
    AlertDialog.Builder(context)
        .setTitle("Session Expired")
        .setMessage("Your session has expired. Please login again.")
        .setCancelable(false) // 🚫 cannot dismiss
        .setPositiveButton("Logout") { _, _ -> onLogout() }
        .show()
}