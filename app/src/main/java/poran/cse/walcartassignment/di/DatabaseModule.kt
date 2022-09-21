package poran.cse.walcartassignment.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import poran.cse.walcartassignment.data.db.CategoryDao
import poran.cse.walcartassignment.data.db.CategoryDatabase
import poran.cse.walcartassignment.data.db.CategoryRemoteKeyDao

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(app: Application): CategoryDatabase =
        Room.databaseBuilder(app, CategoryDatabase::class.java, CategoryDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideCategoryDao(db: CategoryDatabase) : CategoryDao= db.categoryDao()

    @Provides
    fun provideCategoryRemoteKeysDao(db: CategoryDatabase) : CategoryRemoteKeyDao = db.remoteKeyDao()
}