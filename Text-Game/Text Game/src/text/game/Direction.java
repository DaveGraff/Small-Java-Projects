/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.game;

import java.util.Random;

/**
 *
 * @author david
 */
public enum Direction {
    up, down, left, right;
    
    public Direction random(){
        Random rand = new Random();
        int picked = rand.nextInt(4) + 1;
        switch(picked){
            case 1: return up;
            case 2: return down;
            case 3: return left;
            case 4: return right;
        }
        return null;
    }
}
