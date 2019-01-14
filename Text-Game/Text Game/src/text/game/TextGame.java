/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.game;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author david
 */
public class TextGame{
    private static final Map map = new Map(80, 20);
    private static int turncounter = 0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test");
        Container contentPane = frame.getContentPane();
        
        System.out.println(map.printMap(turncounter));
        KeyListener temp = new KeyListener(){
            @Override
            public void keyTyped(KeyEvent event){
                //read move & move player 
                //Add enemy if applicable
                //March enemies 
                //Check if player is dead
                //If not dead, print map & get ready for input
                boolean dead = false;
                boolean isValid = processPlayerTurn(event.getKeyChar());
                if(isValid){//Allows for wrong key to be pressed
                    System.out.println(map.printMap(turncounter));
                    if (turncounter % 5 == 0)
                        map.addMinion();
                    if(map.getMinions().isAt(map.getPlayer().getLocation()) != null)
                        dead = true;
                    turncounter++;
                    if(dead){
                        youLost(turncounter * 100);
                        System.exit(0);
                    }
                }
            }
            
            @Override
            public void keyReleased(KeyEvent event){}
            
            @Override
            public void keyPressed(KeyEvent event){}
        };
        
        JTextField textfield = new JTextField();
        textfield.addKeyListener(temp);
        contentPane.add(textfield, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
    }
    
    public static boolean processPlayerTurn(char input) {
        Direction d = null;//doesn't matter
        switch (input) {
            case 'w':
                d = Direction.up;
                break;
            case 'a':
                d = Direction.left;
                break;
            case 's':
                d = Direction.down;
                break;
            case 'd':
                d = Direction.right;
                break;
            case ' ':
                map.addProjectile();
            default:
        }
        if (d != null){
            map.getPlayer().move(d, map.getSize());
            return true;
        } else if(input == ' ')
            return true;
       return false;
    }
    
    
    public static void youLost(int score){
        System.out.println("*******************************");
        System.out.println("* L       OOOOO  SSSSS TTTTT  *");
        System.out.println("* L       O   O  S       T    *");
        System.out.println("* L       O   O  S       T    *");
        System.out.println("* L       O   O  SSSSS   T    *");
        System.out.println("* L       O   O      S   T    *");
        System.out.println("* L       O   O      S   T    *");
        System.out.println("* LLLLLL  OOOOO  SSSSS   T    *");
        System.out.println("*******************************");
        System.out.println("\nTotal Score: " + score);
    }
}
