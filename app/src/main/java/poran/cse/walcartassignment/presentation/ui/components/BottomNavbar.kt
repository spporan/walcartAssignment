package poran.cse.walcartassignment.presentation.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import poran.cse.walcartassignment.R


@Composable
fun BottomBarNavigation(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: return
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Category,
        BottomNavItem.Cart,
        BottomNavItem.Favourite,
        BottomNavItem.Profile
    )
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Gray
    ) {
       items.forEach { item->
           BottomNavigationItem(
               icon =  { Icon(painterResource(id = item.icon), contentDescription = item.title) },
               selected = currentRoute == item.screen_route,
               selectedContentColor = colorResource(id = R.color.selected_content_color),
               unselectedContentColor = colorResource(id = R.color.un_selected_content_color),
               label = { Text(text = item.title,
                   fontSize = 9.sp) },
               onClick = {
                   navController.navigate(item.screen_route) {
                       navController.graph.startDestinationRoute?.let { screen_route ->
                           popUpTo(screen_route) {
                               saveState = true
                           }
                       }
                       launchSingleTop = true
                       restoreState = true
                   }
               },

           )
       }

    }
}


