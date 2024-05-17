package thierry.cryptoprice.preferredcurrencyusecase

import app.cash.turbine.test
import app.cash.turbine.turbineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import thierry.cryptoprice.preferredcurrencyusecase.fake.FakePreferredTimeFrameDataStoreRepository
import thierry.cryptoprice.userpreferences.PreferredTimeFrameUseCaseImpl
import kotlin.test.assertEquals

class PreferredTimeFrameUseCaseTest {

    @Test
    fun `preferredTimeFrameUseCase should return preferredTimeFrame when repo Succeeded`() =
        runTest {
            //GIVEN
            val preferredTimeFrameUseCase =
                PreferredTimeFrameUseCaseImpl(preferredTimeFrameDataStoreRepository = object :
                    FakePreferredTimeFrameDataStoreRepository() {
                    override suspend fun getPreferredTimeFrame(): Flow<String?> =
                        MutableStateFlow("2w")
                })

            //WHEN
            val result = preferredTimeFrameUseCase.getPreferredTimeFrame()

            //THEN
            turbineScope {
                result.test {
                    assertEquals("2w", awaitItem())
                }
            }
        }

    @Test
    fun `preferredTimeFrameUseCase should return null when repo return null`() = runTest {
        //GIVEN
        val preferredTimeFrameUseCase =
            PreferredTimeFrameUseCaseImpl(preferredTimeFrameDataStoreRepository = object :
                FakePreferredTimeFrameDataStoreRepository() {
                override suspend fun getPreferredTimeFrame(): Flow<String?> =
                    MutableStateFlow(null)
            })

        //WHEN
        val result = preferredTimeFrameUseCase.getPreferredTimeFrame()

        //THEN
        turbineScope {
            result.test {
                assertEquals(null, awaitItem())
            }
        }
    }

    @Test
    fun `preferredTimeFrameUseCase should return new preferredTimeFrame when setPreferredTimeFrameUseCase is called`() =
        runTest {
            //GIVEN
            var defaultPreferredTimeFrame = "2w"
            val preferredTimeFrameUseCase =
                PreferredTimeFrameUseCaseImpl(preferredTimeFrameDataStoreRepository = object :
                    FakePreferredTimeFrameDataStoreRepository() {
                    override suspend fun getPreferredTimeFrame(): Flow<String?> =
                        MutableStateFlow(defaultPreferredTimeFrame)

                    override suspend fun setPreferredTimeFrame(preferredTimeFrameValue: String) {
                        defaultPreferredTimeFrame = preferredTimeFrameValue
                    }
                })

            //WHEN
            preferredTimeFrameUseCase.setPreferredTimeFrame("1w")
            val result = preferredTimeFrameUseCase.getPreferredTimeFrame()

            //THEN
            turbineScope {
                result.test {
                    assertEquals("1w", awaitItem())
                }
            }
        }
}