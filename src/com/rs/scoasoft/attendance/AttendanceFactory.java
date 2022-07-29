/*
 * attendance factory file(class)
 */
package com.rs.scoasoft.attendance;

/**
 *
 * @author Remote Station
 */

//import javafx.scene.control.*;
import javafx.scene.image.*;
//import javafx.geometry.*;
//import javafx.animation.*;
//import javafx.util.*;
import javafx.scene.Scene;

import java.io.*;
//import java.util.*;
import java.sql.*;
//import javafx.collections.*;
import javafx.print.PrinterJob;

public class AttendanceFactory {
    
    /**
     * Initial
     */
    public AttendanceFactory() {
        
    }
    
    /**
     * Creates all profile to present status
     * @param date
     */
    public void createAttendance(String date) {
        
        com.rs.scoasoft.Factory f=new com.rs.scoasoft.Factory();
        com.rs.scoasoft.performance.Performance per=new com.rs.scoasoft.performance.Performance();
        
        try {
            
            boolean aval=true;
            
            PreparedStatement ps=f.getPS("select date from attendancet");
            ResultSet set=ps.executeQuery();
            
            if(set.last()) {
                if(!set.getString(1).equals(date)) {
                    aval=false;
                }
            } else {
                aval=false;
            }
            
            if(!aval) {
                
                //Begin with teachers
                PreparedStatement p=f.getPS("select *from teachers");
                ResultSet s=p.executeQuery();
                
                while(s.next()) {
                    
                    PreparedStatement pst=f.getPS("insert into attendancet(owner,fullnames,title,date,attend,term,year) "
                            + "values(?,?,?,?,?,?,?)");
                    pst.setString(1,s.getString(1));
                    pst.setString(2,s.getString(2)+" "+s.getString(3));
                    pst.setString(3,s.getString("title"));
                    pst.setString(4,date);
                    pst.setString(5,"true");
                    pst.setString(6,per.ty()[0]);
                    pst.setString(7,per.ty()[1]);
                    
                    pst.executeUpdate();
                    
                }
                
                //Go with Other staff
                PreparedStatement p1=f.getPS("select *from otherstaff");
                ResultSet s1=p1.executeQuery();
                
                while(s1.next()) {
                    
                    PreparedStatement pst=f.getPS("insert into attendanceo(owner,fullnames,title,date,attend,term,year) "
                            + "values(?,?,?,?,?,?,?)");
                    pst.setString(1,s1.getString(1));
                    pst.setString(2,s1.getString(2)+" "+s1.getString(3));
                    pst.setString(3,s1.getString("title"));
                    pst.setString(4,date);
                    pst.setString(5,"true");
                    pst.setString(6,per.ty()[0]);
                    pst.setString(7,per.ty()[1]);
                    
                    pst.executeUpdate();
                    
                }
                
                //Go to kids
                PreparedStatement p2=f.getPS("select *from pupils");
                ResultSet s2=p2.executeQuery();
                
                while(s2.next()) {
                    
                    PreparedStatement pst=f.getPS("insert into attendancep(owner,fullnames,class,date,attend,term,year) "
                            + "values(?,?,?,?,?,?,?)");
                    pst.setString(1,s2.getString(1));
                    pst.setString(2,s2.getString(2));
                    pst.setString(3,s2.getString("class"));
                    pst.setString(4,date);
                    pst.setString(5,"true");
                    pst.setString(6,per.ty()[0]);
                    pst.setString(7,per.ty()[1]);
                    
                    
                    pst.executeUpdate();
                    
                }
                
            }
            
        } catch (SQLException er) {
            System.out.println(er.getMessage());
        }
        
    }
    
    
    /**
     * Returns the dialog for the attendance log!
     * @param owner
     * @param id
     * @param combo
     * @param name
     * @return 
     */
    @SuppressWarnings("UseSpecificCatch")
    public smx.jfx.layout.Dialog getAttendanceLog(javafx.stage.Stage owner, String id, String combo, String name) {
        
        dialog=null;
        
        //get Table name
        String tname;
        
        switch(combo)
        {
            
            case "Teachers" : 
                tname="attendancet";
                break;
                
            case "Other staff" :
                tname="attendanceo";
                break;
                
            default : 
                tname="attendancep";
                break;
            
        }
        
        com.rs.scoasoft.Factory factory=new com.rs.scoasoft.Factory();
        com.rs.scoasoft.performance.Performance per=new com.rs.scoasoft.performance.Performance();
        
        try {
        
        //web
        javafx.scene.web.WebView web=new javafx.scene.web.WebView();
        web.setPrefWidth(812); web.setPrefHeight(550);
        
        //get
        
        rep="<html>"
                + "<head>"
                + "<title>Payments</title>"
                + "<style>"
                + ".body {"
                + "font-family: Lucida Sans;"
                + "font-size: 21px;"
                + "color: royalblue;"
                + "background-color: white;"
                + "}"
                + ".body2 {"
                + "font-family: Lucida Sans;"
                + "font-size: 21px;"
                + "color: royalblue;"
                + "background-image: url(bgfile.png);"
                + "}"
                + "</style>"
                + "</head>"
                
                + "<body class=\"body2\" align=\"center\">"
                
                + "<table border=\"10\" cellpadding=\"7\" cellpadding=\"7\" width=\"98%\" bordercolor=\"royalblue\">"
                + "<tr><td align=\"center\" class=\"body\">"
                + "<p align=\"center\"><img src=\"logo.png\" width=\"175\" height=\"182\" alt=\"LOGO\" /></p>"
                
                + "Attendance log "+ "\'for the last 70 days\'<p/>"+per.ty()[0]+" "+per.ty()[1]+"<p class=\"body\">"+name+"</p>"
                
                + "<table border=\"5\" cellpadding=\"7\" cellpadding=\"7\" width=\"100%\" bordercolor=\"royalblue\">"
                + "<tr><td align=\"center\">Date</td>"
                + "<td align=\"center\">Attendance</td></tr>";
                
                //Get Data
                
                PreparedStatement pst=
                        factory.getPS("select *from "+tname+" where owner=\'"+id+"\' order by id desc");
                
                ResultSet set=pst.executeQuery();
                
                int limit=70; String color=null;
                
                while(set.next() && limit>0) {
                    
                    rep+="<tr><td align=\"center\">"+set.getString("date")+"</td>";
                    
                    if(set.getString("attend").equals("true")) {
                        color="darkgreen";
                    }
                    else if(set.getString("attend").equals("false")) {
                        color="darkred";
                    }
                    
                    rep+="<td align=\"center\" bgcolor=\""+color+"\">"+set.getString("attend")
                            +"</td></tr>";
                    
                    limit--;
                    
                }
                
                rep+= "</table></td></tr>"
                + "</table>"
                
                + "</body> </html>";
        
        
        
        //Write data
        factory.writeFile(rep, "temp.html");
        
        //Load on web
        web.getEngine().load(new File("temp.html").toURL().toURI().toString());
        
        //Print button
        smx.jfx.control.Button print=new smx.jfx.control.Button("Print");
        print.setDefaultStyle(factory.getNBTStyle2()); 
        print.setHoverStyle(factory.getNHBTStyle2());
        
        smx.jfx.layout.GridPane pane=factory.getSGrid();
        pane.add(web,0,0);
        pane.add(print,0,1);
        
                //Actions
                print.setOnAction(er->{
                    
                    PrinterJob job=PrinterJob.createPrinterJob();
                    if(job!=null && job.showPrintDialog(owner)) {
                        
                        web.getEngine().print(job);
                        job.endJob(); dialog.close();
                        
                    }
                    
                });
                
                dialog=new smx.jfx.layout.Dialog(owner, true, false, 750, 525, new Scene(pane), "Attendance Log", 
                new Image(new FileInputStream("logo.png")));
                
        
        } catch(Exception er) {
            System.out.println(er.getMessage());
        }
        
        return dialog;
        
    }
    
    
    /**
     * Returns the dialog for the attendance log!
     * @param owner
     * @param ser
     * @param combo
     * @return 
     */
    @SuppressWarnings("UseSpecificCatch")
    public smx.jfx.layout.Dialog getFullAttendanceLog(javafx.stage.Stage owner, String ser, String combo) {
        
        dialog=null;
        
        //get Table name
        String tname;
        
        switch(combo)
        {
            
            case "Teachers" : 
                tname="attendancet";
                break;
                
            case "Other staff" :
                tname="attendanceo";
                break;
                
            default : 
                tname="attendancep";
                break;
            
        }
        
        com.rs.scoasoft.Factory factory=new com.rs.scoasoft.Factory();
        com.rs.scoasoft.performance.Performance per=new com.rs.scoasoft.performance.Performance();
        
        try {
        
        //web
        javafx.scene.web.WebView web=new javafx.scene.web.WebView();
        web.setPrefWidth(812); web.setPrefHeight(550);
        
        //get
        
        rep="<html>"
                + "<head>"
                + "<title>Attendace</title>"
                + "<style>"
                + ".body {"
                + "font-family: Lucida Sans;"
                + "font-size: 21px;"
                + "color: royalblue;"
                + "background-color: white;"
                + "}"
                + ".body2 {"
                + "font-family: Lucida Sans;"
                + "font-size: 21px;"
                + "color: royalblue;"
                + "background-image: url(bgfile.png);"
                + "}"
                + "</style>"
                + "</head>"
                
                + "<body class=\"body2\" align=\"center\">"
                
                + "<table border=\"10\" cellpadding=\"7\" cellpadding=\"7\" width=\"98%\" bordercolor=\"royalblue\">"
                + "<tr><td align=\"center\" class=\"body\">"
                + "<p align=\"center\"><img src=\"logo.png\" width=\"175\" height=\"182\" alt=\"LOGO\" /></p>"
                
                + "Attendance log <p class=\"body\">"+combo+"</p>"+per.ty()[0]+" "+per.ty()[1]+"<p class=\"body\">"+ser+"</p>"
                
                + "<table border=\"5\" cellpadding=\"7\" cellpadding=\"7\" width=\"100%\" bordercolor=\"royalblue\">"
                + "<tr><td align=\"center\">Name</td>"
                + "<td align=\"center\">Attendance</td></tr>";
                
                //Get Data
                
                PreparedStatement pst;
                
                if(tname.equals("attendancep")) {
                        pst=factory.getPS("select *from "+tname+" where date=\'"+ser+"\' "
                                + "&& class=\'"+combo+"\'order by fullnames asc"); }
                else {
                    pst=factory.getPS("select *from "+tname+" where date=\'"+ser+"\' "
                                + "order by fullnames asc");
                }
                
                ResultSet set=pst.executeQuery();
                
                String color=null; int ttt=0,pres=0,abse=0;
                
                while(set.next()) {
                    
                    rep+="<tr><td align=\"center\">"+set.getString("fullnames")+"</td>";
                    
                    if(set.getString("attend").equals("true")) {
                        color="darkgreen"; pres++;
                    }
                    else if(set.getString("attend").equals("false")) {
                        color="darkred"; abse++;
                    }
                    
                    rep+="<td align=\"center\" bgcolor=\""+color+"\">"+set.getString("attend")
                            +"</td></tr>";
                  ttt++;
                }
                
                rep+= "</table>Total : "+ttt+" | Present : "+pres+" | Absent : "+abse+"</td></tr>"
                + "</table>"
                
                + "</body> </html>";
        
        
        
        //Write data
        factory.writeFile(rep, "temp.html");
        
        //Load on web
        web.getEngine().load(new File("temp.html").toURL().toURI().toString());
        
        //Print button
        smx.jfx.control.Button print=new smx.jfx.control.Button("Print");
        print.setDefaultStyle(factory.getNBTStyle2()); 
        print.setHoverStyle(factory.getNHBTStyle2());
        
        smx.jfx.layout.GridPane pane=factory.getSGrid();
        pane.add(web,0,0);
        pane.add(print,0,1);
        
                //Actions
                print.setOnAction(er->{
                    
                    PrinterJob job=PrinterJob.createPrinterJob();
                    if(job!=null && job.showPrintDialog(owner)) {
                        
                        web.getEngine().print(job);
                        job.endJob(); dialog.close();
                        
                    }
                    
                });
                
                dialog=new smx.jfx.layout.Dialog(owner, true, false, 750, 525, new Scene(pane), "Attendance Log", 
                new Image(new FileInputStream("logo.png")));
                
        
        } catch(Exception er) {
            System.out.println(er.getMessage());
        }
        
        return dialog;
        
    }
    
    private static String rep;
    private static smx.jfx.layout.Dialog dialog;
    
    
}
