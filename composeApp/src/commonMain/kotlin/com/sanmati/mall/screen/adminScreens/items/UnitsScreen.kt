package com.sanmati.mall.screen.adminScreens.items

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.FilterList
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sanmati.mall.commonUtils.SharedPreference
import com.sanmati.mall.designUi.CommonDialog
import com.sanmati.mall.designUi.CusBasicTextFieldSearch
import com.sanmati.mall.designUi.CustomButton
import com.sanmati.mall.model.UnitResponse
import com.sanmati.mall.navigation.adminNavigation.ProductNavItems
import com.sanmati.mall.theme.Primary
import com.sanmati.mall.theme.PrimaryDark
import com.sanmati.mall.viewModel.UnitsViewModel

@Composable
fun UnitsScreen(
    navController: NavController,
    viewModel: UnitsViewModel = remember { UnitsViewModel() }
) {
    var search by remember { mutableStateOf("") }
    val units by viewModel.units.collectAsState()
    val isLoading by viewModel.isLoadingUnits.collectAsState()
    val listState = rememberLazyListState()

    val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2p3dC1wcm92aWRlci1kb21haW4vIiwiYXVkIjoiand0LWF1ZGllbmNlIiwidXNlcklkIjoiYWRtLTAwMDEiLCJ1c2VybmFtZSI6ImFkbWluIiwiZXhwIjoxNzU4OTcwMDgyfQ.tDE071_Ip03tq_J5a5HoaJFdL2yCsXkfvcbQXCDVhjc"

    SharedPreference.putString("access_token", token)

    var previousIndex by remember { mutableStateOf(0) }
    var previousScrollOffset by remember { mutableStateOf(0) }
    var fabVisible by remember { mutableStateOf(true) }

    LaunchedEffect(listState.firstVisibleItemIndex, listState.firstVisibleItemScrollOffset) {
        val currentIndex = listState.firstVisibleItemIndex
        val currentOffset = listState.firstVisibleItemScrollOffset

        fabVisible = when {
            currentIndex > previousIndex -> false
            currentIndex < previousIndex -> true
            currentOffset > previousScrollOffset -> false
            currentOffset < previousScrollOffset -> true
            else -> fabVisible
        }

        previousIndex = currentIndex
        previousScrollOffset = currentOffset
    }

    LaunchedEffect(Unit) {
        viewModel.getUnitList()
    }

    Scaffold(
        floatingActionButton = {
            AnimatedVisibility(
                visible = fabVisible,
                modifier = Modifier.background(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(16.dp)
                )
            ) {
                FloatingActionButton(
                    onClick = { navController.navigate(ProductNavItems.AddUnit.route) },
                    containerColor = PrimaryDark,
                    elevation = FloatingActionButtonDefaults.elevation(0.dp)
                ) {
                    Icon(Icons.Rounded.Add, contentDescription = "Add Unit")
                }
            }
        }
    ) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
        ) {
            // Search Field
            CusBasicTextFieldSearch(
                modifier = Modifier.padding(horizontal = 8.dp),
                value = search,
                onValueChange = {
                    search = it
                    viewModel.getUnitList(search)
                },
                placeholder = "Unit Search"
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (isLoading) {
                CircularProgressIndicator(Modifier.align(Alignment.CenterHorizontally))
            } else {
                val filteredUnits = units.filter {
                    it.unitName.contains(search, ignoreCase = true) ||
                            it.unitCode.contains(search, ignoreCase = true)
                }

                if (filteredUnits.isEmpty()) {
                    Text(
                        "No units found",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                } else {
                    LazyColumn(
                        state = listState,
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        items(filteredUnits.size) { unit ->
                            UnitItem(
                                units[unit],
                                onUnitDeleted = {
                                    viewModel.getUnitList()
                                },
                                viewModel = viewModel
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun UnitItem(
    unit: UnitResponse,
    onUnitDeleted: () -> Unit,
    viewModel: UnitsViewModel
) {

    var expanded by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = unit.unitName,
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold),
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Code: ${unit.unitCode}",
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.Gray
                )
            }

            Box {
                Icon(
                    imageVector = Icons.Rounded.MoreVert,
                    contentDescription = "Menu",
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { expanded = true }
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    containerColor = Primary
                ) {
                    DropdownMenuItem(
                        text = { Text("Update") },
                        onClick = {
                            expanded = false
                            //onUpdateClick(unit)
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Rounded.Edit,
                                contentDescription = "update",
                                tint = Color.Black
                            )
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Delete") },
                        onClick = {
                            expanded = false
                            showDialog = true

                        },
                        leadingIcon = {
                            Icon(
                                Icons.Rounded.Delete,
                                contentDescription = "delete",
                                tint = Color.Black
                            )
                        }
                    )
                }
            }
        }
    }

    if (showDialog) {
        CommonDialog(
            title = "Delete Unit",
            message = "Are you sure you want to delete ${unit.unitName}?",
            confirmText = "Delete",
            dismissTextVisible = true,
            dismissText = "Cancel",
            onConfirm = {
                showDialog = false
                viewModel.deleteUnit(unit.unitId) {
                    // Callback after deletion
                    onUnitDeleted()
                }
            },
            onDismiss = { showDialog = false }
        )
    }
}

