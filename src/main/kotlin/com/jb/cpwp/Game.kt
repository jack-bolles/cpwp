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
        fun whoStarts(players: Players): Player = players.single { it.hand.contains(openingCard) }
    }
}

fun takeTurn(table: Table, player: Player) {
    val cardToPlay = player.cardToPlay(table.openSlots()) //future strategery to come here
    cardToPlay?.let { player.play(it) }
    cardToPlay?.let { table.play(it) }

    followAlong(player, cardToPlay, table)
}

fun takeTurnIntermediate(table: Table, player: Player) {
    when {
        player.canPlay(table) -> {
            val cardToPlay = player.cardToPlay(table.openSlots()) //future strategery to come here
            when {
                cardToPlay != null -> {
                    player.play(cardToPlay)
                    table.play(cardToPlay)

                    followAlong(player, cardToPlay, table)
                }
            }
        }

    }
}

//todo here for conversation
fun takeTurnBeforeNulling(table: Table, player: Player) {
    when {
        player.canPlay(table) -> {
            val cardToPlay = player.cardToPlay(table.openSlots()) //future strategery to come here
//            player.play(cardToPlay)
//            table.play(cardToPlay)

            println("${player.name} plays the $cardToPlay")
            println("and is left holding: ${player.hand}")
            println(table.openSlots())
            println()

        }
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