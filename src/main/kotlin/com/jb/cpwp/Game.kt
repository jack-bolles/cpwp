package com.jb.cpwp

class Game(private val deck: Deck, private val players: Set<Player>) {
    fun deal() {
        val cards = deck.shuffle().cards
        val hands = cards.groupBy { cards.indexOf(it) % players.size }

        //TODO - see q in slack; understand suggested behaviour
        for(player in players){
            player.hand = hands[players.indexOf(player)]?.toSet() ?: error("Mis-deal!")
        }
    }

    //suggested by Dave in Slack
//    val hands  = cards.groupBy { cards.indexOf(it) % players.size }.values
//    val playersWithHands = players.zip(hands).map { (player, hand) ->
//        player.withHand(hand)
//    }
}

