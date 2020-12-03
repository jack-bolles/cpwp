package com.jb.cpwp

//TODO - could be a typealias... how do companion objects work with TA?
data class Deck (val cards: Set<Card> = shuffledDeckOf52()){
    fun suits(): Set<Suit> {
        return cards.map { it.suit}.toSet()
    }

    companion object {
        fun shuffledDeckOf52(): Set<Card> = standardDeckOf52().shuffled().toSet()
        fun standardDeckOf52() = cardsForSuits(Suit.values()).toSet()
        private fun cardsForSuits(suits: Array<Suit>) = suits.map { Suit.createSuit(it) }.flatten().toSet()
    }
}