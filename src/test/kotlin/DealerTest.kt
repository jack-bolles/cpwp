import com.jb.cpwp.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class DealerTest {

    private val player1 = Player("First")
    private val player2 = Player("Second")
    private val player3 = Player("Third")
    private val player4 = Player("Fourth")
    private val player5 = Player("Fifth")
    private val player6 = Player("Sixth")
    private fun fourPlayers() = setOf(player1, player2, player3, player4)
    private fun sixPlayers() = setOf(player1, player2, player3, player4, player5, player6)

    @Test
    fun `create deck of 52`() {
        val deck = Deck(Deck.standardDeckOf52())
        assertEquals(52, deck.size())
    }

    @Test
    fun `understand equals in Kotlin`(){
        val deck1 = Deck()
        val deck2 = Deck(Deck.standardDeckOf52())
        assertEquals(deck1, deck2)  //data class or override equals()/hashcode()
        deck1.shuffle()
        assertEquals(deck1, deck2)
    }

    @Test
    fun `deal cards distributes all the cards across four players`(){
        val deck = Deck()
        val game = Game(deck, fourPlayers())
        game.deal()
        assertEquals(13, player1.hand.size)
        assertEquals(13, player2.hand.size)
        assertEquals(13, player3.hand.size)
        assertEquals(13, player4.hand.size)
        assertNotEquals(player1.hand, player2.hand)
        assertNotEquals(player1.hand, player3.hand)
        assertNotEquals(player1.hand, player4.hand)
        assertNotEquals(player2.hand, player3.hand)
        assertNotEquals(player2.hand, player4.hand)
        assertNotEquals(player3.hand, player4.hand)
    }
@Test
    fun `deal cards distributes all the cards across six players adding extra cards individually to as many players as necessary`(){
        val deck = Deck()
        val game = Game(deck, sixPlayers())
        game.deal()
        assertEquals(9, player1.hand.size)
        assertEquals(9, player2.hand.size)
        assertEquals(9, player3.hand.size)
        assertEquals(9, player4.hand.size)
        assertEquals(8, player5.hand.size)
        assertEquals(8, player6.hand.size)
    }

    @Test
    fun `shufflin`(){
        val deck = Deck(Deck.standardDeckOf52())
        val sixPlayers = sixPlayers()
        val game = Game(deck, sixPlayers)
        game.deal()

        for(player in sixPlayers){
            println(player.hand)
        }
    }

}