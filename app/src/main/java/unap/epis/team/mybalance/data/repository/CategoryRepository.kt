package unap.epis.team.mybalance.data.repository

import unap.epis.team.mybalance.data.model.request.CreateCategoryRequest
import unap.epis.team.mybalance.data.model.response.CategoryResponse

interface CategoryRepository {
    suspend fun getCategories(): Result<List<CategoryResponse>>
    suspend fun newCategory(createCategoryRequest: CreateCategoryRequest): Result<CategoryResponse>
}