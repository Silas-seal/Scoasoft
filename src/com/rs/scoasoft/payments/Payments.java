/*
 * Payments B Class
 */
package com.rs.scoasoft.payments;

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

public class Payments extends smx.jfx.layout.GridPane {
    
    /**
     * Initial
     * @param owner
     * @param dtls 
     */
    public Payments(javafx.stage.Stage owner,com.rs.scoasoft.DetailsPane dtls) {
        //Factory class
        com.rs.scoasoft.Factory factory=new com.rs.scoasoft.Factory();
        com.rs.scoasoft.profiles.ProfilesFactory prof=new com.rs.scoasoft.profiles.ProfilesFactory();
        com.rs.scoasoft.payments.PaymentsFactory pfactory=
                new com.rs.scoasoft.payments.PaymentsFactory();
        com.rs.scoasoft.performance.Performance per=new com.rs.scoasoft.performance.Performance();
        
        //Label 
        Label l=new Label("Payments");
        l.setStyle(factory.getLabelBStyle("grey"));
        
        //Button
        smx.jfx.control.Button vlist=new smx.jfx.control.Button("View this list");
        vlist.setDefaultStyle(factory.getNBTStyle2()); vlist.setHoverStyle(factory.getNHBTStyle2());
        
        //Combo
        
        smx.jfx.control.ComboBox combo=new smx.jfx.control.ComboBox();
        combo.setDefStyle(factory.getTFStyle()); combo.setPromptText("Select");
        combo.setHoverStyle(factory.getTFStyle()+"-fx-border-width: 5px; "
                + "-fx-border-color: slateblue;");
        
        combo.getItems().addAll("Nursery", "Primary one", "Primary two", "Primary three",
                "Primary four", "Primary five", "Primary six", "Primary seven");
        
        combo.setPrefWidth(200);
        
        TextField ser=new TextField(); ser.setPromptText("Search and sort");
        ser.setPrefWidth(320); ser.setStyle(factory.getTFStyle());
        
        //ListView
        ListView lv=new ListView(); lv.setStyle(factory.getLVStyle());
        lv.setPrefWidth(1000); lv.setPrefHeight(800);
        
        //Menu Items
        
        MenuItem ed=new MenuItem("Edit data");
        MenuItem pay=new MenuItem("Transactions");
        MenuItem det=new MenuItem("View standings");
        
        ContextMenu cm=new ContextMenu(pay,ed,det);
        cm.setStyle(factory.getTFStyle());
        
        lv.setContextMenu(cm);
        ///////////////////////////////////////////
        
        smx.jfx.layout.GridPane sr=factory.getSGrid();
        sr.add(combo,0,0); sr.add(ser,1,0);
        
        smx.jfx.layout.GridPane bt=factory.getSGrid();
        bt.add(vlist,0,0);
        
        setVgap(5); setHgap(5); setAlignment(Pos.CENTER); setStyle(factory.getPaneColor());
        setPadding(new Insets(3,3,3,3));
        
        addN(l,0,0); addN(bt,0,1); addN(sr,0,2); addN(lv,0,3);
        
        //Actions
        combo.setOnAction(act->{
            try {
                    
                    PreparedStatement ps=factory.getPS("select *from payments where class=? && term=? && year=?");
                    ps.setString(1,combo.getValue().toString());
                    ps.setString(2,per.ty()[0]);
                    ps.setString(3,per.ty()[1]);
                    
                    ResultSet set=ps.executeQuery();
                    
                    list=new ArrayList();
                        
                    while(set.next()) {
                        
                        list.add(set.getString(2)+" | "+set.getString(3));
                        
                    }
                    
                    lv.setItems(FXCollections.observableArrayList(list));
                    
                } catch (SQLException er) {
                    factory.print(er.getMessage());
                }
        });
        
        ser.setOnKeyReleased(key->{
            
            if(!ser.getText().startsWith("#") && !ser.getText().startsWith("#")) {
            
            seri=new java.util.ArrayList();
            
            list.forEach((item)->{
                
                if(item.toLowerCase().contains(ser.getText().toLowerCase())) {
                    seri.add(item);
                }
                
            });
            
            } else {
                
                if(ser.getText().startsWith("#above") && ser.getText().length()>=8) {
                    
            seri=new java.util.ArrayList();
            
                    String[] sp=ser.getText().split(" ");
                    int figure=Integer.valueOf(sp[1]);
                    factory.print(figure);
                    list.forEach((com)->{
                        
                        if(pfactory.getPay(prof.getLogDa(com,"id"))>=figure) {
                            seri.add(com);
                            factory.print(com);
                        }
                        
                    });
                    
                }
                
                if(ser.getText().startsWith("#below") && ser.getText().length()>=8) {
                    
            seri=new java.util.ArrayList();
            
                    String[] sp=ser.getText().split(" ");
                    int figure=Integer.valueOf(sp[1]);
                    
                    list.forEach((com)->{
                        
                        if(pfactory.getPay(prof.getLogDa(com,"id"))<=figure) {
                            seri.add(com);
                        }
                        
                    });
                    
                }
                
            }
            
            lv.setItems(FXCollections.observableArrayList(seri));
            
        });
        
        ed.setOnAction(er->{
            
            if(dialog!=null) dialog.close();
            
            select=lv.getSelectionModel().getSelectedItem().toString();
            
            dialog=pfactory.getPayEditForm(owner, prof.getLogDa(select, "id"));
            
            dialog.show();
            
        });
        
        lv.setOnMouseClicked(act->{
            
            try {
            
            if(act.getClickCount()==1) {
                
                select=lv.getSelectionModel().getSelectedItem().toString(); String msg="";
                
                PreparedStatement ps=factory.getPS("select *from payments where owner=? && term=? && year=?");
                ps.setString(1,prof.getLogDa(select,"id"));
                ps.setString(2,per.ty()[0]);
                ps.setString(3,per.ty()[1]);
                
                ResultSet set=ps.executeQuery();
                
                if(set.next()) {
                    
                    msg+=select+"\n\nPaid : "+set.getString("pay")+"\n\nFees : "+set.getString("fullpay")
                            +"\n\nAdmission : "+set.getString("admission")+"\n\nUniform : "+set.getString("uniform")
                            +"\n\nTransport : "+set.getString("transport");
                    
                }
                
                dtls.setInfo(msg);
                
            }
            
            if(act.getClickCount()==2) {
                
                
            if(dialog!=null) dialog.close();
            
            select=lv.getSelectionModel().getSelectedItem().toString();
            
            dialog=pfactory.getPayEditForm(owner, prof.getLogDa(select, "id"));
            
            dialog.show();
            
                
            }
            
            } catch (SQLException er) {
                factory.print(er.getMessage());
            }
            
        });
        
        vlist.setOnAction(act->{
            
            if(dialog!=null) dialog.close();
            
            dialog=pfactory.getPayList(owner, lv.getItems(), ser.getText(), combo.getValue().toString());
            
            dialog.show();
            
        });
        
        pay.setOnAction(act->{
            
            if(dialog!=null) dialog.close();
            
            dialog=pfactory.getTransactionForm(owner, prof.getLogDa(select, "id"));
            
            dialog.show();
            
        });
        
        det.setOnAction(act->{
            
            if(dialog!=null) dialog.close();
            
            dialog=pfactory.getPayDetails(owner, select, combo.getValue().toString());
            
            dialog.show();
            
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
    
    private static String select;
    
}
