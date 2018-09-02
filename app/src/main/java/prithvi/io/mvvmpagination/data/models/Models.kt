package prithvi.io.mvvmpagination.data.models

import com.squareup.moshi.Json

data class Response<out T>(
        val status: Status,
        val data: T? = null,
        val error: Throwable? = null
) {
    enum class Status { LOADING, SUCCESS, ERROR }

    companion object {
        fun <T> loading(): Response<T> = Response(Status.LOADING, null, null)
        fun <T> success(data: T? = null): Response<T> = Response(Status.SUCCESS, data, null)
        fun <T> error(error: Throwable): Response<T> = Response(Status.ERROR, null, error)
    }
}

data class Character(
        @Json(name = "id") val id: Long,
        @Json(name = "name") val name: String,
        @Json(name = "description") val description: String,
        @Json(name = "modified") val modified: String,
        @Json(name = "thumbnail") val thumbnail: Thumbnail,
        @Json(name = "resourceURI") val resourceURI: String,
        @Json(name = "comics") val comics: Comics,
        @Json(name = "series") val series: Series,
        @Json(name = "stories") val stories: Stories,
        @Json(name = "events") val events: Events,
        @Json(name = "urls") val urls: List<Urls>
)

data class Thumbnail(
        @Json(name = "path") val path: String,
        @Json(name = "extension") val extension: String
)

data class Comics(
        @Json(name = "available") val available: Int,
        @Json(name = "collectionURI") val collectionURI: String,
        @Json(name = "items") val items: List<ComicsItems>,
        @Json(name = "returned") val returned: Int
)

data class ComicsItems(
        @Json(name = "resourceURI") val resourceURI: String,
        @Json(name = "name") val name: String
)

data class Series(
        @Json(name = "available") val available: Int,
        @Json(name = "collectionURI") val collectionURI: String,
        @Json(name = "items") val items: List<SeriesItems>,
        @Json(name = "returned") val returned: Int
)

data class SeriesItems(
        @Json(name = "resourceURI") val resourceURI: String,
        @Json(name = "name") val name: String
)

data class Stories(
        @Json(name = "available") val available: Int,
        @Json(name = "collectionURI") val collectionURI: String,
        @Json(name = "items") val items: List<StoriesItems>,
        @Json(name = "returned") val returned: Int
)

data class StoriesItems(
        @Json(name = "resourceURI") val resourceURI: String,
        @Json(name = "name") val name: String,
        @Json(name = "type") val type: String
)

data class Events(
        @Json(name = "available") val available: Int,
        @Json(name = "collectionURI") val collectionURI: String,
        @Json(name = "items") val items: List<EventsItems>,
        @Json(name = "returned") val returned: Int
)

data class EventsItems(
        @Json(name = "resourceURI") val resourceURI: String,
        @Json(name = "name") val name: String
)

data class Urls(
        @Json(name = "type") val type: String,
        @Json(name = "url") val url: String
)