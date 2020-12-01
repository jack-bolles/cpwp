package com.jb.cpwp

import com.jb.cpwp.Game.Companion.whoStarts
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GameTest{

    private val playerName1 = "First"
    private val playerName2 = "Second"
    private val playerName3 = "Third"
    private val playerName4 = "Fourth"
    private fun fourPlayerNames() = setOf(playerName1, playerName2, playerName3, playerName4)

    @Test
    fun `start game with 7 of Hearts`(){
        val game = Game()
        val players = game.setTheTable(fourPlayerNames())
        assertTrue(whoStarts(players).hand.contains(Game.openingCard))
        assertTrue(whoStarts(players).canPlay(game.table))

    }

    @Test
    fun `play starts with the 7 of Hearts and opens to other suits after the 7 of Hearts`() {
        val game = Game()
        //val players = game.setTheTable(fourPlayerNames())

        canNotPlayCard(Card(Suit.SPADES, 7), game.table)
        canNotPlayCard(Card(Suit.DIAMONDS, 7), game.table)
        canNotPlayCard(Card(Suit.CLUBS, 7), game.table)
        canNotPlayCard(Card(Suit.HEARTS, 6), game.table)
        canNotPlayCard(Card(Suit.HEARTS, 8), game.table)

        canPlayCard(Card(Suit.HEARTS, 7), game.table)
        game.table.play(Game.openingCard)    //todo - table.play(player.play())
        canNotPlayCard(Card(Suit.HEARTS, 7), game.table)

        canPlayCard(Card(Suit.SPADES, 7), game.table)
        canPlayCard(Card(Suit.DIAMONDS, 7), game.table)
        canPlayCard(Card(Suit.CLUBS, 7), game.table)
        canPlayCard(Card(Suit.HEARTS, 6), game.table)
        canPlayCard(Card(Suit.HEARTS, 8), game.table)
    }

    private fun canPlayCard(card: Card, table: Table) {
        assertEquals(true, canPlay(card, table))
    }

    private fun canNotPlayCard(card: Card, table: Table) {
        assertEquals(false, canPlay(card, table))
    }

    private fun canPlay(card: Card, table: Table) = Player("name", mutableSetOf(card)).canPlay(table)
}
