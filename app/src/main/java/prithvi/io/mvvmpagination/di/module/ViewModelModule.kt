package prithvi.io.mvvmpagination.di.module

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import prithvi.io.mvvmpagination.viewmodel.ViewModelFactory

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

//    @Binds
//    @IntoMap
//    @ViewModelKey(::class)
//    internal abstract fun (viewModel: ): ViewModel
}