package unap.epis.team.mybalance.data.api

import retrofit2.Response
import retrofit2.http.GET
import unap.epis.team.mybalance.data.model.response.CategoryResponse

interface CategoryApiService {

    @GET("categorias")
    suspend fun obtenerCategorias(): Response<List<CategoryResponse>>

}