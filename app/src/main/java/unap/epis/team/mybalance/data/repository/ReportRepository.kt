package unap.epis.team.mybalance.data.repository

import unap.epis.team.mybalance.data.model.response.ComparativeReportResponse
import unap.epis.team.mybalance.data.model.response.ReportByCategoryResponse
import unap.epis.team.mybalance.data.model.response.SummaryReportResponse

interface ReportRepository {
    suspend fun getSummaryReport(year:Int, month: Int): Result<SummaryReportResponse>
    suspend fun getReportByCategory(year:Int, month: Int): Result<List<ReportByCategoryResponse>>
    suspend fun getComparativeReport(months: List<String>): Result<List<ComparativeReportResponse>>
}