/*
 This is the visualizing pane
 */
package com.rs.scoasoft;

/**
 *
 * @author Remote Station
 */

//import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.geometry.*;
//import javafx.animation.*;
//import javafx.util.*;
//import javafx.scene.Scene;

import java.io.*;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
//import java.util.*;


public class VisualPane extends smx.jfx.layout.GridPane {
    
    /**
     * Visualizer Pane Initialization
     */
    public VisualPane() {
        
        try {
        this.setVgap(5); this.setHgap(5); this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(4,4,4,4)); this.setStyle(new Factory().getPaneColor());
        this.setPrefWidth(818);
        
        ImageView v=new ImageView(new Image(new FileInputStream("logo.png")));
        v.setFitWidth(218);
        v.setFitHeight(218);
        
        this.addN(v,0,0);
        
        FadeTransition fd=new FadeTransition(Duration.millis(3500));
        fd.setFromValue(0); fd.setToValue(1); fd.setAutoReverse(true); fd.setCycleCount(6);
        fd.setNode(v); fd.play();
        
        fd.setOnFinished(vi->{
            fd.play();
        });
        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    /**
     * Add Node to the grid!
     * @param node
     * @param a
     * @param b 
     */
    public final void addN(javafx.scene.Node node,int a,int b) {
        this.add(node,a,b);
    }
    
}
