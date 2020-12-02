package com.jb.cpwp

import java.lang.StrictMath.abs

data class Card(val suit: Suit, val rank: Int){
    override fun toString(): String = "$rank of $suit"

    //TODO - name suggests multiple is a normal case, but its a special case
    fun nextCards(): List<Card> {
        return if (this.rank == 7) {
            listOf(
                    Card(this.suit, this.rank - 1),
                    Card(this.suit, this.rank + 1))
        } else
            listOf(Card(this.suit, rank + incrementBy())).filter{ it.rank in 1..13}

    }

    private fun incrementBy(): Int {
        return abs(rank - 7) / (rank - 7)
    }
}



