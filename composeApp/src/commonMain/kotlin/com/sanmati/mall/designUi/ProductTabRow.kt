package com.sanmati.mall.designUi

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sanmati.mall.theme.PrimaryDark
import com.sanmati.mall.theme.UnSelectedGrey

@Composable
fun ProductTabRow(
    modifier: Modifier = Modifier,
    tabItems: List<TabItem>,
    selectedTabIndex: Int,
    selectedTextStyle: TextStyle,
    unSelectedTextStyle: TextStyle,
    containerColor: Color = Color.White,
    selectedContentColor: Color = PrimaryDark,
    unselectedContentColor: Color = UnSelectedGrey,
    dividerColor: Color = DividerDefaults.color,
    indicatorHeight: Dp = 4.dp,
    indicatorColor: Color = PrimaryDark,
    onClick: (selectedIndex: Int) -> Unit
) {
    ScrollableTabRow(
        modifier = modifier,
        selectedTabIndex = selectedTabIndex,
        edgePadding = 0.dp, // removes extra spacing at start & end
        containerColor = containerColor,
        divider = { HorizontalDivider(color = dividerColor) },
        indicator = { tabPositions ->
            if (selectedTabIndex < tabPositions.size) {
                SecondaryIndicator(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabIndex])
                        .clip(CircleShape),
                    height = indicatorHeight,
                    color = indicatorColor
                )
            }
        }
    ) {
        tabItems.forEachIndexed { index, item ->
            Tab(
                selected = (index == selectedTabIndex),
                onClick = { onClick(index) },
                text = {
                    Text(
                        text = item.title,
                        style = if (index == selectedTabIndex) selectedTextStyle else unSelectedTextStyle,
                        modifier = Modifier.padding(vertical = 6.dp, horizontal = 12.dp)
                    )
                },
                selectedContentColor = selectedContentColor,
                unselectedContentColor = unselectedContentColor
            )
        }
    }
}
