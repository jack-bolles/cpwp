package com.jb.cpwp

data class Deck (val cards: Set<Card> = standardDeckOf52()){

    fun size(): Int = cards.size

    fun shuffle(): Set<Card> = cards.shuffled().toSet()

    companion object {
        fun standardDeckOf52(): Set<Card> = cardsForSuits(Suit.values())

        private fun cardsForSuits(suits: Array<Suit>): Set<Card> = suits.map { Suit.createSuit(it) }.flatten().toSet()

    }
}