package com.sanmati.mall.screen.adminScreens.items


// Shared code (Kotlin Multiplatform common module)
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.Alignment
import com.sanmati.mall.utils.DeviceConfiguration

@Composable
fun AddItemScreen(
    onSave: (AddItemData) -> Unit
) {
    val state = remember { mutableStateOf(AddItemData()) }
    // Fetch screen info (replace with your WindowSizeClass integration)
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (deviceConfiguration) {
            DeviceConfiguration.MOBILE_PORTRAIT -> AddItemMobilePortrait(state.value) {
                state.value = it
            }

            DeviceConfiguration.DESKTOP -> AddItemDesktop(state.value) {
                state.value = it
            }
            // Add other device types if needed
            else -> AddItemMobilePortrait(state.value) {
                state.value = it
            }
        }
    }

    // A bottom save button
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.End
    ) {
        Button(onClick = { onSave(state.value) }) {
            Text("Save")
        }
    }
}

// Data model representing fields to fill
data class AddItemData(
    var itemName: String = "",
    var hsnCode: String = "",
    var unit: String = "",
    var category: String = "",
    var itemCode: String = "",
    var description: String = "",
    var attributes: List<Pair<String, String>> = listOf(),
    var salePrice: String = "",
    var salePriceGstIncluded: Boolean = true,
    var saleDiscountPercent: String = "",
    var wholesalePrice: String = "",
    var wholesalePriceGstIncluded: Boolean = false,
    var minWholesaleQty: String = "",
    var purchasePrice: String = "",
    var purchasePriceGstIncluded: Boolean = true,
    var gstTaxPercentage: String = ""
)

@Composable
fun AddItemMobilePortrait(data: AddItemData, onDataChanged: (AddItemData) -> Unit) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TextField(
            value = data.itemName,
            onValueChange = { onDataChanged(data.copy(itemName = it)) },
            label = { Text("Item Name *") },
            singleLine = true
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TextField(
                value = data.hsnCode,
                onValueChange = { onDataChanged(data.copy(hsnCode = it)) },
                label = { Text("Item HSN") },
                modifier = Modifier.weight(1f),
                singleLine = true
            )
            Button(onClick = { /* Implement unit select dialog */ }) {
                Text("Select Unit")
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            DropdownField(
                label = "Category",
                options = listOf("Clothing", "Electronics", "Furniture"),
                selectedOption = data.category,
                onSelect = { onDataChanged(data.copy(category = it)) },
                modifier = Modifier.weight(1f)
            )
            TextField(
                value = data.itemCode,
                onValueChange = { onDataChanged(data.copy(itemCode = it)) },
                label = { Text("Item Code") },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
            Button(onClick = { /* Assign code automatically */ }) {
                Text("Assign Code")
            }
        }
        TextField(
            value = data.description,
            onValueChange = { onDataChanged(data.copy(description = it)) },
            label = { Text("Description") }
        )
        // Attributes Section (Simplified)
        AttributeSection(attributes = data.attributes, onAttributesChange = {
            onDataChanged(data.copy(attributes = it))
        })

        Divider()

        // Pricing Section
        Text("Pricing", style = MaterialTheme.typography.titleMedium)
        PriceSection(data = data, onDataChanged = onDataChanged)
    }
}

@Composable
fun AddItemDesktop(data: AddItemData, onDataChanged: (AddItemData) -> Unit) {
    // Desktop layout can be a two-column grid for example

    Row(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TextField(
                value = data.itemName,
                onValueChange = { onDataChanged(data.copy(itemName = it)) },
                label = { Text("Item Name *") },
                singleLine = true
            )
            TextField(
                value = data.hsnCode,
                onValueChange = { onDataChanged(data.copy(hsnCode = it)) },
                label = { Text("Item HSN") },
                singleLine = true
            )
            Button(onClick = { /* Select Unit */ }) {
                Text("Select Unit")
            }
            DropdownField(
                label = "Category",
                options = listOf("Clothing", "Electronics", "Furniture"),
                selectedOption = data.category,
                onSelect = { onDataChanged(data.copy(category = it)) })
            TextField(
                value = data.itemCode,
                onValueChange = { onDataChanged(data.copy(itemCode = it)) },
                label = { Text("Item Code") },
                singleLine = true
            )
            Button(onClick = { /* Assign Code */ }) {
                Text("Assign Code")
            }
            TextField(
                value = data.description,
                onValueChange = { onDataChanged(data.copy(description = it)) },
                label = { Text("Description") }
            )
            AttributeSection(attributes = data.attributes, onAttributesChange = {
                onDataChanged(data.copy(attributes = it))
            })
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Pricing", style = MaterialTheme.typography.titleMedium)
            PriceSection(data = data, onDataChanged = onDataChanged)
        }
    }
}

