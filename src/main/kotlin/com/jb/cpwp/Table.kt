package com.jb.cpwp

import com.jb.cpwp.Game.Companion.openingCard

class Table(suits: Set<Suit>) {

    private val board: Map<Suit, MutableSet<Card>> = suits.associateBy({ it }, { mutableSetOf() })

    init {
        openBoard()
    }

    fun openSlots(): Set<Card> { return board.values.flatten().toSet() }

    fun play(cardToPlay: Card) {
        if (cardToPlay == openingCard) { openBoard() }
        when { canPlay(cardToPlay) -> { playOnBoard(cardToPlay) } }
    }

    private fun canPlay(cardToPlay: Card): Boolean {
        return board.getValue(cardToPlay.suit).contains(cardToPlay)
    }

    private fun playOnBoard(cardToPlay: Card) {
        val slotsForSuit = slotsForSuit(cardToPlay.suit)
        slotsForSuit.remove(cardToPlay)
        slotsForSuit.addAll(cardToPlay.nextCards())
    }

    private fun openSuit(suit: Suit) {
        when { slotsForSuit(suit).isEmpty() -> slotsForSuit(suit).add(Card(suit, 7)) }
    }

    private fun slotsForSuit(suit: Suit) = board[suit] ?: error("suit should always be present")

    private fun openBoard() {
        if (slotsForSuit(openingCard.suit).isEmpty()) {
            openSuit(openingCard.suit)
        } else if (slotsForSuit(openingCard.suit).isNotEmpty()) {
            Suit.values().forEach { openSuit(it) }
        }
        //else - board is already open
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
                .filter { it.rank in 1..13 }
}

