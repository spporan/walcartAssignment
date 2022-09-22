package poran.cse.walcartassignment

import com.google.gson.Gson
import org.json.JSONObject

object Constants {
    fun getQueryBody(page: Int): String {
        val queryStr =  """
            query{
              getCategories(
                pagination: { limit: 10, skip: 0 }

                filter: { uid: \"C-C4PAEU\" }
              ) {
                result {
                  categories {
                    uid
                    bnName
                    enName
                    parent {
                      uid
                      bnName
                      enName
                    }
                    parents {
                      uid
                      bnName
                      enName
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
            }
        """.trimIndent()
        val paramObject = JSONObject()
        paramObject.put("query", queryStr)
        return paramObject.toString()

    }
}