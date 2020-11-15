package com.jb.cpwp

import com.jb.cpwp.Suit.*

class Table(suits: Set<Suit>) {

    fun open(): Boolean {
        return false
    }

    fun openSlots(): Map<Suit, List<Card>> {
        return mapOf(DIAMONDS to listOf(Game.openingCard))
    }

}
