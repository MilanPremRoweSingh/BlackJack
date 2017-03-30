import java.util.ArrayList;
import java.util.Collections;

// Class to represent the notion of a pile of cards.
public class CardPile {
  
  // Store all the cards into a Card[] "behind the scenes"
  private ArrayList<Card> cards; 
  
  // store how many cards are non null (assuming no one inserts null by calling add(null)
  private int numCards;
  private static final int ARRAY_SIZE = 52;
  
  // initialize the private properties of the CardPile object
  public CardPile() {
    this.cards = new ArrayList<>();
    this.numCards = 0;
  }
  
  // return how many cards are non null.
  public int getNumCards() {
   return this.numCards; 
  }
  
  //since we always make sure all "real" cards (i.e. non-null values) are at the front of
  //the array, we can use the numCards variable
  public  void addToBottom(Card c) {
    this.cards.add(c);
    numCards++;
  }
 
  // Check if there are any cards in this card pile and return a boolean
  public boolean isEmpty() {
   return this.numCards == 0; 
  }
  
  // Get the ith Card. It is possible this will return null or throw an exception if the 
  // index i is less than 0 or greater than numCards - 1
  public Card get(int i) {
   return this.cards.get(i); 
  }

  // Search for a specific element by suit and value.
  public int find(Suit s, Value v) {
    // iterate over every card.
   for (int i = 0; i < this.numCards; i++)
   {
    if (this.cards.get(i).getSuit() == s && this.cards.get(i).getValue() == v)
    {
      return i;
    }
   }
   
   return -1;
  }
  
  // Delete the ith card from the cards array and then
  // fill in the "holes" so that subsequent cards are at an index 1 previous
  public Card remove(int i) {
    // store the card so that we can remove it.
   Card toRemove = this.cards.get(i);
   
   this.cards.remove(i);
   
   // decrease numCards by 1.
   this.numCards--;
   return toRemove;
  }
  
  public String toString() {
   String combined = "";
   for (int i = 0; i < this.numCards; i++) {
     combined = combined + "cards[" + i + " ] = " + this.cards.get(i) + ", ";
   }
   
   //useful to print the total number
   combined = combined + " Total number: " + this.numCards;
   return combined;
  }
  
  public static CardPile makeFullDeck() {
    // Create a CardPile object that we will return
    CardPile deck = new CardPile();
    // Every possible suit
    for (Suit s : Suit.values()) {
      // Every possible value
      for (Value v : Value.values()) {
        // add to the bottom of the deck
       deck.addToBottom(new Card(s,v)); 
      }
    }
    
    // use utility code to shuffle.
    Collections.shuffle(deck.cards);
    
    return deck;
  }
  
  public static CardPile makeFullDeck(int n){
    CardPile deck = new CardPile();
    // Every possible suit
    for(int i=0;i<n;i++){ 
      for (Suit s : Suit.values()) {
        // Every possible value
        for (Value v : Value.values()) {
          // add to the bottom of the deck
          deck.addToBottom(new Card(s,v)); 
        }
      }
    }
    
    // use utility code to shuffle.
    Collections.shuffle(deck.cards);
    
    return deck;  
  }
}