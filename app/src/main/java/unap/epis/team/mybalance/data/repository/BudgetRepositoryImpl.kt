package unap.epis.team.mybalance.data.repository

import unap.epis.team.mybalance.data.api.BudgetApiService
import unap.epis.team.mybalance.data.model.request.CopyBudgetRequest
import unap.epis.team.mybalance.data.model.request.CreateUpdateBudgetRequest
import unap.epis.team.mybalance.data.model.response.BudgetResponse
import unap.epis.team.mybalance.util.getErrorMessage
import javax.inject.Inject

class BudgetRepositoryImpl @Inject constructor(private val budgetApiService: BudgetApiService) : BudgetRepository {

    override suspend fun getBudgets(month:Int, year:Int): Result<List<BudgetResponse>> {
        return try {
            val response = budgetApiService.getBudgets(year.toString() + " - " + "%02d".format(month))
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.success(emptyList())
                }
            } else {
                Result.failure(Exception("Error al obtener presupuestos"))
            }
        } catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun newEditBudget(budget: CreateUpdateBudgetRequest): Result<Unit> {

        return try {
            val response = budgetApiService.newEditBudget(budget)

            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception(response.getErrorMessage()))
            }

        } catch (e: Exception){
            Result.failure(e)
        }

    }

    override suspend fun copyBudget(copyBudget: CopyBudgetRequest): Result<Unit> {
        return try {
            val response = budgetApiService.copyBudget(copyBudget)

            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception(response.getErrorMessage()))
            }

        } catch (e: Exception){
            Result.failure(e)
        }
    }

}