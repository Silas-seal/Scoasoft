/*
 * The performance control panel
 */
package com.rs.scoasoft.performance;

/**
 *
 * @author Remote Station
 */

import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.geometry.*;
//import javafx.animation.*;
//import javafx.util.*;
import javafx.scene.Scene;

import java.io.*;
import java.util.*;
import java.sql.*;

public class ControlPane extends smx.jfx.layout.GridPane {
    
    /**
     * Initializing
     * @param owner
     * @param dtls 
     */
    public ControlPane(javafx.stage.Stage owner, com.rs.scoasoft.DetailsPane dtls) {
        
        com.rs.scoasoft.Factory factory=new com.rs.scoasoft.Factory();  
            
        //Ranges
        Label l=new Label("Control Panel");
        l.setStyle(factory.getLabelBStyle("grey"));
        
        Label ranges=new Label("Ranges");
        ranges.setStyle(factory.getLabelMStyle("grey"));
       
        TextField eng=new TextField();
        eng.setStyle(factory.getTFStyle()); eng.setPrefWidth(421);
        eng.setPromptText("Grade scale");
        
        TextField sci=new TextField();
        sci.setStyle(factory.getTFStyle()); sci.setPrefWidth(421);
        sci.setPromptText("Division scale");
        
        List<TextField> lk=new ArrayList();
        lk.add(eng); /*lk.add(math);*/ lk.add(sci); /*lk.add(sst);*/ //lk.add(re);
        
        smx.jfx.control.Button rsub=new smx.jfx.control.Button("Submit");
        rsub.setDefaultStyle(factory.getNBTStyle2()); rsub.setHoverStyle(factory.getNHBTStyle2());
        
        rsub.setOnAction(act->{
            
            if(factory.validateTF(lk)) {
            
            try {
                
                PreparedStatement ps1=factory.getPS("delete from ranges");
                ps1.executeUpdate();
                
                PreparedStatement ps2=factory.getPS("insert into ranges(ranges,divisions) values(?,?)");
                ps2.setString(1,eng.getText());
                ps2.setString(2,sci.getText());
                
                eng.setText(""); sci.setText("");
                
                ps2.executeUpdate();
                
                dtls.setInfo("Ranges data has been successfully updated!");
                
            } catch (SQLException ex) {
                factory.print(ex.getMessage());
            }
            
            }
            
        });
        
        smx.jfx.layout.GridPane rg=factory.getSGrid();
        //rg.add(ranges,0,0); 
        rg.add(eng,1,0);
        rg.add(sci,1,1);
        /*rg.add(re,1,2);*/ rg.add(rsub,2,1);
        
        //Creditials
        Label cred=new Label("Initials");
        cred.setStyle(factory.getLabelMStyle("grey"));
        
        smx.jfx.control.Button crede=new smx.jfx.control.Button("Enter teachers' initials & Add. info");
        crede.setDefaultStyle(factory.getNBTStyle2()); crede.setHoverStyle(factory.getNHBTStyle2());
        
        smx.jfx.layout.GridPane pc=factory.getSGrid();
        /*pc.add(cred,0,0);*/ pc.add(crede,1,0);
        
        crede.setOnAction(rew->{
            
            if(dialog!=null) dialog.close();
            
            dialog=credit(owner, dtls);
            dialog.show();
            
        });
        
        //Comments
        Label cmt=new Label("Comments");
        cmt.setStyle(factory.getLabelMStyle("grey"));
        
        TextArea hm=new TextArea(); hm.setPromptText("Head teacher's commenting");
        hm.setPrefWidth(421); hm.setPrefHeight(300); hm.setStyle(factory.getTFStyle());
        
        TextArea ct=new TextArea(); ct.setPromptText("Class teacher's commenting");
        ct.setPrefWidth(421); ct.setPrefHeight(300); ct.setStyle(factory.getTFStyle());
        
        smx.jfx.control.Button subc=new smx.jfx.control.Button("Submit");
        subc.setDefaultStyle(factory.getNBTStyle2()); subc.setHoverStyle(factory.getNHBTStyle2());
        
        smx.jfx.layout.GridPane cpane=factory.getSGrid();
        //cpane.add(cmt,0,0);
        cpane.add(hm,0,1);
        cpane.add(ct,0,2); cpane.add(subc,1,2);
        
        subc.setOnAction(act->{
            
            if(hm.getText().length()>4 && ct.getText().length()>4) {
            
            try {
                
                PreparedStatement ps1=factory.getPS("delete from comments");
                ps1.executeUpdate();
                
                PreparedStatement ps3=factory.getPS("insert into comments(headteacher,classteacher) values(?,?)");
                ps3.setString(1,hm.getText()); ps3.setString(2,ct.getText());
                
                hm.setText(""); ct.setText("");
                
                ps3.executeUpdate();
                
                dtls.setInfo("Comments data has been successfully updated.");
                
            } catch(SQLException ex) {
                factory.print(ex.getMessage());
            }
            
            }
            
        });
        
        //Term and year
        Label tandy=new Label("Term and year");
        tandy.setStyle(factory.getLabelMStyle("grey"));
        
        TextField term=new TextField();
        term.setStyle(factory.getTFStyle()); term.setPrefWidth(300);
        term.setPromptText("Term");
        
        TextField year=new TextField();
        year.setStyle(factory.getTFStyle()); year.setPrefWidth(300);
        year.setPromptText("Year");
        
        smx.jfx.control.Button subty=new smx.jfx.control.Button("Submit");
        subty.setDefaultStyle(factory.getNBTStyle2()); subty.setHoverStyle(factory.getNHBTStyle2());
        
        smx.jfx.layout.GridPane ty=factory.getSGrid();
        //ty.add(tandy,0,0); 
        ty.add(term,0,1); ty.add(year,1,1);
        ty.add(subty,1,2);
        
        List<TextField> lk1=new ArrayList();
        lk1.add(term); lk1.add(year);
        
        subty.setOnAction(act->{
            
            if(factory.validateTF(lk1)) {
            
            try {
                
                PreparedStatement ps1=factory.getPS("delete from tandy");
                ps1.executeUpdate();
                
                PreparedStatement ps4=factory.getPS("insert into tandy(term,year) values(?,?)");
                ps4.setString(1,term.getText()); ps4.setString(2,year.getText());
                
                term.setText(""); year.setText("");
                
                ps4.executeUpdate();
                
                dtls.setInfo("Term and year data has been successfully updated.");
                
                //Stop application
                owner.close();
                
                //Restart the application
                java.awt.Desktop desktop=java.awt.Desktop.getDesktop();
                desktop.open(new java.io.File("scoasoft.exe"));
                
                
            } catch(SQLException | IOException ex) {
                factory.print(ex.getMessage());
            }
            
            }
            
        });
        
        this.setVgap(5); this.setHgap(5); this.setPadding(new Insets(2,2,2,2));
        this.setAlignment(Pos.TOP_CENTER); this.setStyle(factory.getPaneColor());
        addN(l,0,0); addN(rg,0,2);
        addN(pc,0,3);
        addN(cpane,0,4);
        addN(ty,0,5);
        
        
    }
    
