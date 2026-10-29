package unap.epis.team.mybalance.data.repository

import unap.epis.team.mybalance.data.api.CategoryApiService
import unap.epis.team.mybalance.data.model.request.CreateCategoryRequest
import unap.epis.team.mybalance.data.model.response.CategoryResponse
import unap.epis.team.mybalance.util.getErrorMessage
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(private val categoryApiService: CategoryApiService) : CategoryRepository {

    override suspend fun getCategories(): Result<List<CategoryResponse>> {
        return try {
            val response = categoryApiService.getCategories()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.success(emptyList())
                }
            } else {
                Result.failure(Exception("Error al obtener las categorías"))
            }
        } catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun newCategory(createCategoryRequest: CreateCategoryRequest): Result<CategoryResponse> {
        return try {
            val response = categoryApiService.newCategory(createCategoryRequest)

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception("No se obtuvo respuesta"))
                }
            } else {
                Result.failure(Exception(response.getErrorMessage()))
            }
        } catch (e: Exception){
            Result.failure(e)
        }
    }


}