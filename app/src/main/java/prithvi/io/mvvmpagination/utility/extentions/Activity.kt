package prithvi.io.mvvmpagination.utility.extentions

import android.app.Activity
import android.arch.lifecycle.*
import android.os.Handler
import android.support.v4.app.FragmentActivity
import android.support.v7.app.ActionBar

internal fun <T : ViewModel> FragmentActivity.getViewModel(modelClass: Class<T>, viewModelFactory: ViewModelProvider.Factory? = null): T {
    return viewModelFactory?.let { ViewModelProviders.of(this, it).get(modelClass) }
            ?: ViewModelProviders.of(this).get(modelClass)
}

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(this, Observer(body))
}

fun ActionBar.setScreenTitle(screenTitle: String, enableBack: Boolean = false) {
    this.apply {
        setDisplayHomeAsUpEnabled(enableBack)
        title = screenTitle
    }
}

fun Activity.withDelay(delay: Long = 100L, block: () -> Unit) = Handler().postDelayed(Runnable(block), delay)