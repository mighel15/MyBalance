package unap.epis.team.mybalance.data.repository

import unap.epis.team.mybalance.data.model.response.CategoryResponse

interface CategoryRepository {
    suspend fun getCategories(): List<CategoryResponse>
    suspend fun newCategory(): CategoryResponse
}