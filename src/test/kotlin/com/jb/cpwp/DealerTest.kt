package com.jb.cpwp

import com.jb.cpwp.Deck.Companion.standardDeckOf52
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class DealerTest {

    private val player1 = Player("First")
    private val player2 = Player("Second")
    private val player3 = Player("Third")
    private val player4 = Player("Fourth")
    private val player5 = Player("Fifth")
    private val player6 = Player("Sixth")
    private fun fourPlayers() = setOf(player1, player2, player3, player4)
    private fun sixPlayers() = setOf(player1, player2, player3, player4, player5, player6)

    @Test
    fun `create deck of 52`() {
        val deck = Deck(standardDeckOf52())
        assertEquals(52, deck.size())
    }

    @Test
    fun `deal cards distributes all the cards across four players`(){
        val players = Game(Deck()).deal(fourPlayers())
        assertTrue(players.all { it.hand.size == 13 })

        val cardsToTest = players.map { it.hand.first() }
        for(card in cardsToTest) `cards arent shared`(players, card)
    }

    private fun `cards arent shared`(players: Set<Player>, element: Card) {
        val pair: Pair<List<Player>, List<Player>> = players.partition { it.hand.contains(element) }
        val actual: List<Player> = pair.second.filter { it.hand == pair.first.first().hand }
        assertTrue(actual.isEmpty())
    }

    @Test
    fun `deal cards distributes all the cards across six players adding extra cards sequentially to as many players as necessary, starting with the first player`(){
        val players = Game(Deck()).deal(sixPlayers())
        assertTrue(players.take(4).all { it.hand.size == 9 })
        assertTrue(players.takeLast(2).all { it.hand.size == 8 })
    }

    @Test
    fun `all decks are shuffled`(){
        val players = Game(Deck()).deal(sixPlayers())
        for(player in players) println(player.hand)
    }

    @Test
    fun `verify equals does not care about order`(){
        val deck1 = Deck(standardDeckOf52())
        val deck2 = Deck(standardDeckOf52())
        assertEquals(deck1.cards, deck2.cards)
        assertDeepNotEquals(deck1.cards, deck2.cards)
    }

    private fun assertDeepNotEquals(first: Set<Any>, second: Set<Any>) {
        assertTrue(first.zip(second).any { (x, y) -> x != y })
    }
}

fun <T> Set<T>.takeLast(n: Int): List<T> {
    return this.toList().takeLast(n)
}
