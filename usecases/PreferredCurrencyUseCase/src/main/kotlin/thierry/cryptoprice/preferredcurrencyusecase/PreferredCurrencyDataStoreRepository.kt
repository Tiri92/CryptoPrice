package thierry.cryptoprice.preferredcurrencyusecase

import kotlinx.coroutines.flow.Flow

interface PreferredCurrencyDataStoreRepository {
    suspend fun getPreferredCurrency(): Flow<String?>

    suspend fun setPreferredCurrency(preferredCurrencyValue: String)
}