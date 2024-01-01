package thierry.cryptoprice.preferredcurrencyusecase

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPreferredCurrencyUseCase @Inject constructor(
    private val preferredCurrencyDataStoreRepository: PreferredCurrencyDataStoreRepository,
) {
    suspend operator fun invoke(): Flow<String?> =
        preferredCurrencyDataStoreRepository.getPreferredCurrency()
}