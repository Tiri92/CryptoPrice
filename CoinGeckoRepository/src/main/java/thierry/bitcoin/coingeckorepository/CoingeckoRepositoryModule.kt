package thierry.bitcoin.coingeckorepository

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import thierry.bitcoin.getbitcoinpriceusecase.CoinGeckoRepository

@Module
@InstallIn(SingletonComponent::class)
internal interface CoinGeckoRepositoryModule {

    @Binds
    fun provideCoinGeckoRepository(impl: CoinGeckoRepositoryImpl): CoinGeckoRepository

    companion object {
        @Provides
        internal fun provideJson(): Json = Json {
            isLenient = true
            ignoreUnknownKeys = true
        }

        @Provides
        fun provideCoinGeckoApiService(
            json: Json,
        ): CoingeckoService {
            return Retrofit.Builder()
                .baseUrl("https://api.coingecko.com/")
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .build()
                .create(CoingeckoService::class.java)
        }
    }
}