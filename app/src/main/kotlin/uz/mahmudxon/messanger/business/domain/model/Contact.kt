package uz.mahmudxon.messanger.business.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Contact(
    @SerializedName("id")
    @Expose
    var userId: Long? = null,

    @SerializedName("lastname")
    @Expose
    var lastname: String? = null,

    @SerializedName("firstname")
    @Expose
    var firstname: String? = null,

    @SerializedName("phone")
    @Expose
    var phone: Long? = null
)