package unap.epis.team.mybalance.data.model.request

import com.google.gson.annotations.SerializedName

data class CreateUpdateBudgetRequest(

    @SerializedName("mes")
    val month: String,

    @SerializedName("limites")
    val limits: Map<String, Double>

)

