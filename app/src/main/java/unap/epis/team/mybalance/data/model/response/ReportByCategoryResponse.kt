package unap.epis.team.mybalance.data.model.response

import com.google.gson.annotations.SerializedName

data class ReportByCategoryResponse (

    @SerializedName("categoria_id")
    val categoryId: Int,

    @SerializedName("categoria_nombre")
    val categoryName: String,

    @SerializedName("total")
    val total: Double,

)