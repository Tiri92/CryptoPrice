package thierry.cryptoprice.userpreferences

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PreferredCurrencyUseCase {
    suspend fun getPreferredCurrency(): Flow<String?>

    suspend fun setPreferredCurrency(preferredCurrency: String)
}

class PreferredCurrencyUseCaseImpl @Inject constructor(
    private val preferredCurrencyDataStoreRepository: PreferredCurrencyDataStoreRepository,
) : PreferredCurrencyUseCase {
    override suspend fun getPreferredCurrency(): Flow<String?> =
        preferredCurrencyDataStoreRepository.getPreferredCurrency()

    override suspend fun setPreferredCurrency(preferredCurrency: String) =
        preferredCurrencyDataStoreRepository.setPreferredCurrency(
            preferredCurrency
        )
}