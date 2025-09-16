package com.sanmati.mall.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.sanmati.mall.commonUtils.SharedPreference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navController: NavHostController)
{
    val scope = CoroutineScope(Dispatchers.Main)

    scope.launch {

        if(SharedPreference.getString("access_token").isNullOrBlank())
        {
            navController.navigate("login")
        }
        else
        {
            when(SharedPreference.getString("user_type"))
            {
                "admin" -> {
                    navController.navigate("admin") {
                        popUpTo("splash") { inclusive = true } // remove splash from back stack
                        launchSingleTop = true
                    }
                }
                /*"buyer" -> {
                    navController.navigate("buyer")
                }
                "customer" -> {
                    navController.navigate("customer")
                }*/
            }
        }

        delay(3000)
    }

    SplashScreenUI()
}

@Composable
private fun SplashScreenUI()
{
    Scaffold(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally)
        {
            Icon(Icons.Rounded.Home, contentDescription = "Splash")
        }

    }
}