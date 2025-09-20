package com.sanmati.mall.navigation

enum class Screens {
    SPLASH,
    REGISTRATION,
    USER_TYPE

}

enum class RegistrationScreens
{
    LOGIN,
    SIGNUP,
    FORGOT_PASSWORD
}

enum class UserTypeScreens
{
    ADMIN,
    BUYER,
    CUSTOMER
}

enum class AdminInnerScreens
{
    DASHBOARD,
    SEARCH,
    ADD_PRODUCT,
    ADD_CATEGORY,
    ADD_UNIT
}

sealed class NavItems(val route : String)
{
    object Splash : NavItems(Screens.SPLASH.name)
    object Registration : NavItems(Screens.REGISTRATION.name)
    {
        object Login : NavItems(RegistrationScreens.LOGIN.name)
        object SignUp : NavItems(RegistrationScreens.SIGNUP.name)
    }
    object UserType : NavItems(Screens.USER_TYPE.name)
    {
        object Admin : NavItems(UserTypeScreens.ADMIN.name)
        {
            object Dashboard : NavItems(AdminInnerScreens.DASHBOARD.name)
            object Search : NavItems(AdminInnerScreens.SEARCH.name)
            object AddProduct : NavItems(AdminInnerScreens.ADD_PRODUCT.name)
            object AddCategory : NavItems(AdminInnerScreens.ADD_CATEGORY.name)
            object AddUnit : NavItems(AdminInnerScreens.ADD_UNIT.name)
        }
        object Buyer : NavItems(UserTypeScreens.BUYER.name)
        object Customer : NavItems(UserTypeScreens.CUSTOMER.name)

    }


}