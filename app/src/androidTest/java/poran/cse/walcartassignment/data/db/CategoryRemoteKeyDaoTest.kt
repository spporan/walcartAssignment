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
import poran.cse.walcartassignment.domain.model.CategoriesRemoteKey
import poran.cse.walcartassignment.domain.model.Category


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class CategoryRemoteKeyDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: CategoryDatabase
    private lateinit var dao: CategoryRemoteKeyDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CategoryDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.remoteKeyDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    private fun mockCategoryRemoteKeys(): List<CategoriesRemoteKey> {
        return listOf(
            CategoriesRemoteKey("uid1", 0, 2, 0L),
            CategoriesRemoteKey("uid2", 1, 3, 0L)
        )
    }

    @Test
    fun addAllCategoryRemoteKeys() = runTest {
        dao.addAllCategoryRemoteKeys(mockCategoryRemoteKeys())

        val remoteKey = dao.getCategoryRemoteKeys(mockCategoryRemoteKeys().first().id)

        assertEquals( remoteKey, mockCategoryRemoteKeys().first())
    }

    @Test
    fun deleteAllRemoteKeys() = runTest {
        dao.addAllCategoryRemoteKeys(mockCategoryRemoteKeys())

        val deleted = dao.deleteAllCategoryRemoteKeys()
        assertEquals(deleted, mockCategoryRemoteKeys().size)
    }
}

