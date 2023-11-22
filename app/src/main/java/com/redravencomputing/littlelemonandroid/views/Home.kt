package com.redravencomputing.littlelemonandroid.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.redravencomputing.littlelemonandroid.R
import com.redravencomputing.littlelemonandroid.model.MenuDatabase
import com.redravencomputing.littlelemonandroid.model.MenuItemRoom
import com.redravencomputing.littlelemonandroid.ui.theme.LittleLemonAndroidTheme
import com.redravencomputing.littlelemonandroid.ui.theme.LittleLemonDarkGrey
import com.redravencomputing.littlelemonandroid.ui.theme.LittleLemonLightGrey
import com.redravencomputing.littlelemonandroid.ui.theme.LittleLemonYellow
import com.redravencomputing.littlelemonandroid.viewModels.Profile
import java.util.Locale

@Composable
fun HomeScreen(navController: NavHostController) {
	val context = LocalContext.current
	val database = MenuDatabase.getInstance(context = context).menuItemDao()

	val menuItems by database.getAll().observeAsState(emptyList())

	val categories by database.getCategories().observeAsState(emptyList())
	

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
		OrderCategory(categories)
		MenuList(menuItems)
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
			fontSize = 28.sp,
			fontWeight = FontWeight.SemiBold,
			modifier = Modifier
				.padding(start = 20.dp, top = 8.dp)
		)
		Text(
			text = "Chicago",
			color = LittleLemonLightGrey,
			fontSize = 20.sp,
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
				fontSize = 14.sp,
				modifier = Modifier
					.fillMaxWidth(0.7f)
					.padding(10.dp)
			)

			Spacer(modifier = Modifier.weight(1f))

			Image(
				painter = painterResource(id = R.drawable.hero_image),
				contentDescription = "Hero Image",
				contentScale = ContentScale.FillWidth,
				modifier = Modifier
					.sizeIn(
						minWidth = 100.dp,
						minHeight = 100.dp,
						maxHeight = 125.dp,
						maxWidth = 125.dp
					)
					.padding(4.dp)
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
				.padding(8.dp),
			colors =  TextFieldDefaults.textFieldColors(
				containerColor = MaterialTheme.colorScheme.background,
				focusedIndicatorColor = Color.Transparent, // Remove the black line below
				unfocusedIndicatorColor = Color.Transparent // Remove the black line below
			) ,
			shape = RoundedCornerShape(16.dp)
		)

	}
}

@Composable
fun OrderCategory(menuCategories : List<String>) {

	val capitalizeCategories = menuCategories.toSet().map { it ->
		it.replaceFirstChar {
			if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
		}
	}

	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(vertical = 4.dp)
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically
		) {
			Text(
				text = "ORDER FOR DELIVERY!",
				fontWeight = FontWeight.Black,
				fontFamily = FontFamily.SansSerif,
				modifier = Modifier
					.padding(start = 15.dp, top = 10.dp, bottom = 8.dp)
			)

			Image(
				painter = painterResource(id = R.drawable.delivery_van),
				contentDescription = "deliver_van icon",
				modifier = Modifier
					.padding(start = 20.dp)
					.size(40.dp)
				)
		}

		LazyRow(
			modifier = Modifier
				.fillMaxWidth()
				.padding(vertical = 8.dp)
				.nestedScroll(rememberNestedScrollInteropConnection()),
			horizontalArrangement = Arrangement.SpaceBetween
		) {
			items(capitalizeCategories.toTypedArray()) { category ->
				PillButton(text = category)
			}
		}

		Divider(
			thickness = 1.dp,
			color = LittleLemonLightGrey
		)
	}
}

@Composable
fun PillButton(text: String) {
	Box(
		modifier = Modifier
			.padding(horizontal = 10.dp)
			.clip(CircleShape)
			.background(Color.LightGray)
			.clickable { /* Handle button click */ }
			.padding(horizontal = 16.dp, vertical = 8.dp)
	) {
		Text(
			text = text,
			fontFamily = FontFamily.SansSerif,
			fontWeight = FontWeight.Bold,
			color = LittleLemonDarkGrey
		)
	}
}

@Composable
fun MenuList(menuItems : List<MenuItemRoom>) {

	LazyColumn {
		items(menuItems) { menuItem ->
			MenuCell(menuItem)
		}
	}

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuCell(menuItem : MenuItemRoom) {
	Row (
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier
			.fillMaxWidth()
			.padding(10.dp)
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth(0.7f)
		) {
			Text(
				text = menuItem.title,
				fontWeight = FontWeight.Bold,
				fontFamily = FontFamily.SansSerif,
				modifier = Modifier
					.padding(vertical = 4.dp)
			)
			Text(
				text = menuItem.description,
				maxLines = 2,
				overflow = TextOverflow.Ellipsis,
				fontWeight = FontWeight.Medium,
				fontSize = 13.sp,
				color = Color.Gray,
				lineHeight = 18.sp,
				modifier = Modifier
				.padding(vertical = 8.dp)
			)
			Text(
				text = "$${String.format("%.2f", menuItem.price)}",
				fontWeight = FontWeight.SemiBold,
				modifier = Modifier
					.padding(vertical = 8.dp)
			)
		}

		Spacer(modifier = Modifier.weight(1f))

		GlideImage(
			model = menuItem.imageUrl,
			contentDescription = menuItem.title,
			loading = placeholder(R.drawable.logo_hero),
			contentScale = ContentScale.Crop,
			modifier = Modifier
				.size(100.dp)
				.padding(8.dp)
		)
	}

	Divider(
		thickness = 1.dp,
		color = LittleLemonLightGrey
	)
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

@Preview(showBackground = true)
@Composable
fun PillPreview() {
	LittleLemonAndroidTheme {
		PillButton(text = "Starters")
	}
}

@Preview(showBackground = true)
@Composable
fun MenuCellPreview() {
	LittleLemonAndroidTheme {
		val menuItem = MenuItemRoom(
			1, "Greek Something", 10.99, "This is something to show here", "mains", ""
		)
		MenuCell(menuItem)
	}
}