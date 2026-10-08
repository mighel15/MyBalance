package unap.epis.team.mybalance.data.repository

import unap.epis.team.mybalance.data.api.CategoryApiService
import unap.epis.team.mybalance.data.model.response.CategoryResponse
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(private val apiService: CategoryApiService) : CategoryRepository {
    override suspend fun getCategories(): List<CategoryResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun newCategory(): CategoryResponse {
        TODO("Not yet implemented")
    }

}