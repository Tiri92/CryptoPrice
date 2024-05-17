package thierry.cryptoprice.bitcoininfo.fake

import kotlinx.coroutines.flow.Flow
import thierry.cryptoprice.preferredcurrencyusecase.PreferredTimeFrameUseCase

open class FakePreferredTimeFrameUseCase : PreferredTimeFrameUseCase {
    override suspend fun getPreferredTimeFrame(): Flow<String?> {
        TODO("Not yet implemented")
    }

    override suspend fun setPreferredTimeFrame(preferredTimeFrame: String) {
        TODO("Not yet implemented")
    }
}