package com.jb.cpwp

typealias Players = Set<Player>

data class Player(val name:String, var hand: Set<Card> = setOf()) {

    fun canPlay(table: Table): Boolean {
        //for each suit in the hand
        //determine if player has a card for a row
        //if yes, true
        val slotsMap: Map<Suit, List<Card>> = table.openSlots()
        val slots = slotsMap.values.flatten()
        return hand.intersect(slots).isNotEmpty()

    }
}
