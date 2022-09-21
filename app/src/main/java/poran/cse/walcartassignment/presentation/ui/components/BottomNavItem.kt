package poran.cse.walcartassignment.presentation.ui.components

import poran.cse.walcartassignment.R

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){
    object Home : BottomNavItem("Home", R.drawable.ic_home,"home")
    object Category: BottomNavItem("Category", R.drawable.ic_category,"category")
    object Cart: BottomNavItem("Cart",R.drawable.ic_cart,"cart")
    object Favourite: BottomNavItem("Favourite",R.drawable.ic_favourite,"favourite")
    object Profile: BottomNavItem("Account",R.drawable.ic_profile,"account")
}

