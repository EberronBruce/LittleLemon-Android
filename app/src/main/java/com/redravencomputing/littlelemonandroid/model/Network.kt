package com.redravencomputing.littlelemonandroid.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuNetworkData(
	val menu : List<MenuItemNetwork>
)

@Serializable
data class MenuItemNetwork(

	@SerialName("id")
	val id : Int,

	@SerialName("title")
	val title : String,

	@SerialName("price")
	val price : String,

	@SerialName("description")
	val description : String,

	@SerialName("category")
	val category : String,

	@SerialName("image")
	val imageUrl : String

) {
	fun toMenuItemRoom() = MenuItemRoom(
		id = id,
		title = title,
		price = price.toDouble(),
		description = description,
		category = category,
		imageUrl = imageUrl
	)
}