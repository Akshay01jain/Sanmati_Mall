package com.sanmati.mall.screen.adminScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sanmati.mall.designUi.ProductTabRow
import com.sanmati.mall.designUi.TabItem
import com.sanmati.mall.screen.adminScreens.items.CategoriesScreen
import com.sanmati.mall.screen.adminScreens.items.ProductScreens
import com.sanmati.mall.screen.adminScreens.items.UnitsScreen
import com.sanmati.mall.theme.Primary
import com.sanmati.mall.theme.PrimaryDark
import com.sanmati.mall.theme.UnSelectedGrey

@Composable
fun AdminProductScreen(navController: NavHostController) {

    var selectedTabIndex by remember { mutableStateOf(0) }

    val tabItems = listOf(
        TabItem(title = "Products"),
        TabItem(title = "Categories"),
        TabItem(title = "Units"),
    )

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Tabs at top
            ProductTabRow(
                modifier = Modifier,
                tabItems = tabItems,
                containerColor = Color.Transparent,
                selectedTabIndex = selectedTabIndex,
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
                onClick = { selectedTabIndex = it }
            )

            // Content area for selected screen
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                when (selectedTabIndex) {
                    0 -> ProductScreens()
                    1 -> CategoriesScreen()
                    2 -> UnitsScreen()
                }
            }
        }
    }
}

