package com.sanmati.mall.navigation.adminNavigation

sealed class ProductNavItems(val route: String, val title: String) {
    object Products : ProductNavItems("products_tab", "Products")
    object Categories : ProductNavItems("categories_tab", "Categories")
    object Units : ProductNavItems("units_tab", "Units")
    object Companies : ProductNavItems("companies_tab", "Company")

    object AddProduct : ProductNavItems("add_product", "Add products")
    object AddCategory : ProductNavItems("add_category", "Add category")
    object AddUnit : ProductNavItems("add_unit", "Add unit")
}

