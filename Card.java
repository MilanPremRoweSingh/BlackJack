public class Card{
  private Suit suit;
  private Value value;
  
  public Card(Suit theSuit, Value theValue){
    suit = theSuit;
    value = theValue;
  }
  
  public Suit getSuit(){
    return suit;
  }

  public Value getValue(){
    return value;
  }
  
  public String toString(){
    return (value +" of "+ suit);
  }
}