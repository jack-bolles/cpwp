package com.jb.cpwp

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PlayTest {
    @Test
    fun `open play`() {
        val game = Game()
        val openSlots = game.table.openSlots()

        val players = game.setTheTable(setOf("me", "you"))
        val firstPlayer = Game.whoStarts(players)
        val otherPlayer = players.last { it != firstPlayer }

        assertTrue(firstPlayer.canPlay(game.table))
        assertFalse(otherPlayer.canPlay(game.table))

        assertEquals(1, openSlots.size)
        assertTrue(openSlots.contains(Game.openingCard))
    }

    @Test
    fun `first turn`(){
        val game = Game()
        val players = game.setTheTable(setOf("me", "you"))
        val firstPlayer = Game.whoStarts(players)
        val otherPlayer = players.last { it != firstPlayer }

        assertEquals(Game.openingCard, firstPlayer.cardToPlay(game.table.openSlots()))
        //TODO time to learn about Nullable
        //assertNull(otherPlayer.cardToPlay(game.table.openSlots()))

        takeTurn(game.table, firstPlayer)
        assertBoardOpen(game)
        assertFalse(firstPlayer.hand.contains(Game.openingCard))
    }

    private fun assertBoardOpen(game: Game) {
        assertTrue(game.table.openSlots().containsAll(setOf(
                Card(Suit.HEARTS, 6),
                Card(Suit.HEARTS, 8),
                Card(Suit.DIAMONDS, 7),
                Card(Suit.CLUBS, 7),
                Card(Suit.SPADES, 7),
        )))
    }
}


