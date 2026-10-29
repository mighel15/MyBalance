package unap.epis.team.mybalance.data.model.response

import com.google.gson.annotations.SerializedName

data class ComparativeReportResponse(
    @SerializedName("mes")
    val month: String,
    @SerializedName("total")
    val total: Double,
)
