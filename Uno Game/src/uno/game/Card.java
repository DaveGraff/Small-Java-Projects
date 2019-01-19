/*
 * David Graff 2018
 */
package uno.game;

/**
 *
 * @author david
 */
public class Card {
    private CardType type;
    private CardColor color;
    private int number; //-1 for non-numbered cards
    private boolean isWild; //Flag for when color must be changed

    public Card(CardType type, CardColor color, int number) {
        this.type = type;
        this.color = color;
        this.number = number;
    }

    public CardType getType() {
        return type;
    }

    public CardColor getColor() {
        return color;
    }

    public int getNumber() {
        return number;
    }


    public void setColor(CardColor color) {
        this.color = color;
    }
    
    @Override
    public String toString(){
        String notWild = "";
        if(type == CardType.Wild && color != CardColor.Wild)
            notWild = color.toString() + " ";
        switch (type) {
            case Normal:
                return color.toString() + " " + Integer.toString(number);
            case Wild:
                return notWild + "Wild";
            case Wild_4:
                return notWild + "Wild Draw 4";
            default:
                return color.toString() + " " + type.toString();
        }
        
    }
}
