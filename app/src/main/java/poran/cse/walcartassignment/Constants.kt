package poran.cse.walcartassignment

import android.util.Log
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

object Constants {
    fun getQueryBody(page: Int): RequestBody {
        val queryStr =  """
            {
  getCategories(pagination: { limit: ${page}, skip: 0 }) {
    result {
      categories {
        uid
        enName
        bnName
        parent {
          uid
          enName
          bnName
        }
        parents {
          uid
          enName
          bnName
        }
        image {
          name
          url
          signedUrl
        }
        attributeSetUid
        isActive
        inActiveNote
        createdAt
        updatedAt
      }
    }
  }
}""".trimIndent()
        val paramObject = JSONObject()
        paramObject.put("query", queryStr)
        Log.e("API", "query body  ${paramObject}")
        return createRequestBody(Pair<String,String>("query", queryStr))

    }
    private fun createRequestBody(vararg params : Pair<String, Any>) =
        JSONObject(mapOf(*params)).toString()
            .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
}