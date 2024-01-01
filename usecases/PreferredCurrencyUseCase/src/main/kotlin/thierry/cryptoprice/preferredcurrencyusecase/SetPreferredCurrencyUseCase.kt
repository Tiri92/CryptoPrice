package thierry.cryptoprice.preferredcurrencyusecase

import javax.inject.Inject

class SetPreferredCurrencyUseCase @Inject constructor(
    private val preferredCurrencyDataStoreRepository: PreferredCurrencyDataStoreRepository,
) {
    suspend operator fun invoke(preferredCurrency: String) =
        preferredCurrencyDataStoreRepository.setPreferredCurrency(
            preferredCurrency
        )
}