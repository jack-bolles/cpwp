package com.jb.cpwp

class Game(private val deck: Deck = Deck(Deck.standardDeckOf52())) {
    val table = Table(deck.suits())

    fun setTheTable(names: Set<String>): Players {
        val hands = names.pivot(this.deck.cards)
        val createPlayer: (Pair<String, List<Card>>)
            -> Player = { (name, hand) -> Player(name, hand.toSet()) }
        return names.zip(hands).map(createPlayer).toSet()
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