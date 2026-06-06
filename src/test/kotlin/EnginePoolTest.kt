import io.elephantchess.engines.EnginePool
import io.elephantchess.engines.process.EngineConfig
import io.elephantchess.engines.process.PikafishEngineId
import io.elephantchess.engines.protocol.model.InfoLineResult
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors.newFixedThreadPool
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class EnginePoolTest {

    private val startingFen = "rnbakabnr/9/1c5c1/p1p1p1p1p/9/9/P1P1P1P1P/1C5C1/9/RNBAKABNR w - - 0 0"

    @Test
    fun `parseInfoLine extracts depth, score and principal variation`() {
        val line =
            "info depth 10 seldepth 11 multipv 1 score cp 44 nodes 14722 nps 253827 hashfull 6 tbhits 0 time 58 pv h2e2 b9c7 h0g2 b7a7 i0h0 a9b9"

        val result = InfoLineResult.parseInfoLine(line)

        assertEquals(10, result.depth)
        assertEquals(58L, result.time)
        assertEquals(44, result.cp)
        assertEquals(null, result.mate)
        assertEquals(listOf("h2e2", "b9c7", "h0g2", "b7a7", "i0h0", "a9b9"), result.pv)
        assertEquals(line, result.line)
    }

    @Test
    fun `Pikafish suggests a best move for the starting position`() {
        val engineConfig = EngineConfig("2023-03-05", poolSize = 1, numberOfThreads = 1)
        val enginePool = EnginePool(mapOf(PikafishEngineId to engineConfig), newFixedThreadPool(2))

        try {
            runBlocking {
                val infoLinesResult = enginePool.queryForDepth(startingFen, PikafishEngineId, 6, timeout = 10_000)

                assertNotNull(infoLinesResult, "the engine should return a result")
                assertEquals(false, infoLinesResult.checkmate)

                val bestMove = infoLinesResult.bestMove
                assertNotNull(bestMove, "the engine should return a best move")
                assertEquals(4, bestMove.length, "a UCI move is encoded with 4 characters")

                val deepestResult = infoLinesResult.deepestResult()
                assertNotNull(deepestResult, "the engine should return at least one info line")
                assertTrue((deepestResult.depth ?: 0) >= 6, "the engine should reach the requested depth")
                assertEquals(bestMove, deepestResult.pv.first(), "best move is the first move of the deepest pv")
            }
        } finally {
            enginePool.close()
        }
    }

}
