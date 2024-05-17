package thierry.cryptoprice.bitcoininfo.injection

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import thierry.cryptoprice.getbitcoinpriceusecase.GetBitcoinPriceUseCase
import thierry.cryptoprice.getbitcoinpriceusecase.GetBitcoinPriceUseCaseImpl
import thierry.cryptoprice.preferredcurrencyusecase.PreferredCurrencyUseCase
import thierry.cryptoprice.preferredcurrencyusecase.PreferredCurrencyUseCaseImpl
import thierry.cryptoprice.preferredcurrencyusecase.PreferredTimeFrameUseCase
import thierry.cryptoprice.preferredcurrencyusecase.PreferredTimeFrameUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
interface BitcoinInfoModule {
    @Binds
    fun provideGetBitcoinPriceUseCase(impl: GetBitcoinPriceUseCaseImpl): GetBitcoinPriceUseCase

    @Binds
    fun providePreferredCurrencyUseCase(impl: PreferredCurrencyUseCaseImpl): PreferredCurrencyUseCase

    @Binds
    fun providePreferredTimeFrameUseCase(impl: PreferredTimeFrameUseCaseImpl): PreferredTimeFrameUseCase
}