package com.jb.cpwp

typealias Players = Set<Player>

data class Player(val name:String, var hand: Set<Card> = setOf()) {

    fun canPlay(table: Table): Boolean { return canPlay(table.openSlots()) }

    private fun canPlay(slots: Set<Card>): Boolean { return (hand intersect slots).isNotEmpty() }

    fun cardToPlay(slots: Set<Card>): Card { return (hand intersect slots).first() } //todo - from brute first to ???
}