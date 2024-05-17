package thierry.cryptoprice.bitcoininfo.injection

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import thierry.cryptoprice.getbitcoinpriceusecase.GetBitcoinPriceUseCase
import thierry.cryptoprice.getbitcoinpriceusecase.GetBitcoinPriceUseCaseImpl
import thierry.cryptoprice.userpreferences.PreferredCurrencyUseCase
import thierry.cryptoprice.userpreferences.PreferredCurrencyUseCaseImpl
import thierry.cryptoprice.userpreferences.PreferredTimeFrameUseCase
import thierry.cryptoprice.userpreferences.PreferredTimeFrameUseCaseImpl

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