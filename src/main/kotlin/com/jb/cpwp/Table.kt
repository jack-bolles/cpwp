package com.jb.cpwp

import com.jb.cpwp.Suit.DIAMONDS

class Table(suits: Set<Suit>) {

    fun openSlots(): Map<Suit, List<Card>> {
        return mapOf(DIAMONDS to listOf(Game.openingCard))
    }

}
