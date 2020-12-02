package com.jb.cpwp

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
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
    fun `firstPlayers first turn`(){
        val game = Game()
        val players = game.setTheTable(setOf("me", "you"))
        val firstPlayer = Game.whoStarts(players)
        val otherPlayer = players.last { it != firstPlayer }

        assertEquals(Game.openingCard, firstPlayer.cardToPlay(game.table.openSlots()))
        assertNull(otherPlayer.cardToPlay(game.table.openSlots()))

        takeTurn(game.table, firstPlayer)
        assertBoardOpen(game)
        assertFalse(firstPlayer.hand.contains(Game.openingCard))
    }

    @Test
    fun `secondPlayers first turn`() {
        val game = Game()
        val players = game.setTheTable(setOf("me", "you"))
        val firstPlayer = Game.whoStarts(players)
        val otherPlayer = players.last { it != firstPlayer }

        while(!otherPlayer.canPlay(game.table)){ takeTurn(game.table, firstPlayer)}

        takeTurn(game.table, otherPlayer)
        println(firstPlayer.hand)
        println(otherPlayer.hand)
        println(game.table.openSlots())
    }

    @Test
    fun simulate(){
        val game = Game()
        val players = game.setTheTable(setOf("me", "you"))
        val firstPlayer = Game.whoStarts(players)
        val otherPlayer = players.last { it != firstPlayer }

        while(firstPlayer.hand.isNotEmpty() || otherPlayer.hand.isNotEmpty()){
            if(firstPlayer.canPlay(game.table)) { takeTurn(game.table, firstPlayer)}
            if(otherPlayer.canPlay(game.table)) { takeTurn(game.table, otherPlayer)}
        }
    }

    @Test
    fun simulateFourPlayers(){
        val game = Game()
        val players = game.setTheTable(setOf("dem", "dese", "dose", "dother"))

        while(game.table.openSlots().isNotEmpty()){
            for(player in players)
            takeATurn(player, game)
        }
    }

    @Test
    fun `simulate odd number of players`(){
        val game = Game()
        val players = game.setTheTable(setOf("dem", "dese", "dose"))

        while(game.table.openSlots().isNotEmpty()){
            for(player in players)
            takeATurn(player, game)
        }
    }
    @Test
    fun `simulate large number of players`(){
        val game = Game()
        val names = generateUniqueButNotImportantAsValuesNames()
        val players = game.setTheTable(names)

        while(game.table.openSlots().isNotEmpty()){
            for(player in players) takeATurn(player, game)
        }
    }

    private fun generateUniqueButNotImportantAsValuesNames() =
            Deck.standardDeckOf52().map { "Jack${it.suit}${it.rank}" }.plus("JACKJACKJACK").toSet()

    private fun takeATurn(player: Player?, game: Game) {
        if (player?.canPlay(game.table)!!) {
            takeTurn(game.table, player)
        } else { //todo rm println
            println("${player.name} can't play: ${player.hand}")
            println()
        }
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


