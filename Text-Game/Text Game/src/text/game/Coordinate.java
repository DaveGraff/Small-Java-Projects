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
public class Coordinate {
    private int x;
    private int y;
    
    //0,0 1,0 2,0 3,0 4,0 5,0
    //0,1 1,1 2,1 3,1 4,1 5,1
    //0,2 1,2 2,2 3,2 4,2 5,2
    //0,3 1,3 2,3 3,3 4,3 5,3
    //0,4 1,4 2,4 3,4 4,4 5,4
    //0,5 1,5 2,5 3,5 4,5 5,5
    
    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public boolean move(Direction d, Coordinate max){
        if(d == Direction.up)
            if (y > 0){
                y--;
                return true;
            } else return false;
        
        if(d == Direction.down)
            if (y < max.getY()){
                y++;
                return true;
            } else return false;
        
        if(d == Direction.left)
            if(x > 0){
                x--;
                return true;
            } else return false;
        
        //assumed
        if(x < max.getX()){
            x++;
            return true;
        }
        return false;
    }
    
    @Override
    public boolean equals(Object c){
        if(c == null)
            return false;
        if(!Coordinate.class.isAssignableFrom(c.getClass()))
            return false;
        
        final Coordinate co = (Coordinate)c;
        return co.getX() == x && co.getY() == y;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.x;
        hash = 97 * hash + this.y;
        return hash;
    }
}
