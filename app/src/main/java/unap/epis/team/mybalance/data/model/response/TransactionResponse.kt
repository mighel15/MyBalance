package unap.epis.team.mybalance.data.model.response

import com.google.gson.annotations.SerializedName

data class TransactionResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("tipo")
    val type: String,
    @SerializedName("monto")
    val amount: Double,
    @SerializedName("fecha")
    val date: String,
    @SerializedName("categoria_id")
    val categoryId: Int,
    @SerializedName("fuente")
    val source: Int,
    @SerializedName("nota")
    val note: String,
)
