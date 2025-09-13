package com.sanmati.mall.navigation.adminNavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material.icons.rounded.Groups
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

enum class AdminDashboardScreens {
    HOME,
    PRODUCTS,
    PARTIES,
    SALES,
    PURCHASE,
    SETTINGS,

}

sealed class AdminNavItems(val route: String, val title : String  = "", val icon : ImageVector? = null) {

    object Home : AdminNavItems(AdminDashboardScreens.HOME.name, "Home", Icons.Rounded.Home)
    object Product : AdminNavItems(AdminDashboardScreens.PRODUCTS.name, "Products", Icons.Rounded.Category)
    object Parties : AdminNavItems(AdminDashboardScreens.PARTIES.name, "Parties", Icons.Rounded.Groups)
    object Sales : AdminNavItems(AdminDashboardScreens.SALES.name, "Sales", Icons.Rounded.BarChart)
    object Purchase : AdminNavItems(AdminDashboardScreens.PURCHASE.name, "Purchase", Icons.Rounded.ShoppingCart)
    object Settings : AdminNavItems(AdminDashboardScreens.SETTINGS.name, "Settings", Icons.Rounded.Settings)

}