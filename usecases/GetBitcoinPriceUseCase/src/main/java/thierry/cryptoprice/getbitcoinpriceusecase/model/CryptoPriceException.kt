package thierry.cryptoprice.getbitcoinpriceusecase.model

sealed class CryptoPriceException {
    data object InvalidParameterPriceException : CryptoPriceException()
    data object MissingAuthTokenPriceException : CryptoPriceException()
    data object ActionNotAllowedPriceException : CryptoPriceException()
    data object InfoNotFoundPriceException : CryptoPriceException()
    data object ConflictActionPriceException : CryptoPriceException()
    data object MissingActionMappingPriceException : CryptoPriceException()
    data object GenericPriceException : CryptoPriceException()
}