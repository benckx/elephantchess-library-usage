import io.elephantchess.xiangqi.Board
import kotlin.test.Test
import kotlin.test.assertEquals

class BoardTest {

    @Test
    fun `initial board has the standard starting FEN`() {
        val board = Board()

        assertEquals(
            "rnbakabnr/9/1c5c1/p1p1p1p1p/9/9/P1P1P1P1P/1C5C1/9/RNBAKABNR w - - 0 0",
            board.outputFen()
        )
    }

    @Test
    fun `FEN reflects registered moves`() {
        val board = Board()

        board.registerMove("h2e2") // C2=5
        board.registerMove("h9g7") // H8+7

        assertEquals(
            "rnbakab1r/9/1c4nc1/p1p1p1p1p/9/9/P1P1P1P1P/1C2C4/9/RNBAKABNR w - - 0 1",
            board.outputFen()
        )
    }
}
