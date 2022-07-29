/*
 * Factory File for main!
 */
package com.rs.scoasoft;

/**
 *
 * @author Remote Station
 */

import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.geometry.*;
import javafx.animation.*;
import javafx.util.*;
import javafx.scene.Scene;

import java.io.*;
//import java.util.*;
import java.sql.*;

public class Factory {
    
    public final String trdf="DataFiles/Teachers", odf="DataFiles/Otherstaff", pupdf="DataFiles/Pupils";
    
    private static String[] scodet;
    
    /**
     * Instance Initializer
     */
    public Factory() {
        
    }
    
    
    /**
     * This method returns the application pre-loader Node!
     * @return 
     */
    public smx.jfx.layout.Dialog getAppLoader() {
        
        smx.jfx.layout.Dialog pre=null;
        
        //Arranging the required Nodes
        
        try {
        
        //Calling the image!
        ImageView vier=new ImageView(new Image(new FileInputStream("logo.png")));
        vier.setFitWidth(217);
        vier.setFitHeight(217);
        
        //Call the progress indicator
        com.gluonhq.charm.glisten.control.ProgressBar bar=
                new com.gluonhq.charm.glisten.control.ProgressBar();
        bar.setStyle("-fx-accent:royalblue");
        bar.setPrefWidth(217);
        bar.setPrefHeight(31);
        
        //Call the Application name and version
        String str="Scoasoft v1.0.1\n\n© Remote Station 2022";
        Label l=new Label(str);
        l.setStyle("-fx-font: normal 19px 'Lucida Sans'; -fx-text-fill:royalblue");
        
        //Arrange on the GridPane
        smx.jfx.layout.GridPane pane =new smx.jfx.layout.GridPane();
        pane.setAlignment(Pos.CENTER); pane.setPadding(new Insets(8,8,8,8));
        pane.setVgap(5); pane.setHgap(5);
        pane.setStyle("-fx-background-color: white;"
                + "-fx-background-radius:12px 12px 12px 12px;");
        
        pane.add(vier,0,0); pane.add(bar,0,1); pane.add(l,0,2);
        
        FadeTransition fd=new FadeTransition(Duration.seconds(9));
        fd.setFromValue(0); fd.setToValue(1); fd.setAutoReverse(false); fd.setCycleCount(1);
        fd.setNode(vier);
        
        pre=new smx.jfx.layout.Dialog(null, false, false, 518, 350, new Scene(pane), "Welcome", 
                new Image(new FileInputStream("logo.png")));
        
        fd.play();
        
        fd.setOnFinished(er->{System.out.println("Done!");});
        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        return pre;
        
    }
    
    /**
     * This will wait for the pane to load and switch them to authentication!
     * @param old
     * @param nuw 
     */
    public void waitForWelcome(smx.jfx.layout.Dialog old,javafx.stage.Stage nuw) {
        
        FadeTransition fd=new FadeTransition(Duration.seconds(10));
        fd.setFromValue(0); fd.setToValue(1); fd.setAutoReverse(false); fd.setCycleCount(1);
        fd.setNode(new smx.jfx.layout.HBox());
        
        fd.play();
        
        fd.setOnFinished(tu->{
            
            old.close(); nuw.show();
            
        });
        
    }
    
    /**
     * Returns the authentication panel
     * @param topane
     * @return 
     */
    
    private static smx.jfx.layout.Dialog dialog=null;
    public smx.jfx.layout.Dialog getAuthoPane(javafx.stage.Stage topane) {
        
        smx.jfx.layout.GridPane pane;
        
        Label l=new Label("Signin to proceed!");
        l.setStyle(getLabelMStyle("white"));
        
        TextField t=new TextField();
        t.setStyle(getTFStyle().replaceAll("5px","0px")); t.setPrefWidth(121);
        t.setPromptText("Username");
        
        PasswordField p=new PasswordField();
        p.setStyle(getTFStyle().replaceAll("5px","0px")); p.setPrefWidth(121);
        p.setPromptText("Password");
        
        smx.jfx.control.Button sub=new smx.jfx.control.Button("Submit");
        sub.setHoverStyle(getNHBTStyle2()); sub.setDefaultStyle(getNBTStyle2());
        sub.setTooltip(new javafx.scene.control.Tooltip("Sigin Now"));
        
        pane=getSGrid(); pane.setStyle("-fx-background-color: slategrey; -fx-background-radius: 9px 9px 9px 9px;");
        
        pane.add(l,0,0); pane.add(t,0,1); pane.add(p,0,2); pane.add(sub,0,3);
        
        try {
        dialog=new smx.jfx.layout.Dialog(null, false, false, 518, 350, new Scene(pane), "Sigin", 
                new Image(new FileInputStream("logo.png")));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        sub.setOnAction(re->{
            
            try {
            
            PreparedStatement ps=getPS("select *from admins where username=? && password=?");
            ps.setString(1,t.getText()); ps.setString(2,p.getText());
            
            ResultSet rs=ps.executeQuery();
            
            if(rs.next()) {
                
                t.setText(""); p.setText("");
                dialog.close(); topane.show();
                
            }
            
            
            } catch (SQLException ex) {
                print(ex.getMessage());
            }
            
        });
        
        p.setOnAction(ed->{
            
            
            try {
            
            PreparedStatement ps=getPS("select *from admins where username=? && password=?");
            ps.setString(1,t.getText()); ps.setString(2,p.getText());
            
            ResultSet rs=ps.executeQuery();
            
            if(rs.next()) {
                
                t.setText(""); p.setText("");
                dialog.close(); topane.show();
                
            }
            
            
            } catch (SQLException ex) {
                print(ex.getMessage());
            }
            
        });
        
        return dialog;
        
    }
    
    
    /**
     * Returns a standard scoasoft panel
     * @return 
     */
    public smx.jfx.layout.GridPane getSGrid() {
        
        smx.jfx.layout.GridPane pane=new smx.jfx.layout.GridPane();
        pane.setVgap(3); pane.setHgap(3); pane.setPadding(new Insets(5,5,5,5));
        pane.setStyle(getPaneColor()); pane.setAlignment(Pos.CENTER);
        
        return pane;
        
    }
    
    /**
     * Returns a standard scoasoft panel #2
     * @return 
     */
    public smx.jfx.layout.GridPane getSGrid2() {
        
        smx.jfx.layout.GridPane pane=new smx.jfx.layout.GridPane();
        pane.setVgap(1); pane.setHgap(9); pane.setPadding(new Insets(1,1,1,1));
        pane.setStyle(getPaneColor()); pane.setAlignment(Pos.CENTER);
        
        return pane;
        
    }
   
    /**
     * Returns Normal Size label style
     * @param color
     * @return 
     */
    public String getLabelNStyle(String color) {
        
        return "-fx-font: normal 19px 'Lucida Sans'; -fx-text-fill: "+color+";";
        
    }
    
    /**
     * Returns Medium Size label style
     * @param color
     * @return 
     */
    public String getLabelMStyle(String color) {
        
        return "-fx-font: normal 22px 'Lucida Sans'; -fx-text-fill: "+color+";";
        
    }
    
    /**
     * Returns Big Size label style
     * @param color
     * @return 
     */
    public String getLabelBStyle(String color) {
        
        return "-fx-font: normal 25px 'Lucida Sans'; -fx-text-fill: "+color+";";
        
    }
    
    /**
     * Returns casual pane style
     * @return 
     */
    public String getPaneColor() {
        
        return "-fx-background-color: white; -fx-background-radius:9px 9px 9px 9px;";
    }
    
    /**
     * Returns text field color
     * @return 
     */
    public String getTFStyle() {
        
        return "-fx-background-color: white; -fx-font:normal 19px 'Lucida Sans'; -fx-accent: royalblue;"
                + " -fx-text-fill: royalblue; -fx-border-width: 5px; "
                + "-fx-border-color: royalblue;";
    }
    
    /**
     * Returns normal button default color
     * @return 
     */
    public String getNBTStyle() {
        
        return "-fx-background-color: white; -fx-font: 21px 'Lucida Sans'; -fx-text-fill: royalblue; -fx-padding: 7px;";
    }
    
    /**
     * Returns normal button hover color
     * @return 
     */
    public String getNHBTStyle() {
        
        return "-fx-background-color: royalblue; -fx-font: 21px 'Lucida Sans'; -fx-text-fill: white; -fx-padding: 7px;";
    }
    
    /**
     * Returns normal button default color #2
     * @return 
     */
    public String getNBTStyle2() {
        
        return "-fx-background-color: royalblue; -fx-font: 21px 'Lucida Sans'; -fx-text-fill: white; -fx-padding: 9px;";
    }
    
    /**
     * Returns normal button hover color #2
     * @return 
     */
    public String getNHBTStyle2() {
        
        return "-fx-background-color: slateblue; -fx-font: 21px 'Lucida Sans'; -fx-text-fill: white; -fx-padding: 9px;";
    }
    
    /**
     * Returns navigation button style!
     * @return 
     */
    public String getNavBStyle() {
        
        return "-fx-background-color: royalblue; "
                + "-fx-font: 19px 'Lucida Sans'; -fx-text-fill: white; -fx-padding: 21px; -fx-background-radius: 18%;";
    }
    
    /**
     * Returns navigation button hover style!
     * @return 
     */
    public String getNavBHStyle() {
        
        return "-fx-background-color: white; "
                + "-fx-font: 19px 'Lucida Sans'; -fx-text-fill: royalblue; -fx-padding: 21px; -fx-background-radius: 18%;";
    }
    
    public String getLVStyle() {
        
        return "-fx-control-inner-background: royalblue; -fx-accent: white; -fx-focus-faint: royalblue;"
                + "-fx-border-color: royalblue; -fx-control-inner-border: royalblue; -fx-border-width: 12px;"
                + "-fx-font:normal 19px 'Lucida Sans';";
    }
    
    
    /**
     * To close the old stage and show new one!
     * @param old
     * @param nuw 
     */
    public void swicthStage(javafx.stage.Stage old, javafx.stage.Stage nuw) {
        old.close(); nuw.show();
    }
    
    private static Connection con;
    
    public static Connection connect() {
        
        if(con==null) {
            
            try {
                
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                String host="jdbc:mysql://127.0.0.1:3306/scoasoft?useSSL=false&autoReconnect=true", 
                        user="silas", pass="jesus";
                
                con=DriverManager.getConnection(host,user,pass);
                
                if(con!=null) {System.out.println("Connection suceeded!");} else {System.out.println("Connection failed!");}
                
            } catch (ClassNotFoundException | SQLException er) {
                System.out.println(er.getMessage());
            }
            
        }
        
        return con;
    }
    
    /**
     * Returns a result set
     * @param query
     * @return
     * @throws SQLException 
     */
    public PreparedStatement getPS(String query) throws SQLException {
        
        return connect().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    }
    
    /**
     * Prints the passed object
     * @param <T>
     * @param t 
     */
    public <T> void print(T t) {
        System.out.println(t);
    }
    
    /**
     * To copy file to other location
     * @param fro
     * @param to
     */
    @SuppressWarnings("ConvertToTryWithResources")
    public void copyFile(String fro, String to) {
        
        try {
            
            FileInputStream in=new FileInputStream(fro);
            FileOutputStream out=new FileOutputStream(to);
            
            byte[] data=new byte[1024];
            
            int read;
            
            while((read=in.read(data,0,1024))>-1) {
                out.write(data,0,read);
            }
            
            in.close(); out.flush(); out.close();
            
        } catch (IOException ed) {
            print(ed.getMessage());
        }
        
    }
    
    /**
     * Write string to file
     * @param str
     * @param path 
     */
    @SuppressWarnings("ConvertToTryWithResources")
    public void writeFile(String str, String path) {
        
        try {
            
            BufferedWriter bw=new BufferedWriter(new FileWriter(path));
            bw.write(str); bw.flush(); bw.close();
            
        } catch (IOException ew) {
            print(ew.getMessage());
        }
        
    }
    
    /**
     * To see if text-field is above the size 2 characters
     * @param list
     * @return
     */
    public boolean validateTF(java.util.List<TextField> list) {
        
        bool=true;
        
        list.forEach((tf)->{
            
            if(tf.getText().length()<1) {
                
                FadeTransition fd=new FadeTransition(Duration.millis(818));
                fd.setFromValue(0); fd.setToValue(1); fd.setAutoReverse(false); fd.setCycleCount(2);
                fd.setNode(tf);
                
                fd.play();
                
                bool=false;
            }
            
        });
        
        return bool;
        
    }
    
    private boolean bool;
    
    @SuppressWarnings("ConvertToTryWithResources")
    public String[] getSchoolDetails() {
        
        if(scodet==null) {
            try {
            BufferedReader read=new BufferedReader(new InputStreamReader(new FileInputStream("app.cfg")));
            
            scodet=new String[2];
            
            int i=0;
            while(i<2) {
                scodet[i]=read.readLine();
                i++;
            }
            
            read.close();
            
            } catch (IOException x) {
                print(x.getMessage());
            }
        }
        
        return scodet;
    }
    
}
