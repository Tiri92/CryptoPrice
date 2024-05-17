package thierry.cryptoprice.preferredcurrencyusecase.fake

import kotlinx.coroutines.flow.Flow
import thierry.cryptoprice.preferredcurrencyusecase.PreferredTimeFrameDataStoreRepository

open class FakePreferredTimeFrameDataStoreRepository: PreferredTimeFrameDataStoreRepository {
    override suspend fun getPreferredTimeFrame(): Flow<String?> {
        TODO("Not yet implemented")
    }

    override suspend fun setPreferredTimeFrame(preferredTimeFrameValue: String) {
        TODO("Not yet implemented")
    }
}