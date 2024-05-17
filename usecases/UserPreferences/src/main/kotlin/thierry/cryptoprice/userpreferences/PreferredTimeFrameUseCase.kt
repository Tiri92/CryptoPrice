package thierry.cryptoprice.userpreferences

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PreferredTimeFrameUseCase {
    suspend fun getPreferredTimeFrame(): Flow<String?>

    suspend fun setPreferredTimeFrame(preferredTimeFrame: String)
}

class PreferredTimeFrameUseCaseImpl @Inject constructor(
    private val preferredTimeFrameDataStoreRepository: PreferredTimeFrameDataStoreRepository,
) : PreferredTimeFrameUseCase {
    override suspend fun getPreferredTimeFrame(): Flow<String?> =
        preferredTimeFrameDataStoreRepository.getPreferredTimeFrame()

    override suspend fun setPreferredTimeFrame(preferredTimeFrame: String) =
        preferredTimeFrameDataStoreRepository.setPreferredTimeFrame(
            preferredTimeFrame
        )
}