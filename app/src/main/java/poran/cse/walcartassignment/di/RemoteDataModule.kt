package poran.cse.walcartassignment.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import poran.cse.walcartassignment.data.api.CategoriesApi
import poran.cse.walcartassignment.data.db.CategoryDatabase
import poran.cse.walcartassignment.data.repository.dataSource.RemoteCategoryDataSource
import poran.cse.walcartassignment.data.repository.dataSourceImpl.RemoteCategoryDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {
    @Provides
    fun provideRemoteDataSource(categoryApi: CategoriesApi, db: CategoryDatabase) : RemoteCategoryDataSource =
        RemoteCategoryDataSourceImpl(categoryApi, categoryDao = db.categoryDao(), remoteKeyDao = db.remoteKeyDao())
}