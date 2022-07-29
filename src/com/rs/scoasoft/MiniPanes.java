/*
 * This class is for mini panes!
 */
package com.rs.scoasoft;

/**
 *
 * @author Remote Station
 */

import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.geometry.*;
//import javafx.animation.*;
//import javafx.util.*;
//import javafx.scene.Scene;

import java.io.*;
//import javafx.application.HostServices;
//import java.util.*;

public class MiniPanes {
    
    private double xoff,yoff;
    
    /**
     * Instance Initializer
     */
    public MiniPanes() {
        
        
    }
    
    /**
     * Returns the header pane of the application
     * @param stage
     * @return 
     */
    public smx.jfx.layout.BorderPane getHeaderPane(javafx.stage.Stage stage) {
        
        smx.jfx.layout.BorderPane hpane=null; Factory factory=new Factory();
        
        try {
            
            ImageView view=new ImageView(new Image(new FileInputStream("logo.png")));
            view.setFitHeight(51);
            view.setFitWidth(51);
            
            Label l=new Label("Scoasoft");
            l.setStyle(factory.getLabelBStyle("royalblue"));
            
            smx.jfx.control.Button mini=new smx.jfx.control.Button("-");
            mini.setDefaultStyle(factory.getNBTStyle()); 
            mini.setHoverStyle(factory.getNHBTStyle());
            
            smx.jfx.control.Button max=new smx.jfx.control.Button("^");
            max.setDefaultStyle(factory.getNBTStyle()); 
            max.setHoverStyle(factory.getNHBTStyle());
            
            smx.jfx.control.Button close=new smx.jfx.control.Button("X");
            close.setDefaultStyle(factory.getNBTStyle()); 
            close.setHoverStyle(factory.getNHBTStyle());
            
            help=new smx.jfx.control.Button("Get help");
            help.setDefaultStyle(factory.getNHBTStyle());
            help.setHoverStyle(factory.getNHBTStyle2());
            
            
            smx.jfx.layout.GridPane right=factory.getSGrid2();
            right.add(help,0,0); right.add(mini,1,0); right.add(max,2,0); right.add(close,3,0);
            
            smx.jfx.layout.GridPane left=factory.getSGrid2();
            left.add(view,0,0); left.add(l,1,0);
            
            hpane=new smx.jfx.layout.BorderPane(); hpane.setPadding(new Insets(0,0,0,0));
            hpane.setStyle(factory.getPaneColor());
            hpane.setLeft(left);
            hpane.setRight(right);
            
            //Actions
            hpane.setOnMousePressed(ev->{
                
                xoff=ev.getSceneX(); yoff=ev.getSceneY();
                
            });
            
            hpane.setOnMouseDragged(ev->{
                
                stage.setX(ev.getScreenX()-xoff); stage.setY(ev.getScreenY()-yoff);
                
                if(stage.isFullScreen()) {
                    stage.setFullScreen(false);
                }
                
            });
            
            mini.setOnAction(ev->{
                
                stage.setIconified(true);
                
            });
            
            max.setOnAction(ev->{
                
                if(!stage.isFullScreen()) {
                    stage.setFullScreen(true);
                }
                
                else {
                    stage.setFullScreen(false);
                }
                
            });
            
            close.setOnAction(ev->{
                
                stage.close();
                
            });
            
        } catch (IOException ed) {
            System.out.println(ed.getMessage());
        }
        
        return hpane;
        
    }
    
    private static smx.jfx.control.Button help;
    
    public smx.jfx.control.Button getHelpBtn() {
        return help;
    }
    
}
