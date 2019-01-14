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
public class Player extends Entity{
    private Direction direction = Direction.up;
    
    public Player(Coordinate coords) {
        super(coords);
    }
    
    public String getFacing(){
        switch(direction){
            case up: return "↑";
            case down: return "↓";
            case left: return "←";
            default: return "→";
        }
    }
    
    public Direction getDirection(){
        return direction;
    }
    
    @Override
    public Coordinate getLocation(){
        return location;
    }
    
    public void move(Direction d, Coordinate maxCoords){
        location.move(d, maxCoords);
        direction = d;
    }
}
