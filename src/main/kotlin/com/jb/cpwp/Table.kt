package com.jb.cpwp

import com.jb.cpwp.Game.Companion.openingCard

class Table(suits: Set<Suit>) {

    private val board: Map<Suit, MutableSet<Card>> = suits.associateBy({ it }, { mutableSetOf() })

    init {
        createBoard()
    }

    fun openSlots(): Set<Card> { return board.values.flatten().toSet() }

    fun play(cardToPlay: Card) {
        if (cardToPlay == openingCard) { openBoard() }
        if (canPlay(cardToPlay)) { playOnBoard(cardToPlay) }
    }

    private fun canPlay(cardToPlay: Card) = board.getValue(cardToPlay.suit).contains(cardToPlay)

    private fun playOnBoard(cardToPlay: Card) {
        val slotsForSuit = slotsForSuit(cardToPlay.suit)
        slotsForSuit.remove(cardToPlay)
        slotsForSuit.addAll(cardToPlay.nextCards())
    }

    private fun slotsForSuit(suit: Suit) = board.get(suit)?: error("suit should always be present")

    private fun createBoard() { openSuit(openingCard.suit) }

    private fun openBoard() {
        when { slotsForSuit(openingCard.suit).isNotEmpty() -> {
                Suit.values().forEach { openSuit(it) }
            }
        }
    }

    private fun openSuit(suit: Suit) {
        val slot = slotsForSuit(suit)
        when { slot.isEmpty() -> slot.add(Card(suit, 7)) }
    }
}

internal fun Card.nextCards(): List<Card> {
    fun incrementBy(): Int = StrictMath.abs(rank - 7) / (rank - 7)

    return if (this.rank == 7) {
        listOf(
                Card(this.suit, this.rank - 1),
                Card(this.suit, this.rank + 1))
    } else
        listOf(
                Card(this.suit, rank + incrementBy()))
                .filter{ it.rank in 1..13}
}

