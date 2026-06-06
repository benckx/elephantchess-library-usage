import io.elephantchess.xiangqi.Board
import io.elephantchess.xiangqi.Color.BLACK
import io.elephantchess.xiangqi.Color.RED
import io.elephantchess.xiangqi.testutils.GameMovesDtoCache
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class XiangqiGamesDataTest {

    private val gamesCache = GameMovesDtoCache()

    @Test
    fun cacheIsNotEmpty() {
        assertTrue(gamesCache.listAll().isNotEmpty())
    }

    @Test
    fun playARandomSampleOfGames() {
        repeat(5) {
            val game = gamesCache.randomGame()
            val board = Board()

            // RED always plays first in Xiangqi
            assertEquals(RED, board.colorToPlay())

            game.uciMoves.forEachIndexed { index, uci ->
                val expectedColor = if (index % 2 == 0) RED else BLACK
                assertEquals(expectedColor, board.colorToPlay(), "game ${game.gameId}, move #$index ($uci)")
                board.registerMove(uci) // legal & parseable, doesn't throw
            }

            // all moves were replayed and the resulting position is valid
            assertTrue(game.uciMoves.isNotEmpty())
            assertTrue(board.outputFen().isNotBlank())
        }
    }

}
