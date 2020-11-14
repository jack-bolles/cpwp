package com.jb.cpwp

class Game(private val deck: Deck, private val players: Set<Player>) {
    fun deal() {
        val cards = deck.shuffle()
        val hands = cards.groupBy { cards.indexOf(it) % players.size }
        for(player in players){
            player.hand = hands[players.indexOf(player)]?.toSet() ?: error("Mis-deal!")
        }
    }
}

