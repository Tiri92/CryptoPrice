package thierry.cryptoprice.coingeckorepository.mapper

import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import thierry.cryptoprice.getbitcoinpriceusecase.model.CryptoPriceException
import thierry.cryptoprice.resultof.HttpStatusCode
import thierry.cryptoprice.resultof.entity.ApiCallFailure
import java.io.IOException

class ApiCallFailureMapperTest {
    @Test
    fun `toCryptoException InvalidParameterException`() {
        val apiCallFailure =
            ApiCallFailure.Http(
                HttpException(Response.success(null)),
                HttpStatusCode.BAD_REQUEST
            )
        assertEquals(
            /* expected = */ CryptoPriceException.InvalidParameterPriceException,
            /* actual = */ apiCallFailure.toCryptoPriceException()
        )
    }

    @Test
    fun `toCryptoException MissingAuthTokenException`() {
        val apiCallFailure =
            ApiCallFailure.Http(
                HttpException(Response.success(null)),
                HttpStatusCode.UNAUTHORIZED
            )
        assertEquals(
            /* expected = */ CryptoPriceException.MissingAuthTokenPriceException,
            /* actual = */ apiCallFailure.toCryptoPriceException()
        )
    }

    @Test
    fun `toCryptoException ActionNotAllowedException`() {
        val apiCallFailure =
            ApiCallFailure.Http(
                HttpException(Response.success(null)),
                HttpStatusCode.FORBIDDEN
            )
        assertEquals(
            /* expected = */ CryptoPriceException.ActionNotAllowedPriceException,
            /* actual = */ apiCallFailure.toCryptoPriceException()
        )
    }

    @Test
    fun `toCryptoException InfoNotFoundException`() {
        val apiCallFailure =
            ApiCallFailure.Http(
                HttpException(Response.success(null)),
                HttpStatusCode.NOT_FOUND
            )
        assertEquals(
            /* expected = */ CryptoPriceException.InfoNotFoundPriceException,
            /* actual = */ apiCallFailure.toCryptoPriceException()
        )
    }

    @Test
    fun `toCryptoException MissingActionMappingException`() {
        val apiCallFailure =
            ApiCallFailure.Http(
                HttpException(Response.success(null)),
                HttpStatusCode.UNPROCESSABLE_ENTITY_CODE
            )
        assertEquals(
            /* expected = */ CryptoPriceException.MissingActionMappingPriceException,
            /* actual = */ apiCallFailure.toCryptoPriceException()
        )
    }

    @Test
    fun `toCryptoException ConflictActionException`() {
        val apiCallFailure =
            ApiCallFailure.Http(
                HttpException(Response.success(null)),
                HttpStatusCode.CONFLICT
            )
        assertEquals(
            /* expected = */ CryptoPriceException.ConflictActionPriceException,
            /* actual = */ apiCallFailure.toCryptoPriceException()
        )
    }

    @Test
    fun `toCryptoException GenericException`() {
        val apiCallFailure =
            ApiCallFailure.Http(
                HttpException(Response.success(null)),
                HttpStatusCode.TOO_MANY_REQUEST_CODE
            )
        assertEquals(
            /* expected = */ CryptoPriceException.GenericPriceException,
            /* actual = */ apiCallFailure.toCryptoPriceException()
        )
    }

    @Test
    fun `toCryptoException GenericException IO`() {
        val apiCallFailure = ApiCallFailure.IO(
            IOException(),
        )
        assertEquals(
            /* expected = */ CryptoPriceException.GenericPriceException,
            /* actual = */ apiCallFailure.toCryptoPriceException()
        )
    }
}