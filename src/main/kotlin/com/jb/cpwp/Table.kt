package com.jb.cpwp

import com.jb.cpwp.Suit.*

class Table(suits: Set<Suit>) {

    //todo - mutable bad?
    private val suitRows = suits.associateBy<Suit, Suit, MutableSet<Card>>({ it }, { mutableSetOf() })

    fun openSlots(): Map<Suit, List<Card>> {
        return if (suitRows[HEARTS]?.isEmpty()!!)
            mapOf(HEARTS to listOf(Game.openingCard))
        else
            mapOf(
                    HEARTS to listOf(Card(HEARTS, 6), Card(HEARTS, 8)),
                    DIAMONDS to listOf(Card(DIAMONDS, 7)),
                    CLUBS to listOf(Card(CLUBS, 7)),
                    SPADES to listOf(Card(SPADES, 7)),
            )
    }

    fun play(card: Card) {
        val set = suitRows[card.suit]
        set?.add(card)
    }

}
