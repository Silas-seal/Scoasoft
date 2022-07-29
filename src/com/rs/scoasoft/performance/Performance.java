/*
 * The performance interface
 */
package com.rs.scoasoft.performance;

/**
 *
 * @author bruce
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

public class Performance extends smx.jfx.layout.GridPane {
    
    /**
     * Initializing
     * @param owner
     * @param dtls 
     */
    public Performance(javafx.stage.Stage owner, com.rs.scoasoft.DetailsPane dtls) {
        
        //Factory class
        com.rs.scoasoft.Factory factory=new com.rs.scoasoft.Factory();
        com.rs.scoasoft.profiles.ProfilesFactory prof=new com.rs.scoasoft.profiles.ProfilesFactory();
        PerformanceFactory pfactory=new PerformanceFactory();
        
        //Label 
        Label l=new Label("Performance");
        l.setStyle(factory.getLabelBStyle("grey"));
        
        //Button
        smx.jfx.control.Button reports=new smx.jfx.control.Button("View reports for this class");
        reports.setDefaultStyle(factory.getNBTStyle2()); reports.setHoverStyle(factory.getNHBTStyle2());
        
        //Combo
        
        smx.jfx.control.ComboBox combo=new smx.jfx.control.ComboBox();
        combo.setDefStyle(factory.getTFStyle()); combo.setPromptText("Select");
        combo.setHoverStyle(factory.getTFStyle()+"-fx-border-width: 5px; "
                + "-fx-border-color: slateblue;");
        
        combo.getItems().addAll("Primary three","Primary four", "Primary five", "Primary six", "Primary seven");
        
        combo.setPrefWidth(200);
        
        smx.jfx.control.MenuButton sheet=new smx.jfx.control.MenuButton();
        sheet.setDefStyle(factory.getNBTStyle2()+"-fx-accent: royalblue;"
                +"-fx-accent: royalblue;".replaceAll("9px","0px")); 
        sheet.setText("Sheet");
        sheet.setHoverStyle(factory.getNHBTStyle2()+"-fx-accent: royalblue;".replaceAll("9px","0px"));
        
        MenuItem mid=new MenuItem("Mid-term");
        MenuItem ter=new MenuItem("Termly");
        MenuItem back=new MenuItem("Back page");
        
        sheet.getItems().addAll(mid,ter,back);
        
        TextField ser=new TextField(); ser.setPromptText("Search and sort");
        ser.setPrefWidth(320); ser.setStyle(factory.getTFStyle());
        
        //ListView
        ListView lv=new ListView(); lv.setStyle(factory.getLVStyle());
        lv.setPrefWidth(1000); lv.setPrefHeight(800);
        
        //Menu Items
        MenuItem vr=new MenuItem("View report");
        MenuItem ed=new MenuItem("Edit data");
        
        ContextMenu cm=new ContextMenu(vr,ed);
        cm.setStyle(factory.getTFStyle());
        
        lv.setContextMenu(cm);
        ///////////////////////////////////////////
        
        smx.jfx.layout.GridPane sr=factory.getSGrid();
        sr.add(combo,0,0); sr.add(ser,1,0);
        
        smx.jfx.layout.GridPane bt=factory.getSGrid();
        bt.setHgap(51);
        bt.add(reports,0,0); bt.add(sheet,1,0);
        
        setVgap(5); setHgap(5); setAlignment(Pos.CENTER); setStyle(factory.getPaneColor());
        setPadding(new Insets(3,3,3,3));
        
        addN(l,0,0); addN(bt,0,1); addN(sr,0,2); addN(lv,0,3);
        
        //set year and term values
        try {
                    PreparedStatement pp=factory.getPS("select *from tandy");
                    ResultSet r=pp.executeQuery();
                    
                    if(r.next()) {
                        
                        y=r.getString(3);
                        t=r.getString(2);
                        
                    }
                    
        } catch (SQLException er) {
            factory.print(er.getMessage());
        }
        
        //Actions
        
        combo.setOnAction(act->{
            
            if(combo.getValue().toString()!=null) {
                
                String clas=combo.getValue().toString();
                
                try {
                    
                    PreparedStatement ps=factory.getPS("select *from pupils where class=\'"+clas+"\'");
                    ResultSet set=ps.executeQuery();
                    
                    list=new ArrayList();
                    
                    while(set.next()) {
                        
                        list.add(set.getString(1)+" | "+set.getString(2));
                        
                    }
                    
                    lv.setItems(FXCollections.observableArrayList(list));
                    
                    
                } catch (SQLException er) {
                    factory.print(er.getMessage());
                }
                
            }
            
        });
        
        lv.setOnMouseClicked(clk->{
            
            if(clk.getClickCount()==1) {
                
                select=lv.getSelectionModel().getSelectedItem().toString();
                
                String info=prof.getLogDa(select, "name")+"\n\nMid-term:\n\n";
                
                
                try {
                    /*y=null; t=null;
                    PreparedStatement pp=factory.getPS("select *from tandy");
                    ResultSet r=pp.executeQuery();
                    
                    if(r.next()) {
                        
                        y=r.getString(3);
                        t=r.getString(2);
                        
                    }
                    */
                    //Mid term
                PreparedStatement ps=factory.getPS("select *from midterm where owner=? && term=? && year=?");
                ps.setString(1,prof.getLogDa(select, "id"));
                ps.setString(2,t); ps.setString(3,y);
                
                ResultSet set=ps.executeQuery();
                
                if(set.next()) {
                    info+="English="+set.getString("english")+" | Mathematics="+set.getString("math")
                            +" | Science="+set.getString("science")+" | SST="+set.getString("sst")+";\n\n";
                }
                
                   //End term
                   
                   info+="Termly: \n\n";
                   
                PreparedStatement ps1=factory.getPS("select *from endterm where owner=? && term=? && year=?");
                ps1.setString(1,prof.getLogDa(select, "id"));
                ps1.setString(2,t); ps1.setString(3,y);
                
                ResultSet set1=ps1.executeQuery();
                
                if(set1.next()) {
                    info+="English="+set1.getString("english")+" | Mathematics="+set1.getString("math")
                            +" | Science="+set1.getString("science")+" | SST="+set1.getString("sst")+";\n\n";
                }
                
                info+="\n\n===============";
                
                dtls.setInfo(info);
                
                } catch (SQLException ex) {
                    factory.print(ex.getMessage());
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
        
        vr.setOnAction(act->{
            
            if(dialog!=null) {dialog.close();}
            
            dialog=pfactory.getFullReport(owner, prof.getLogDa(select,"id"), y, t, combo.getValue().toString());
            
            dialog.show();
            
        });
        
        ed.setOnAction(act->{
            
            if(dialog!=null) {dialog.close();}
            
            dialog=pfactory.getPerEditForm(owner, prof.getLogDa(select,"id"), y, t);
            
            dialog.show();
            
        });
        
        reports.setOnAction(act->{
                if(combo.getValue()!=null && list.size()>0) {
            //Disable button
            reports.setDisable(true); String tempoa=reports.getText(); reports.setText("Processing reports.......");
            
            javafx.animation.FadeTransition fd=new javafx.animation.FadeTransition(javafx.util.Duration.seconds(2));
            fd.setFromValue(0); fd.setToValue(1); fd.setAutoReverse(false); fd.setCycleCount(1);
            fd.setNode(reports);
            
            fd.play();
            
            fd.setOnFinished(acti->{
                
            if(dialog!=null) {dialog.close();}
          
            dialog=pfactory.getAllReports(owner, y, t, combo.getValue().toString(),reports,tempoa);
            
            dialog.show();
            
            });
            
                }
            
        });
        
        mid.setOnAction(act->{
            
            if(dialog!=null) {dialog.close();}
            
            dialog=pfactory.getPerformanceSheet(owner, combo.getValue().toString());
            
            dialog.show();
            
        });
        
        ter.setOnAction(act->{
            
            if(dialog!=null) {dialog.close();}
            
            dialog=pfactory.getPerformanceSheet2(owner, combo.getValue().toString());
            
            dialog.show();
            
        });
        
        back.setOnAction(act->{
            
            if(dialog!=null) {dialog.close();}
            
            dialog=pfactory.getBackPage(owner);
            
            dialog.show();
            
        });
        
    }
    
    private static smx.jfx.layout.Dialog dialog;
    
    private static List<String> list,seri;
    
    private void addN(javafx.scene.Node node,int a,int b) {
        add(node,a,b);
    }
    
    private static String y,t,select;
    
    /**
     * Returns the term and year
     * @return 
     */
    public String[] ty(){
        
        String[] rtn=new String[2];
        rtn[0]=t; rtn[1]=y;
        
        return rtn;
    }
    
    /**
     * Initial
     */
    public Performance() {
        
    }
}
