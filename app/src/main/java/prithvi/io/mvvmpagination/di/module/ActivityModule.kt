package prithvi.io.mvvmpagination.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import prithvi.io.mvvmpagination.di.ActivityScoped
import prithvi.io.mvvmpagination.ui.characters.CharacterActivity

@Module
abstract class ActivityModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [])
    abstract fun charactersActivity(): CharacterActivity

}