package thierry.cryptoprice.coingeckorepository.mapper

import thierry.cryptoprice.getbitcoinpriceusecase.model.CryptoPriceException
import thierry.cryptoprice.resultof.HttpStatusCode
import thierry.cryptoprice.resultof.entity.ApiCallFailure

fun ApiCallFailure.toCryptoPriceException(): CryptoPriceException = when (this) {
    is ApiCallFailure.Http -> when (code) {
        HttpStatusCode.BAD_REQUEST -> CryptoPriceException.InvalidParameterPriceException
        HttpStatusCode.UNAUTHORIZED -> CryptoPriceException.MissingAuthTokenPriceException
        HttpStatusCode.FORBIDDEN -> CryptoPriceException.ActionNotAllowedPriceException
        HttpStatusCode.NOT_FOUND -> CryptoPriceException.InfoNotFoundPriceException
        HttpStatusCode.UNPROCESSABLE_ENTITY_CODE -> CryptoPriceException.MissingActionMappingPriceException
        HttpStatusCode.CONFLICT -> CryptoPriceException.ConflictActionPriceException
        else -> CryptoPriceException.GenericPriceException
    }

    else -> CryptoPriceException.GenericPriceException
}