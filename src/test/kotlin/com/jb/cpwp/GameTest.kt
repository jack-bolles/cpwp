package com.jb.cpwp

import com.jb.cpwp.Game.Companion.whoStarts
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class GameTest{

    private val player1 = Player("First")
    private val player2 = Player("Second")
    private val player3 = Player("Third")
    private val player4 = Player("Fourth")

    private fun fourPlayers() = seatPlayers(player1, player2, player3, player4)

    @Test
    fun `start game with 7 of Hearts`(){
        player1.hand = Suit.createSuit(Suit.SPADES)
        player2.hand = Suit.createSuit(Suit.CLUBS)
        player3.hand = Suit.createSuit(Suit.HEARTS)
        player4.hand = Suit.createSuit(Suit.DIAMONDS)

        //TODO usurping the Game constructor -- bad programmer
        val expectedFirst = whoStarts(fourPlayers())
        assertEquals(player3.name, expectedFirst.name)
    }

    @Test
    fun `play starts with the 7 of Hearts`(){
        player1.hand = Suit.createSuit(Suit.SPADES)
        player2.hand = Suit.createSuit(Suit.CLUBS)
        player3.hand = Suit.createSuit(Suit.HEARTS)
        player4.hand = Suit.createSuit(Suit.DIAMONDS)

        val players = fourPlayers()
        val expectedFirst = whoStarts(players)

        val table = Game(Deck()).table
        for(player in players){
            assertEquals(player == expectedFirst, player.canPlay(table))
        }
    }

    @Test
    fun `play opens to other suits after the 7 of Hearts`(){
        val players = fourPlayers()
        val table = Game(Deck()).table

        //usurps game.deal(...)
        player1.hand = Suit.createSuit(Suit.SPADES)
        player2.hand = Suit.createSuit(Suit.CLUBS)
        player3.hand = Suit.createSuit(Suit.HEARTS)
        player4.hand = Suit.createSuit(Suit.DIAMONDS)

        table.play(Game.openingCard)    //todo - table.play(player.play())
        for(player in players){
            assertTrue(player.canPlay(table))
        }
    }

    @Test
    fun `let the games begin`(){
        val (table, players) = Game.start(fourPlayers())
        val firstPlayer = whoStarts(players)
        val canPlay = firstPlayer.canPlay(table)
        assertTrue(canPlay)

        canNotPlayCard(Card(Suit.SPADES, 7), table)
        canNotPlayCard(Card(Suit.DIAMONDS, 7), table)
        canNotPlayCard(Card(Suit.CLUBS, 7), table)
        canNotPlayCard(Card(Suit.HEARTS, 6), table)
        canNotPlayCard(Card(Suit.HEARTS, 8), table)

        canPlayCard(Card(Suit.HEARTS, 7), table)
        table.play(Game.openingCard)    //todo - table.play(player.play())
        canNotPlayCard(Card(Suit.HEARTS, 7), table)

        canPlayCard(Card(Suit.SPADES, 7), table)
        canPlayCard(Card(Suit.DIAMONDS, 7), table)
        canPlayCard(Card(Suit.CLUBS, 7), table)
        canPlayCard(Card(Suit.HEARTS, 6), table)
        canPlayCard(Card(Suit.HEARTS, 8), table)

    }

    private fun canPlayCard(card: Card, table: Table) {
        assertEquals(true, canPlay(card, table))
    }

    private fun canNotPlayCard(card: Card, table: Table) {
        assertEquals(false, canPlay(card, table))
    }

    private fun canPlay(card: Card, table: Table) = Player("name", setOf(card)).canPlay(table)
}
