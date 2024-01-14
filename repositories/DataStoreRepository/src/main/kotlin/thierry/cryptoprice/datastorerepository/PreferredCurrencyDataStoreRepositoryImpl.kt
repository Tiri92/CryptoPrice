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
import thierry.cryptoprice.preferredcurrencyusecase.PreferredCurrencyDataStoreRepository
import javax.inject.Inject

private const val PREFERRED_CURRENCY_DATASTORE = "PreferredCurrencyDataStore"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERRED_CURRENCY_DATASTORE)

class PreferredCurrencyDataStoreRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : PreferredCurrencyDataStoreRepository {

    override suspend fun getPreferredCurrency(): Flow<String?> = //TODO Handle exception ?
        context.dataStore.data.map { preferredCurrency ->
            preferredCurrency[PREFERRED_CURRENCY_KEY]
        }

    override suspend fun setPreferredCurrency(preferredCurrencyValue: String) { //TODO Handle exception ?
        context.dataStore.edit { preferredCurrency ->
            preferredCurrency[PREFERRED_CURRENCY_KEY] =
                preferredCurrencyValue
        }
    }
}

private val PREFERRED_CURRENCY_KEY = stringPreferencesKey(name = "Preferred_Currency")
