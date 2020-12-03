package com.jb.cpwp

typealias Players = Set<Player>

data class Player(val name:String, var hand: Set<Card> = setOf()) {

    fun canPlay(table: Table): Boolean { return canPlay(table.openSlots()) }

    fun cardToPlay(slots: Set<Card>): Card? { return (hand intersect slots).firstOrNull() } //todo - from brute first to ???

    fun play(cardToPlay: Card) { hand = hand.minus(cardToPlay) }

    private fun canPlay(slots: Set<Card>): Boolean { return (hand intersect slots).isNotEmpty() }
}

