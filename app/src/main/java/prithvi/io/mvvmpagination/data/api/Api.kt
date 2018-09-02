package prithvi.io.mvvmpagination.data.api

import io.reactivex.Single
import prithvi.io.mvvmpagination.data.models.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("/v1/public/characters")
    fun getMarvelCharacters(
            @Path("orderBy") orderBy: String,
            @Path("limit") limit: Int,
            @Path("offset") offset: Int,
            @Path("apikey") apikey: String
    ): Single<CharactersResponse>
}