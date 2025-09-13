package com.sanmati.mall.navigation.adminNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sanmati.mall.screen.adminScreens.AdminHomeScreen
import com.sanmati.mall.screen.adminScreens.AdminPartiesScreen
import com.sanmati.mall.screen.adminScreens.AdminProductScreen
import com.sanmati.mall.screen.adminScreens.AdminPurchaseScreen
import com.sanmati.mall.screen.adminScreens.AdminSalesScreen
import com.sanmati.mall.screen.adminScreens.AdminSettingsScreen

@Composable
fun AdminAppNavHost(
    navController: NavHostController,
    startDestination: String = AdminNavItems.Home.route
)
{
    NavHost(navController = navController,startDestination = startDestination )
    {
        composable(AdminNavItems.Home.route)
        {
            AdminHomeScreen(navController)
        }
        composable(AdminNavItems.Product.route)
        {
            AdminProductScreen(navController)
        }
        composable(AdminNavItems.Parties.route)
        {
            AdminPartiesScreen(navController)
        }
        composable(AdminNavItems.Sales.route)
        {
            AdminSalesScreen(navController)
        }
        composable(AdminNavItems.Purchase.route)
        {
            AdminPurchaseScreen(navController)
        }
        composable(AdminNavItems.Settings.route)
        {
            AdminSettingsScreen(navController)
        }
    }
}