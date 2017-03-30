import java.util.Scanner;

public class Blackjack{
  
  public enum Results{
  DEALER_WINS,
    PLAYER_WINS,
    TIE,
    BLACKJACK
}
  
  public static void main(String[] args){
    CardPile deck = CardPile.makeFullDeck(4);
    Scanner scan = new Scanner(System.in);
    double pile = Integer.parseInt(args[0]);
    int bet = 0;
    
    while ((deck.getNumCards() > 10)&&(pile>0)){
      System.out.println("CURRENT PILE:"+pile);
      bet = scan.nextInt();
      if (bet < -1){break;}
      while (bet > pile){
        System.out.println("BET EXCEEDS PILE("+pile+"), BET AGAIN");
        bet = scan.nextInt();
      }                   
      switch (playRound(deck)){
        case DEALER_WINS: 
          pile-=bet;
          break;
        case PLAYER_WINS: 
          pile+=bet;
          break;
        case BLACKJACK: 
          pile+=bet*1.5;
          break;
      }
      System.out.println("");
      if (pile>0) {
        System.out.println("NEW ROUND");
      } else {
        System.out.println("GAME OVER");
      }
    }
  }
  
  
  public static int getScore(Card card){
    Value value = card.getValue();
    switch (value) {
      case TWO: return 2;
      case THREE: return 3;
      case FOUR: return 4;
      case FIVE: return 5;
      case SIX: return 6;
      case SEVEN: return 7;
      case EIGHT: return 8;
      case NINE: return 9;
      case TEN: return 10;
      case JACK: return 10;
      case QUEEN: return 10;
      case KING: return 10;
      case ACE: return 11;
    }      
   return -1; //only happens if card is invalid, is default value
  }
  
  public static int countValues(CardPile hand){
    int returnInt = 0;
    int incNum = 0;
    int num = 0;
    for (int i=0;i<hand.getNumCards();i++){
      num = getScore(hand.get(i));
      returnInt+=num;
      if (num == 11){
        incNum++;
      }
      }
    while ((incNum>0)&&(returnInt>21)){
      returnInt-=10;
      incNum--;
    }

    return returnInt;
  }

  
  public static Results playRound(CardPile deck){
    //Deal to each
    CardPile playerHand = new CardPile();
    CardPile dealerHand = new CardPile();
    for(int i=0;i<4;i++){
      if (i<2){
        playerHand.addToBottom(deck.get(0));
        deck.remove(0);
      } else {
        dealerHand.addToBottom(deck.get(0));
        deck.remove(0);
      }
    }
    //Print info @ time
    System.out.println("DEALER CARD:");
    System.out.println(dealerHand.get(0).toString());
    System.out.println("PLAYER HAND:");
    System.out.println(playerHand.toString());
    
    //Check for black jack victories
    if (countValues(playerHand)==21) {
      if (countValues(dealerHand)==21) {
        System.out.println("DEALER HAND:");
        System.out.println(dealerHand.toString());
        System.out.println("RESULT: TIE");
        return Results.TIE; //exits the method
      } else {
        System.out.println("DEALER HAND:");
        System.out.println(dealerHand.toString());
        System.out.println("RESULT: PLAYER WINS");
        return Results.BLACKJACK;
      }
    }
    //Player plays
    Scanner scan = new Scanner(System.in);
    String input = "";
    while (!(input.equals("stay"))) {
      input=scan.next();
      System.out.println(input);
      if (input.equals("hit")) {
        System.out.println(input);
        playerHand.addToBottom(deck.get(0));
        deck.remove(0);
        System.out.println("PLAYER HAND:");
        System.out.println(playerHand.toString());
        if (countValues(playerHand)>21) {
          System.out.println("PLAYER BUST");
          System.out.println("PLAYER HAND:");
          System.out.println(playerHand.toString());
          System.out.println("DEALER HAND:");
          System.out.println(dealerHand.toString());
          System.out.println("DEALER WINS:");
          return Results.DEALER_WINS;
        }
      } 
    }
    while (countValues(dealerHand)<18) {
      dealerHand.addToBottom(deck.get(0));
      deck.remove(0);
      if (countValues(dealerHand)>21) {
        System.out.println("DEALER BUST");
        System.out.println("DEALER HAND:");
        System.out.println(dealerHand.toString());
        System.out.println("RESULT: PLAYER WINS");
        return Results.PLAYER_WINS;
      }
    }
    if (countValues(dealerHand)<countValues(playerHand)){
      System.out.println("DEALER HAND:");
      System.out.println(dealerHand.toString());
      System.out.println("RESULT: PLAYER WINS");
      return Results.PLAYER_WINS;
    } else if (countValues(dealerHand)==countValues(playerHand)) {
      System.out.println("DEALER HAND:");
      System.out.println(dealerHand.toString());
      System.out.println("RESULT: TIE");
      return Results.TIE;
    } else {
      System.out.println("DEALER HAND:");
      System.out.println(dealerHand.toString());
      System.out.println("RESULT: DEALER WINS");
      return Results.DEALER_WINS;
    }
  }
  
}
    



