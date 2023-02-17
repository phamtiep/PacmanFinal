package userinterface;

import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;



public class Keyer {

    /** Stores currently pressed keys */
    HashSet<Integer> pressedKeys = new HashSet<Integer>();

    public Keyer(){

        //Check every 100ms if there's keys pressed
        //(This is the Swing Timer they talk about)
    	new Timer(100, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String keysString = "";
                if(!pressedKeys.isEmpty()){
                    Iterator<Integer> i = pressedKeys.iterator();
                    while(i.hasNext()){
                        keysString += i.next() + ",";
                    }
                } 
                System.out.println(keysString);
            }
        }).start();
    }

	
    
}