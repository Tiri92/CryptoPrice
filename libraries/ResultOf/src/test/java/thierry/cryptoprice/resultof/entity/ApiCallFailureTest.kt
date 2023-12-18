package thierry.cryptoprice.resultof.entity

import kotlinx.serialization.SerializationException
import org.junit.Assert
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.TimeoutException

class ApiCallFailureTest {

    @Test
    fun `toApiCallFailure Http`() {
        val exception = HttpException(Response.success(null))
        Assert.assertEquals(
            /* expected = */ ApiCallFailure.Http(exception, exception.code()),
            /* actual = */ exception.toApiCallFailure()
        )
    }

    @Test
    fun `toApiCallFailure Parsing`() {
        val serializationException = SerializationException()
        Assert.assertEquals(
            /* expected = */ ApiCallFailure.Parsing(serializationException),
            /* actual = */ serializationException.toApiCallFailure()
        )
    }

    @Test
    fun `toApiCallFailure IO`() {
        val exception = IOException()
        Assert.assertEquals(
            /* expected = */ ApiCallFailure.IO(exception),
            /* actual = */ exception.toApiCallFailure()
        )
    }

    @Test
    fun `toApiCallFailure Timeout`() {
        val exception = TimeoutException()
        Assert.assertEquals(
            /* expected = */ ApiCallFailure.Timeout(exception),
            /* actual = */ exception.toApiCallFailure()
        )
    }

    @Test
    fun `toApiCallFailure Unknown`() {
        val exception = Throwable()
        Assert.assertEquals(
            /* expected = */ ApiCallFailure.Unknown(exception),
            /* actual = */ exception.toApiCallFailure()
        )
    }
}