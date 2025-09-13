package com.sanmati.mall.screen.adminScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sanmati.mall.designUi.CustomIconButton
import com.sanmati.mall.designUi.FilledTextField
import com.sanmati.mall.navigation.adminNavigation.AdminAppNavHost
import com.sanmati.mall.navigation.adminNavigation.AdminDashboardScreens
import com.sanmati.mall.navigation.adminNavigation.AdminNavItems
import com.sanmati.mall.theme.OnSurfaceVariant
import com.sanmati.mall.theme.Primary
import com.sanmati.mall.theme.PrimaryDark

@Composable
fun AdminDashboardScreen(navController: NavHostController) {

    val adminNavController = rememberNavController()
    var search by remember { mutableStateOf("") }

    Scaffold(bottomBar = {
        AdminSideNav(adminNavController)
    }) {innerPadding ->
        Row {
            Box() {
                Column {
//                    DashboardUI(navController, search, { search = it })

                    AdminAppNavHost(adminNavController)

                }
            }
        }


    }


}

@Composable
fun AdminSideNav(navController: NavHostController) {
    NavigationBar(
        tonalElevation = 12.dp,
        containerColor = Primary, contentColor = PrimaryDark
    ) {
        listOf(
            AdminNavItems.Home,
            AdminNavItems.Product,
            AdminNavItems.Parties,
            AdminNavItems.Sales,
            AdminNavItems.Purchase,
            //AdminNavItems.Settings
        ).forEach { item ->

            val isSelected = navController.currentDestination?.route == item.route

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        launchSingleTop = true
                        popUpTo(navController.graph.startDestDisplayName) {
                            saveState = true
                        }
                        restoreState = true
                    }
                }, icon = {
                    Icon(
                        imageVector = item.icon!!,
                        contentDescription = item.title,
                        tint = if (isSelected) {
                            MaterialTheme.colorScheme.onPrimaryContainer
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        }
                    )
                }, label = { Text(item.title) },
                alwaysShowLabel = false, // only selected item will show label
                colors = NavigationBarItemDefaults.colors(
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

@Composable
fun DashboardUI(
    navController: NavHostController, search: String,
    onSearchChange: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally)
    {

        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            FilledTextField(
                text = search,
                onValidate = onSearchChange,
                showLabel = false,
                placeholderText = "Search Anything.",
                singleLine = true,
                modifier = Modifier,
                keyBoardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = "search",
                        tint = OnSurfaceVariant
                    )
                }
            )

            Button(
                onClick = { /* handle click */ },
                modifier = Modifier,
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Sales"
                )
                Spacer(modifier = Modifier.width(8.dp)) // space between icon & text
                Text(text = "Login")
            }


        }

    }

}