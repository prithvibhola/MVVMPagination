package prithvi.io.mvvmpagination.data.repository

import io.reactivex.Flowable
import prithvi.io.mvvmpagination.data.api.Api
import prithvi.io.mvvmpagination.data.models.Character
import prithvi.io.mvvmpagination.utility.extentions.toMD5
import java.security.NoSuchAlgorithmException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CharacterRepository @Inject constructor(
        private val api: Api
) {
    fun getMarvelCharacters(orderBy: String = "name", limit: Int = 10, offset: Int, apiKey: String = "55892510f2342c36cb6efafb6cae7aac"): Flowable<List<Character>> =
            api.getMarvelCharacters(orderBy,
                    limit,
                    offset,
                    System.currentTimeMillis(),
                    apiKey,
                    "${System.currentTimeMillis()}${apiKey}//PRIVATE_KEY".toMD5())
                    .map { it.data.results }
                    .toFlowable()
}