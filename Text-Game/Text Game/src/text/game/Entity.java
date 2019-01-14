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
public class Entity {
    protected Coordinate location;
    private boolean isDead = false;
    
    public Entity(Coordinate coords){
        location = coords;
    }
    
    public boolean getIsDead(){
        return isDead;
    }
    
    public void setDead(){isDead = true;}
    
    public Coordinate getLocation(){
        return location;
    }
    
    public void setLocation(Coordinate location){
        this.location = location;
    }    
}
