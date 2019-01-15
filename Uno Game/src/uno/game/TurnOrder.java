/*
 * David Graff 2018
 */
package uno.game;

import java.util.Stack;

/**
 *
 * @author david
 */
public class TurnOrder {
    private Stack<Player> players;
    
    public TurnOrder(int playerNum, int peopleNum){
        for(int i=1; i <= peopleNum; i++)
            players.add(new Player("Player " + i, false));
        for(int i=0; i <= playerNum - peopleNum; i++)
            players.add(new Player("Bot " + i, true));
    }
    
    public Player getNext(){
        Player next = players.pop();
        players.add(next);
        return next;
    }
    
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
