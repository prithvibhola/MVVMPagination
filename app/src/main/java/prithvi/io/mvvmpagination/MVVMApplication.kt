package prithvi.io.mvvmpagination

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import prithvi.io.mvvmpagination.di.component.DaggerAppComponent
import prithvi.io.mvvmpagination.utility.logging.CrashReportingTree
import timber.log.Timber

class MVVMApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(if (BuildConfig.DEBUG) Timber.DebugTree() else CrashReportingTree())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
        return appComponent
    }
}
