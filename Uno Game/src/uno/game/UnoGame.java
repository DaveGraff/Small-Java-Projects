/*
 * David Graff 2018
 */
package uno.game;

import java.util.Scanner;

/**
 *
 * @author david
 */
public class UnoGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to Dave's UNO!");
        System.out.println("**********************\n\n");
        
        int playerNum = getInt("Please enter the number of players: ", 3, 10);
        int peopleNum = getInt("Please enter the number of human players: ", 3, playerNum);
        
        TurnOrder turnOrder = new TurnOrder(playerNum, peopleNum);
        
        Deck deck = initDeck();
        Deck discard = new Deck();
        Card topCard = deck.getTop(discard);deck.addToPile(topCard);
        
        boolean noWinner = true;
        Player current = turnOrder.getNext();
        while(noWinner){
            Card played = null;
            Player next = null;
            if(!current.hasValidMove(topCard)){
                current.addCard(deck.getTop(discard));
            } else if (current.isBot()){
                played = current.botTurn(topCard);
            } else {
                //Human turn
            }
            if(current.emptyHand()){
                noWinner = false;
            } else current = handleCardActions(played, turnOrder, deck, discard);
        }
        System.out.println("\n\n\n\n\n" + current.getName() + " has won!");
    }
    
    /**
     * Queries the user for an integer
     * @param request Request to be stated
     * @param lowRange Lowest acceptable number
     * @param highRange Highest acceptable integer
     * @return The users response
     */
    public static int getInt(String request, int lowRange, int highRange){
        boolean invalid = true;
        int input = lowRange - 1;
        while(invalid){
            System.out.println(request);
            Scanner sc = new Scanner(System.in);
            try{
                input = Integer.parseInt(sc.next());
            } catch (NumberFormatException e){}
            if(input >= lowRange && input <= highRange)
                invalid = false;
        }
        return input;
    }
    
    /**
     * Handles the result of a cards action and returns
     * the next player in the new order
     * @param played The card that was played
     * @param order The current turn order
     * @param deck The main deck
     * @param discard The discard pile
     * @return The next player's turn
     */
    public static Player handleCardActions(Card played, TurnOrder order, Deck deck, Deck discard){
        //Will always happen
        Player current = order.getCurrent();
        Player next;
        discard.addToPile(played);
        System.out.println(current.getName() + " has played a " + played.toString() + " card!");
        
        if(played.getType() == CardType.Reverse){
            System.out.println("The turn order has been reversed!");
            order.reverse();
        }
        next = order.getNext();
        switch (played.getType()) {
            case Skip:
                System.out.println(next.getName() + " has been skipped!");
                next = order.getNext();
                break;
            case Draw:
                System.out.println(next.getName() + " must draw 2 cards!");
                next.addCard(deck.getTop(discard));
                next.addCard(deck.getTop(discard));
                break;
            case Wild:
            case Wild_4:
                if(played.getType() == CardType.Wild_4){
                    System.out.println(next.getName() + " must draw 4 cards!");
                    next.addCard(deck.getTop(discard));next.addCard(deck.getTop(discard));
                    next.addCard(deck.getTop(discard));next.addCard(deck.getTop(discard));
                }   if(played.getColor() == CardColor.Wild)
                    played.setColor(findColor());
                break;
        }
        
        
            
        return next;
    }
    
    /**
     * A method in which the played chooses the
     * color for their wild card
     * @return The chosen color
     */
    public static CardColor findColor(){
        Scanner sc = new Scanner(System.in);
        boolean invalid = true;
        while(invalid){
            System.out.println("Please pick the color of you choice; Red, Blue, Green or Yellow: ");
            String input = sc.next().toLowerCase();
            switch(input){
                case "r":
                case "red": return CardColor.Red;
                case "b":
                case "blue": return CardColor.Blue;
                case "g":
                case "green": return CardColor.Green;
                case "y":
                case "yellow": return CardColor.Yellow;
                default:
            }
        }
        return null;//Will never happen
    }
    
    /**
     * Initializes the main deck object with
     * a standard UNO deck
     * @return 
     */
    public static Deck initDeck(){
        Deck deck = new Deck();
        
        
        
        
        
        
        
        
        return deck;
    }
}
