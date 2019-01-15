/*
 * David Graff 2018
 */
package uno.game;

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
    
    public void addToPile(Card card){
        cards.add(card);
    }
    
    public boolean isEmpty(){
        return cards.isEmpty();
    }
    
    public Card getTop(){
        return cards.pop();
    }
    
    public void addToPile(Deck deck){
        while(!deck.isEmpty())
            cards.add(deck.getTop());
    }
}
