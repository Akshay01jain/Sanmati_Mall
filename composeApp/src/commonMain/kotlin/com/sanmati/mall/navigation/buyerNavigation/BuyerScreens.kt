package com.sanmati.mall.navigation.buyerNavigation

enum class BuyerDashboardScreens {
    HOME,
    PARTIES,
    SALES,
    PURCHASE,
    SETTINGS

}

sealed class BuyerNavItems(val route: String) {

    object Home : BuyerNavItems(BuyerDashboardScreens.HOME.name)
    object Parties : BuyerNavItems(BuyerDashboardScreens.PARTIES.name)
    object Sales : BuyerNavItems(BuyerDashboardScreens.SALES.name)
    object Purchase : BuyerNavItems(BuyerDashboardScreens.PURCHASE.name)
    object Settings : BuyerNavItems(BuyerDashboardScreens.SETTINGS.name)

}