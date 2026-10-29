package unap.epis.team.mybalance.data.repository

import unap.epis.team.mybalance.data.model.request.CopyBudgetRequest
import unap.epis.team.mybalance.data.model.request.CreateUpdateBudgetRequest
import unap.epis.team.mybalance.data.model.response.BudgetResponse

interface BudgetRepository {

    suspend fun getBudgets(year: Int, month: Int): Result<List<BudgetResponse>>
    suspend fun newEditBudget(budget: CreateUpdateBudgetRequest): Result<Unit>
    suspend fun copyBudget(copyBudget: CopyBudgetRequest): Result<Unit>

}