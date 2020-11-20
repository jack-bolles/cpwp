package com.jb.cpwp

import com.jb.cpwp.Game.Companion.whoStarts
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
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
        player1.hand = Suit.createSuit(Suit.SPADES)
        player2.hand = Suit.createSuit(Suit.CLUBS)
        player3.hand = Suit.createSuit(Suit.HEARTS)
        player4.hand = Suit.createSuit(Suit.DIAMONDS)

        val players = fourPlayers()
        val table = Game(Deck()).table
        table.play(Game.openingCard)    //todo - table.play(player.play())

        for(player in players){
            assertTrue(player.canPlay(table))
        }
    }

}