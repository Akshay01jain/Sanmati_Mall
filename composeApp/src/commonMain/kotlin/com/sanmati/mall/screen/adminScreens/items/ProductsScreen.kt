package com.sanmati.mall.screen.adminScreens.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.sanmati.mall.designUi.FilledTextField
import com.sanmati.mall.theme.OnSurfaceVariant
import com.sanmati.mall.theme.Primary
import com.sanmati.mall.theme.PrimaryDark

@Composable
fun ProductScreens() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: Add product */ },
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add Product"
                )
            }
        }
    ) { innerPadding ->
        ProductScreenUI(Modifier.padding(innerPadding))
    }
}

@Composable
fun ProductScreenUI(modifier: Modifier = Modifier) {
    var search by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        FilledTextField(
            modifier = Modifier.fillMaxWidth(),
            text = search,
            onValidate = { search = it },
            placeholderText = "Product Search",
            singleLine = true,
            keyBoardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            leadingIcon = {
                Icon(
                    Icons.Rounded.Search,
                    contentDescription = "Search",
                    tint = OnSurfaceVariant
                )
            },
            trailingIcon = {
                if (search.isNotEmpty()) {
                    Icon(
                        Icons.Rounded.Clear,
                        contentDescription = "Clear",
                        tint = PrimaryDark,
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .clickable { search = "" }
                    )
                }
            }
        )
    }
}

