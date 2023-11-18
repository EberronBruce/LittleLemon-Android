package com.redravencomputing.littlelemonandroid.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.redravencomputing.littlelemonandroid.R
import com.redravencomputing.littlelemonandroid.ui.theme.LittleLemonAndroidTheme
import com.redravencomputing.littlelemonandroid.ui.theme.LittleLemonDarkGrey
import com.redravencomputing.littlelemonandroid.ui.theme.LittleLemonLightGrey
import com.redravencomputing.littlelemonandroid.ui.theme.LittleLemonYellow


@Composable
fun Onboarding() {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(MaterialTheme.colorScheme.background)
	) {
		Header()
		OnboardingTitle()
		OnboardingForm()
	}
}

@Composable
fun Header() {
	val logo = painterResource(id = R.drawable.logo)
	Box(
		modifier = Modifier
			.fillMaxWidth()
			.height(100.dp)
			.background(MaterialTheme.colorScheme.background)
			.padding(16.dp),
		contentAlignment = Alignment.Center
	) {
		Image(
			painter = logo,
			contentDescription = "Logo",
			modifier = Modifier
				.padding(5.dp)
		)
	}
}

@Composable
fun OnboardingTitle() {
	Box(
		modifier = Modifier
			.fillMaxWidth()
			.height(150.dp)
			.background(MaterialTheme.colorScheme.primary),
		contentAlignment = Alignment.Center
	) {
		Text(
			text = "Let's get to know you",
			fontSize = 24.sp,
			color = LittleLemonLightGrey
		)
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingForm() {
	Text(
		text = "Personal information",
		fontWeight = FontWeight.SemiBold,
		modifier = Modifier
			.padding(top = 40.dp, bottom = 40.dp, start = 20.dp)
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
			Text(text = "First name")
			OutlinedTextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth())
		}

		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(bottom = 30.dp)
		) {
			Text(text = "Last name")
			OutlinedTextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth())
		}

		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(bottom = 30.dp)
		) {
			Text(text = "Email")
			OutlinedTextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth())
		}

		Spacer(modifier = Modifier.weight(1f))

		OutlinedButton(
			modifier = Modifier
				.fillMaxWidth()
				.padding(bottom = 20.dp),
			colors = ButtonDefaults.buttonColors(containerColor = LittleLemonYellow),
			onClick = { /*TODO*/ }
		) {
			Text(
				text = "Register",
				color = LittleLemonDarkGrey)
		}
	}
}


@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
	LittleLemonAndroidTheme {
		Onboarding()
	}
}