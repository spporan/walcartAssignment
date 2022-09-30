package poran.cse.walcartassignment.data.repository

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import poran.cse.walcartassignment.data.api.FakeCategoryApi
import poran.cse.walcartassignment.data.createCategoryResponse
import poran.cse.walcartassignment.data.db.FakeCategoryDao
import poran.cse.walcartassignment.data.db.FakeRemoteKeyDao
import poran.cse.walcartassignment.data.repository.dataSource.LocalCategoryDataSource
import poran.cse.walcartassignment.data.repository.dataSource.RemoteCategoryDataSource
import poran.cse.walcartassignment.data.repository.dataSourceImpl.LocalCategoryDataSourceImpl
import poran.cse.walcartassignment.data.repository.dataSourceImpl.RemoteCategoryDataSourceImpl
import poran.cse.walcartassignment.domain.repository.CategoryRepository

@ExperimentalCoroutinesApi
@ExperimentalPagingApi
class CategoryRepositoryTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")


    private val coroutineDispatcher = TestCoroutineDispatcher()
    private lateinit var categoryDao: FakeCategoryDao
    private lateinit var categoryKeysDao: FakeRemoteKeyDao

    private lateinit var categoryApi: FakeCategoryApi
    private lateinit var remoteSource: RemoteCategoryDataSource
    private lateinit var localSource: LocalCategoryDataSource

    private val remoteCategoriesResponse = createCategoryResponse()
    private val domainCategories = remoteCategoriesResponse
        .data?.getCategories?.result?.categories ?: emptyList()

    private lateinit var categoryRepository: CategoryRepository

    @Before
    fun createRepository() =  coroutineDispatcher.runBlockingTest {
        Dispatchers.setMain(mainThreadSurrogate)

        categoryDao = FakeCategoryDao()
        categoryKeysDao = FakeRemoteKeyDao()
        localSource = LocalCategoryDataSourceImpl(categoryDao)
        categoryApi = FakeCategoryApi(remoteCategoriesResponse)
        remoteSource = RemoteCategoryDataSourceImpl(categoryApi, categoryDao, categoryKeysDao)
        categoryRepository = CategoryRepositoryImpl(remoteSource, localSource)
    }

    @Test
    fun loadCategories_returnsCorrect(): Unit = runBlocking {

        launch(Dispatchers.Main) {

            categoryRepository.getCategories().collect { pagingData ->
                val categories = pagingData.collectData()
                Log.e("collect", "categories $categories")
                //THEN: retrieved posts should be the remotePosts
                assertThat(categories, IsEqual(domainCategories))
            }

        }

    }

    suspend fun <T : Any> PagingData<T>.collectData(): List<T> {
        val dcb = object : DifferCallback {
            override fun onChanged(position: Int, count: Int) {}
            override fun onInserted(position: Int, count: Int) {}
            override fun onRemoved(position: Int, count: Int) {}
        }
        val items = mutableListOf<T>()
        val dif = object : PagingDataDiffer<T>(dcb, TestCoroutineDispatcher()) {
            override suspend fun presentNewList(
                previousList: NullPaddedList<T>,
                newList: NullPaddedList<T>,
                lastAccessedIndex: Int,
                onListPresentable: () -> Unit
            ): Int? {
                for (idx in 0 until newList.size)
                    items.add(newList.getFromStorage(idx))
                return null
            }
        }

        dif.collectFrom(this)
        return items
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }
}


