package poran.cse.walcartassignment.data.paging

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import poran.cse.walcartassignment.data.api.CategoriesApi
import poran.cse.walcartassignment.data.repository.dataSource.RemoteCategoryDataSource

@OptIn(ExperimentalCoroutinesApi::class)
class CategoryRemoteMediatorTest{
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var api: CategoriesApi

    lateinit var pagingSourceRemote: RemoteCategoryDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}