package prithvi.io.mvvmpagination.data.repository

import android.content.Context
import io.reactivex.Flowable
import prithvi.io.mvvmpagination.R
import prithvi.io.mvvmpagination.data.api.Api
import prithvi.io.mvvmpagination.data.models.Character
import prithvi.io.mvvmpagination.utility.extentions.toMD5
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CharacterRepository @Inject constructor(
        private val api: Api,
        private val context: Context
) {
    fun getMarvelCharacters(orderBy: String = "name", limit: Int = 10, offset: Int, apiKey: String = "55892510f2342c36cb6efafb6cae7aac"): Flowable<List<Character>> =
            api.getMarvelCharacters(orderBy,
                    limit,
                    offset,
                    System.currentTimeMillis(),
                    apiKey,
                    "${System.currentTimeMillis()}${apiKey}${context.resources.getString(R.string.marvel_private_key)}".toMD5())
                    .map { it.data.results }
                    .toFlowable()
}