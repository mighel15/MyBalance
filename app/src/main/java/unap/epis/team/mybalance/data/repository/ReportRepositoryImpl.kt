package unap.epis.team.mybalance.data.repository

import unap.epis.team.mybalance.data.api.ReportApiService
import unap.epis.team.mybalance.data.model.response.ComparativeReportResponse
import unap.epis.team.mybalance.data.model.response.ReportByCategoryResponse
import unap.epis.team.mybalance.data.model.response.SummaryReportResponse
import javax.inject.Inject

class ReportRepositoryImpl @Inject constructor(private val reportApiService: ReportApiService) : ReportRepository {
    override suspend fun getSummaryReport(
        year: Int,
        month: Int
    ): Result<SummaryReportResponse> {
        return try {
            val response = reportApiService.getSummaryReport(year.toString() + " - " + "%02d".format(month))
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception("No se encontró lo solicitado"))
                }
            } else {
                Result.failure(Exception("Error al obtener reporte"))
            }
        } catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun getReportByCategory(
        year: Int,
        month: Int
    ): Result<List<ReportByCategoryResponse>> {
        return try {
            val response = reportApiService.getReportByCategory(year.toString() + " - " + "%02d".format(month))
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.success(emptyList())
                }
            } else {
                Result.failure(Exception("Error al obtener reporte"))
            }
        } catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun getComparativeReport(
        months: List<String>
    ): Result<List<ComparativeReportResponse>> {
        return try {
            val response = reportApiService.getComparativeReport(months)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.success(emptyList())
                }
            } else {
                Result.failure(Exception("Error al obtener reporte"))
            }
        } catch (e: Exception){
            Result.failure(e)
        }
    }


}