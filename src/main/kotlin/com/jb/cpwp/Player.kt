package com.jb.cpwp

typealias Players = Set<Player>

fun seatPlayers(vararg players: Player): Players { return players.toSet() }

fun Players.nextPlayer(): Player {
    //TODO("Not yet implemented")
    return this.iterator().next()
}


data class Player(val name:String) {
    fun withHand(hand: Set<Card>): Player {
        return Player(name, hand)
    }

    fun canPlay(table: Table): Boolean {
        //for each suit in the hand
        //determine if player has a card for a row
        //if yes, true
        val slotsMap: Map<Suit, List<Card>> = table.openSlots()
        val slots = slotsMap.values.flatten()

//        if(!table.open()){
//            return hand.contains(Card(Suit.DIAMONDS, 7))
//        }
        val intersect = hand.intersect(slots)

        return intersect.isNotEmpty()

    }

    constructor(name: String, hand: Set<Card>) : this(name) {
        this.hand = hand
    }

    lateinit var hand: Set<Card>
}
