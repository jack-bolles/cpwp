package com.jb.cpwp

data class Deck (val cards: Set<Card> = standardDeckOf52()){

    companion object {
        fun standardDeckOf52(): Set<Card> = cardsForSuits(Suit.values()).shuffled().toSet()

        private fun cardsForSuits(suits: Array<Suit>) = suits.map { Suit.createSuit(it) }.flatten().toSet()
    }

    fun size(): Int = cards.size

    fun shuffle(): Deck = Deck(cards.shuffled().toSet())
}