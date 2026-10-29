package unap.epis.team.mybalance.data.model.response

import com.google.gson.annotations.SerializedName

data class SummaryReportResponse(
    @SerializedName("mes")
    val month: String,
    @SerializedName("ingresos")
    val incomes: Double,
    @SerializedName("gastos")
    val spends: Double,
    @SerializedName("balance")
    val balance: Double
)
