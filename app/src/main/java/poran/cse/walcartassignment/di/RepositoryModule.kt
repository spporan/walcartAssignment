package poran.cse.walcartassignment.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import poran.cse.walcartassignment.data.repository.CategoryRepositoryImpl
import poran.cse.walcartassignment.data.repository.dataSource.LocalCategoryDataSource
import poran.cse.walcartassignment.data.repository.dataSource.RemoteCategoryDataSource
import poran.cse.walcartassignment.domain.repository.CategoryRepository

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideCategoryRepository(
        remoteDataSource: RemoteCategoryDataSource,
        localDataSource: LocalCategoryDataSource
    ): CategoryRepository =
        CategoryRepositoryImpl(remoteDataSource, localDataSource)
}