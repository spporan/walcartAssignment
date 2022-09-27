package poran.cse.walcartassignment.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import poran.cse.walcartassignment.presentation.ui.components.BottomBarNavigation
import poran.cse.walcartassignment.presentation.ui.components.BottomNavItem
import poran.cse.walcartassignment.presentation.ui.screens.*
import poran.cse.walcartassignment.presentation.ui.theme.WalcartAssignmentTheme
import poran.cse.walcartassignment.presentation.viewmodels.CategoryViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WalcartAssignmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    MainScreenView()
                }
            }
        }
    }
}


@Composable
fun MainScreenView(){
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Browse Category",
                        style = androidx.compose.material.MaterialTheme.typography.h5,
                    )
                },
                backgroundColor = Color.White,
                contentColor = Color.Black,
                elevation = 0.dp
            )
        },
        bottomBar = {
            BottomBarNavigation(navController)
        }
    ) { innerPadding->
        NavigationGraph(navController, innerPadding)
    }
}

@Composable
fun NavigationGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(navController, startDestination = BottomNavItem.Category.screen_route, Modifier.padding(paddingValues)) {
        composable(BottomNavItem.Home.screen_route) {
            HomeScreen()
        }
        composable(BottomNavItem.Category.screen_route) {
            CategoryScreen()
        }
        composable(BottomNavItem.Cart.screen_route) {
           CartScreen()
        }
        composable(BottomNavItem.Favourite.screen_route) {
            FavouriteScreen()
        }
        composable(BottomNavItem.Profile.screen_route) {
            ProfileScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview() {
    MainScreenView()
}


