package thierry.cryptoprice.resultof.entity

import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

sealed class ApiCallFailure {
    abstract val throwable: Throwable

    data class Http(
        override val throwable: retrofit2.HttpException,
        val code: Int
    ) : ApiCallFailure()

    data class Parsing(override val throwable: Throwable) : ApiCallFailure()
    data class IO(override val throwable: IOException) : ApiCallFailure()
    data class Timeout(override val throwable: TimeoutException) : ApiCallFailure()
    data class Unknown(override val throwable: Throwable) : ApiCallFailure()
}

fun Throwable.toApiCallFailure(): ApiCallFailure = when (this) {
    is HttpException -> ApiCallFailure.Http(this, this.code())
    is SerializationException -> ApiCallFailure.Parsing(this)
    is IOException -> ApiCallFailure.IO(this)
    is TimeoutException -> ApiCallFailure.Timeout(this)
    else -> ApiCallFailure.Unknown(this)
}