package com.jb.cpwp

typealias Players = Set<Player>

fun seatPlayers(vararg players: Player): Players { return players.toSet() }

data class Player(val name:String) {
    fun withHand(hand: Set<Card>): Player {
        return Player(name, hand)
    }

    constructor(name: String, hand: Set<Card>) : this(name) {
        this.hand = hand
    }

    lateinit var hand: Set<Card>
}
