package com.redravencomputing.littlelemonandroid.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.redravencomputing.littlelemonandroid.ui.theme.LittleLemonAndroidTheme
import com.redravencomputing.littlelemonandroid.viewModels.Profile

@Composable
fun HomeScreen(navController: NavHostController) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(MaterialTheme.colorScheme.background)
			.clickable {
				navController.navigate(Profile.route)
			}
	) {
		Text(text = "Home Screen")
	}
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
	LittleLemonAndroidTheme {
		val navController = rememberNavController()
		HomeScreen(navController)
	}
}