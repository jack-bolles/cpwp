package com.jb.cpwp

import com.jb.cpwp.Game.Companion.whoStarts
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GameTest{

    private val player1 = Player("First")
    private val player2 = Player("Second")
    private val player3 = Player("Third")
    private val player4 = Player("Fourth")
    private val player5 = Player("Fifth")
    private val player6 = Player("Sixth")

    private fun fourPlayers() = seatPlayers(player1, player2, player3, player4)

    @Test
    fun `start game with 7 of Hearts`(){
        val game = Game(Deck())
        player1.hand = Suit.createSuit(Suit.SPADES)
        player2.hand = Suit.createSuit(Suit.CLUBS)
        player3.hand = Suit.createSuit(Suit.HEARTS)
        player4.hand = Suit.createSuit(Suit.DIAMONDS)

        //usurping the constructor -- bad programmer
        val expectedFirst = whoStarts(fourPlayers())
        assertEquals(player3.name, expectedFirst.name)
    }
}