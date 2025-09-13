package com.sanmati.mall.screen.adminScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sanmati.mall.designUi.ProductTabLayout
import com.sanmati.mall.designUi.TabItem
import com.sanmati.mall.navigation.adminNavigation.AdminNavItems
import com.sanmati.mall.navigation.adminNavigation.ProductNavItems
import com.sanmati.mall.navigation.adminNavigation.ProductsNavHost
import com.sanmati.mall.screen.adminScreens.items.ProductScreens
import com.sanmati.mall.theme.Primary
import com.sanmati.mall.theme.PrimaryDark

@Composable
fun AdminProductScreen(navController: NavHostController) {

    Scaffold { innerPadding ->
        Row(modifier = Modifier.fillMaxSize().background(color = Color.Red).verticalScroll(rememberScrollState()))
        {
            val productNavController = rememberNavController()

            // ProductTabLayout()
            ProductSideNav(productNavController)


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                ProductsNavHost(productNavController)
            }

        }
    }

}

@Composable
fun ProductSideNav(navController: NavHostController) {

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavigationRail(
        modifier = Modifier.fillMaxHeight() ,
        containerColor = Primary,
        contentColor = PrimaryDark
    ) {
        listOf(
            ProductNavItems.Product,
            ProductNavItems.Categories,  // Add new
            ProductNavItems.Units        // Add new
        ).forEach { item ->

            val isSelected = currentRoute == item.route

            NavigationRailItem(
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 5.dp),
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        launchSingleTop = true
                        popUpTo(navController.graph.startDestDisplayName) {
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon!!,
                        contentDescription = item.title
                    )
                },
                label = {
                        Text(item.title)
                },
                alwaysShowLabel = true,
                colors = NavigationRailItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }


}