package unap.epis.team.mybalance.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import unap.epis.team.mybalance.data.model.request.CreateCategoryRequest
import unap.epis.team.mybalance.data.model.response.CategoryResponse

interface CategoryApiService {

    @GET("categorias")
    suspend fun getCategories(): Response<List<CategoryResponse>>
    @POST("categorias")
    suspend fun newCategory(createCategoryRequest: CreateCategoryRequest): Response<CategoryResponse>

}