package thierry.cryptoprice.preferredcurrencyusecase

import app.cash.turbine.test
import app.cash.turbine.turbineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import thierry.cryptoprice.preferredcurrencyusecase.fake.FakePreferredCurrencyDataStoreRepository
import kotlin.test.assertEquals

class PreferredCurrencyUseCaseTest {

    @Test
    fun `preferredCurrencyUseCase should return preferredCurrency when repo Succeeded`() =
        runTest {
            //GIVEN
            val preferredCurrencyUseCase =
                PreferredCurrencyUseCaseImpl(preferredCurrencyDataStoreRepository = object :
                    FakePreferredCurrencyDataStoreRepository() {
                    override suspend fun getPreferredCurrency(): Flow<String?> =
                        MutableStateFlow("eur")
                })

            //WHEN
            val result = preferredCurrencyUseCase.getPreferredCurrency()

            //THEN
            turbineScope {
                result.test {
                    assertEquals("eur", awaitItem())
                }
            }
        }

    @Test
    fun `preferredCurrencyUseCase should return null when repo return null`() = runTest {
        //GIVEN
        val preferredCurrencyUseCase =
            PreferredCurrencyUseCaseImpl(preferredCurrencyDataStoreRepository = object :
                FakePreferredCurrencyDataStoreRepository() {
                override suspend fun getPreferredCurrency(): Flow<String?> =
                    MutableStateFlow(null)
            })

        //WHEN
        val result = preferredCurrencyUseCase.getPreferredCurrency()

        //THEN
        turbineScope {
            result.test {
                assertEquals(null, awaitItem())
            }
        }
    }

    @Test
    fun `preferredCurrencyUseCase should return new preferredCurrency when setPreferredCurrencyUseCase is called`() =
        runTest {
            //GIVEN
            var defaultPreferredCurrency = "eur"
            val preferredCurrencyUseCase =
                PreferredCurrencyUseCaseImpl(preferredCurrencyDataStoreRepository = object :
                    FakePreferredCurrencyDataStoreRepository() {
                    override suspend fun getPreferredCurrency(): Flow<String?> =
                        MutableStateFlow(defaultPreferredCurrency)

                    override suspend fun setPreferredCurrency(preferredCurrencyValue: String) {
                        defaultPreferredCurrency = preferredCurrencyValue
                    }
                })

            //WHEN
            preferredCurrencyUseCase.setPreferredCurrency("usd")
            val result = preferredCurrencyUseCase.getPreferredCurrency()

            //THEN
            turbineScope {
                result.test {
                    assertEquals("usd", awaitItem())
                }
            }
        }
}