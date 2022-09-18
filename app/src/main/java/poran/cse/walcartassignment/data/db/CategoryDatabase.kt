package poran.cse.walcartassignment.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import poran.cse.walcartassignment.domain.model.Category
import retrofit2.Converter

@Database(entities = [
    Category::class,
], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class CategoryDatabase: RoomDatabase() {
    abstract fun dao(): CategoryDao

    companion object {
        private const val DATABASE_NAME = "category-database"
        private var database: CategoryDatabase? = null

        fun create(applicationContext: Application): CategoryDatabase {
            if (database == null) {
                database = Room.databaseBuilder(
                    applicationContext,
                    CategoryDatabase::class.java, DATABASE_NAME
                ).build()
            }
            return database!!
        }
    }
}