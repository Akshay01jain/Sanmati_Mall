package com.sanmati.mall.screen.adminScreens.items

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sanmati.mall.commonUtils.SharedPreference
import com.sanmati.mall.designUi.CommonDialog
import com.sanmati.mall.designUi.CusBasicTextFieldSearch
import com.sanmati.mall.navigation.adminNavigation.ProductNavItems
import com.sanmati.mall.theme.Primary
import com.sanmati.mall.theme.PrimaryDark
import com.sanmati.mall.model.CategoryResponse
import com.sanmati.mall.viewModel.CategoryViewModel

@Composable
fun CategoriesScreen(
    navController: NavController,
    viewModel: CategoryViewModel = remember { CategoryViewModel() }
) {
    var search by remember { mutableStateOf("") }
    val categories by viewModel.categories.collectAsState()
    val isLoading by viewModel.isLoadingCategories.collectAsState()
    val listState = rememberLazyListState()

    val token = SharedPreference.getString("access_token").orEmpty()

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
        viewModel.getCategoryList()
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
                    onClick = { navController.navigate(ProductNavItems.AddCategory.route) },
                    containerColor = PrimaryDark,
                    elevation = FloatingActionButtonDefaults.elevation(0.dp)
                ) {
                    Icon(Icons.Rounded.Add, contentDescription = "Add Category")
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
                    viewModel.getCategoryList(search)
                },
                placeholder = "Category Search"
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (isLoading) {
                CircularProgressIndicator(Modifier.align(Alignment.CenterHorizontally))
            } else {
                val filteredCategories = categories.filter {
                    it.name.contains(search, ignoreCase = true)
                }

                if (filteredCategories.isEmpty()) {
                    Text(
                        "No categories found",
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
                        items(filteredCategories.size) { index ->
                            CategoryItem(
                                category = filteredCategories[index],
                                onCategoryDeleted = {
                                    viewModel.getCategoryList()
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
fun CategoryItem(
    category: CategoryResponse,
    onCategoryDeleted: () -> Unit,
    viewModel: CategoryViewModel
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
                    text = category.name,
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold),
                    color = Color.Black
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
                    /*DropdownMenuItem(
                        text = { Text("Update") },
                        onClick = {
                            expanded = false
                            // TODO: Navigate to update category screen
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Rounded.Edit,
                                contentDescription = "update",
                                tint = Color.Black
                            )
                        }
                    )*/
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
            title = "Delete Category",
            message = "Are you sure you want to delete ${category.name}?",
            confirmText = "Delete",
            dismissTextVisible = true,
            dismissText = "Cancel",
            onConfirm = {
                showDialog = false
                viewModel.deleteCategory(category.categoryId) {
                    onCategoryDeleted()
                }
            },
            onDismiss = { showDialog = false }
        )
    }
}
