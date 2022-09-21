package poran.cse.walcartassignment.domain.model


import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import poran.cse.walcartassignment.data.dto.Image
import poran.cse.walcartassignment.data.dto.Parent

@Keep
@Parcelize
@Entity(
    tableName = "categories",
)
data class Category(
    @PrimaryKey @ColumnInfo(name = "id")
    @Json(name = "uid")
    val uid: String,
    @Json(name = "attributeSetUid")
    val attributeSetUid: String?,
    @Json(name = "bnName")
    val bnName: String?,
    @Json(name = "createdAt")
    val createdAt: String?,
    @Json(name = "enName")
    val enName: String?,
    @Json(name = "image")
    val image: Image?,
    @Json(name = "inActiveNote")
    val inActiveNote: String?,
    @Json(name = "isActive")
    val isActive: Boolean?,
    @Json(name = "parent")
    val parent: Parent?,
    @Json(name = "parents")
    val parents: List<Parent>?,
    @Json(name = "updatedAt")
    val updatedAt: String?
): Parcelable