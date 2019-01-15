/*
 * David Graff 2018
 */
package uno.game;

import java.util.Random;
import java.util.Stack;

/**
 *
 * @author david
 */
public class TurnOrder {
    private Stack<Player> players;
    
    public TurnOrder(int playerNum, int peopleNum){
        Stack<Player> temp = new Stack<>();
        for(int i=1; i <= peopleNum; i++)
            temp.add(new Player("Player " + i, false));
        for(int i=0; i <= playerNum - peopleNum; i++)
            temp.add(new Player("Bot " + i, true));
        
        players = new Stack<>();
        Random rand = new Random();
        while(!temp.empty()){
            int index = rand.nextInt(temp.size());
            players.add(temp.get(index));
            temp.remove(index);
        }  
    }
    
    /**
     * Moves the order to the next person to go
     * @return The next player
     */
    public Player getNext(){
        Player next = players.pop();
        players.add(next);
        return next;
    }
    
    /**
     * 
     * @return The player who is making their move
     */
    public Player getCurrent(){
        return players.elementAt(players.capacity()-1);
    }
    
    /**
     * Reverses the order as done by the Reverse card
     */
    public void reverse(){
        Stack<Player> temp = new Stack<>();
        while(!players.isEmpty()){
            Player tempPlayer = players.get(players.size()-1);
            players.remove(tempPlayer);
            temp.add(tempPlayer);
        }
        players = temp;
    }
}
