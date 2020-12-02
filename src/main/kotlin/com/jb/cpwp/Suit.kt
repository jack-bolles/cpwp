package com.jb.cpwp

enum class Suit {
    HEARTS,
    DIAMONDS,
    CLUBS,
    SPADES;

    companion object {
        fun createSuit(suit: Suit): Set<Card> {
            return (1..13)
                    .map { Card(suit, it) }
                    .toSet()
        }
    }
}
