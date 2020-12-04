package com.jb.cpwp

import com.jb.cpwp.Suit.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GameTest {

    private val playerName1 = "First"
    private val playerName2 = "Second"
    private val playerName3 = "Third"
    private val playerName4 = "Fourth"
    private fun fourPlayerNames() = setOf(playerName1, playerName2, playerName3, playerName4)

    @Test
    fun `start game with 7 of Hearts`() {
        val game = Game(fourPlayerNames())
        assertTrue(game.whoStarts().hand.contains(Game.openingCard))
        assertTrue(game.whoStarts().canPlay(game.table))

    }

    @Test
    fun `play starts with the 7 of Hearts and opens to other suits after the 7 of Hearts`() {
        val game = Game(fourPlayerNames())

        canNotPlayCard(Card(SPADES, 7), game.table)
        canNotPlayCard(Card(DIAMONDS, 7), game.table)
        canNotPlayCard(Card(CLUBS, 7), game.table)
        canNotPlayCard(Card(HEARTS, 6), game.table)
        canNotPlayCard(Card(HEARTS, 8), game.table)

        canPlayCard(Card(HEARTS, 7), game.table)
        game.table.play(Game.openingCard)    //todo - table.play(player.play())

        `increments board when suit is played`(game.table)
        `opens board after opening card is played`(game.table)
    }

    private fun `increments board when suit is played`(table: Table) {
        canNotPlayCard(Card(HEARTS, 7), table)
        canPlayCard(Card(HEARTS, 6), table)
        canPlayCard(Card(HEARTS, 8), table)
    }

    private fun `opens board after opening card is played`(table: Table) {
        canPlayCard(Card(SPADES, 7), table)
        canPlayCard(Card(DIAMONDS, 7), table)
        canPlayCard(Card(CLUBS, 7), table)
    }

    private fun canPlayCard(card: Card, table: Table) {
        assertEquals(true, canPlay(card, table))
    }

    private fun canNotPlayCard(card: Card, table: Table) {
        assertEquals(false, canPlay(card, table))
    }

    private fun canPlay(card: Card, table: Table) = Player("name", setOf(card)).canPlay(table)
}
