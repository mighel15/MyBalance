package unap.epis.team.mybalance.data.model.response

import com.google.gson.annotations.SerializedName

data class BudgetResponse(
    @SerializedName("categoria_id")
    val categoryId: Int,
    @SerializedName("categoria_nombre")
    val categoryName: String,
    @SerializedName("monto_limite")
    val limitAmount: Double,
    @SerializedName("monto_gastado")
    val spentAmount: Double,
)
