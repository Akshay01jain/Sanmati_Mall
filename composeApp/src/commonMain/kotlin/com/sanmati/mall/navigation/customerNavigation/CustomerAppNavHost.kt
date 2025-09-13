package com.sanmati.mall.navigation.customerNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun CustomerAppNavHost(
    navController: NavHostController,
    startDestination: String = CustomerNavItems.Home.route
)
{
    NavHost(navController = navController,startDestination = startDestination )
    {
        composable(CustomerNavItems.Home.route)
        {

        }
        composable(CustomerNavItems.Parties.route)
        {

        }
        composable(CustomerNavItems.Sales.route)
        {

        }
        composable(CustomerNavItems.Purchase.route)
        {

        }
        composable(CustomerNavItems.Settings.route)
        {

        }

    }
}