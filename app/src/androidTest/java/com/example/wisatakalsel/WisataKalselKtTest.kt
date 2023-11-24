package com.example.wisatakalsel

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.wisatakalsel.model.TourData
import com.example.wisatakalsel.navigation.Screen
import com.example.wisatakalsel.ui.TourApp
import com.example.wisatakalsel.ui.theme.WisataKalselTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class WisataKalselKtTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.setContent {
            WisataKalselTheme  {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                TourApp(navController = navController)
            }
        }
    }

//    Check First Page when open apk
    @Test
    fun checkCureFirstPage() {
        navController.assertCurrentRouteName(Screen.Home.route)
    }


//    Testing Bottom Nav
    @Test
    fun bottomNavTesting() {
        composeTestRule.onNodeWithText("Favorite").performClick()
        navController.assertCurrentRouteName(Screen.Favorite.route)
        composeTestRule.onNodeWithText("About").performClick()
        navController.assertCurrentRouteName(Screen.About.route)
        composeTestRule.onNodeWithText("Home").performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

//    Testing Navigation to About with valid data
    @Test
    fun navtoDetail() {
        composeTestRule.onNodeWithText("About").performClick()
        navController.assertCurrentRouteName(Screen.About.route)
        composeTestRule.onNodeWithText("Willy Agustri Djabar").assertIsDisplayed()
        composeTestRule.onNodeWithText("willyofficial082@gmail.com").assertIsDisplayed()
        composeTestRule.onNodeWithText("A2023012").assertIsDisplayed()
    }

//    Testing if Tour is Empty!
    @Test
    fun emptyListTour() {
        val incorrectSearch = "Fh43238fdhdw"
        composeTestRule.onNodeWithText("Cari Tempat Wisata").performTextInput(incorrectSearch)
        composeTestRule.onNodeWithTag("emptyList").assertIsDisplayed()
    }

//    Testing if Tour Is Available!
    @Test
    fun showListTour() {
        val rightSearch = "Tahura"
        composeTestRule.onNodeWithText("Cari Tempat Wisata").performTextInput(rightSearch)
        composeTestRule.onNodeWithText("Tahura").assertIsDisplayed()
    }


//    Testing Add Favorite and navigate to check Favorite if has added!
    @Test
    fun clickAddFavoriteAndCheck() {
        composeTestRule.onNodeWithText(TourData.dummyTour[0].name).performClick()
        navController.assertCurrentRouteName(Screen.DetailTour.route)
        composeTestRule.onNodeWithTag("favorite_detail_button").performClick()
        composeTestRule.onNodeWithContentDescription("Home").performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
        composeTestRule.onNodeWithText("Favorite").performClick()
        navController.assertCurrentRouteName(Screen.Favorite.route)
        composeTestRule.onNodeWithText(TourData.dummyTour[0].name).assertIsDisplayed()
    }

    //    Testing Delete Favorite and navigate to check Favorite if has Deleted!
    @Test
    fun clickDeleteFavoriteAndCheck() {
        composeTestRule.onNodeWithText(TourData.dummyTour[0].name).performClick()
        navController.assertCurrentRouteName(Screen.DetailTour.route)
        composeTestRule.onNodeWithTag("favorite_detail_button").performClick()
        composeTestRule.onNodeWithTag("favorite_detail_button").performClick()
        composeTestRule.onNodeWithContentDescription("Home").performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
        composeTestRule.onNodeWithText("Favorite").performClick()
        navController.assertCurrentRouteName(Screen.Favorite.route)
        composeTestRule.onNodeWithText("Tidak ada Daftar Favorite").assertIsDisplayed()
    }
}