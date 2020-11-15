package com.jb.cpwp

class Game(private val deck: Deck) {

    fun deal(players: Players): Players {
        val hands = players.pivot(deck.cards)
        return players.zip(hands).map { (player, hand) -> player.withHand(hand.toSet()) }.toSet()
    }
    
    companion object {
        fun whoStarts(players: Players) =
                players.single { it.hand.contains(Card(Suit.HEARTS, 7)) }
    }
}

fun <T> Set<T>.pivot(cards: Set<Card>): Collection<List<Card>> {
    return cards.groupBy { cards.indexOf(it) % size }.values
}

