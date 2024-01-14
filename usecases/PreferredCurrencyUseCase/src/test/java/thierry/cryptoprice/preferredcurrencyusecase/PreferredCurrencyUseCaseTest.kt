package thierry.cryptoprice.preferredcurrencyusecase

import app.cash.turbine.test
import app.cash.turbine.turbineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import thierry.cryptoprice.preferredcurrencyusecase.fake.FakePreferredCurrencyDataStoreRepository
import kotlin.test.assertEquals

class PreferredCurrencyUseCaseTest { //TODO Add test for setPreferredCurrency function

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
            val result = preferredCurrencyUseCase.getPreferredCurrencyUseCase()

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
        val result = preferredCurrencyUseCase.getPreferredCurrencyUseCase()

        //THEN
        turbineScope {
            result.test {
                assertEquals(null, awaitItem())
            }
        }
    }
}