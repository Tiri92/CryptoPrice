package thierry.cryptoprice.resultof

import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import thierry.cryptoprice.resultof.entity.toApiCallFailure

class ResultOfTest {

    @Test
    fun getValue() {
        assertEquals(Unit, ResultOf.success(Unit).value)
        assertEquals(Unit, ResultOf.failure(Unit).value)
    }

    @Test
    fun `isSuccess & is Failure`() {
        assertTrue(ResultOf.success(Unit).isSuccess)
        assertTrue(ResultOf.failure(Unit).isFailure)
    }

    @Test
    fun `apiCall result`() {
        val exception = Exception()

        assertTrue(apiCall { }.isSuccess)
        assertTrue(apiCall<Unit> { throw exception }.isFailure)
    }

    @Test
    fun `apiCall mapping`() = mockkStatic(Throwable::toApiCallFailure) {
        val exception = Exception()
        apiCall<Unit> { throw exception }
        verify { exception.toApiCallFailure() }
    }
}