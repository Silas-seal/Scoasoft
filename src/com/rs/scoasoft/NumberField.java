/*
 * To make the javafx NumberField!
 */
package com.rs.scoasoft;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Remote Station
 */

public class NumberField extends javafx.scene.control.TextField {
    
    /**
     * Initialization
     */
    public NumberField() {
        
        EventHandler<KeyEvent> keyEvent=(KeyEvent key) ->{
            
            char a=key.getText().charAt(0);
            
            if(!Character.isDigit(a)) {
                this.setText("");
            }
            
        };
        
        this.setOnKeyReleased(keyEvent);
        
    }
    
    /**
     * Initialization with a text parameter
     * @param text 
     */
    public NumberField(String text) {
        
        EventHandler<KeyEvent> keyEvent=(KeyEvent key) ->{
            
            char a=key.getText().charAt(0);
            
            if(!Character.isDigit(a)) {
                this.setText("");
            }
            
        };
        
        this.setOnKeyReleased(keyEvent);
        
        this.setText(text);
        
    }
    
}
