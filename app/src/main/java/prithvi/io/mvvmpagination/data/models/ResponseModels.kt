package prithvi.io.mvvmpagination.data.models

import com.squareup.moshi.Json

data class CharactersResponse(
        @Json(name = "code") val code: Int,
        @Json(name = "status") val status: String,
        @Json(name = "copyright") val copyright: String,
        @Json(name = "attributionText") val attributionText: String,
        @Json(name = "attributionHTML") val attributionHTML: String,
        @Json(name = "etag") val etag: String,
        @Json(name = "data") val data: CharactersDataResponse
)

data class CharactersDataResponse(
        @Json(name = "offset") val offset: Int,
        @Json(name = "limit") val limit: Int,
        @Json(name = "total") val total: Int,
        @Json(name = "count") val count: Int,
        @Json(name = "results") val results: List<Character>
)