package com.jb.cpwp

class Game(private val deck: Deck = Deck(Deck.shuffledDeckOf52())) {
    val table = Table(deck.suits())

    fun setTheTable(names: Set<String>): Players {
        val hands = names.pivot(this.deck.cards)
        return names
                .zip(hands)
                .map { (name, hand) -> Player(name, hand.toSet()) }
                .toSet()
    }

    companion object {
        val openingCard = Card(Suit.HEARTS, 7)

        fun whoStarts(players: Players): Player {
            return players.single { it.hand.contains(openingCard) }
        }
    }
}

fun takeTurn(table: Table, player: Player) {
    when {
        player.canPlay(table) -> {
            val cardToPlay = player.cardToPlay(table.openSlots()) //future strategery to come here
            player.play(cardToPlay)
            table.play(cardToPlay)

            println("${player.name} is holding: ${player.hand}")
            println("They play the $cardToPlay")
            println(table.openSlots())
            println()

        }
    }
}

fun <T> Set<T>.pivot(cards: Set<Card>): Set<List<Card>> {
    return cards.groupBy { cards.indexOf(it) % size }.values.toSet()
}