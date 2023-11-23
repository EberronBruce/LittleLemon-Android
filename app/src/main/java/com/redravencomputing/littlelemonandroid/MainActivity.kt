package com.redravencomputing.littlelemonandroid

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.redravencomputing.littlelemonandroid.model.MenuDatabase
import com.redravencomputing.littlelemonandroid.model.MenuItemNetwork
import com.redravencomputing.littlelemonandroid.model.MenuNetworkData
import com.redravencomputing.littlelemonandroid.ui.theme.LittleLemonAndroidTheme
import com.redravencomputing.littlelemonandroid.viewModels.Home
import com.redravencomputing.littlelemonandroid.viewModels.MENU_DATA_URL
import com.redravencomputing.littlelemonandroid.viewModels.isUserLoggedIn
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
				val context = LocalContext.current
				val isLoggedIn = isUserLoggedIn(context)
				if (isLoggedIn) {navController.navigate(Home.route)}
			}
		}

		lifecycleScope.launch(Dispatchers.IO) {
			val database = MenuDatabase.getInstance(applicationContext).menuItemDao()
			// Check for data in database and clear it
			try {
				val menuItems = fetchMenu()
				if (!database.isEmpty()) {
					database.deleteAll()
				}
				saveMenuToDatabase(menuItems)
			} catch (e: Exception) {
				Log.e(MAIN_ACTIVITY, e.message ?: "Error: Unable to populate database from server")
				e.printStackTrace()
			}

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

	private fun saveMenuToDatabase(menuItemsNetwork : List<MenuItemNetwork>) {
		val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
		MenuDatabase.getInstance(applicationContext).menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
	}

}



