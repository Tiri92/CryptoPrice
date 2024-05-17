package thierry.cryptoprice.bitcoininfo.fake

import kotlinx.coroutines.flow.Flow
import thierry.cryptoprice.preferredcurrencyusecase.PreferredCurrencyUseCase

open class FakePreferredCurrencyUseCase: PreferredCurrencyUseCase {
    override suspend fun getPreferredCurrency(): Flow<String?> {
        TODO("Not yet implemented")
    }

    override suspend fun setPreferredCurrency(preferredCurrency: String) {
        TODO("Not yet implemented")
    }
}