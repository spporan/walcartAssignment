package poran.cse.walcartassignment.data.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import poran.cse.walcartassignment.domain.model.Category


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class CategoryDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: CategoryDatabase
    private lateinit var dao: CategoryDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CategoryDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.categoryDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    private fun mockCategories(): List<Category> {
        return listOf(
            Category("uid1", "attr",
                "categoryNameBn", "2345123456",
                "categoryNameEn", image = null, null,
                null, null, null, null),
            Category("uid2", "attr",
                "categoryNameBn", "2345123456",
                "categoryNameEn", image = null, null,
                null, null, null, null),
            Category("uid3", "attr",
                "categoryNameBn", "2345123456",
                "categoryNameEn", image = null, null,
                null, null, null, null)
        )
    }

    @Test
    fun addCategories() = runTest {
        dao.addCategories(mockCategories())


        val allCategoriesPagerItems = dao.getAllCategories()

        val actual = allCategoriesPagerItems.load(PagingSource.LoadParams.Refresh(null, 3,false))
        assertEquals(PagingSource.LoadResult.Page(mockCategories(), null, null), actual)
    }

    @Test
    fun deleteAllCategories() = runTest {
        dao.addCategories(mockCategories())

        val deleted = dao.deleteAllCategories()
        assertEquals(deleted, mockCategories().size)
    }
}

