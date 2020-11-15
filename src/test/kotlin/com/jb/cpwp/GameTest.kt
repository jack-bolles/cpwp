package com.jb.cpwp

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GameTest{

    private val player1 = Player("First")
    private val player2 = Player("Second")
    private val player3 = Player("Third")
    private val player4 = Player("Fourth")
    private val player5 = Player("Fifth")
    private val player6 = Player("Sixth")

    private fun fourPlayers() = setOf(player1, player2, player3, player4)

    @Test
    fun `start game with 7 of Hearts`(){
        val game = Game(Deck())
        val players = game.deal(fourPlayers())

        player1.hand = Suit.createSuit(Suit.HEARTS)
        player2.hand = Suit.createSuit(Suit.DIAMONDS)
        player3.hand = Suit.createSuit(Suit.CLUBS)
        player4.hand = Suit.createSuit(Suit.SPADES)

        val expectedFirst = whoStarts(players)
        assertEquals(player1.name, expectedFirst.name)

    }

    companion object {
        fun whoStarts(players: Set<Player>) =
                players.single { it.hand.contains(Card(Suit.HEARTS, 7)) }
    }
}