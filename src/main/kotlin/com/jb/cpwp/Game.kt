package com.jb.cpwp

class Game(names: Set<String>, deck: Deck = Deck(Deck.shuffledDeckOf52())) {
    var table = Table()
    val players:Players = seatTheTable(names, deck)

    fun playCard(player: Player, cardToPlay: Card) {
        //todo - ensure guard moved to ext function. Should guard for canPlay() here? before calling?
        //return null/current instance of can't or throw something?
        player.play(cardToPlay)
        table = table.play(cardToPlay)
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

fun Game.takeTurn(player: Player) {
    beforeCardIsPlayed(player, table)

    val cardToPlay = player.cardToPlay(table.availableSlots()) ?:
        return //future strategery to come here

    playCard(player, cardToPlay)

    afterCardIsPlayed(player, cardToPlay, table)
}

private fun beforeCardIsPlayed(player: Player, table: Table) {
    println("Open Slots: ${table.availableSlots()}")
    if (!player.canPlay(table)) {println("${player.name} can't play: ${player.hand}") }
    println()
}

private fun afterCardIsPlayed(player: Player, cardToPlay: Card?, table: Table) {
    println("${player.name} plays the $cardToPlay")
    println("and is left holding: ${player.hand}")
    println(table.availableSlots())
    println()
}

fun <T> Set<T>.pivot(cards: Set<Card>): Set<List<Card>> {
    return cards.groupBy { cards.indexOf(it) % size }.values.toSet()
}