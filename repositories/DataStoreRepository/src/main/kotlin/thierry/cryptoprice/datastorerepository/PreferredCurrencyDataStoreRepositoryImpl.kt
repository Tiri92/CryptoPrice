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

class PreferredCurrencyDataStoreRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : PreferredCurrencyDataStoreRepository {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERRED_CURRENCY_DATASTORE)
    override suspend fun getPreferredCurrency(): Flow<String?> =
        context.dataStore.data.map { preferredCurrency ->
            preferredCurrency[PREFERRED_CURRENCY_KEY]
        }

    override suspend fun setPreferredCurrency(preferredCurrencyValue: String) {
        context.dataStore.edit { preferredCurrency ->
            preferredCurrency[PREFERRED_CURRENCY_KEY] =
                preferredCurrencyValue
        }
    }
}

private const val PREFERRED_CURRENCY_DATASTORE = "PreferredCurrencyDataStore"
private val PREFERRED_CURRENCY_KEY = stringPreferencesKey(name = "Preferred_Currency")
