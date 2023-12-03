package thierry.bitcoin.coingeckorepository

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import thierry.bitcoin.getbitcoinpriceusecase.CoingeckoRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface CoingeckoRepositoryModule {

    @Binds
    fun provideCoingeckoRepository(impl: CoingeckoRepositoryImpl): CoingeckoRepository

    companion object {
        /*@Singleton
        @Provides
        internal fun provideJson(json: Json): Json = Json(json) {
            isLenient = true
        }*/

        @Provides
        fun provideCoingeckoApiService(
            //json: Json,
        ): CoingeckoService {
            return Retrofit.Builder()
                .baseUrl("https://api.coingecko.com/")
                .addConverterFactory(GsonConverterFactory.create())
                //.addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .build()
                .create(CoingeckoService::class.java)
        }
    }
}