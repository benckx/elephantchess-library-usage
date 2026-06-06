import io.elechantchess.sevenkingdoms.testutils.GameEntryCacheManager.getAllGames
import io.elephantchess.sevenkingdoms.Board
import io.elephantchess.sevenkingdoms.Color
import io.elephantchess.sevenkingdoms.Color.WHITE
import io.elephantchess.sevenkingdoms.VictoryType
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class SevenKingdomsBoardTest {

    @Test
    fun defaultBoardTest() {
        val board = Board()
        assertEquals(WHITE, board.colorToPlay())
        assertEquals(null, board.winner())
        assertEquals(board.captures().size, Color.entries.size)
        assertEquals(board.capturedKingdomsMap().size, Color.entries.size)
        assertTrue(board.listCaptureEvents().isEmpty())
        assertEquals(board.listLegalMovesFor(WHITE).toSet(), board.listCurrentLegalMoves().toSet())

        board.listCurrentLegalMoves().forEach { move ->
            assertEquals(WHITE, board.pieceAt(move.from)!!.color)
            board.copy().registerMove(move) // doesn't throw Exception
        }
        board.listLegalMovesFor(WHITE).forEach { move ->
            assertEquals(WHITE, board.pieceAt(move.from)!!.color)
        }

        val legalMovesForAllColors = Color.entries.flatMap { board.listLegalMovesFor(it) }
        assertEquals(legalMovesForAllColors.toSet(), board.listAllLegalMoves().toSet())
    }

    @Test
    fun replaySomeGamesFromTestUtils() {
        val games = getAllGames().shuffled().take(20)
        assertTrue(games.isNotEmpty())

        games.forEach { game ->
            val board = game.toBoard()

            assertTrue(board.isGameOver)
            assertEquals(game.winner, board.winner())
            assertEquals(game.victoryType, board.victoryType())
            assertEquals(game.capturedKingdoms, board.capturedKingdomsMap())
            assertEquals(game.colorsStillInGame, board.colorsStillInGame())

            val winner = assertNotNull(board.winner())
            assertTrue(winner in board.colorsStillInGame())
            assertTrue(board.victoryType() in VictoryType.entries)
        }
    }
}
