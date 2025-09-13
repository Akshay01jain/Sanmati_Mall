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
        object Buyer : NavItems(UserTypeScreens.BUYER.name)
        object Customer : NavItems(UserTypeScreens.CUSTOMER.name)

    }


}