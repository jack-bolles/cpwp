package com.jb.cpwp

import com.jb.cpwp.Game.Companion.openingCard

class Table(cards: Set<Card>) {

    constructor() : this(setOf(openingCard))

    private val board: Map<Suit, Set<Card>> = cards.groupBy { it.suit }.mapValues { it.value.toSet() }

    fun availableSlots(): Set<Card> {
        return board.values.flatten().toSet()
    }

    fun play(cardToPlay: Card): Table {
        fun canPlay(cardToPlay: Card): Boolean { return board.getValue(cardToPlay.suit).contains(cardToPlay) }

        return when {
            !canPlay(cardToPlay) -> this
            else -> when (cardToPlay) {
                openingCard -> newBoard(cardToPlay.suit, playOnBoard(cardToPlay)).openBoard()
                else -> newBoard(cardToPlay.suit, playOnBoard(cardToPlay))
            }
        }
    }

    private fun newBoard(suit: Suit, newRow: Set<Card>): Table {
        val currentSlots = board.filterKeys { it != suit }
        val oldAndNewSlots = newRow.plus(currentSlots.values.flatten()).toSet()
        return Table(oldAndNewSlots)
    }

    private fun playOnBoard(cardToPlay: Card): Set<Card> {
        val slotsForSuit = slotsForSuit(cardToPlay.suit).toMutableSet()
        slotsForSuit.remove(cardToPlay)
        slotsForSuit.addAll(cardToPlay.nextCards())
        return slotsForSuit.toSet()
    }

    private fun slotsForSuit(suit: Suit): Set<Card> {
        return board[suit] ?: emptySet()
    }

    private fun openBoard(): Table {
        return when {
            slotsForSuit(openingCard.suit).isEmpty() -> {
                newBoard(openingCard.suit, openSuit(openingCard.suit))
            }
            slotsForSuit(openingCard.suit).isNotEmpty() -> {
                val openSlots: Map<Suit, Set<Card>> = openSuits()
                Table(openSlots.values.flatten().toSet())
            }
            else -> this
        }
    }

    private fun openSuits(): Map<Suit, Set<Card>> {
        Suit.values().forEach { openSuit(it) }
        val openingSlot: Set<Card> = slotsForSuit(openingCard.suit)
        val toMap: Map<Suit, Set<Card>> = Suit.values()
                .filterNot { it == openingCard.suit }
                .map { it to openSuit(it) }
                .toMap()
        return toMap.plus(Pair(openingCard.suit, openingSlot))
    }

    private fun openSuit(suit: Suit): Set<Card> {
        val slotsForSuit = slotsForSuit(suit)
        return when {
            slotsForSuit.isEmpty() -> { setOf(Card(suit, 7)) }
            else -> slotsForSuit
        }
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

