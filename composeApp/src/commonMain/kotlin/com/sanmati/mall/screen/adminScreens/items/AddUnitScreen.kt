package com.sanmati.mall.screen.adminScreens.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sanmati.mall.designUi.CustomButton
import com.sanmati.mall.designUi.CustomOutlineButton
import com.sanmati.mall.designUi.FilledTextField
import com.sanmati.mall.viewModel.UnitsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUnitScreen(
    navController: NavHostController,
    viewModel: UnitsViewModel = remember { UnitsViewModel() }
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var name by remember { mutableStateOf("") }
    var code by remember { mutableStateOf("") }

    // Separate loading states for each button
    var isLoadingStay by remember { mutableStateOf(false) }
    var isLoadingGoBack by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Unit", style = MaterialTheme.typography.titleMedium) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Rounded.ArrowBackIosNew, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black
                )
            )
        },

        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Unit Name field
            FilledTextField(
                text = name,
                onValidate = { input ->
                    name = input.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
                },
                labelText = "Unit Name",
                placeholderText = "Enter unit name",
                singleLine = true,
                keyBoardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyBoardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Unit Code field
            FilledTextField(
                text = code,
                onValidate = { input -> code = input.uppercase().take(5) },
                labelText = "Unit Code",
                placeholderText = "e.g. PCS",
                singleLine = true,
                keyBoardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyBoardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Add & Stay (Outlined)
                CustomOutlineButton(
                    text = if (isLoadingStay) "" else "Add New",
                    onClick = {
                        if (name.isNotBlank() && code.length in 2..5) {
                            isLoadingStay = true
                            viewModel.addUnit(name, code, snackbarHostState) {
                                name = ""
                                code = ""
                                isLoadingStay = false
                            }
                        }
                    },
                    isLoading = isLoadingStay,
                    enabled = !isLoadingStay && name.isNotBlank() && code.length in 2..5,
                    modifier = Modifier.weight(1f)
                )

                // Add & Go Back (Filled)
                CustomButton(
                    text = if (isLoadingGoBack) "" else "Add Unit",
                    onClick = {
                        if (name.isNotBlank() && code.length in 2..5) {
                            isLoadingGoBack = true
                            viewModel.addUnit(name, code, snackbarHostState) {
                                isLoadingGoBack = false
                                navController.popBackStack()
                            }
                        }
                    },
                    isLoading = isLoadingGoBack,
                    enabled = !isLoadingGoBack && name.isNotBlank() && code.length in 2..5,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

