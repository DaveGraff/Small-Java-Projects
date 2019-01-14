/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.game;

/**
 *
 * @author david
 */
public class Projectile extends Entity{
    private final Direction direction;
    private final String symbol;
    
    public Projectile(Coordinate coords, Direction d) {
        super(coords);
        direction = d;
        switch(direction){
            case up:
            case down: symbol = "|";
            break;
            case left:
            case right: 
            default: symbol = "-";//default is not possible
        }
    }
    
    public Direction getDirection(){
        return direction;
    }
    
    public String getSymbol(){
        return symbol;
    }
    
    public boolean move(Coordinate max){
        return location.move(direction, max);
    }
}
