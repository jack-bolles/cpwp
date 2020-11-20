package com.jb.cpwp

class Game(private val deck: Deck) {
    val table = Table(deck.suits())

    fun deal(players: Players): Players {
        val hands = players.pivot(deck.cards)
        return players.zip(hands).map { (player, hand) -> player.withHand(hand) }.toSet()
    }

    companion object {
        val openingCard = Card(Suit.HEARTS, 7)

        fun whoStarts(players: Players): Player {
            return players.single { it.hand.contains(openingCard) }
        }
    }
}

fun <T> Set<T>.pivot(cards: Set<Card>): Set<List<Card>> {
    return cards.groupBy { cards.indexOf(it) % size }.values.toSet()
}