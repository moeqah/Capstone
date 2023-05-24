package com.littlelemon.littlelemonapp.ui.theme

interface Destinations{
    val route: String
}

object Home: Destinations{
    override val route = "Home"
}

object Onboarding: Destinations{
    override val route = "Onboarding"
}

object Profile: Destinations{
    override val route = "Profile"

}
