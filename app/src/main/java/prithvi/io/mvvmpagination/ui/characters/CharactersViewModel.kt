package prithvi.io.mvvmpagination.ui.characters

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.DataSource
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import prithvi.io.mvvmpagination.data.models.Character
import prithvi.io.mvvmpagination.data.models.Response
import prithvi.io.mvvmpagination.data.repository.Repository
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
        repository: Repository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    var characters: LiveData<PagedList<Character>>
    private var sourceFactory: CharactersDataSourceFactory
    private var pageSize = 10

    init {
        sourceFactory = CharactersDataSourceFactory(repository, compositeDisposable)
        characters = LivePagedListBuilder<Long, Character>(sourceFactory,
                PagedList.Config.Builder()
                        .setPageSize(pageSize)
                        .setInitialLoadSizeHint(pageSize * 2)
                        .setEnablePlaceholders(false)
                        .build())
                .build()
    }

    fun retry() = sourceFactory.charactersDataSource.value!!.retry()
    fun refresh() = sourceFactory.charactersDataSource.value!!.invalidate()
    fun getNetworkState(): LiveData<Response<Character>> = Transformations.switchMap<CharactersDataSource, Response<Character>>(sourceFactory.charactersDataSource) { it.networkState }
    fun getRefreshState(): LiveData<Response<List<Character>>> = Transformations.switchMap<CharactersDataSource, Response<List<Character>>>(sourceFactory.charactersDataSource) { it.initialLoad }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    open class CharactersDataSourceFactory(
            private val repository: Repository,
            private val compositeDisposable: CompositeDisposable
    ) : DataSource.Factory<Long, Character>() {

        val charactersDataSource = MutableLiveData<CharactersDataSource>()

        override fun create(): DataSource<Long, Character> {
            val dataSource = CharactersDataSource(repository, compositeDisposable)
            charactersDataSource.postValue(dataSource)
            return dataSource
        }
    }
}