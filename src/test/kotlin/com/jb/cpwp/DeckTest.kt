package com.jb.cpwp

import com.jb.cpwp.Deck.Companion.shuffledDeckOf52
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class DeckTest {

    private val playerName1 = "First"
    private val playerName2 = "Second"
    private val playerName3 = "Third"
    private val playerName4 = "Fourth"
    private val playerName5 = "Fifth"
    private val playerName6 = "Sixth"
    private fun fourPlayers() = setOf(playerName1, playerName2, playerName3, playerName4)
    private fun sixPlayers() = setOf(playerName1, playerName2, playerName3, playerName4, playerName5, playerName6)

    @Test
    fun `deal cards distributes all the cards across four players`() {
        val players = Game().setTheTable(fourPlayers())
        assertTrue(players.all { it.hand.size == 13 })

        val cardsToTest = players.map { it.hand.first() }
        for (card in cardsToTest) `cards are not shared`(players, card)
    }

    private fun `cards are not shared`(players: Set<Player>, element: Card) {
        val pair: Pair<List<Player>, List<Player>> = players.partition { it.hand.contains(element) }
        val actual: List<Player> = pair.second.filter { it.hand == pair.first.first().hand }
        assertTrue(actual.isEmpty())
    }

    @Test
    fun `deal cards distributes all the cards across six players adding extra cards sequentially to as many players as necessary, starting with the first player`() {
        val players = Game().setTheTable(sixPlayers())
        assertTrue(players.take(4).all { it.hand.size == 9 })
        assertTrue(players.takeLast(2).all { it.hand.size == 8 })
    }

    @Test
    fun `verify equals does not care about order`() {
        val deck1 = Deck(shuffledDeckOf52())
        val deck2 = Deck(shuffledDeckOf52())
        assertEquals(deck1.cards, deck2.cards)
        assertTrue(deck1.cards.areElementsInOrder(deck1.cards))
        assertTrue(!deck1.cards.areElementsInOrder(deck2.cards))
    }

    @Test
    fun incrementTest() {
        val upCard = Card(Suit.HEARTS, 8)
        assertEquals(Card(Suit.HEARTS, 9), upCard.nextCards().first())
        assertEquals(1, upCard.nextCards().size)

        val downCard = Card(Suit.HEARTS, 4)
        assertEquals(Card(Suit.HEARTS, 3), downCard.nextCards().first())
        assertEquals(1, downCard.nextCards().size)

        val openingCard = Card(Suit.HEARTS, 7)
        assertEquals(Card(Suit.HEARTS, 6), openingCard.nextCards().first())
        assertEquals(Card(Suit.HEARTS, 8), openingCard.nextCards().last())
        assertEquals(2, openingCard.nextCards().size)
    }

}

fun <T> Set<T>.areElementsInOrder(toMatch: Set<T>): Boolean {
    return this.zip(toMatch).all { (x, y) -> x == y }
}

fun <T> Set<T>.takeLast(n: Int): List<T> {
    return this.toList().takeLast(n)
}
