package de.arztdata.app.data

import com.google.gson.annotations.SerializedName
import android.os.Parcelable

data class Data(
    @SerializedName("category") val category: List<CategoryItem>,
)

data class CategoryItem(
    @SerializedName("name") val name: String = "",
    @SerializedName("subcategory") val subcategory: List<SubcategoryItem>?,
)

data class AddressContact(
    @SerializedName("address") val address: String = "",
    @SerializedName("phoneNumber")val phoneNumber: String = "",
    @SerializedName("email") val email: String = "",
)

data class SubcategoryItem(
    @SerializedName("entry") val entry: List<EntryItem>?,
    @SerializedName("name") val name: String = "",
)

data class EntryItem(
    @SerializedName("imageUrl") val imageUrl: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("link") val link: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("youtubeVideoLink") val youtubeVideoLink: String = "",
    @SerializedName("job") val job: String = "",
    @SerializedName("addressContact") val addressContact: AddressContact,
)