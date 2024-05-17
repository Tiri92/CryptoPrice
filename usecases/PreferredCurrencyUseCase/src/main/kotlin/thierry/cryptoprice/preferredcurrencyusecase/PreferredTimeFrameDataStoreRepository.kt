package thierry.cryptoprice.preferredcurrencyusecase

import kotlinx.coroutines.flow.Flow

interface PreferredTimeFrameDataStoreRepository {
    suspend fun getPreferredTimeFrame(): Flow<String?>

    suspend fun setPreferredTimeFrame(preferredTimeFrameValue: String)
}