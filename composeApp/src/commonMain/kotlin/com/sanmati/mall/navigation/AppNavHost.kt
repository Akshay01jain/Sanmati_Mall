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
import com.sanmati.mall.screen.adminScreens.items.AddCategoryScreen
import com.sanmati.mall.screen.adminScreens.items.AddUnitScreen

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

                    navigation(
                        route = NavItems.UserType.Admin.route,
                        startDestination = NavItems.UserType.Admin.Dashboard.route
                    )
                    {
                        composable(NavItems.UserType.Admin.Dashboard.route)
                        {
                            AdminDashboardScreen(navController)
                        }
                        composable(NavItems.UserType.Admin.Search.route)
                        {

                        }
                        composable(NavItems.UserType.Admin.AddProduct.route)
                        {

                        }
                        composable(NavItems.UserType.Admin.AddCategory.route)
                        {
                            AddCategoryScreen(navController)
                        }
                        composable(NavItems.UserType.Admin.AddUnit.route)
                        {
                            AddUnitScreen(navController)
                        }
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