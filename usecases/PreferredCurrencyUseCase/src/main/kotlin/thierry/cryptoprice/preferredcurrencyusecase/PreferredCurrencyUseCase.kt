package thierry.cryptoprice.preferredcurrencyusecase

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PreferredCurrencyUseCase @Inject constructor(
    private val preferredCurrencyDataStoreRepository: PreferredCurrencyDataStoreRepository,
) {
    suspend fun getPreferredCurrencyUseCase(): Flow<String?> =
        preferredCurrencyDataStoreRepository.getPreferredCurrency()

    suspend fun setPreferredCurrencyUseCase(preferredCurrency: String) =
        preferredCurrencyDataStoreRepository.setPreferredCurrency(
            preferredCurrency
        )
}