@Composable
fun DropdownField(
    label: String,
    options: List<String>,
    selectedOption: String,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            label = { Text(label) },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Rounded.ArrowDropDown, contentDescription = null)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onSelect(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun AttributeSection(
    attributes: List<Pair<String, String>>,
    onAttributesChange: (List<Pair<String, String>>) -> Unit
) {
    Column {
        Text("Attributes", style = MaterialTheme.typography.titleSmall)
        attributes.forEachIndexed { index, attr ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = attr.first,
                    onValueChange = {
                        val newAttrs = attributes.toMutableList()
                        newAttrs[index] = it to attr.second
                        onAttributesChange(newAttrs)
                    },
                    label = { Text("Attribute Name") },
                    modifier = Modifier.weight(1f)
                )
                TextField(
                    value = attr.second,
                    onValueChange = {
                        val newAttrs = attributes.toMutableList()
                        newAttrs[index] = attr.first to it
                        onAttributesChange(newAttrs)
                    },
                    label = { Text("Value") },
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = {
                    val newAttrs = attributes.toMutableList()
                    newAttrs.removeAt(index)
                    onAttributesChange(newAttrs)
                }) {
                    Icon(Icons.Default.Delete, contentDescription = "Remove")
                }
            }
        }
        Button(onClick = {
            onAttributesChange(attributes + ("" to ""))
        }) {
            Text("Add Attribute")
        }
    }
}

@Composable
fun PriceSection(data: AddItemData, onDataChanged: (AddItemData) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Sale Price")
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TextField(
                value = data.salePrice,
                onValueChange = { onDataChanged(data.copy(salePrice = it)) },
                label = { Text("Sale Price") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
            DropdownField(
                label = "",
                options = listOf("With Tax", "Without Tax"),
                selectedOption = if (data.salePriceGstIncluded) "With Tax" else "Without Tax",
                onSelect = { selected -> onDataChanged(data.copy(salePriceGstIncluded = selected == "With Tax")) },
                modifier = Modifier.weight(1f)
            )
            TextField(
                value = data.saleDiscountPercent,
                onValueChange = { onDataChanged(data.copy(saleDiscountPercent = it)) },
                label = { Text("Disc. On Sale Price") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
            DropdownField(
                label = "",
                options = listOf("Percentage", "Amount"),
                selectedOption = "Percentage", // Extend if needed
                onSelect = {}
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("Wholesale Price")
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TextField(
                value = data.wholesalePrice,
                onValueChange = { onDataChanged(data.copy(wholesalePrice = it)) },
                label = { Text("Wholesale Price") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
            DropdownField(
                label = "",
                options = listOf("With Tax", "Without Tax"),
                selectedOption = if (data.wholesalePriceGstIncluded) "With Tax" else "Without Tax",
                onSelect = { selected -> onDataChanged(data.copy(wholesalePriceGstIncluded = selected == "With Tax")) },
                modifier = Modifier.weight(1f)
            )
            TextField(
                value = data.minWholesaleQty,
                onValueChange = { onDataChanged(data.copy(minWholesaleQty = it)) },
                label = { Text("Minimum Wholesale Qty") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("Purchase Price / Taxes")
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TextField(
                value = data.purchasePrice,
                onValueChange = { onDataChanged(data.copy(purchasePrice = it)) },
                label = { Text("Purchase Price") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
            DropdownField(
                label = "",
                selectedOption = if (data.purchasePriceGstIncluded) "With Tax" else "Without Tax",
                options = listOf("With Tax", "Without Tax"),
                onSelect = { selected -> onDataChanged(data.copy(purchasePriceGstIncluded = selected == "With Tax")) },
                modifier = Modifier.weight(1f)
            )
            TextField(
                value = data.gstTaxPercentage,
                onValueChange = { onDataChanged(data.copy(gstTaxPercentage = it)) },
                label = { Text("GST Tax %") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
        }
    }
}
