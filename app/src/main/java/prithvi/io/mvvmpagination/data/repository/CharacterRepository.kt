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
    companion object {
        const val PAGE_LIMIT = 10
    }

    fun getMarvelCharacters(orderBy: String = "name", offset: Int): Flowable<List<Character>> =
            api.getMarvelCharacters(orderBy, PAGE_LIMIT, offset, 1, context.resources.getString(R.string.marvel_public_key),
                    "1${context.resources.getString(R.string.marvel_private_key)}${context.resources.getString(R.string.marvel_public_key)}".toMD5())
                    .map { it.data.results }
                    .toFlowable()
}