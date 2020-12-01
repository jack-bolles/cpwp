package com.jb.cpwp

typealias Players = Set<Player>

data class Player(val name:String, var hand: Set<Card> = setOf()) {

    fun canPlay(table: Table): Boolean {
        //determine if player has a card for a row
        val slots = table.openSlots().values.flatten()
        return (hand intersect slots).isNotEmpty()
    }
}
