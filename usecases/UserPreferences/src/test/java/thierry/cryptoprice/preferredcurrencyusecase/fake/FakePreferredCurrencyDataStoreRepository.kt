package thierry.cryptoprice.preferredcurrencyusecase.fake

import kotlinx.coroutines.flow.Flow
import thierry.cryptoprice.userpreferences.PreferredCurrencyDataStoreRepository

open class FakePreferredCurrencyDataStoreRepository : PreferredCurrencyDataStoreRepository {
    override suspend fun getPreferredCurrency(): Flow<String?> {
        TODO("Not yet implemented")
    }

    override suspend fun setPreferredCurrency(preferredCurrencyValue: String) {
        TODO("Not yet implemented")
    }
}