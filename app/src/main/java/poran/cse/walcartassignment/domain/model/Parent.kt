package poran.cse.walcartassignment.domain.model


import android.os.Parcelable
import androidx.annotation.Keep
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Parent(
    @Json(name = "bnName")
    val bnName: String?,
    @Json(name = "enName")
    val enName: String?,
    @Json(name = "uid")
    val uid: String?
): Parcelable