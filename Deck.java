public class Deck {
    private Card[] deck;
    private int cardsUsed;

    public Deck(){
        this(false);
    }

    public Deck(boolean includeJokers){
        if(includeJokers) deck=new Card[54];
        else deck=new Card[52];
        int cardCt=0;
        for(int suit=0;suit<=3;suit++){
            for(int value=1;value<=13;value++){
                deck[cardCt++] = new Card(value,suit);
            }
        }
        if(includeJokers){
            deck[52]=new Card(1,Card.JOKER);
            deck[53]=new Card(2,Card.JOKER);
        }
        cardsUsed=0;
    }
    public void shuffle()
    {
        for(int i=deck.length-1;i>0;i--)
        {
            int rand=(int) (Math.random() * (i+1));
            Card temp=deck[i];
            deck[i]=deck[rand];
            deck[rand]=temp;
        }
        cardsUsed=0;
    }
     //how many cards are left
     public int cardsLeft()
     {
         return deck.length - cardsUsed;
     }
     //remove next card from deck and return it
     public Card dealCard()
     {
         if(cardsLeft() ==0) throw new IllegalStateException("No cards are left in the deck");
         return deck[cardsUsed++];
     }
     //does this deck have jokers?
     public boolean hasJokers()
     {
         return(deck.length==54);
     }

}
