package prithvi.io.mvvmpagination.data.repository

import io.reactivex.Flowable
import prithvi.io.mvvmpagination.data.api.Api
import prithvi.io.mvvmpagination.data.models.Character
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepository @Inject constructor(
        private val api: Api
) {
    fun getMarvelCharacters(orderBy: String = "name", limit: Int = 10, offset: Int, apiKey: String = ""): Flowable<List<Character>> =
            api.getMarvelCharacters(orderBy, limit, offset, apiKey).map { it.data.results }.toFlowable()
}