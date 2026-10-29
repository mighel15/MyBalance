package unap.epis.team.mybalance.data.repository

import unap.epis.team.mybalance.data.model.request.TransactionRequest
import unap.epis.team.mybalance.data.model.response.TransactionResponse

interface TransactionRepository {
    suspend fun GetTransactions(): Result<List<TransactionResponse>>
    suspend fun NewTransaction(transactionRequest: TransactionRequest): Result<TransactionResponse>
    suspend fun DeleteTransaction(type: String, id: Int): Result<Boolean>
}