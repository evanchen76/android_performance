package evan.chen.tutorial.arch.roomcoroutinesample.data

import kotlinx.coroutines.flow.Flow

interface IAccountCategoryRepository{
    suspend fun getCategoryListByType(type: String): Flow<List<AccountCategory>>
    suspend fun insert(category: AccountCategory)
}

class AccountCategoryRepository(private val dao: AccountCategoryDao) : IAccountCategoryRepository {

    override suspend fun getCategoryListByType(type: String): Flow<List<AccountCategory>> {
        return dao.getCategory(type)
    }

    override suspend fun insert(category: AccountCategory) {
        dao.insertCategory(category)
    }

}
