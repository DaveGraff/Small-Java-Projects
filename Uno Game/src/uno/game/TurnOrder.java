/*
 * David Graff 2018
 */
package uno.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author david
 */
public class TurnOrder {
    private ArrayList<Player> players;
    
    public TurnOrder(int playerNum, int peopleNum){
        players = new ArrayList<>();
        for(int i=1; i < peopleNum+1; i++)
            players.add(new Player("Player " + i, false));
        for(int i=0; i < playerNum - peopleNum; i++)
            players.add(new Player("Bot " + i, true));
        
        System.out.println(players.size());
        Collections.shuffle(players);
    }
    
    /**
     * Moves the order to the next person to go
     * @return The next player
     */
    public Player getNext(){
        Player next = players.remove(0);
        players.add(next);
        return next;
    }
    
    /**
     * 
     * @return The player who is making their move
     */
    public Player getCurrent(){
        return players.get(players.size()-1);
    }
    
    /**
     * Reverses the order as done by the Reverse card
     */
    public void reverse(){
        Collections.reverse(players);
    }
    
    public void initHands(Deck deck, Deck discard){
        for(Player player : players)
            for(int i=0; i < 7; i++)
                player.addCard(deck.getTop(discard));
    }
}
