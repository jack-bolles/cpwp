package com.jb.cpwp

typealias Players = Set<Player>

fun seatPlayers(vararg players: Player): Players { return players.toSet() }


data class Player(val name:String) {
    fun withHand(hand: Set<Card>): Player {
        return Player(name, hand)
    }

    fun canPlay(table: Table): Boolean {
        //for each suit in the hand
        //determine if player has a card for a row
        //if yes, true
        val slotsMap: Map<Suit, List<Card>> = table.openSlots()
        val slots = slotsMap.values.flatten()
        return hand.intersect(slots).isNotEmpty()

    }

    constructor(name: String, hand: Set<Card>) : this(name) {
        this.hand = hand
    }

    lateinit var hand: Set<Card>
}
