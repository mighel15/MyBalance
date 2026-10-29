package unap.epis.team.mybalance.data.api

import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import unap.epis.team.mybalance.data.model.request.TransactionRequest
import unap.epis.team.mybalance.data.model.response.TransactionResponse

interface TransactionApiService {

    @GET("movimientos")
    suspend fun getTransactions(): Response<List<TransactionResponse>>

    @POST("movimientos")
    suspend fun newTransaction(transactionRequest: TransactionRequest): Response<TransactionResponse>

    @DELETE("movimientos/{type}/{id}")
    suspend fun deleteTransaction(type: String, id: Int):Response<Boolean>

}