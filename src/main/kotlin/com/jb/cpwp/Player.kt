package com.jb.cpwp

typealias Players = Set<Player>

data class Player(val name:String, var hand: Set<Card> = setOf()) {

    fun canPlay(table: Table) = (hand intersect table.availableSlots()).isNotEmpty()

    fun cardToPlay(slots: Set<Card>): Card? { return (hand intersect slots).firstOrNull() } //todo - from brute first to ???

    fun play(cardToPlay: Card) { hand = hand.minus(cardToPlay) }
}

