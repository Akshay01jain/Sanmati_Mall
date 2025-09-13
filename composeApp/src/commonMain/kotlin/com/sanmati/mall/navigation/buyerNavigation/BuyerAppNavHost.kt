package com.sanmati.mall.navigation.buyerNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BuyerAppNavHost(
    navController: NavHostController,
    startDestination: String = BuyerNavItems.Home.route
)
{
    NavHost(navController = navController,startDestination = startDestination )
    {
        composable(BuyerNavItems.Home.route)
        {
           // BuyerHomeScreen(navController)
        }
        composable(BuyerNavItems.Parties.route)
        {
            //BuyerPartiesScreen(navController)
        }
        composable(BuyerNavItems.Sales.route)
        {
            //BuyerSalesScreen(navController)
        }
        composable(BuyerNavItems.Purchase.route)
        {
            //BuyerPurchaseScreen(navController)
        }
        composable(BuyerNavItems.Settings.route)
        {
            //BuyerSettingsScreen(navController)

        }

    }
}