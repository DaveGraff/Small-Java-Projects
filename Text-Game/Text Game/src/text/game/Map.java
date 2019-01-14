/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.game;

import java.util.ArrayList;

/**
 *
 * @author david
 */
public class Map {
    private final Coordinate size;//hold max x and y vals
    private final Army minions;
    private final Player player;
    private final ArrayList<Projectile> projectiles;
    
    public Army getMinions(){
        return minions;
    }
    
    public Player getPlayer(){
        return player;
    }
    
    public Coordinate getSize(){return size;}
    
    public Map(int x, int y){
        size = new Coordinate(x, y);
        int space = size.getX() * size.getY();
        player = new Player(new Coordinate(x/2, y/2));
        minions = new Army(space / 10, player, size);
        projectiles = new ArrayList<>();
    }
    
    public String printMap(int turncounter){
        String board = "\n\n\n\n\n\n\n\n";
        
        collisionCheck();
        if(turncounter % 2 == 0){
            minions.march();
            collisionCheck();
        }
        moveProjectiles();
        collisionCheck();
        for(int x = 0; x < size.getX() + 3; x++)
            board = board.concat("*");
        board = board.concat("\n");
        for(int y = 0; y < size.getY() + 1; y++){
            board = board.concat("*");
            for(int x = 0; x < size.getX() + 1; x++){
                Coordinate spot = new Coordinate(x, y);
                Projectile spotProjectile = hasProjectile(spot);
                Entity exists = minions.isAt(spot);
                
                if(exists != null)//Kill monster if on the same tile as projectile
                    if(exists.getIsDead()){
                        minions.remove(exists);
                        board = board.concat("X");
                    } else board = board.concat("Z");
                else if(spotProjectile != null)//Prints projectile
                    board = board.concat(spotProjectile.getSymbol());
                else if(player.getLocation().equals(spot))//Print player
                    board = board.concat(player.getFacing());
                else board = board.concat(" ");
            }
            board = board.concat("*\n");
        }
        for(int x = 0; x < size.getX() + 3; x++)
            board = board.concat("*");
        return board;
    }
    
    public void addMinion(){
        minions.add(size);
    }
    
    public void moveProjectiles(){
        ArrayList<Projectile> toBeRemoved = new ArrayList<>();
        projectiles.forEach(pew ->{
            boolean inRange = pew.move(size);
            if(!inRange)
                toBeRemoved.add(pew);
        });
        toBeRemoved.forEach(pew -> {
            projectiles.remove(pew);
        });
    }
    
    public void addProjectile(){
        Coordinate temp = new Coordinate(player.getLocation().getX(), player.getLocation().getY());
        projectiles.add(new Projectile(temp, player.getDirection()));
    }
    
    private Projectile hasProjectile(Coordinate location){
        for(Projectile pew : projectiles)
            if(pew.getLocation().equals(location))
                return pew;
        return null;
    }
    
    private void collisionCheck(){
        for(int y = 0; y < size.getY(); y++){
            for(int x = 0; x < size.getX(); x++){
                Coordinate spot = new Coordinate(x, y);
                Entity minion = minions.isAt(spot);
                Projectile proj = hasProjectile(spot);
                if(minion != null && proj != null){
                    minion.setDead();
                    projectiles.remove(proj);
                }
            }
        }
    }
}
