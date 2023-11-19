package com.redravencomputing.littlelemonandroid.views.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.redravencomputing.littlelemonandroid.viewModels.Home
import com.redravencomputing.littlelemonandroid.viewModels.Onboarding
import com.redravencomputing.littlelemonandroid.viewModels.Profile
import com.redravencomputing.littlelemonandroid.views.HomeScreen
import com.redravencomputing.littlelemonandroid.views.OnboardingScreen
import com.redravencomputing.littlelemonandroid.views.ProfileScreen

@Composable
fun MyNavigation(navController: NavHostController) {
	NavHost(navController = navController , startDestination = Onboarding.route) {
		composable(Onboarding.route) {
			OnboardingScreen(navController)
		}
		composable(Home.route) {
			HomeScreen()
		}
		composable(Profile.route) {
			ProfileScreen()
		}
	}
}