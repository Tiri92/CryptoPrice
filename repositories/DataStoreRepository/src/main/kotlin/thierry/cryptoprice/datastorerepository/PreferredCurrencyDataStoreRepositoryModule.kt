package thierry.cryptoprice.datastorerepository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import thierry.cryptoprice.preferredcurrencyusecase.PreferredCurrencyDataStoreRepository

@Module
@InstallIn(SingletonComponent::class)
internal interface PreferredCurrencyDataStoreRepositoryModule {

    @Binds
    fun providePreferredCurrencyDataStoreRepository(impl: PreferredCurrencyDataStoreRepositoryImpl): PreferredCurrencyDataStoreRepository
}