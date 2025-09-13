package com.sanmati.mall.designUi

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanmati.mall.theme.PrimaryDark
import com.sanmati.mall.theme.UnSelectedGrey
import com.sanmati.mall.theme.UnSelectedText

@Composable
fun OutLineTabLayout(
    modifier: Modifier,
    tabItems: List<TabItem>,
    selectedTabIndex: Int,
    containerColor: Color = Color.White,
    selectedContentColor: Color = Color.Transparent,
    unselectedContentColor: Color = UnSelectedGrey,
    selectedBorderColor: Color = PrimaryDark,
    unselectedBorderColor: Color = Color.Transparent,
    tabShape: Shape = RoundedCornerShape(10.dp),
    selectedTextStyle: TextStyle,
    unSelectedTextStyle: TextStyle,
    margin: PaddingValues = PaddingValues(vertical = 10.dp, horizontal = 4.dp),
    padding: PaddingValues = PaddingValues(vertical = 8.dp, horizontal = 0.dp),
    onClick: (selectedIndex: Int) -> Unit
) {
    Surface(
        modifier = modifier,
        color = containerColor
    )
    {
        TabRow(
            modifier = Modifier
                .fillMaxWidth(),
            containerColor = containerColor,
            divider = {},
            selectedTabIndex = selectedTabIndex,
            indicator = { tabPositions ->
                if (selectedTabIndex < tabPositions.size) {
                    SecondaryIndicator(color = Color.Transparent)
                }
            }
        )
        {
            tabItems.forEachIndexed { index, item ->

                Box(
                    modifier = Modifier
                        .padding(margin)
                        .background(
                            color = if (selectedTabIndex == index) {
                                selectedContentColor
                            } else {
                                unselectedContentColor
                            },
                            shape = tabShape
                        )
                        .border(
                            BorderStroke(
                                color = if (selectedTabIndex == index) {
                                    selectedBorderColor
                                } else {
                                    unselectedBorderColor
                                },
                                width = 1.dp
                            ),
                            shape = tabShape
                        )
                        .clip(shape = tabShape)
                        .noRippleEffect {
                            onClick(index)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item.title,
                        style = if (index == selectedTabIndex) selectedTextStyle else unSelectedTextStyle,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(padding)
                    )
                }

            }

        }
    }

}

fun Modifier.noRippleEffect(
    onClick: () -> Unit
) = composed {
    this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        onClick = onClick
    )
}

data class TabItem(val title: String)

@Composable
fun ProductTabLayout() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabItems = listOf(
        TabItem(title = "PRODUCTS"),
        TabItem(title = "CATEGORIES"),
        TabItem(title = "UNITS")
    )

    OutLineTabLayout(
        modifier = Modifier,
        tabItems = tabItems,
        selectedTabIndex = selectedTabIndex,
        selectedTextStyle = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight(500),
            color = PrimaryDark,
            textAlign = TextAlign.Center,
        ),
        unSelectedTextStyle = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight(400),
            color = UnSelectedText,
            textAlign = TextAlign.Center,
        ),
        onClick = {
            selectedTabIndex = it
        })
}
