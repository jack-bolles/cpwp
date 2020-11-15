package com.jb.cpwp

class Game(private val deck: Deck) {

    fun deal(players: Set<Player>): Set<Player> {
        val hands = players.pivot(deck.cards)
        return players.zip(hands).map { (player, hand) -> player.withHand(hand.toSet()) }.toSet()
    }
}

fun <T> Set<T>.pivot(cards: Set<Card>): Collection<List<Card>> {
    return cards.groupBy { cards.indexOf(it) % size }.values
}

