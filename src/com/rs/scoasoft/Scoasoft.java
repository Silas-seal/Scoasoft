/*
 * The School Management and data application Main Class
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
import javafx.scene.Scene;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import javafx.stage.*;
//import java.util.*;

public class Scoasoft extends javafx.application.Application{
    
    @Override
    public void init() {
        
        System.out.println("Welcome!\nStarting app.......>");
        
        File files=new File("DataFiles");
        File temp=new File("temp.html");
        File appcfg=new File("app.cfg");
        File tr=new File("DataFiles/Teachers");
        File other=new File("DataFiles/Otherstaff");
        File pup=new File("DataFiles/Pupils");
        
        if(!files.exists()) {
            files.mkdir();
        }
        if(!tr.exists()) {
            tr.mkdir();
        }
        if(!other.exists()) {
            other.mkdir();
        }
        if(!pup.exists()) {
            pup.mkdir();
        }
        
        if(!temp.exists()) {
            new Factory().writeFile("temporary file here!", temp.getPath());
        }
        if(!appcfg.exists()) {
            new Factory().writeFile("SAMPLE SCHOOL NAME HERE\nSCHOOL MOTTO HERE", appcfg.getPath());
        }
        
        java.util.Date date=new java.util.Date();
        java.text.DateFormat fmt=DateFormat.getDateInstance(DateFormat.LONG);
        System.out.println(fmt.format(date));
        
        //File copy thread test!
        UploadThread th=new UploadThread();
        th.setData(temp.getPath(), "temp123.xml");
        
        th.start();
        
    }
    
    @Override
    public void start(javafx.stage.Stage stage) {
        
        try {
        
        //Initialize factory class
        Factory factory=new Factory();
        
        //Config stage
        stage.setWidth(1000); stage.setHeight(600); 
        stage.getIcons().addAll(new Image(new FileInputStream("logo.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Scoasoft");
        
        //Authentication Window!
        smx.jfx.layout.Dialog authe=factory.getAuthoPane(stage);
        
        
        //Call header pane
        MiniPanes mini=new MiniPanes();
        
        //Call Show 
        VisualPane vp=new VisualPane();
        
        //Call details pane
        DetailsPane dp=new DetailsPane();
        
        //call Nav Menu
        NavigationPane nvp=new NavigationPane(vp, dp, stage, authe);
        
        //HERE WE GOOOOO!
        
        smx.jfx.layout.BorderPane brd=new smx.jfx.layout.BorderPane();
        brd.setPadding(new Insets(1));
        brd.setStyle("-fx-background-color:royalblue; -fx-background-radius: 9px 9px 9px 9px;");
        
        brd.setTop(mini.getHeaderPane(stage));
        brd.setCenter(vp);
        brd.setLeft(nvp);
        brd.setRight(dp);
        
        Scene sn=new Scene(brd); sn.setFill(javafx.scene.paint.Color.TRANSPARENT);
        stage.setScene(sn);
        
        //App loader
        smx.jfx.layout.Dialog load=factory.getAppLoader(); 
        load.show();
        factory.waitForWelcome(load, authe);
        
        //Action to help button
        mini.getHelpBtn().setOnAction(act->{
            try {
                
            javafx.application.HostServices serv=getHostServices();
            
            serv.showDocument(new File("Documentation/index.html").toURL().toURI().toString());
            
            } catch (MalformedURLException | URISyntaxException ex) {
                factory.print(ex.getMessage());
            }
        });
        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void stop() {
        
        System.out.println("Bye!\nShutting down.......>");
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
        
    }
    
    //Promoting | Deleting | Editing
    
    public void hserv() {
        
        javafx.application.HostServices hser=getHostServices();
        hser.showDocument("Documentation/index.html");
        
    }
    
}
