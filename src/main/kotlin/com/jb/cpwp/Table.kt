package com.jb.cpwp

import com.jb.cpwp.Game.Companion.openingCard

class Table(suits: Set<Suit>) {

    //todo - mutable bad?
    private val board: Map<Suit, MutableSet<Card>> = suits.associateBy({ it }, { mutableSetOf() })
    init{
      createBoard()
    }

    fun openSlots(): Map<Suit, Set<Card>> {
        return board
    }

    fun play(cardToPlay: Card) {
        if(board.getValue(cardToPlay.suit).contains(cardToPlay)){
            addToBoard(cardToPlay)
        }
        if(cardToPlay == openingCard){ openBoard() }
    }

    private fun addToBoard(cardToPlay: Card) {
        slotsForSuit(cardToPlay.suit).add(cardToPlay)
    }

    private fun slotsForSuit(suit: Suit) = board.getOrDefault(suit, mutableSetOf())

    private fun openBoard() {
        if (slotsForSuit(openingCard.suit).isNotEmpty()){
            Suit.values().forEach { openSuit(it) }
        }
        return
    }

    private fun createBoard(){
//        board.keys.associateBy ({ it },{ openSuit(it) })
        openSuit(openingCard.suit)
    }

    private fun openSuit(suit :Suit) {
        val slot = slotsForSuit(suit)
        if (slot.isEmpty()) {
            slot.add(Card(suit, 7))
        }
    }
}
