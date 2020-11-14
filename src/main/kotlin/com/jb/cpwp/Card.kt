package com.jb.cpwp

data class Card(val suit: Suit, val rank: Int){
    override fun toString(): String = "$rank of $suit"
}



