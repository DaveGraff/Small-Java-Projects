/*
 * David Graff 2018
 */
package uno.game;

import java.util.Collections;
import java.util.Stack;

/**
 *
 * @author david
 */
public class Deck {
    private Stack<Card> cards;
    
    public Deck(){
        cards = new Stack<>();
    }
    
    /**
     * Adds any number of cards to the deck.
     * @param cards A list of cards to be added
     */
    public void addToPile(Card... cards){
        Collections.addAll(this.cards, cards);
    }
    
    /**
     * 
     * @return true if the deck is empty, false if not
     */
    public boolean isEmpty(){
        return cards.isEmpty();
    }
    
    /**
     * Draws a card from the deck. If the deck is empty, it
     * consumes the discard pile and then draws. It is assumed that
     * not all cards will be in players hands
     * @param discard The discard pile
     * @return The drawn card
     */
    public Card getTop(Deck discard){
        if(cards.isEmpty())
            while(!discard.getCards().empty()){
                Card current = discard.getCards().pop();
                if(current.getType() == CardType.Wild || current.getType() == CardType.Wild_4)
                    current.setColor(CardColor.Wild);
                cards.add(current);
                shuffle();
            }
        return cards.pop();
    }
    
    /**
     * Shuffles the cards of a deck in random order
     */
    public void shuffle(){
        Collections.shuffle(cards);
    }
    
    public Stack<Card> getCards(){
        return cards;
    }
    
    @Override
    public String toString(){
        String output = "";
        for(Card card : cards)
            output = output.concat(card.toString() + ", ");
        return output.substring(0, output.length()-1);
    }
}
