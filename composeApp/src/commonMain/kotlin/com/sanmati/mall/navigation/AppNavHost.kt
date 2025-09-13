package com.sanmati.mall.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.sanmati.mall.commonUtils.SharedPreference
import com.sanmati.mall.commonUtils.SharedPreferenceKeys
import com.sanmati.mall.navigation.adminNavigation.AdminAppNavHost
import com.sanmati.mall.navigation.buyerNavigation.BuyerAppNavHost
import com.sanmati.mall.navigation.customerNavigation.CustomerAppNavHost
import com.sanmati.mall.screen.registrationScreens.LoginScreen
import com.sanmati.mall.screen.SplashScreen
import com.sanmati.mall.screen.adminScreens.AdminDashboardScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = NavItems.Splash.route
) {
    NavHost(navController = navController, startDestination = startDestination)
    {
        composable(NavItems.Splash.route)
        {
            SplashScreen(navController)
        }
        navigation(
            route = NavItems.Registration.route,
            startDestination = NavItems.Registration.Login.route
        )
        {
            composable(NavItems.Registration.Login.route)
            {
                LoginScreen(navController)
            }

        }

        when (SharedPreference.getString("user_type")) {
            "admin" -> {

                composable(NavItems.UserType.Admin.route)
                {
                    AdminDashboardScreen(navController)
                }
            }

            "buyer" -> {
                composable(NavItems.UserType.Buyer.route)
                {
                    BuyerAppNavHost(navController)
                }
            }

            "customer" -> {
                composable(NavItems.UserType.Customer.route)
                {
                    CustomerAppNavHost(navController)
                }
            }
        }

    }
}