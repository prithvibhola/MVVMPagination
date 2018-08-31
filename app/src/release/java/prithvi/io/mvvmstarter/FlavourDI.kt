package prithvi.io.mvvmpagination

import dagger.Module

@Module
class FlavourDI {

    @Provides
    @Singleton
    fun provideOkHttpClientBuilder(cache: Cache) = OkHttpClient.Builder().cache(cache)
}