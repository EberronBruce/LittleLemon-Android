package com.redravencomputing.littlelemonandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.redravencomputing.littlelemonandroid.ui.theme.LittleLemonAndroidTheme
import com.redravencomputing.littlelemonandroid.views.navigation.MyNavigation

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			LittleLemonAndroidTheme {
				val navController = rememberNavController()
				MyNavigation(navController = navController)
			}
		}
	}
}



