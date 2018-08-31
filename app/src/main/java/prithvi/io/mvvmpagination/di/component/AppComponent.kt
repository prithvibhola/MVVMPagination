package prithvi.io.mvvmpagination.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import prithvi.io.mvvmpagination.FlavourDI
import prithvi.io.mvvmpagination.MVVMApplication
import prithvi.io.mvvmpagination.di.module.ActivityModule
import prithvi.io.mvvmpagination.di.module.AppModule
import prithvi.io.mvvmpagination.di.module.NetModule
import prithvi.io.mvvmpagination.di.module.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    ActivityModule::class,
    AppModule::class,
    NetModule::class,
    ViewModelModule::class,
    FlavourDI::class
])
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(application: MVVMApplication)

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}