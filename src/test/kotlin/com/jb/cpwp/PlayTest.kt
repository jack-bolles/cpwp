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
    fun `first turn`(){
        val game = Game()
        val players = game.setTheTable(setOf("me", "you"))
        val firstPlayer = Game.whoStarts(players)
        val otherPlayer = players.last { it != firstPlayer }

        assertEquals(Game.openingCard, firstPlayer.cardToPlay(game.table.openSlots()))
        //time to learn about Nullable
        //assertNull(otherPlayer.cardToPlay(game.table.openSlots()))
    }

    //other approach - goes against mechanics of play with ppl, but more functional in approach?
    //board.play(player.strategy, player.hand)
}


