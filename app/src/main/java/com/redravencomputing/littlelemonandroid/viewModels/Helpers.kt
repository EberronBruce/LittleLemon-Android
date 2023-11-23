package com.redravencomputing.littlelemonandroid.viewModels

import android.content.Context
import android.content.SharedPreferences

const val KEY_USER_DATA = "UserData"
const val KEY_FIRST_NAME = "firstName"
const val KEY_LAST_NAME = "lastName"
const val KEY_EMAIL = "email"

const val MENU_DATA_URL = "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json" +
		" "

/**
 * Stores user data to SharedPreferences.
 *
 * @param context The application context.
 * @param firstName The user's first name.
 * @param lastName The user's last name
 * @param email The user's email
 * .
 */
fun saveUserData(context: Context, firstName: String, lastName: String, email: String) {
	val sharedPreferences : SharedPreferences = context.getSharedPreferences(KEY_USER_DATA, Context.MODE_PRIVATE)
	val editor = sharedPreferences.edit()

	editor.putString(KEY_FIRST_NAME, firstName)
	editor.putString(KEY_LAST_NAME, lastName)
	editor.putString(KEY_EMAIL, email)

	editor.apply()
}

/**
 * Retrieves user data from SharedPreferences.
 *
 * @param context The application context.
 * @return A [Triple] containing the user's first name, last name, and email.
 *         If any of the data is not available, the corresponding value in the [Triple] is `null`.
 */
fun getUserData(context: Context): Triple<String?, String?, String?> {
	val sharedPreferences : SharedPreferences = context.getSharedPreferences(KEY_USER_DATA, Context.MODE_PRIVATE)

	val firstName = sharedPreferences.getString(KEY_FIRST_NAME, null)
	val lastName = sharedPreferences.getString(KEY_LAST_NAME, null)
	val email = sharedPreferences.getString(KEY_EMAIL, null)

	return Triple(firstName, lastName, email)
}

fun clearUserData(context: Context) {
	val sharedPreferences : SharedPreferences = context.getSharedPreferences(KEY_USER_DATA, Context.MODE_PRIVATE)
	val editor = sharedPreferences.edit()

	editor.putString(KEY_FIRST_NAME, null)
	editor.putString(KEY_LAST_NAME, null)
	editor.putString(KEY_EMAIL, null)

	editor.apply()
}

/**
 * Checks for valid email.
 *
 * @param email The email string being checked.
 * @return A Boolean if the email is valid or not.
 */
fun isValidEmail(email: String) : Boolean {
	val emailRegex = Regex("^\\S+@\\S+\\.\\S+\$")
	return emailRegex.matches(email)
}