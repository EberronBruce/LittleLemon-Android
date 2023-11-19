package com.redravencomputing.littlelemonandroid.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.redravencomputing.littlelemonandroid.ui.theme.LittleLemonAndroidTheme

@Composable
fun HomeScreen() {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(MaterialTheme.colorScheme.background)
	) {
		Text(text = "Home Screen")
	}
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
	LittleLemonAndroidTheme {
		HomeScreen()
	}
}