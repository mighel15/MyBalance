package unap.epis.team.mybalance.data.repository

import unap.epis.team.mybalance.data.api.TransactionApiService
import unap.epis.team.mybalance.data.model.request.TransactionRequest
import unap.epis.team.mybalance.data.model.response.TransactionResponse
import unap.epis.team.mybalance.util.getErrorMessage
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(private val transactionApiService: TransactionApiService) : TransactionRepository {
    override suspend fun GetTransactions(): Result<List<TransactionResponse>> {
        return try {
            val response = transactionApiService.getTransactions()
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

    override suspend fun NewTransaction(transactionRequest: TransactionRequest): Result<TransactionResponse> {
        return try {
            val response = transactionApiService.newTransaction(transactionRequest)

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

    override suspend fun DeleteTransaction(
        type: String,
        id: Int
    ): Result<Boolean> {
        return try {
            val response = transactionApiService.deleteTransaction(type,id)

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