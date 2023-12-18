package thierry.cryptoprice.resultof

import thierry.cryptoprice.resultof.entity.ApiCallFailure
import thierry.cryptoprice.resultof.entity.toApiCallFailure

sealed class ResultOf<out S, out F> {
    abstract val value: Any?

    data class Success<out S>(override val value: Any?) : ResultOf<S, Nothing>()
    data class Failure<out F>(override val value: Any?) : ResultOf<Nothing, F>()

    val isSuccess: Boolean get() = this is Success<*>
    val isFailure: Boolean get() = this is Failure<*>

    companion object {
        inline operator fun <reified S> invoke(block: () -> S): ResultOf<S, Throwable> = try {
            Success(block())
        } catch (e: Throwable) {
            Failure(e)
        }

        inline fun <reified S> success(value: S): Success<S> = Success(value)

        inline fun <reified F> failure(value: F): Failure<F> = Failure(value)
    }
}

inline fun <reified S> apiCall(call: () -> S): ResultOf<S, ApiCallFailure> =
    ResultOf(call).mapFailure { it.toApiCallFailure() }

inline fun <S1, S2, F> ResultOf<S1, F>.mapSuccess(
    transform: (S1) -> S2,
): ResultOf<S2, F> = when (this) {
    is ResultOf.Failure -> this
    is ResultOf.Success -> ResultOf.Success(transform(value as S1))
}

inline fun <S, F1, F2> ResultOf<S, F1>.mapFailure(
    transform: (F1) -> F2,
): ResultOf<S, F2> = when (this) {
    is ResultOf.Success -> this
    is ResultOf.Failure -> ResultOf.Failure(transform(value as F1))
}