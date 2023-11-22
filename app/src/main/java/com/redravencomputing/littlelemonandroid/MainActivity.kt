package com.redravencomputing.littlelemonandroid

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.redravencomputing.littlelemonandroid.model.MenuItemNetwork
import com.redravencomputing.littlelemonandroid.model.MenuNetworkData
import com.redravencomputing.littlelemonandroid.ui.theme.LittleLemonAndroidTheme
import com.redravencomputing.littlelemonandroid.viewModels.MENU_DATA_URL
import com.redravencomputing.littlelemonandroid.views.navigation.MyNavigation
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val MAIN_ACTIVITY = "MAIN ACTIVITY"
class MainActivity : ComponentActivity() {
	private val httpClient = HttpClient(Android) {
		install(ContentNegotiation) {
			json(contentType = ContentType("text", "plain"))
		}
	}
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			LittleLemonAndroidTheme {
				val navController = rememberNavController()
				MyNavigation(navController = navController)
			}
		}

		lifecycleScope.launch(Dispatchers.IO) {
			val menuItems = fetchMenu()
		}
	}

	private suspend fun fetchMenu(): List<MenuItemNetwork> {
		return try {
			val response : MenuNetworkData = httpClient.get(MENU_DATA_URL).body()
			response.menu
		} catch (e: Exception) {
			Log.e(MAIN_ACTIVITY, "${e.message}")
			e.printStackTrace()
			Log.e(MAIN_ACTIVITY, e.localizedMessage ?: "No localized error message")
			emptyList()
		}
	}

}



