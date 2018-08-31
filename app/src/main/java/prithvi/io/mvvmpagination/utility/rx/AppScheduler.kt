package prithvi.io.mvvmpagination.utility.rx

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import prithvi.io.mvvmpagination.utility.rx.Scheduler

class AppScheduler : Scheduler {
    override fun mainThread() = AndroidSchedulers.mainThread()
    override fun io() = Schedulers.io()
}