package unap.epis.team.mybalance.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import unap.epis.team.mybalance.data.model.request.CopyBudgetRequest
import unap.epis.team.mybalance.data.model.request.CreateUpdateBudgetRequest
import unap.epis.team.mybalance.data.model.response.BudgetResponse

interface BudgetApiService {

    @GET("presupuestos")
    suspend fun getBudgets(fecha: String): Response<List<BudgetResponse>>

    @PUT("presupuestos")
    suspend fun newEditBudget(budget: CreateUpdateBudgetRequest):Response<Unit>

    @POST("presupuestos/copiar")
    suspend fun copyBudget(copyBudget: CopyBudgetRequest): Response<Unit>

}