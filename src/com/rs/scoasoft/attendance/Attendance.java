/*
 * Attendance class
 */
package com.rs.scoasoft.attendance;

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
import java.sql.*;
import javafx.collections.*;

public class Attendance extends smx.jfx.layout.GridPane {
    
    private static String datee;
    
    private String getDatee() {return datee;}
    
    /**
     * Initial
     * @param owner
     * @param dtls 
     */
    @SuppressWarnings("ConvertToStringSwitch")
    public Attendance(javafx.stage.Stage owner, com.rs.scoasoft.DetailsPane dtls) {
        
        //Factory class
        com.rs.scoasoft.Factory factory=new com.rs.scoasoft.Factory();
        com.rs.scoasoft.profiles.ProfilesFactory prof=new com.rs.scoasoft.profiles.ProfilesFactory();
        AttendanceFactory afactory=
                new AttendanceFactory();
        
            com.rs.scoasoft.performance.Performance per=new com.rs.scoasoft.performance.Performance();
            
        
        java.util.Date date=new java.util.Date();
        java.text.DateFormat fmt=java.text.DateFormat.getDateInstance(java.text.DateFormat.LONG);
        
        afactory.createAttendance(fmt.format(date));
        datee=fmt.format(date);
        
        //Label 
        Label l=new Label("Attendance");
        l.setStyle(factory.getLabelBStyle("grey"));
        
        //Combo
        
        smx.jfx.control.ComboBox combo=new smx.jfx.control.ComboBox();
        combo.setDefStyle(factory.getTFStyle()); combo.setPromptText("Select");
        combo.setHoverStyle(factory.getTFStyle()+"-fx-border-width: 5px; "
                + "-fx-border-color: slateblue;");
        
        combo.getItems().addAll("Teachers","Other staff","Nursery", "Primary one", "Primary two", "Primary three",
                "Primary four", "Primary five", "Primary six", "Primary seven");
        
        combo.setPrefWidth(200);
        
        TextField ser=new TextField(); ser.setPromptText("Search and sort");
        ser.setPrefWidth(320); ser.setStyle(factory.getTFStyle());
        
        //ListView
        ListView lv=new ListView(); lv.setStyle(factory.getLVStyle());
        lv.setPrefWidth(1000); lv.setPrefHeight(800);
        
        //Menu Items
        
        MenuItem cs=new MenuItem("Set attendance to false");
        MenuItem cs2=new MenuItem("Set attendance to true");
        MenuItem log=new MenuItem("View Attendance log");
        MenuItem cs3=new MenuItem("Set all to false");
        MenuItem cs4=new MenuItem("Set all to true");
        
        ContextMenu cm=new ContextMenu(log,cs2,cs,cs3,cs4);
        cm.setStyle(factory.getTFStyle());
        
        lv.setContextMenu(cm);
        ///////////////////////////////////////////
        
        smx.jfx.layout.GridPane sr=factory.getSGrid();
        sr.add(combo,0,0); sr.add(ser,1,0);
        
        setVgap(5); setHgap(5); setAlignment(Pos.CENTER); setStyle(factory.getPaneColor());
        setPadding(new Insets(3,3,3,3));
        
        addN(l,0,0); addN(sr,0,2); addN(lv,0,3);
        
        // Actions
        combo.setOnAction(act->{
            
            if(combo.getValue().toString().equals("Teachers")) {
            
            try {
                    
                    PreparedStatement ps=
                            factory.getPS("select *from attendancet where date=\'"+datee+"\' "
                                    + "&& term=? && year=? order by fullnames asc");
                    ps.setString(1,per.ty()[0]);
                    ps.setString(2,per.ty()[1]);
                    
                    ResultSet set=ps.executeQuery();
                    
                    list=new ArrayList();
                    
                    while(set.next()) {
                        
                        list.add(set.getString(2)+" | "+set.getString(3));
                        
                    }
                    
                    lv.setItems(FXCollections.observableArrayList(list));
                        
                } catch (SQLException er) {
                    factory.print(er.getMessage());
                }
            }
            
            else if(combo.getValue().toString().equals("Other staff")) {
            
            try {
                    
                    PreparedStatement ps=
                            factory.getPS("select *from attendanceo where date=\'"+datee+"\' "
                                    + "&& term=? && year=? order by fullnames asc");
                    ps.setString(1,per.ty()[0]);
                    ps.setString(2,per.ty()[1]);
                    
                    ResultSet set=ps.executeQuery();
                    
                        list=new ArrayList();
                        
                    while(set.next()) {
                        
                        list.add(set.getString(2)+" | "+set.getString(3));
                        
                    }
                    
                        lv.setItems(FXCollections.observableArrayList(list));
                        
                    
                } catch (SQLException er) {
                    factory.print(er.getMessage());
                }
            }
            
            else {
              
            try {
                    
                    PreparedStatement ps=
                            factory.getPS("select *from attendancep where date=\'"+datee+"\' && class=\'"
                                    +combo.getValue().toString()+"\' "
                                            + "&& term=? && year=? order by fullnames asc");
                    ps.setString(1,per.ty()[0]);
                    ps.setString(2,per.ty()[1]);
                    
                    ResultSet set=ps.executeQuery();
                    
                        list=new ArrayList();
                        
                    while(set.next()) {
                        
                        list.add(set.getString(2)+" | "+set.getString(3));
                        
                    }
                    
                        lv.setItems(FXCollections.observableArrayList(list));
                        
                } catch (SQLException er) {
                    factory.print(er.getMessage());
                }
            }
            
            
        });
        
        
        ser.setOnKeyReleased(key->{
            
            seri=new java.util.ArrayList();
            
            list.forEach((item)->{
                
                if(item.toLowerCase().contains(ser.getText().toLowerCase())) {
                    seri.add(item);
                }
                
            });
            
            
            lv.setItems(FXCollections.observableArrayList(seri));
            
        });
        
        ser.setOnAction(act->{
            
            if(dialog!=null) {dialog.close();}
            
            dialog=afactory.getFullAttendanceLog(owner, ser.getText(), combo.getValue().toString());
            
            dialog.show();
            
        });
        
        lv.setOnMouseClicked(mouse->{
            
            try {
            
            if(mouse.getClickCount()==1) {
                
                select=lv.getSelectionModel().getSelectedItem().toString();
                lb="";
                
                
                if(combo.getValue().toString().equals("Teachers"))
                {
                    
                    PreparedStatement pst=factory.getPS("select *from attendancet where owner=? "
                            + "&& term=? && year=?");
                    pst.setString(1,prof.getLogDa(select,"id"));
                    pst.setString(2,per.ty()[0]);
                    pst.setString(3,per.ty()[1]);
                    
                    ResultSet se=pst.executeQuery();
                    
                    if(se.last()) {
                        
                        lb+=se.getString(3)+"\n\n"+se.getString(4)+"\n\n"+se.getString(5)+"\n\nAttendance: "
                                +se.getString(6);
                        
                    }
                    
                }
                
                else if(combo.getValue().toString().equals("Other staff"))
                {
                    
                    PreparedStatement pst=factory.getPS("select *from attendanceo where owner=? "
                            + "&& term=? && year=?");
                    pst.setString(1,prof.getLogDa(select,"id"));
                    pst.setString(2,per.ty()[0]);
                    pst.setString(3,per.ty()[1]);
                    
                    ResultSet se=pst.executeQuery();
                    
                    if(se.last()) {
                        
                        lb+=se.getString(3)+"\n\n"+se.getString(4)+"\n\n"+se.getString(5)+"\n\nAttendance: "
                                +se.getString(6);
                        
                    }
                    
                    
                }
                
                else {
                    
                    PreparedStatement pst=factory.getPS("select *from attendancep where owner=? "
                            + "&& term=? && year=?");
                    pst.setString(1,prof.getLogDa(select,"id"));
                    pst.setString(2,per.ty()[0]);
                    pst.setString(3,per.ty()[1]);
                    
                    ResultSet se=pst.executeQuery();
                    
                    if(se.last()) {
                        
                        lb+=se.getString(3)+"\n\n"+se.getString(4)+"\n\n"+se.getString(5)+"\n\nAttendance: "
                                +se.getString(6);
                        
                    }
                    
                    
                }
                
                dtls.setInfo(lb);
                
            }
            
            } catch (SQLException er) {
                factory.print(er.getMessage());
            }
            
        });
        
        cs.setOnAction(act->{
            
            try {
            
            String me=combo.getValue().toString();
            
            switch(me) {
                
                case "Teachers" :
                    
                    PreparedStatement pst=factory.getPS("update attendancet set attend=\'false\' where owner=\'"
                            + prof.getLogDa(select,"id") + "\' && date=\'"+getDatee()+"\'");
                    pst.executeUpdate();
                    
                    dtls.setInfo(prof.getLogDa(select, "name")+" attendance status has been set to 'false'");
                    
                    break;
                    
                case "Other staff" :
                    
                    PreparedStatement pst1=factory.getPS("update attendanceo set attend=\'false\' where owner=\'"
                            + prof.getLogDa(select,"id") + "\' && date=\'"+getDatee()+"\'");
                    pst1.executeUpdate();
                    
                    dtls.setInfo(prof.getLogDa(select, "name")+" attendance status has been set to 'false'");
                    
                    break;
                    
                default : 
                    
                    PreparedStatement pst2=factory.getPS("update attendancep set attend=\'false\' where owner=\'"
                            + prof.getLogDa(select,"id") + "\' && date=\'"+getDatee()+"\'");
                    pst2.executeUpdate();
                    
                    dtls.setInfo(prof.getLogDa(select, "name")+" attendance status has been set to 'false'");
                    
                    
                    break;
                
            }
            
            } catch (SQLException er) {
                factory.print(er.getMessage());
            }
            
        });
        
        cs2.setOnAction(act->{
            
            try {
            
            String me=combo.getValue().toString();
            
            switch(me) {
                
                case "Teachers" :
                    
                    PreparedStatement pst=factory.getPS("update attendancet set attend=\'true\' where owner=\'"
                            + prof.getLogDa(select,"id") + "\' && date=\'"+getDatee()+"\'");
                    pst.executeUpdate();
                    
                    dtls.setInfo(prof.getLogDa(select, "name")+" attendance status has been set to 'trues'");
                    
                    break;
                    
                case "Other staff" :
                    
                    PreparedStatement pst1=factory.getPS("update attendanceo set attend=\'true\' where owner=\'"
                            + prof.getLogDa(select,"id") + "\' && date=\'"+getDatee()+"\'");
                    pst1.executeUpdate();
                    
                    dtls.setInfo(prof.getLogDa(select, "name")+" attendance status has been set to 'true'");
                    
                    break;
                    
                default : 
                    
                    PreparedStatement pst2=factory.getPS("update attendancep set attend=\'true\' where owner=\'"
                            + prof.getLogDa(select,"id") + "\' && date=\'"+getDatee()+"\'");
                    pst2.executeUpdate();
                    
                    dtls.setInfo(prof.getLogDa(select, "name")+" attendance status has been set to 'true'");
                    
                    
                    break;
                
            }
            
            } catch (SQLException er) {
                factory.print(er.getMessage());
            }
            
        });
        
        log.setOnAction(act->{
            
            if(dialog!=null) dialog.close();
            
            dialog=afactory.getAttendanceLog(owner, prof.getLogDa(select,"id"), 
                    combo.getValue().toString(), prof.getLogDa(select,"name"));
            
            dialog.show();
            
            
        });
        
        cs3.setOnAction(act->{
            
            try {
            
            PreparedStatement ps=factory.getPS("update attendancet set attend=\'false\' where date=? && term=? && year=?");
            ps.setString(1,getDatee());
            ps.setString(2,per.ty()[0]);
            ps.setString(3,per.ty()[1]);
            
            ps.executeUpdate();
            
            
            PreparedStatement ps2=factory.getPS("update attendanceo set attend=\'false\' where date=? && term=? && year=?");
            ps2.setString(1,getDatee());
            ps2.setString(2,per.ty()[0]);
            ps2.setString(3,per.ty()[1]);
            
            ps2.executeUpdate();
            
            
            PreparedStatement ps3=factory.getPS("update attendancep set attend=\'false\' where date=? && term=? && year=?");
            ps3.setString(1,getDatee());
            ps3.setString(2,per.ty()[0]);
            ps3.setString(3,per.ty()[1]);
            
            ps3.executeUpdate();
            
            
            } catch (SQLException ex) {
                factory.print(ex.getMessage());
            }
        });
        
        cs4.setOnAction(act->{
            
            try {
            
            PreparedStatement ps=factory.getPS("update attendancet set attend=\'true\' where date=? && term=? && year=?");
            ps.setString(1,getDatee());
            ps.setString(2,per.ty()[0]);
            ps.setString(3,per.ty()[1]);
            
            ps.executeUpdate();
            
            
            PreparedStatement ps2=factory.getPS("update attendanceo set attend=\'true\' where date=? && term=? && year=?");
            ps2.setString(1,getDatee());
            ps2.setString(2,per.ty()[0]);
            ps2.setString(3,per.ty()[1]);
            
            ps2.executeUpdate();
            
            
            PreparedStatement ps3=factory.getPS("update attendancep set attend=\'true\' where date=? && term=? && year=?");
            ps3.setString(1,getDatee());
            ps3.setString(2,per.ty()[0]);
            ps3.setString(3,per.ty()[1]);
            
            ps3.executeUpdate();
            
            } catch (SQLException ex) {
                factory.print(ex.getMessage());
            }
        });
        
    }
    
    /**
     * add nodes to grid
     * @param node
     * @param a
     * @param b 
     */
    private void addN(javafx.scene.Node node,int a,int b) {
        add(node,a,b);
    }
    
    private static List<String> list,seri;
     
    private static smx.jfx.layout.Dialog dialog;
    
    private static String select,lb;
    
}
