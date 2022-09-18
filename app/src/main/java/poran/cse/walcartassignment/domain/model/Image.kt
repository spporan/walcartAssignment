package poran.cse.walcartassignment.domain.model


import android.os.Parcelable
import androidx.annotation.Keep
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Image(
    @Json(name = "name")
    val name: String?,
    @Json(name = "signedUrl")
    val signedUrl: String?,
    @Json(name = "url")
    val url: String?
): Parcelable