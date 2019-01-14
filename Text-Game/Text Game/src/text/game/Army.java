/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.game;

import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author david
 */
public class Army {
    final private LinkedList<Entity> monsters;
    final private int maxSize;
    private int currentSize;
    final private Entity player;//to avoid spawning on top
    final private Coordinate maxCoords;
    
    public Army(int max, Entity player, Coordinate maxCoords){
        monsters = new LinkedList<>();
        maxSize = max;
        currentSize = 0;
        this.player = player;
        this.maxCoords = maxCoords;
    }
    
    public void add(Coordinate max){
        if(maxSize == currentSize)
            return;
        int maxX = max.getX();
        int maxY = max.getY();
        
        Random rand = new Random();
        boolean isSpace = false;
        Coordinate attempt = new Coordinate(0,0);
        while(!isSpace){
            attempt = new Coordinate(rand.nextInt(maxX + 1), rand.nextInt(maxY + 1));
            isSpace = isAt(attempt) == null;
            if(attempt.equals(player.getLocation()))
                isSpace = false;
        }
        monsters.add(new Entity(attempt));
        currentSize++;
    }
    
    public Entity isAt(Coordinate coord){
        for(Entity monster: monsters)
            if(monster.getLocation().equals(coord))
                return monster;
        return null;
    }
    
    public void march(){
        monsters.forEach((monster) -> {
            int xDifference = Math.abs(player.getLocation().getX() - monster.getLocation().getX());
            int yDifference = Math.abs(player.getLocation().getY() - monster.getLocation().getY());
            
            Coordinate newCoords;
            if(xDifference > yDifference){
                if(monster.getLocation().getX() > player.getLocation().getX())
                    newCoords = new Coordinate(monster.getLocation().getX() -1, monster.getLocation().getY());
                else newCoords = new Coordinate(monster.getLocation().getX() +1, monster.getLocation().getY());
            } else {
                if(monster.getLocation().getY() > player.getLocation().getY())
                    newCoords = new Coordinate(monster.getLocation().getX(), monster.getLocation().getY() -1);
                else newCoords = new Coordinate(monster.getLocation().getX(), monster.getLocation().getY() +1);
            }
            if(isAt(newCoords) != null || newCoords.getX() < 0 || newCoords.getY() < 0 
                    || newCoords.getX() > maxCoords.getX() || newCoords.getY() > maxCoords.getY())
                moveRandom(monster);
            else monster.setLocation(newCoords);
        });
    }
    
    public void remove(Entity mob){
        monsters.remove(mob);
        currentSize--;
    }
    
    public void moveRandom(Entity monster) {
        int tries = 0;
        boolean isOccupied = true;
        Coordinate current = monster.getLocation();

        while (tries < 5 && isOccupied == true) {//try 5 times to find a random spot to move
            isOccupied = false;
            Direction d = Direction.down;
            d = d.random();
            current.move(d, current);

            for (Entity mob : monsters) {
                if (mob.getLocation().equals(current)) {
                    isOccupied = true;
                }
            }
            tries++;
        }
        if (!isOccupied) {
            monster.setLocation(current);
        }
    }
}
