package com.jb.cpwp

data class Player(val name:String) {
    fun withHand(hand: Set<Card>): Player {
        return Player(name, hand)
    }

    constructor(name: String, hand: Set<Card>) : this(name) {
        this.hand = hand
    }

    lateinit var hand: Set<Card>
}