    /**
     * Adding nodes to 
     * @param node
     * @param a
     * @param b 
     */
    private void addN(javafx.scene.Node node,int a,int b) {
        this.add(node,a,b);
    }
    
    private static smx.jfx.layout.Dialog credit(javafx.stage.Stage owner, com.rs.scoasoft.DetailsPane dtls) {
        
        com.rs.scoasoft.Factory factory=new com.rs.scoasoft.Factory();
        
        try {
            
            Label l=new Label("Teachers' initials");
            l.setStyle(factory.getLabelMStyle("grey"));
            
            Label add=new Label("Additional information");
            add.setStyle(factory.getLabelNStyle("grey"));
            /*
            Label p5=new Label("Primary five");
            p5.setStyle(factory.getLabelNStyle("grey"));
            
            Label p6=new Label("Primary six");
            p6.setStyle(factory.getLabelNStyle("grey"));
            
            Label p7=new Label("Primary seven");
            p7.setStyle(factory.getLabelNStyle("grey"));
            */
                    
        TextField p3cred=new TextField();
        p3cred.setStyle(factory.getTFStyle()); p3cred.setPrefWidth(355);
        p3cred.setPromptText("Primary three Initials");
        
        TextField p4cred=new TextField();
        p4cred.setStyle(factory.getTFStyle()); p4cred.setPrefWidth(355);
        p4cred.setPromptText("Primary four Initials");
        
        TextField p5cred=new TextField();
        p5cred.setStyle(factory.getTFStyle()); p5cred.setPrefWidth(355);
        p5cred.setPromptText("Primary five Initials");
        
        TextField p6cred=new TextField();
        p6cred.setStyle(factory.getTFStyle()); p6cred.setPrefWidth(355);
        p6cred.setPromptText("Primary six Initials");
        
        TextField p7cred=new TextField();
        p7cred.setStyle(factory.getTFStyle()); p7cred.setPrefWidth(355);
        p7cred.setPromptText("Primary seven Initials");
        
        smx.jfx.control.Button sub=new smx.jfx.control.Button("Submit");
        sub.setDefaultStyle(factory.getNBTStyle2()); sub.setHoverStyle(factory.getNHBTStyle2());
        
        //More Additional Info
        
        TextArea addin=new TextArea(); addin.setPromptText("Additional information here!\tForexample: "
                + "next term starting date,"
                + "Fees and school requirements,"
                + "\tAnd any other info. \tCompose it here!");
        addin.setPrefWidth(421); addin.setPrefHeight(300); addin.setStyle(factory.getTFStyle());
        addin.setWrapText(true);
        
        
        smx.jfx.layout.GridPane crp=factory.getSGrid();
        crp.add(p3cred,0,0); crp.add(p4cred,0,1);
        crp.add(p5cred,0,2);
        crp.add(p6cred,0,3);
        crp.add(p7cred,0,4);
        crp.add(add,0,5);
        crp.add(addin,0,6);
        
        crp.add(sub,0,7);
        
        smx.jfx.layout.GridPane lo=factory.getSGrid();
        lo.add(l,0,0);
        
        smx.jfx.layout.GridPane sn=factory.getSGrid();
        sn.add(lo,0,0); sn.add(crp,0,1);
        
        List<TextField> lk1=new ArrayList();
        lk1.add(p3cred);
        lk1.add(p4cred); lk1.add(p5cred);
        lk1.add(p6cred); lk1.add(p7cred);
        
        sub.setOnAction(act->{
            
            if(factory.validateTF(lk1)) {
            
            try {
                
                PreparedStatement ps1=factory.getPS("delete from creditials");
                ps1.executeUpdate();
                
                PreparedStatement ps0=factory.getPS("insert into creditials(class,creditials) values(?,?)");
                ps0.setString(1,"Primary three"); ps0.setString(2,p3cred.getText()); 
                
                ps0.executeUpdate();
                
                PreparedStatement ps=factory.getPS("insert into creditials(class,creditials) values(?,?)");
                ps.setString(1,"Primary four"); ps.setString(2,p4cred.getText()); 
                
                ps.executeUpdate();
                
                PreparedStatement ps2=factory.getPS("insert into creditials(class,creditials) values(?,?)");
                ps2.setString(1,"Primary five"); ps2.setString(2,p5cred.getText()); 
                
                ps2.executeUpdate();
                
                
                PreparedStatement ps3=factory.getPS("insert into creditials(class,creditials) values(?,?)");
                ps3.setString(1,"Primary six"); ps3.setString(2,p6cred.getText()); 
                
                ps3.executeUpdate();
                
                PreparedStatement ps4=factory.getPS("insert into creditials(class,creditials) values(?,?)");
                ps4.setString(1,"Primary seven"); ps4.setString(2,p7cred.getText()); 
                
                ps4.executeUpdate();
                
                //for additional info here
                
                PreparedStatement ps12=factory.getPS("delete from additionalinfo");
                ps12.executeUpdate();
                
                PreparedStatement ps123=factory.getPS("insert into additionalinfo(content) values(?)");
                ps123.setString(1,addin.getText());
                ps123.executeUpdate();
                
                
                dialog.close();
                
                dtls.setInfo("Credentials data has been successfully updated.");
                
            } catch (SQLException ex) {
                factory.print(ex.getMessage());
            }
            
            }
            
        });
        
        dialog=new smx.jfx.layout.Dialog(owner, true, false, 531, 571, new Scene(sn), 
                "Update Credentials", new Image(new FileInputStream("logo.png")));
            
        } catch (IOException ex) {
            factory.print(ex.getMessage());
        }
        
        return dialog;
    }
    
    private static smx.jfx.layout.Dialog dialog;
    
}
