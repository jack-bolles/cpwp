package com.jb.cpwp

import org.junit.jupiter.api.Test
import kotlin.test.*

class PlayTest {
    @Test
    fun `game starts with 7 of Hearts`() {
        val game = Game(setOf("dem", "dese", "dose", "dother"))
        assertTrue(game.whoStarts().hand.contains(Game.openingCard))
        assertTrue(game.whoStarts().canPlay(game.table))
    }

    @Test
    fun `open play`() {
        val game = Game(setOf("me", "you"))
        val openSlots = game.table.availableSlots()

        val players = game.players
        val firstPlayer = game.whoStarts()
        val otherPlayer = players.last { it != firstPlayer }

        assertTrue(firstPlayer.canPlay(game.table))
        assertFalse(otherPlayer.canPlay(game.table))

        assertEquals(1, openSlots.size)
        assertTrue(openSlots.contains(Game.openingCard))
    }

    @Test
    fun `firstPlayers first turn`() {
        val game = Game(setOf("me", "you"))
        val players = game.players
        val firstPlayer = game.whoStarts()
        val otherPlayer = players.last { it != firstPlayer }

        assertEquals(Game.openingCard, firstPlayer.cardToPlay(game.table.availableSlots()))
        assertTrue(firstPlayer.canPlay(game.table))

        assertNull(otherPlayer.cardToPlay(game.table.availableSlots()))
        assertFalse(otherPlayer.canPlay(game.table))

        game.takeTurn(firstPlayer)
        assertBoardOpen(game)
        assertFalse(firstPlayer.hand.contains(Game.openingCard))
    }

    @Test
    fun `secondPlayers first turn`() {
        val game = Game(setOf("me", "you"))
        val players = game.players
        val firstPlayer = game.whoStarts()
        val otherPlayer = players.last { it != firstPlayer }

        while (!otherPlayer.canPlay(game.table)) {
            game.takeTurn(firstPlayer)
        }

        val handBefore = otherPlayer.hand
        game.takeTurn(otherPlayer)
        val handAfter = otherPlayer.hand
        assertNotEquals(handBefore, handAfter)
    }

    @Test
    fun simulateTwoPlayers() {
        val game = Game(setOf("me", "you"))
        val players = game.players
        val firstPlayer = game.whoStarts()
        val otherPlayer = players.last { it != firstPlayer }

        while (firstPlayer.hand.isNotEmpty() || otherPlayer.hand.isNotEmpty()) {
            if (firstPlayer.canPlay(game.table)) {
                game.takeTurn(firstPlayer)
            }
            if (otherPlayer.canPlay(game.table)) {
                game.takeTurn(otherPlayer)
            }
        }
    }

    @Test
    fun simulateFourPlayers() {
        playOut(Game(setOf("dem", "dese", "dose", "dother")))
    }

    @Test
    fun `simulate odd number of players`() {
        playOut(Game(setOf("dem", "dese", "dose")))
    }

    @Test
    fun `simulate large number of players`() {
        playOut(Game(generateUniqueButNotImportantAsValuesNames()))
    }

    private fun playOut(game: Game) {
        while (game.table.availableSlots().isNotEmpty()) {
            game.players.forEach { player -> game.takeTurn(player) }
        }

        assertTrue(game.players.flatMap { it.hand  }.isEmpty())
    }

    private fun generateUniqueButNotImportantAsValuesNames() =
            Deck.standardDeckOf52().map { "Jack${it.suit}${it.rank}" }.plus("JACKJACKJACK").toSet()

    private fun assertBoardOpen(game: Game) {
        assertTrue(game.table.availableSlots().containsAll(setOf(
                Card(Suit.HEARTS, 6),
                Card(Suit.HEARTS, 8),
                Card(Suit.DIAMONDS, 7),
                Card(Suit.CLUBS, 7),
                Card(Suit.SPADES, 7),
        )))
    }
}


