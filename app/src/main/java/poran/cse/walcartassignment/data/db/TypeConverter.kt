package poran.cse.walcartassignment.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import poran.cse.walcartassignment.data.dto.Image
import poran.cse.walcartassignment.data.dto.Parent

class TypeConverter {

    @TypeConverter
    fun fromImage(image:  Image?): String {
        return Gson().toJson(image)
    }

    @TypeConverter
    fun toImage(jsonString: String?) : Image? {
        if (jsonString == "null")
            return null
        return Gson().fromJson(jsonString, Image::class.java)
    }

    @TypeConverter
    fun fromParent(parent: Parent?): String {
        return Gson().toJson(parent)
    }

    @TypeConverter
    fun toParent(jsonString: String?) : Parent? {
        if (jsonString == "null")
            return null
        return Gson().fromJson(jsonString, Parent::class.java)
    }

    @TypeConverter
    fun fromParents(parentList:  List<Parent?>?): String {
        return Gson().toJson(parentList)
    }

    @TypeConverter
    fun toParents(jsonString: String?) : List<Parent?>? {
        if (jsonString == "null")
            return null
        return Gson().fromJson(jsonString, Array<Parent>::class.java).toList()
    }

}