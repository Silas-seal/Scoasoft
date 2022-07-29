/*
 * The Details Panel on the right class!
 */
package com.rs.scoasoft;

/**
 *
 * @author Remote Station
 */

import javafx.scene.control.*;
//import javafx.scene.image.*;
import javafx.geometry.*;
//import javafx.animation.*;
//import javafx.util.*;
//import javafx.scene.Scene;

//import java.io.*;
//import java.util.*;
public class DetailsPane extends smx.jfx.layout.GridPane {
    
    private static Label l;
    
    /**
     * Initializing the details Pane
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public DetailsPane() {
        
        Label v=new Label("Details\n===========");
        v.setStyle(new Factory().getLabelMStyle("white"));
        
        String welcome="\tWelcome!\n\n   Scoasoft\n\n\tv1.0.1\n"
                + "------------------\n\nDeveloped by:\n\tRemote Station"
                + "\n------------------\n\n\tAccess Of \n\tNo Distance\n"
                + "------------------\n\nSite : \nhttps://remotestation.herokuapp.com/\n"
                + "------------------";
        
        l=new Label();
        l.setText(welcome); l.setStyle(new Factory().getLabelNStyle("white")); l.setWrapText(true);
        
        this.setVgap(17); this.setHgap(7); this.setPadding(new Insets(1,1,1,1));
        this.setAlignment(Pos.TOP_LEFT); this.setStyle("-fx-background-color: royalblue; -fx-background-radius: 8%;");
        this.setPrefWidth(200);
        
        this.add(v,0,0); this.add(l,0,1);
        
    }
    
    /**
     * This sets the argument string to the details pane
     * @param info 
     */
    public void setInfo(String info) {
        
        l.setText(info);
        
    }
    
}
