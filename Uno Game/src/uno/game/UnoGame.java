/*
 * David Graff 2018
 */
package uno.game;

import java.util.ArrayList;
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
        int peopleNum = getInt("Please enter the number of human players: ", 1, playerNum);
        
        TurnOrder turnOrder = new TurnOrder(playerNum, peopleNum);
        
        Deck deck = initDeck();
        Deck discard = new Deck();
        Card topCard = deck.getTop(discard);deck.addToPile(topCard);
        turnOrder.initHands(deck, discard);
        
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
                played = handleHumanTurn(current, topCard);
            }
            if(current.emptyHand()){
                noWinner = false;
            } else if(played == null){}
            else{
                current = handleCardActions(played, turnOrder, deck, discard);
            }
            
            System.out.println("\n\n");
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
        
        for(int j = 0; j < 20; j++){
            int i = j;
            if(j >= 10)
                i += -9;
            Card red = new Card(CardType.Normal, CardColor.Red, i);
            Card blue = new Card(CardType.Normal, CardColor.Blue, i);
            Card green = new Card(CardType.Normal, CardColor.Green, i);
            Card yellow = new Card(CardType.Normal, CardColor.Yellow, i);
            deck.addToPile(red, blue, green, yellow);
        }
        
        for(int i=0; i<2; i++){
            Card redReverse = new Card(CardType.Reverse, CardColor.Red, i);
            Card blueReverse = new Card(CardType.Reverse, CardColor.Blue, i);
            Card greenReverse = new Card(CardType.Reverse, CardColor.Green, i);
            Card yellowReverse = new Card(CardType.Reverse, CardColor.Yellow, i);
            Card redSkip = new Card(CardType.Skip, CardColor.Red, i);
            Card blueSkip = new Card(CardType.Skip, CardColor.Blue, i);
            Card greenSkip = new Card(CardType.Skip, CardColor.Green, i);
            Card yellowSkip = new Card(CardType.Skip, CardColor.Yellow, i);
            Card redDraw = new Card(CardType.Draw, CardColor.Red, i);//Draw 2
            Card blueDraw = new Card(CardType.Draw, CardColor.Blue, i);
            Card greenDraw = new Card(CardType.Draw, CardColor.Green, i);
            Card yellowDraw = new Card(CardType.Draw, CardColor.Yellow, i);
            deck.addToPile(redReverse, blueReverse, greenReverse, yellowReverse);
            deck.addToPile(redSkip, blueSkip, greenSkip, yellowSkip);
            deck.addToPile(redDraw, blueDraw, greenDraw, yellowDraw);
        }
        
        for(int i=0; i< 4; i++){
            Card wild = new Card(CardType.Wild, CardColor.Wild, i);
            Card wildDraw = new Card(CardType.Wild, CardColor.Wild, i);
            deck.addToPile(wild, wildDraw);
        }
        deck.shuffle();
        return deck;
    }
    
    public static Card handleHumanTurn(Player player, Card topCard){
        System.out.println("It is " + player.getName() + "'s turn!\nThe top card is a " + topCard.toString() + "\nPlease pick a card to play: " + player.toString());
        int move = getHumanTurnResponse(player.getHand());
        move--;
        return player.getHand().remove(move);
    }
    
    public static int getHumanTurnResponse(ArrayList<Card> hand){
        //CHECK FOR VALID MOVES
        boolean invalid = true;
        int input = -1;
        Scanner sc = new Scanner(System.in);
        while(invalid){
            System.out.println();
            String rawInput = sc.next();
            try{
                input = Integer.parseInt(rawInput);
            } catch (NumberFormatException e){}
            if(input > 0 && input <= hand.size()-1)
                return input + 1;
            else if(input == 0)
                return 0;
            else{
                for(Card card : hand)
                    if(card.toString().toLowerCase().equals(rawInput.toLowerCase()))
                        return hand.indexOf(card);//WRONG INDEX??
            }
        }
        return -1;//Will never happen
    }
}
