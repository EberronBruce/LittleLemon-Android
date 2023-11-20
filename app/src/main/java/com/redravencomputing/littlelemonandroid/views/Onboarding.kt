package com.redravencomputing.littlelemonandroid.views

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.redravencomputing.littlelemonandroid.ui.theme.LittleLemonAndroidTheme
import com.redravencomputing.littlelemonandroid.ui.theme.LittleLemonDarkGrey
import com.redravencomputing.littlelemonandroid.ui.theme.LittleLemonYellow
import com.redravencomputing.littlelemonandroid.viewModels.Home
import com.redravencomputing.littlelemonandroid.viewModels.isValidEmail
import com.redravencomputing.littlelemonandroid.viewModels.saveUserData


@Composable
fun OnboardingScreen(navController: NavHostController) {
	val focusManager = LocalFocusManager.current
	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(MaterialTheme.colorScheme.background)
			.clickable {
				focusManager.clearFocus()
			}
	) {
		Header()
		ScreenTitle(title = "Let's get to know you")
		OnboardingForm(navController)
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingForm(navController: NavHostController) {
	val context = LocalContext.current
	val focusManager = LocalFocusManager.current
	var firstName by remember { mutableStateOf("") }
	var lastName by remember { mutableStateOf("") }
	var email by remember { mutableStateOf("") }

	// State variables for tracking whether to highlight the text fields
	var firstNameError by remember { mutableStateOf(false) }
	var lastNameError by remember { mutableStateOf(false) }
	var emailError by remember { mutableStateOf(false) }

	Text(
		text = "Personal information",
		fontWeight = FontWeight.SemiBold,
		modifier = Modifier
			.padding(top = 30.dp, bottom = 20.dp, start = 20.dp)
	)
	Column (
		modifier = Modifier
			.fillMaxWidth()
			.padding(20.dp)
	){
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(bottom = 30.dp)
		) {
			Text(
				text = "First name",
				color = if (firstNameError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onBackground
			)
			OutlinedTextField(
				value = firstName,
				onValueChange = {
					firstName = it
					firstNameError = false
				},
				isError = firstNameError,
				placeholder = {
					Text(text = "Enter your first name",
						color = if (firstNameError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onBackground
					)
			    },
				keyboardOptions = KeyboardOptions(
					keyboardType = KeyboardType.Text,
					imeAction = ImeAction.Next
				),
				keyboardActions = KeyboardActions(
					onNext = {
						Log.d("Keyboard Tapped", "Move to the Lastname")
						firstNameError = firstName.isBlank()
						focusManager.moveFocus(
							focusDirection = FocusDirection.Next
						)
					}
				),
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
				color = if (lastNameError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onBackground
			)
			OutlinedTextField(
				value = lastName,
				onValueChange = {
					lastName = it
					lastNameError = false
				},
				isError = lastNameError,
				placeholder = {
					Text(text ="Enter your last name",
						color = if (lastNameError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onBackground
					)},
				keyboardOptions = KeyboardOptions(
					keyboardType = KeyboardType.Text,
					imeAction = ImeAction.Next
				),
				keyboardActions = KeyboardActions(
					onNext = {
						Log.d("Keyboard Tapped", "Move to the Email")
						lastNameError = lastName.isBlank()
						focusManager.moveFocus(
							focusDirection = FocusDirection.Next
						)
					}
				),
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
				text = "Email",
				color = if (emailError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onBackground)
			OutlinedTextField(
				value = email,
				onValueChange = {
					email = it
					emailError = !isValidEmail(email)
					 },
				isError = emailError,
				placeholder = { Text(text = "Enter your email",
					color = if (lastNameError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onBackground)},
				keyboardOptions = KeyboardOptions(
					keyboardType = KeyboardType.Email,
					imeAction = ImeAction.Done
				),
				keyboardActions = KeyboardActions(
					onDone = {
						Log.d("Keyboard Tapped", "Remove Keyboard")
						emailError = email.isBlank() || !isValidEmail(email = email)
						focusManager.clearFocus()
					}
				),
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
				firstNameError = firstName.isBlank()
				lastNameError = lastName.isBlank()
				emailError = email.isBlank() || !isValidEmail(email)
				//Check Fields and let user know if they are not filled out.
				if (!firstNameError && !lastNameError && email.isNotBlank()) {
					handleRegistration(context, navController, firstName, lastName, email)

				} else {
					Toast.makeText(context, "You are missing a field. Please fill out all the information.", Toast.LENGTH_SHORT).show()
				}
			}
		) {
			Text(
				text = "Register",
				color = LittleLemonDarkGrey)
		}
	}

}

private fun handleRegistration(context: Context, navController: NavHostController, firstName: String, lastName: String, email: String) {
	if (!isValidEmail(email)) {
		Toast.makeText(context, "Your email is invalid. Please provide a valid email.", Toast.LENGTH_SHORT).show()
		return
	}

	saveUserData(context = context, firstName = firstName, lastName = lastName, email = email)
	navController.navigate(Home.route)
}


@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
	LittleLemonAndroidTheme {
		val navController = rememberNavController()
		OnboardingScreen(navController)
	}
}