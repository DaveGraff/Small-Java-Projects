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
    
    public String getName(){
        return name;
    }
    
    public boolean isBot(){
        return isBot;
    }
    
    public void addCard(Card card){
        hand.add(card);
    }
    
    /**
     * Checks whether playing a given card is a 
     * valid move
     * @param topCard The current top card
     * @param played The card that would be played
     * @return True if it is a valid move, false if not
     */
    public boolean isValidMove(Card topCard, Card played){
        if(topCard.getType() == CardType.Normal && topCard.getNumber() == played.getNumber())
            return true;
        if(played.getType() == CardType.Wild || played.getType() == CardType.Wild_4)
            return true;
        if(topCard.getColor() == played.getColor())
            return true;
        if(played.getType() != CardType.Normal && played.getType() == topCard.getType())
            return true;
        return false;
    }
    
    /**
     * Checks whether the player has a valid move
     * @param topCard the current top card
     * @return True if there is a valid move, false if not
     */
    public boolean hasValidMove(Card topCard){
        for(Card card : hand)
            if(isValidMove(topCard, card))
                return true;
        return false;
    }
    
    /**
     * Logic for the bot turn. See order
     * of decisions below.
     * @param topCard The top card in the deck
     * @return The card to be played
     */
    public Card botTurn(Card topCard){
        //Play a normal card if possible
        for(Card card : hand)
            if(card.getType() == CardType.Normal && (card.getColor() == topCard.getColor() || card.getNumber() == topCard.getNumber()))
                return card;
        //Play a special card thats not wild if possible
        for(Card card : hand)
            if(card.getType() != CardType.Normal && (card.getColor() == topCard.getColor() || card.getType() == topCard.getType()))
                if(card.getType() != CardType.Wild && card.getType() != CardType.Wild_4)
                    return card;
        //Play a wild card
        for(Card card : hand)
            if(card.getType() == CardType.Wild || card.getType() == CardType.Wild_4){
                botChooseWildColor(card);
                return card;
            }
        /*for(Card card : hand)
            if(isValidMove(topCard, card))
                return card;*/
        //Should never happen
        return null;
    }
    
    /**
     * Logic for the bot to choose which color to
     * make the wildcard. It will always pick the 
     * color that it has the most of.
     * @param wildCard The wildcard that has been played
     */
    public void botChooseWildColor(Card wildCard){
        int red=0, blue=0, green=0, yellow=0;
        for(Card card : hand){
            switch(card.getColor()){
                case Red: red++;
                break;
                case Blue: blue++;
                break;
                case Green: green++;
                break;
                case Yellow: yellow++;
            }
        }
        if(red > blue && red > green && red > yellow)
            wildCard.setColor(CardColor.Red);
        else if(blue > green && blue > yellow)
            wildCard.setColor(CardColor.Blue);
        else if(green > yellow)
            wildCard.setColor(CardColor.Green);
        else wildCard.setColor(CardColor.Yellow);
    }
    
    public boolean emptyHand(){
        return hand.isEmpty();
    }
    
    @Override
    public String toString(){
        String output = "";
        for(Card card : hand)
            output = output.concat(card.toString() + ", ");
        return output.substring(0, output.length()-2);
    }
    
    public ArrayList<Card> getHand(){
        return hand;
    }
}
