package unap.epis.team.mybalance.data.api

import retrofit2.Response
import retrofit2.http.GET
import unap.epis.team.mybalance.data.model.response.ComparativeReportResponse
import unap.epis.team.mybalance.data.model.response.ReportByCategoryResponse
import unap.epis.team.mybalance.data.model.response.SummaryReportResponse

interface ReportApiService {

    @GET("reportes/resumen")
    suspend fun getSummaryReport(month: String): Response<SummaryReportResponse>

    @GET("reportes/por-categoria")
    suspend fun getReportByCategory(month: String): Response<List<ReportByCategoryResponse>>

    @GET("reportes/compartivo")
    suspend fun getComparativeReport(months: List<String>): Response<List<ComparativeReportResponse>>

}