package thierry.cryptoprice.datastorerepository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import thierry.cryptoprice.userpreferences.PreferredCurrencyDataStoreRepository
import thierry.cryptoprice.userpreferences.PreferredTimeFrameDataStoreRepository

@Module
@InstallIn(SingletonComponent::class)
internal interface DataStoreRepositoryModule {

    @Binds
    fun providePreferredCurrencyDataStoreRepository(impl: PreferredCurrencyDataStoreRepositoryImpl): PreferredCurrencyDataStoreRepository

    @Binds
    fun providePreferredTimeFrameDataStoreRepository(impl: PreferredTimeFrameDataStoreRepositoryImpl): PreferredTimeFrameDataStoreRepository
}