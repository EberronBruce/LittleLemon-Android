package com.redravencomputing.littlelemonandroid.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.redravencomputing.littlelemonandroid.ui.theme.LittleLemonAndroidTheme
import com.redravencomputing.littlelemonandroid.ui.theme.LittleLemonDarkGrey
import com.redravencomputing.littlelemonandroid.ui.theme.LittleLemonYellow
import com.redravencomputing.littlelemonandroid.viewModels.clearUserData
import com.redravencomputing.littlelemonandroid.viewModels.getUserData

@Composable
fun ProfileScreen(navController: NavHostController) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(MaterialTheme.colorScheme.background)
	) {
		Header(profile = true, navController = navController)
		ScreenTitle(title = "Profile")
		ProfileInfo(navController)
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileInfo(navController: NavHostController) {
	val context = LocalContext.current

	val (firstName, lastName, email) = getUserData(context)

	Column (
		modifier = Modifier
			.fillMaxWidth()
			.padding(20.dp),
		verticalArrangement = Arrangement.Center
	){
		Text(
			text = "Personal information",
			fontWeight = FontWeight.SemiBold,
			modifier = Modifier
				.padding(top = 30.dp, bottom = 30.dp)
		)
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(bottom = 30.dp)
		) {
			Text(
				text = "First name"
			)
			OutlinedTextField(
				value = firstName ?: "No First name",
				onValueChange = {
					//Do nothing
				},
				readOnly = true,
				modifier = Modifier
					.fillMaxWidth()
			)
		}

		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(bottom = 30.dp)
		) {
			Text(
				text = "Last name",
				//color = if (lastNameError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onBackground
			)
			OutlinedTextField(
				value = lastName ?: "No last Name",
				onValueChange = {
					//Do Nothing
				},
				readOnly = true,
				modifier = Modifier
					.fillMaxWidth()
			)
		}

		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(bottom = 30.dp)
		) {
			Text(
				text = "Email"
			)
			OutlinedTextField(
				value = email ?: "No Email",
				onValueChange = {
					//Do Nothing
				},
				readOnly = true,
				modifier = Modifier
					.fillMaxWidth()
			)
		}

		Spacer(modifier = Modifier.weight(1f))

		OutlinedButton(
			modifier = Modifier
				.fillMaxWidth()
				.padding(bottom = 10.dp),
			colors = ButtonDefaults.buttonColors(containerColor = LittleLemonYellow),
			onClick = {
				//Navigate back to Onboarding
				clearUserData(context = context)
				navController.popBackStack(navController.graph.startDestinationId, false)
			}
		) {
			Text(
				text = "Log out",
				color = LittleLemonDarkGrey
			)
		}
	}

}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
	LittleLemonAndroidTheme {
		val navController = rememberNavController()
		ProfileScreen(navController)
	}
}