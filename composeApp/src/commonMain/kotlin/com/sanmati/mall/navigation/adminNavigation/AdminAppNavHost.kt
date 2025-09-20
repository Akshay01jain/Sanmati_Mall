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
    mainNavController: NavHostController,
    adminNavController: NavHostController,
    startDestination: String = AdminNavItems.Home.route
)
{
    NavHost(navController = adminNavController,startDestination = startDestination )
    {
        composable(AdminNavItems.Home.route)
        {
            AdminHomeScreen(adminNavController)
        }
        composable(AdminNavItems.Product.route)
        {
            AdminProductScreen(mainNavController, adminNavController)
        }
        composable(AdminNavItems.Parties.route)
        {
            AdminPartiesScreen(adminNavController)
        }
        composable(AdminNavItems.Sales.route)
        {
            AdminSalesScreen(adminNavController)
        }
        composable(AdminNavItems.Purchase.route)
        {
            AdminPurchaseScreen(adminNavController)
        }
        composable(AdminNavItems.Settings.route)
        {
            AdminSettingsScreen(adminNavController)
        }
    }
}