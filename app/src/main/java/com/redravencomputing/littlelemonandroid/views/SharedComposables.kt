package com.redravencomputing.littlelemonandroid.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.redravencomputing.littlelemonandroid.R
import com.redravencomputing.littlelemonandroid.ui.theme.LittleLemonLightGrey

@Composable
fun Header() {
	val logo = painterResource(id = R.drawable.logo)
	Box(
		modifier = Modifier
			.fillMaxWidth()
			.height(90.dp)
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