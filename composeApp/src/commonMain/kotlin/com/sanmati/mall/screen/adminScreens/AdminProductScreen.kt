package com.sanmati.mall.screen.adminScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sanmati.mall.designUi.ProductTabRow
import com.sanmati.mall.designUi.TabItem
import com.sanmati.mall.navigation.adminNavigation.ProductNavItems
import com.sanmati.mall.screen.adminScreens.items.CategoriesScreen
import com.sanmati.mall.screen.adminScreens.items.CompanyScreen
import com.sanmati.mall.screen.adminScreens.items.ProductScreens
import com.sanmati.mall.screen.adminScreens.items.UnitsScreen
import com.sanmati.mall.theme.Primary
import com.sanmati.mall.theme.PrimaryDark
import com.sanmati.mall.theme.UnSelectedGrey

@Composable
fun AdminProductScreen(mainNavController: NavHostController,adminNavController: NavHostController) {

    val localNavController = rememberNavController()

    val tabItems = listOf(
        ProductNavItems.Products,
        ProductNavItems.Companies,
        ProductNavItems.Categories,
        ProductNavItems.Units,
    )

    val currentBackStack by localNavController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route ?: ProductNavItems.Products.route

    Scaffold() { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Tabs
            ProductTabRow(
                modifier = Modifier,
                tabItems = tabItems.map { TabItem(it.title) },
                containerColor = Color.Transparent,
                selectedTabIndex = tabItems.indexOfFirst { it.route == currentRoute },
                selectedTextStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    color = PrimaryDark,
                ),
                unSelectedTextStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = UnSelectedGrey,
                ),
                dividerColor = Primary,
                onClick = { index ->
                    localNavController.navigate(tabItems[index].route) {
                        launchSingleTop = true
                        popUpTo(ProductNavItems.Products.route) { saveState = true }
                    }
                }
            )

            Box(modifier = Modifier.fillMaxSize().padding(innerPadding))
            {
                NavHost(
                    navController = localNavController,
                    startDestination = ProductNavItems.Products.route,
                    modifier = Modifier.fillMaxSize()
                ) {
                    composable(ProductNavItems.Products.route) { ProductScreens() }
                    composable(ProductNavItems.Companies.route) { CompanyScreen() }
                    composable(ProductNavItems.Categories.route) { CategoriesScreen() }
                    composable(ProductNavItems.Units.route) { UnitsScreen(mainNavController) }

                }
            }
        }
    }
}
