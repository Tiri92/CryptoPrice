package thierry.cryptoprice.bitcoininfo.fake

import kotlinx.coroutines.flow.Flow
import thierry.cryptoprice.preferredcurrencyusecase.PreferredCurrencyUseCase

open class FakePreferredCurrencyUseCase: PreferredCurrencyUseCase {
    override suspend fun getPreferredCurrencyUseCase(): Flow<String?> {
        TODO("Not yet implemented")
    }

    override suspend fun setPreferredCurrencyUseCase(preferredCurrency: String) {
        TODO("Not yet implemented")
    }
}