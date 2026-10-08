package unap.epis.team.mybalance.data.model.request

import com.google.gson.annotations.SerializedName

data class CreateCategoryRequest(

    @SerializedName("id")
    val id: Int,

    @SerializedName("nombre")
    val name: String,

    @SerializedName("icono")
    val icon: String,

    @SerializedName("usuario_id")
    val userId: Int

)
