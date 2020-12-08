package com.jb.cpwp

class Game(names: Set<String>, deck: Deck = Deck(Deck.shuffledDeckOf52())) {
    val table = Table(deck.suits())
    val players:Players = seatTheTable(names, deck)

    fun takeTurn(player: Player) {
        val cardToPlay = player.cardToPlay(table.openSlots()) ?:
        return //future strategery to come here

        player.play(cardToPlay)
        table.play(cardToPlay)

        followAlong(player, cardToPlay, table)
    }

    fun whoStarts() = players.single { it.hand.contains(openingCard) }

    private fun seatTheTable(names: Set<String>, deck: Deck): Players {
        val hands = names.pivot(deck.cards)
        return names
                .zip(hands)
                .map { (name, hand) -> Player(name, hand.toSet()) }
                .toSet()
    }

    companion object {
        val openingCard = Card(Suit.HEARTS, 7)
    }
}

private fun followAlong(player: Player, cardToPlay: Card?, table: Table) {
    println("${player.name} plays the $cardToPlay")
    println("and is left holding: ${player.hand}")
    println(table.openSlots())
    println()
}

fun <T> Set<T>.pivot(cards: Set<Card>): Set<List<Card>> {
    return cards.groupBy { cards.indexOf(it) % size }.values.toSet()
}