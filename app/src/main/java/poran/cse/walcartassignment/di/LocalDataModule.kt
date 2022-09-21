package poran.cse.walcartassignment.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import poran.cse.walcartassignment.data.db.CategoryDao
import poran.cse.walcartassignment.data.repository.dataSource.LocalCategoryDataSource
import poran.cse.walcartassignment.data.repository.dataSourceImpl.LocalCategoryDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    @Provides
    fun provideLocalDataSource(categoryDao: CategoryDao): LocalCategoryDataSource =
        LocalCategoryDataSourceImpl(categoryDao = categoryDao)
}