/*
 * This is application navigation pane
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
import java.util.*;
public class NavigationPane extends smx.jfx.layout.BorderPane {
    
    /**
     * Initializing the Navigation!
     * @param show
     * @param dtls 
     * @param old
     * @param nuw
     */
    public NavigationPane(VisualPane show, DetailsPane dtls, 
            javafx.stage.Stage old, javafx.stage.Stage nuw) {
        
        Factory factory=new Factory();
        //Organise the buttons on the nav pane!
        smx.jfx.control.Button profiles=new smx.jfx.control.Button("Profiles");
        profiles.setDefaultStyle(factory.getNavBStyle()); profiles.setHoverStyle(factory.getNavBHStyle());
        profiles.setPrefSize(218,40);
        
        smx.jfx.control.Button performance=new smx.jfx.control.Button("Performance");
        performance.setDefaultStyle(factory.getNavBStyle()); performance.setHoverStyle(factory.getNavBHStyle());
        performance.setPrefSize(218,40);
        
        smx.jfx.control.Button attendance=new smx.jfx.control.Button("Attendance");
        attendance.setDefaultStyle(factory.getNavBStyle()); attendance.setHoverStyle(factory.getNavBHStyle());
        attendance.setPrefSize(218,40);
        
        smx.jfx.control.Button payments=new smx.jfx.control.Button("Payments");
        payments.setDefaultStyle(factory.getNavBStyle()); payments.setHoverStyle(factory.getNavBHStyle());
        payments.setPrefSize(218,40);
        
        smx.jfx.control.Button control=new smx.jfx.control.Button("Control Panel");
        control.setDefaultStyle(factory.getNavBStyle()); control.setHoverStyle(factory.getNavBHStyle());
        control.setPrefSize(218,40);
        
        smx.jfx.control.Button out=new smx.jfx.control.Button("Sign Out");
        out.setDefaultStyle(factory.getNavBStyle()); out.setHoverStyle(factory.getNavBHStyle());
        out.setPrefSize(218,40);
        
        smx.jfx.layout.GridPane nav=new smx.jfx.layout.GridPane();
        nav.setVgap(3); nav.setHgap(2); nav.setPadding(new Insets(1,1,1,1));
        nav.setAlignment(Pos.TOP_CENTER); nav.setStyle(getThisStyle());
        nav.add(profiles,0,0);
        nav.add(performance,0,1);
        nav.add(attendance,0,2);
        nav.add(payments,0,3);
        nav.add(control,0,4);
        nav.add(out,0,5);
        
        //add to list
        List li=new ArrayList();
        li.add(profiles);
        li.add(performance);
        li.add(attendance);
        li.add(payments);
        li.add(control);
        li.add(out);
        
        //The app version data on the bottom!
        
        Label l=new Label("===============\nScoasoft v1.0.1\n© Remote Station 2022.");
        l.setStyle(factory.getLabelNStyle("white"));
        
        smx.jfx.layout.VBox v=new smx.jfx.layout.VBox();
        v.setPadding(new Insets(3)); v.setAlignment(Pos.BOTTOM_CENTER);
        v.setStyle(getThisStyle());
        v.addS(l);
        
        //Create the mother!
        this.setPadding(new Insets(3));
        this.setStyle(getThisStyle());
        this.setCenter(nav);
        this.setBottom(v);
        
        //Declare the panes
        com.rs.scoasoft.profiles.Profiles pro=new com.rs.scoasoft.profiles.Profiles(old,dtls);
        
        com.rs.scoasoft.performance.Performance per=new com.rs.scoasoft.performance.Performance(old,dtls);
        
        com.rs.scoasoft.performance.ControlPane contr=new com.rs.scoasoft.performance.ControlPane(old,dtls);
        
        com.rs.scoasoft.payments.Payments pay=new com.rs.scoasoft.payments.Payments(old,dtls);
        
        com.rs.scoasoft.attendance.Attendance attend=new com.rs.scoasoft.attendance.Attendance(old,dtls);
        
        //Actions
        
        profiles.setOnAction(er->{
            
            dtls.setInfo("Profiles Panel!"); setBack(li,profiles);
            
            show.getChildren().clear(); show.add(pro,0,0);
            
        });
        
        performance.setOnAction(er->{
            
            dtls.setInfo("Performance Panel!"); setBack(li,performance);
            
            show.getChildren().clear(); show.add(per,0,0);
            
        });
        
        attendance.setOnAction(er->{
            
            dtls.setInfo("Attendance Panel!"); setBack(li,attendance);
            
            show.getChildren().clear(); show.add(attend,0,0);
            
        });
        
        payments.setOnAction(er->{
            
            dtls.setInfo("Payments Panel!"); setBack(li,payments);
            
            show.getChildren().clear(); show.add(pay,0,0);
            
        });
        
        control.setOnAction(er->{
            
            dtls.setInfo("Control Panel"); setBack(li,control);
            
            show.getChildren().clear(); show.add(contr,0,0);
            
            
        });
        
        out.setOnAction(ei->{
            
            factory.swicthStage(old, nuw);
            
        });
        
    }
    
    /**
     * Returns this pane theme color!
     * @return 
     */
    public final static String getThisStyle() {
        
        return "-fx-background-color: royalblue; -fx-background-radius: 8%;";
    }
    
    /**
     * To reset all buttons color
     * @param list 
     * @param btn
     */
    public void setBack(List<smx.jfx.control.Button> list, smx.jfx.control.Button btn) {
        
        Factory factory=new Factory();
        
        list.forEach((button) -> {
            button.setDefaultStyle(factory.getNavBStyle());
        });
        
        btn.setDefaultStyle(factory.getNavBHStyle());
        
    }
    
}
