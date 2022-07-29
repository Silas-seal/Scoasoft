/*
 * Profiles section of the application!
 */
package com.rs.scoasoft.profiles;

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
import javafx.collections.*;

//import java.io.*;
//import java.util.*;
import java.sql.*;

public class Profiles extends smx.jfx.layout.GridPane {
    
    /**
     * Initializing the profile thing
     * @param owner
     * @param dtls
     */
    @SuppressWarnings("ConvertToStringSwitch")
    public Profiles(javafx.stage.Stage owner, com.rs.scoasoft.DetailsPane dtls) {
        
        // Factory
        
        com.rs.scoasoft.Factory factory=new com.rs.scoasoft.Factory();
        ProfilesFactory pfactory=new ProfilesFactory();
        
        //Menu Button
        
        smx.jfx.control.MenuButton mb=new smx.jfx.control.MenuButton("Add new");
        mb.setDefStyle(factory.getNBTStyle2()+"-fx-accent: royalblue;"); 
        mb.setHoverStyle(factory.getNHBTStyle2()+"-fx-accent: royalblue;");
        
        smx.jfx.control.Button vpr=new smx.jfx.control.Button("View All Profiles");
        vpr.setDefaultStyle(factory.getNBTStyle2()+"-fx-accent: royalblue;"); 
        vpr.setHoverStyle(factory.getNHBTStyle2()+"-fx-accent: royalblue;");
        
        MenuItem tr=new MenuItem("Teacher");
        MenuItem st=new MenuItem("Other staff");
        MenuItem pp=new MenuItem("Pupil");
        
        mb.getItems().addAll(tr,st,pp);
        
        
        //Combo
        
        smx.jfx.control.ComboBox combo=new smx.jfx.control.ComboBox();
        combo.setDefStyle(factory.getTFStyle()); combo.setPromptText("Select");
        combo.setHoverStyle(factory.getTFStyle()+"-fx-border-width: 5px; "
                + "-fx-border-color: slateblue;");
        
        combo.getItems().addAll("Teachers", "Other Staff", "Nursery", "Primary one", "Primary two", "Primary three", 
                "Primary four" , "Primary five", "Primary six", "Primary seven"); 
        combo.setPrefWidth(200);
        
        //Search bar
        
        TextField sr=new TextField(); sr.setStyle(factory.getTFStyle());
        sr.setPromptText("Search or Sort profiles"); sr.setPrefWidth(320);
        
        //ListView
        ListView lv=new ListView(); lv.setStyle(factory.getLVStyle());
        lv.setPrefWidth(1000); lv.setPrefHeight(800);
        
        //Add MenuItems and contex
        MenuItem pr=new MenuItem("View profile");
        MenuItem cs=new MenuItem("Change status");
        
        ContextMenu cm=new ContextMenu(pr,cs); cm.setStyle(factory.getTFStyle());
        
        lv.setContextMenu(cm);
        
        
        //Arranging nodes on pane
        
        this.setVgap(5); this.setHgap(5); this.setPadding(new Insets(4,4,4,4));
        this.setAlignment(Pos.TOP_CENTER); this.setStyle(factory.getPaneColor());
        
        smx.jfx.layout.GridPane b=factory.getSGrid();
        
        b.add(mb,0,0); b.add(vpr,1,0);
        b.add(combo,0,1); b.add(sr,1,1);
        
        smx.jfx.layout.GridPane l=factory.getSGrid();
        l.add(lv,0,0);
        
        Label liu=new Label("Profiles");
        liu.setStyle(factory.getLabelBStyle("grey"));
        
        addN(liu,0,0);
        this.addN(b,0,1); this.addN(l,0,2);
        
        //Actions
        
        tr.setOnAction(ac->{
            
            if(trd!=null) {trd.close();}
            
            trd=pfactory.getTeacherRegForm(owner);
            trd.show();
            
        });
        
        st.setOnAction(ac->{
            
            if(trd!=null) {trd.close();}
            
            trd=pfactory.getOStaffRegForm(owner);
            trd.show();
            
        });
        
        pp.setOnAction(ac->{
            
            if(trd!=null) {trd.close();}
            
            trd=pfactory.getPupilRegForm(owner);
            trd.show();
            
        });
        
        combo.setOnAction(act->{
            
            if(combo.getValue().toString().equals("Teachers")) {
                
                String fullnam; list=new java.util.ArrayList();
                
                try {
                    
                    PreparedStatement ps=factory.getPS("select *from teachers order by fname,lname asc");
                    ResultSet set=ps.executeQuery();
                    
                    while(set.next()) {
                        
                        fullnam=set.getString(1)+" | "+set.getString(2)+" "+set.getString(3);
                        
                        list.add(fullnam);
                        
                    }
                    
                    ObservableList<String> obl=FXCollections.observableArrayList(list);
                    
                    lv.setItems(obl);
                    
                } catch (SQLException ex) {
                    factory.print(ex.getMessage());
                }
                
            }
            
            else if(combo.getValue().toString().equals("Other Staff")) {
                
                String fullnam; list=new java.util.ArrayList();
                
                try {
                    
                    PreparedStatement ps=factory.getPS("select *from otherstaff order by fname,lname asc");
                    ResultSet set=ps.executeQuery();
                    
                    while(set.next()) {
                        
                        fullnam=set.getString(1)+" | "+set.getString(2)+" "+set.getString(3);
                        
                        list.add(fullnam);
                        
                    }
                    
                    ObservableList<String> obl=FXCollections.observableArrayList(list);
                    
                    lv.setItems(obl);
                    
                } catch (SQLException ex) {
                    factory.print(ex.getMessage());
                }
                
            }
            
            else {
                
                String fullnam; list=new java.util.ArrayList();
                
                try {
                    
                    PreparedStatement ps=factory.getPS("select *from pupils where class=? order by fullnames asc");
                    ps.setString(1,combo.getValue().toString());
                    ResultSet set=ps.executeQuery();
                    
                    while(set.next()) {
                        
                        fullnam=set.getString(1)+" | "+set.getString(2);
                        
                        list.add(fullnam);
                        
                    }
                    
                    ObservableList<String> obl=FXCollections.observableArrayList(list);
                    
                    lv.setItems(obl);
                    
                } catch (SQLException ex) {
                    factory.print(ex.getMessage());
                }
                
            }
            
        });
        
        lv.setOnMouseClicked(act->{
            
            //For mini profile view
            if(act.getClickCount()==1) {
                
                if(combo.getValue().toString().equals("Teachers")) { 
                    
                    try {
                        
                        PreparedStatement ps=factory.getPS("select *from teachers where id=?");
                        ps.setString(1,pfactory.getLogDa(lv.getSelectionModel().getSelectedItem().toString(), "id"));
                        
                        ResultSet set=ps.executeQuery();
                        
                        if(set.next()) {
                            
                            String let=set.getString(2)+" "+set.getString(3)+"\n\n"+set.getString(4)+"\n\n"
                                    +set.getString(5)+"\n\n"+set.getString(6)+"\n\n"+set.getString(7)
                                    +"\n\n"+set.getString(8)+"\n\n"+set.getString(9)+"\n\n"
                                    +set.getString("regnumber")
                                    +"\n============";
                            
                            dtls.setInfo(let);
                            
                        }
                        
                    } catch (SQLException er) {
                        factory.print(er.getMessage());
                    }
                    
                }
                
                else if(combo.getValue().toString().equals("Other Staff")) {
                    
                    try {
                        
                        PreparedStatement ps=factory.getPS("select *from otherstaff where id=?");
                        ps.setString(1,pfactory.getLogDa(lv.getSelectionModel().getSelectedItem().toString(), "id"));
                        
                        ResultSet set=ps.executeQuery();
                        
                        if(set.next()) {
                            
                            String let=set.getString(2)+" "+set.getString(3)+"\n\n"+set.getString(4)+"\n\n"
                                    +set.getString(5)+"\n\n"+set.getString(6)+"\n\n"+set.getString(7)
                                    +"\n\n"+set.getString(8)+"\n\n"+set.getString("nin")+"\n\n"
                                    
                                    +"\n============";
                            
                            dtls.setInfo(let);
                            
                        }
                        
                    } catch (SQLException er) {
                        factory.print(er.getMessage());
                    }
                    
                }
                
                else {
                    
                    try {
                        
                        PreparedStatement ps=factory.getPS("select *from pupils where id=? && class=?");
                        ps.setString(1,pfactory.getLogDa(lv.getSelectionModel().getSelectedItem().toString(), "id"));
                        ps.setString(2,combo.getValue().toString());
                        
                        ResultSet set=ps.executeQuery();
                        
                        if(set.next()) {
                            
                            String let=set.getString(2)+"\n\n"+set.getString(3)+"\n\n"
                                    +set.getString(4)+"\n\n"+set.getString(5)+"\n\n"+set.getString(6)
                                    +"\n\n"+set.getString("stream")+"\n\n"+set.getString("modeofstudy")+"\n\n"
                                    +set.getString("healthystatus")
                                    +"\n============";
                            
                            dtls.setInfo(let);
                            
                        }
                        
                    } catch (SQLException er) {
                        factory.print(er.getMessage());
                    }
                    
                }
                
            }
            
            //For full profile view
            if(act.getClickCount()==2) {
                
                if(combo.getValue().toString().equals("Teachers")) {
                    
                    if(trd!=null) {trd.close();}
                    
                    trd=pfactory.getTeacherProfile(owner, 
                            pfactory.getLogDa(lv.getSelectionModel().getSelectedItem().toString(), "id"));
                    
                    trd.show();
                    
                }
                
                if(combo.getValue().toString().equals("Other Staff")) {
                    
                    if(trd!=null) {trd.close();}
                    
                    trd=pfactory.getStaffProfile(owner, 
                            pfactory.getLogDa(lv.getSelectionModel().getSelectedItem().toString(), "id"));
                    
                    trd.show();
                    
                }
                
                
                if(combo.getValue().toString().equals("Pupils")) {
                    
                    if(trd!=null) {trd.close();}
                    
                    trd=pfactory.getPupilProfile(owner, 
                            pfactory.getLogDa(lv.getSelectionModel().getSelectedItem().toString(), "id"));
                    
                    trd.show();
                    
                }
            }
            
        });
        
        sr.setOnKeyReleased(key->{
            
            if(sr.getText().toLowerCase().equals("#transported")) {
                
                try {
                
                    ser=new java.util.ArrayList();
                       
                PreparedStatement ps=factory.getPS("select *from pupils where transport=\'true\' && class=?");
                ps.setString(1,combo.getValue().toString());
                ResultSet set=ps.executeQuery();
                
                while(set.next()) {
                    
                    ser.add(set.getString(1)+" | "+set.getString(2));
                    
                }
                
                } catch (SQLException er) {
                    System.out.println(er.getMessage());
                }
                
            }
            
            else if(sr.getText().toLowerCase().equals("#not transported")) {
                
                try {
                
                    ser=new java.util.ArrayList();
                    
                PreparedStatement ps=factory.getPS("select *from pupils where transport=\'false\' && class=?");
                ps.setString(1,combo.getValue().toString());
                ResultSet set=ps.executeQuery();
                
                while(set.next()) {
                    
                    ser.add(set.getString(1)+" | "+set.getString(2));
                    
                }
                
                } catch (SQLException er) {
                    System.out.println(er.getMessage());
                }
                
            }
            
            else {
            
            ser=new java.util.ArrayList();
            
            list.forEach((item)->{
                
                if(item.toLowerCase().contains(sr.getText().toLowerCase())) {
                    ser.add(item);
                }
                
                
                
            });
            
            }
            
            lv.setItems(FXCollections.observableArrayList(ser));
            
        });
        
        pr.setOnAction(act->{
            
                if(combo.getValue().toString().equals("Teachers")) {
                    
                    if(trd!=null) {trd.close();}
                    
                    trd=pfactory.getTeacherProfile(owner, 
                            pfactory.getLogDa(lv.getSelectionModel().getSelectedItem().toString(), "id"));
                    
                    trd.show();
                    
                }
                
                else if(combo.getValue().toString().equals("Other Staff")) {
                    
                    if(trd!=null) {trd.close();}
                    
                    trd=pfactory.getStaffProfile(owner, 
                            pfactory.getLogDa(lv.getSelectionModel().getSelectedItem().toString(), "id"));
                    
                    trd.show();
                    
                }
                
                
                else {
                    
                    if(trd!=null) {trd.close();}
                    
                    trd=pfactory.getPupilProfile(owner, 
                            pfactory.getLogDa(lv.getSelectionModel().getSelectedItem().toString(), "id"));
                    
                    trd.show();
                    
                }
            
        });
        
        cs.setOnAction(er->{
            
                if(combo.getValue().toString().equals("Teachers")) {
                    
                    try {
                    PreparedStatement ps=factory.getPS("select *from teachers where id=?");
                    ps.setString(1,pfactory.getLogDa(lv.getSelectionModel().getSelectedItem().toString(), "id"));
                    ResultSet set=ps.executeQuery();
                    
                    if(set.next()) {
                        
                        String newstat;
                        if(set.getString("status").equals("true"))
                            
                            newstat="false";
                        
                        else newstat="true";
                        
                        PreparedStatement ps1=factory.getPS("update teachers set status=? where id=?");
                        ps1.setString(1,newstat); ps1.setString(2,
                                pfactory.getLogDa(lv.getSelectionModel().getSelectedItem().toString(), "id"));
                        
                        ps1.executeUpdate();
                        
                        dtls.setInfo("Profile for "+
                                pfactory.getLogDa(lv.getSelectionModel().getSelectedItem().toString(), "name")
                                +" has been set to a new "
                                        + "status. \n\n Status: "+newstat);
                        
                    }
                    
                    } catch (SQLException he) {
                        System.out.println(he.getMessage());
                    }
                    
                }
                
                else if(combo.getValue().toString().equals("Other Staff")) {
                    
                    try {
                    PreparedStatement ps=factory.getPS("select *from otherstaff where id=?");
                    ps.setString(1,pfactory.getLogDa(lv.getSelectionModel().getSelectedItem().toString(), "id"));
                    ResultSet set=ps.executeQuery();
                    
                    if(set.next()) {
                        
                        String newstat;
                        if(set.getString("status").equals("true"))
                            
                            newstat="false";
                        
                        else newstat="true";
                        
                        PreparedStatement ps1=factory.getPS("update otherstaff set status=? where id=?");
                        ps1.setString(1,newstat); ps1.setString(2,
                                pfactory.getLogDa(lv.getSelectionModel().getSelectedItem().toString(), "id"));
                        
                        ps1.executeUpdate();
                        
                        dtls.setInfo("Profile for "+
                                pfactory.getLogDa(lv.getSelectionModel().getSelectedItem().toString(), "name")
                                +" has been set to a new "
                                        + "status. \n\n Status: "+newstat);
                        
                    }
                    
                    } catch (SQLException he) {
                        System.out.println(he.getMessage());
                    }
                    
                }
                
                
                else {
                    
                    try {
                    PreparedStatement ps=factory.getPS("select *from pupils where id=?");
                    ps.setString(1,pfactory.getLogDa(lv.getSelectionModel().getSelectedItem().toString(), "id"));
                    ResultSet set=ps.executeQuery();
                    
                    if(set.next()) {
                        
                        String newstat;
                        if(set.getString("status").equals("true"))
                            
                            newstat="false";
                        
                        else newstat="true";
                        
                        PreparedStatement ps1=factory.getPS("update pupils set status=? where id=?");
                        ps1.setString(1,newstat); ps1.setString(2,
                                pfactory.getLogDa(lv.getSelectionModel().getSelectedItem().toString(), "id"));
                        
                        ps1.executeUpdate();
                        
                        dtls.setInfo("Profile for "+
                                pfactory.getLogDa(lv.getSelectionModel().getSelectedItem().toString(), "name")
                                +" has been set to a new "
                                        + "status. \n\n Status: "+newstat);
                        
                    }
                    
                    } catch (SQLException he) {
                        System.out.println(he.getMessage());
                    }
                    
                }
        });
        
        vpr.setOnAction(act->{
            
            if(trd!=null) {trd.close();}
            
            trd=pfactory.getTheseProiles(owner, combo.getValue().toString());
            
            trd.show();
            
        });
        
    }
    
    private void addN(javafx.scene.Node node,int a, int b) {
        this.add(node,a,b);
    }
    
    private static smx.jfx.layout.Dialog trd;
    
    private static java.util.List<String> list,ser;
    
}
