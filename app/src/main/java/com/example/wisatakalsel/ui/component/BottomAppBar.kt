package com.example.wisatakalsel.ui.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.wisatakalsel.navigation.NavigationItem
import com.example.wisatakalsel.navigation.Screen
import com.example.wisatakalsel.ui.theme.WisataKalselTheme
import io.eyram.iconsax.IconSax

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(modifier = modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = "Home",
                icon = painterResource(id = IconSax.Linear.Home2),
                screen = Screen.Home,
            ),
            NavigationItem(
                title = "Favorite",
                icon = painterResource(id = IconSax.Linear.Lovely),
                screen = Screen.Favorite,
            ),
            NavigationItem(
                title = "About",
                icon = painterResource(id = IconSax.Linear.User),
                screen = Screen.About,
            ),

            )
        navigationItems.map { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                label = {
                    Text(text = item.title)
                },
                icon = {
                    Icon(
                        painter = item.icon,
                        contentDescription = item.title,
                    )
                },
            )
        }
    }

}

@Preview
@Composable
fun BottomBarPreview() {
    WisataKalselTheme {
        val navController = rememberNavController()
        BottomBar(navController = navController)
    }
}