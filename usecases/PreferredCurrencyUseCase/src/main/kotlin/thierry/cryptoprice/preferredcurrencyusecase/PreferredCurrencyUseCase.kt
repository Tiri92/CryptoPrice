package thierry.cryptoprice.preferredcurrencyusecase

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PreferredCurrencyUseCase {
    suspend fun getPreferredCurrencyUseCase(): Flow<String?>

    suspend fun setPreferredCurrencyUseCase(preferredCurrency: String)
}

class PreferredCurrencyUseCaseImpl @Inject constructor(
    private val preferredCurrencyDataStoreRepository: PreferredCurrencyDataStoreRepository,
) : PreferredCurrencyUseCase {
    override suspend fun getPreferredCurrencyUseCase(): Flow<String?> =
        preferredCurrencyDataStoreRepository.getPreferredCurrency()

    override suspend fun setPreferredCurrencyUseCase(preferredCurrency: String) =
        preferredCurrencyDataStoreRepository.setPreferredCurrency(
            preferredCurrency
        )
}