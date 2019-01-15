/*
 * David Graff 2018
 */
package uno.game;

import java.util.ArrayList;

/**
 *
 * @author david
 */
public class Player {
    private String name;
    private ArrayList<Card> hand;
    private boolean isBot;
    
    public Player(String name, boolean isBot){
        this.name = name;
        hand = new ArrayList<>();
        this.isBot = isBot;
    }
    
    public boolean isBot(){
        return isBot;
    }
    
    public void addCard(Card card){
        hand.add(card);
    }
    
    public boolean isValidMove(Card topCard, Card played){
        if(topCard.getNumber() != -1 && topCard.getNumber() == played.getNumber())
            return true;
        if(played.IsWild())
            return true;
        if(topCard.getColor() == played.getColor())
            return true;
        if(played.getType() != CardType.Normal && played.getType() == topCard.getType())
            return true;
        return false;
    }
    
    public boolean hasValidMove(Card topCard){
        for(Card card : hand)
            if(isValidMove(topCard, card))
                return true;
        return false;
    }
    
}
