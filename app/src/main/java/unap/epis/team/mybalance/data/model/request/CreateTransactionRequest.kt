package unap.epis.team.mybalance.data.model.request

import com.google.gson.annotations.SerializedName

data class CreateTransactionRequest(
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
    val note: String
)
