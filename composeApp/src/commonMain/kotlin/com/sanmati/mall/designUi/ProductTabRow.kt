package com.sanmati.mall.designUi

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
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

    TabRow(
        modifier = modifier,
        divider = { HorizontalDivider(color = dividerColor) },
        containerColor = containerColor,
        selectedTabIndex = selectedTabIndex,
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
        },
        tabs = {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    selected = (index == selectedTabIndex),
                    onClick = {
                        onClick(index)
                    },
                    text = {
                        Text(
                            text = item.title,
                            style = if (index == selectedTabIndex) selectedTextStyle else unSelectedTextStyle,
                            modifier = Modifier.padding(vertical = 6.dp)
                        )
                    },
                    icon = null,
                    selectedContentColor = selectedContentColor,
                    unselectedContentColor = unselectedContentColor
                )
            }
        }
    )


}