package com.redravencomputing.littlelemonandroid.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

@Entity
data class MenuItemRoom(
	@PrimaryKey val id : Int,
	val title : String,
	val price : Double,
	val description : String,
	val category : String,
	val imageUrl : String
)

@Dao
interface MenuItemDao {
	@Query("SELECT * FROM MenuItemRoom")
	fun getAll(): LiveData<List<MenuItemRoom>>

	@Query("SELECT category FROM MenuItemRoom")
	fun getCategories(): LiveData<List<String>>

	@Insert
	fun insertAll(vararg menuItems: MenuItemRoom)

	@Query("SELECT (SELECT COUNT(*) FROM MenuItemRoom) == 0")
	fun isEmpty(): Boolean

	@Query("DELETE FROM MenuItemRoom")
	fun deleteAll()
}

@Database(entities = [MenuItemRoom::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
	abstract fun menuItemDao(): MenuItemDao
}

object MenuDatabase {
	private var instance: AppDatabase? = null

	fun getInstance(context: Context): AppDatabase {
		return instance ?: synchronized(this) {
			instance ?: buildDatabase(context).also { instance = it }
		}
	}

	private fun buildDatabase(context: Context): AppDatabase {
		return Room.databaseBuilder(
			context.applicationContext,
			AppDatabase::class.java,
			"database"
		).build()
	}
}