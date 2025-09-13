package com.sanmati.mall

import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.sanmati.mall.navigation.AppNavHost
import com.sanmati.mall.screen.adminScreens.AdminDashboardScreen
import com.sanmati.mall.screen.registrationScreens.LoginScreen
import com.sanmati.mall.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    AppTheme {
       val navController = rememberNavController()

        AppNavHost(navController)


    }
}