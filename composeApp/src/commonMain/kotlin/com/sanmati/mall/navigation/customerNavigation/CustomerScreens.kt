package com.sanmati.mall.navigation.customerNavigation

enum class CustomerDashboardScreens {
    HOME,
    PARTIES,
    SALES,
    PURCHASE,
    SETTINGS

}

sealed class CustomerNavItems(val route: String) {

    object Home : CustomerNavItems(CustomerDashboardScreens.HOME.name)
    object Parties : CustomerNavItems(CustomerDashboardScreens.PARTIES.name)
    object Sales : CustomerNavItems(CustomerDashboardScreens.SALES.name)
    object Purchase : CustomerNavItems(CustomerDashboardScreens.PURCHASE.name)
    object Settings : CustomerNavItems(CustomerDashboardScreens.SETTINGS.name)

}