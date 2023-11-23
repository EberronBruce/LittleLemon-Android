package com.redravencomputing.littlelemonandroid.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.redravencomputing.littlelemonandroid.R
import com.redravencomputing.littlelemonandroid.ui.theme.LittleLemonAndroidTheme
import com.redravencomputing.littlelemonandroid.ui.theme.LittleLemonGreen
import com.redravencomputing.littlelemonandroid.ui.theme.LittleLemonLightGrey
import com.redravencomputing.littlelemonandroid.ui.theme.LittleLemonYellow
import com.redravencomputing.littlelemonandroid.viewModels.Profile

@Composable
fun Header(navController: NavHostController? = null, home: Boolean = false, profile : Boolean = false) {
	val logo = painterResource(id = R.drawable.logo)
	val profileImage = painterResource(id = R.drawable.ic_profile)
	val backImage = painterResource(id = R.drawable.ic_arrow_back)

	Row(
		modifier = Modifier
			.fillMaxWidth()
			.height(90.dp)
			.background(MaterialTheme.colorScheme.background)
			.padding(16.dp),
		horizontalArrangement = Arrangement.Center,
		verticalAlignment = Alignment.CenterVertically
	) {

		if (profile) {
			Box(
				modifier = Modifier
					.size(40.dp) // Adjust size as needed
					.background(shape = CircleShape, color = LittleLemonGreen),
				contentAlignment = Alignment.Center
			) {
				Image(
					painter = backImage,
					contentDescription = "Back Button",
					colorFilter = ColorFilter.tint(LittleLemonYellow),
					modifier = Modifier
						.size(40.dp) // Adjust size as needed
						.padding(8.dp)
						.clickable {
							navController?.popBackStack()
						}
				)
			}
		}

		Spacer(modifier = Modifier.weight(if (home) 2f else 1f))

		Image(
			painter = logo,
			contentDescription = "Logo",
			modifier = Modifier
				.padding(5.dp)
		)

		Spacer(modifier = Modifier.weight(if (profile) 2f else 1f))

		if (home) {
			Box(
				modifier = Modifier
					.size(50.dp) // Adjust size as needed
					.background(shape = CircleShape, color = LittleLemonGreen)
					.clickable { navController?.navigate(Profile.route) },
				contentAlignment = Alignment.Center
			) {
				Image(
					painter = profileImage,
					contentDescription = "Profile Image",
					colorFilter = ColorFilter.tint(LittleLemonYellow),
					modifier = Modifier
						.size(50.dp) // Adjust size as needed
						.padding(8.dp)
				)
			}
		}

	}
}

@Composable
fun ScreenTitle(title: String) {
	Box(
		modifier = Modifier
			.fillMaxWidth()
			.height(125.dp)
			.background(MaterialTheme.colorScheme.primary),
		contentAlignment = Alignment.Center
	) {
		Text(
			text = title,
			fontSize = 24.sp,
			color = LittleLemonLightGrey
		)
	}
}

@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
	LittleLemonAndroidTheme {
		Header()
	}
}