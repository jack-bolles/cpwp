package com.jb.cpwp

import com.jb.cpwp.Suit.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TableTest {

    @Test
    fun `table opens with the 7 of Hearts then opens to other suits after the 7 of Hearts`() {
        val table = Table(Deck().suits())

        canNotPlayCard(Card(SPADES, 7), table)
        canNotPlayCard(Card(DIAMONDS, 7), table)
        canNotPlayCard(Card(CLUBS, 7), table)
        canNotPlayCard(Card(HEARTS, 6), table)
        canNotPlayCard(Card(HEARTS, 8), table)

        canPlayCard(Card(HEARTS, 7), table)
        table.play(Game.openingCard)

        `increments board when suit is played`(table)
        `opens board after opening card is played`(table)
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
