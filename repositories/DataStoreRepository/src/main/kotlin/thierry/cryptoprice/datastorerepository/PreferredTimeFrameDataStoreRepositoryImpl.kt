package thierry.cryptoprice.datastorerepository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import thierry.cryptoprice.preferredcurrencyusecase.PreferredTimeFrameDataStoreRepository
import javax.inject.Inject

private const val PREFERRED_TIMEFRAME_DATASTORE = "PreferredTimeFrameDataStore"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERRED_TIMEFRAME_DATASTORE)

class PreferredTimeFrameDataStoreRepositoryImpl @Inject constructor( //TODO Add test
    @ApplicationContext private val context: Context
) : PreferredTimeFrameDataStoreRepository {

    override suspend fun getPreferredTimeFrame(): Flow<String?> = //TODO Handle exception ?
        context.dataStore.data.map { preferredTimeFrame ->
            preferredTimeFrame[PREFERRED_TIMEFRAME_KEY]
        }

    override suspend fun setPreferredTimeFrame(preferredTimeFrameValue: String) { //TODO Handle exception ?
        context.dataStore.edit { preferredTimeFrame ->
            preferredTimeFrame[PREFERRED_TIMEFRAME_KEY] =
                preferredTimeFrameValue
        }
    }
}

private val PREFERRED_TIMEFRAME_KEY = stringPreferencesKey(name = "Preferred_TimeFrame")