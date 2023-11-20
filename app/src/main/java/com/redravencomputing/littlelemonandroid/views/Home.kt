package com.redravencomputing.littlelemonandroid.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.redravencomputing.littlelemonandroid.R
import com.redravencomputing.littlelemonandroid.ui.theme.LittleLemonAndroidTheme
import com.redravencomputing.littlelemonandroid.ui.theme.LittleLemonLightGrey
import com.redravencomputing.littlelemonandroid.ui.theme.LittleLemonYellow
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
		Header(home = true, navController = navController)
		Hero()
	}
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Hero() {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.background(MaterialTheme.colorScheme.primary)
	) {
		Text(
			text = "Little Lemon",
			color = LittleLemonYellow,
			fontSize = 40.sp,
			fontWeight = FontWeight.SemiBold,
			modifier = Modifier
				.padding(start = 20.dp, top = 8.dp)
		)
		Text(
			text = "Chicago",
			color = LittleLemonLightGrey,
			fontSize = 30.sp,
			fontWeight = FontWeight.Medium,
			modifier = Modifier
				.padding(start = 20.dp)
		)
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(10.dp)
		) {
			Text(
				text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
				color = LittleLemonLightGrey,
				modifier = Modifier
					.fillMaxWidth(0.6f)
					.padding(10.dp)
			)
			Image(
				painter = painterResource(id = R.drawable.hero_image),
				contentDescription = "Hero Image",
				contentScale = ContentScale.FillWidth,
				modifier = Modifier
					.sizeIn(
						minWidth = 100.dp,
						minHeight = 100.dp,
						maxHeight = 150.dp,
						maxWidth = 150.dp
					)
//					.size(150.dp)
//					.height(150.dp)
					.padding(10.dp)
					.clip(RoundedCornerShape(16.dp))
			)
		}

		TextField(
			value = "",
			onValueChange = {},
			leadingIcon = {
				Icon(
					imageVector = Icons.Default.Search,
					contentDescription = "Search Icon")
			},
			placeholder = { Text("Search...")},
			keyboardOptions = KeyboardOptions(
				imeAction = ImeAction.Search
			),
			modifier = Modifier
				.fillMaxWidth()
				.padding(10.dp),
			colors =  TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.background),
			shape = RoundedCornerShape(16.dp)
		)

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

@Preview(showBackground = true)
@Composable
fun HeroPreview() {
	LittleLemonAndroidTheme {
		Hero()
	}
}