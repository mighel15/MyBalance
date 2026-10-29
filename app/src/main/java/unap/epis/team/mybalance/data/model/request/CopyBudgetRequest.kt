package unap.epis.team.mybalance.data.model.request

import com.google.gson.annotations.SerializedName

data class CopyBudgetRequest(

    @SerializedName("mesOrigen")
    val sourceMonth: String,

    @SerializedName("mesDestino")
    val destinationMonth: String,

)