public class CardGame{
  public static void main(String[] args){
    CardPile deck = CardPile.makeFullDeck();
    int players = Integer.parseInt(args[0]);
    CardPile[] hands = new CardPile[players];
    for (int j=0;j<players;j++) {hands[j] = new CardPile();};
    mainLoop:
    while (1<2) {
      for (int i=0;i<players;i++){ 
        if ((deck.isEmpty())) {break mainLoop;};
        hands[i].addToBottom(deck.get(0));
        deck.remove(0);
      }
    }
    for (int i=0;i<players;i++){
      if (hands[i].find(Suit.SPADES,Value.ACE) > -1) { 
        System.out.println("Player " + i);
      }
    }
  }
}