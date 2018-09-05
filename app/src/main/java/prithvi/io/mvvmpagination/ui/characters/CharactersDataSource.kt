package prithvi.io.mvvmpagination.ui.characters

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.ItemKeyedDataSource
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import prithvi.io.mvvmpagination.data.models.Character
import prithvi.io.mvvmpagination.data.models.Response
import prithvi.io.mvvmpagination.data.repository.CharacterRepository
import prithvi.io.mvvmpagination.data.repository.Repository
import timber.log.Timber

class CharactersDataSource(
        private val repository: Repository,
        private val compositeDisposable: CompositeDisposable
) : ItemKeyedDataSource<Long, Character>() {

    val networkState = MutableLiveData<Response<Character>>()
    val initialLoad = MutableLiveData<Response<List<Character>>>()
    var page = 0

    private var retryCompletable: Completable? = null

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Character>) {
        networkState.postValue(Response.loading())
        initialLoad.postValue(Response.loading())
        compositeDisposable.add(
                repository.character.getMarvelCharacters(offset = page)
                        .subscribeBy(
                                onNext = {
                                    page += CharacterRepository.PAGE_LIMIT
                                    networkState.postValue(Response.success(null))
                                    initialLoad.postValue(Response.success(it))
                                    callback.onResult(it)
                                },
                                onError = {
                                    setRetry(Action { loadInitial(params, callback) })
                                    networkState.postValue(Response.error(it))
                                    initialLoad.postValue(Response.error(it))
                                    Timber.e(it, "Error in getting bonus")
                                })
        )
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Character>) {
        networkState.postValue(Response(Response.Status.LOADING, null, null))
        compositeDisposable.add(
                repository.character.getMarvelCharacters(offset = page)
                        .subscribeBy(
                                onNext = {
                                    page += CharacterRepository.PAGE_LIMIT
                                    networkState.postValue(Response.success(null))
                                    callback.onResult(it)
                                },
                                onError = {
                                    setRetry(Action { loadAfter(params, callback) })
                                    networkState.postValue(Response.error(it))
                                    Timber.e(it, "Error in getting bonus")
                                })
        )
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Character>) {}

    override fun getKey(item: Character) = item.id

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ }, { throwable -> Timber.e(throwable.message) }))
        }
    }

    private fun setRetry(action: Action?) {
        if (action == null) {
            this.retryCompletable = null
        } else {
            this.retryCompletable = Completable.fromAction(action)
        }
    }
}