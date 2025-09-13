package com.sanmati.mall.navigation.adminNavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material.icons.rounded.Straight
import androidx.compose.material.icons.rounded.Straighten
import androidx.compose.material.icons.rounded.ViewAgenda
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sanmati.mall.screen.adminScreens.items.CategoriesScreen
import com.sanmati.mall.screen.adminScreens.items.ProductScreens


enum class ProductScreens {
    PRODUCTS,
    CATEGORIES,
    UNITS,

}

sealed class ProductNavItems(val route: String, val title : String  = "", val icon : ImageVector? = null) {

    object Product : ProductNavItems(ProductScreens.PRODUCTS.name, "Products", Icons.Rounded.Category)
    object Categories : ProductNavItems(ProductScreens.CATEGORIES.name, "Categories", Icons.Rounded.ViewAgenda)
    object Units : ProductNavItems(ProductScreens.UNITS.name, "Units", Icons.Rounded.Straighten)

}

@Composable
fun ProductsNavHost( navController: NavHostController,
                     startDestination: String = ProductScreens.PRODUCTS.name) {

    NavHost(navController = navController, startDestination = startDestination)
    {
        composable(ProductScreens.PRODUCTS.name)
        {
            ProductScreens(navController)
        }
        composable(ProductScreens.CATEGORIES.name)
        {
            CategoriesScreen(navController)
        }
        composable(ProductScreens.UNITS.name)
        {

        }
    }

}
