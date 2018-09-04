package prithvi.io.mvvmpagination.data.api

import io.reactivex.Single
import prithvi.io.mvvmpagination.data.models.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/v1/public/characters/")
    fun getMarvelCharacters(
            @Query("orderBy") orderBy: String,
            @Query("limit") limit: Int,
            @Query("offset") offset: Int,
            @Query("ts") ts: Long,
            @Query("apikey") apikey: String,
            @Query("hash") hash: String
    ): Single<CharactersResponse>
}