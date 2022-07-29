/*
 * The performance factory
 */
package com.rs.scoasoft.performance;

/**
 *
 * @author Remote Station
 */

import com.rs.scoasoft.NumberField;
import javafx.scene.control.*;
import javafx.scene.image.*;
//import javafx.geometry.*;
//import javafx.animation.*;
//import javafx.util.*;
import javafx.scene.Scene;

import java.io.*;
import java.util.*;
import java.sql.*;
import javafx.print.PrinterJob;

public class PerformanceFactory {
    
    /**
     * Initializing
     */
    public PerformanceFactory() {
        
    }
    
    /**
     * Returns the grade in accordance to score argument
     * @param score
     * @return 
     */
    public String getGrade(String score) {
        
        String grade=null, tempor="";
        
        try {
            
            PreparedStatement ps=new com.rs.scoasoft.Factory().getPS("select ranges from ranges");
            ResultSet set=ps.executeQuery();
            
            if(set.next()) {
                
                tempor=set.getString(1);
                
            }
            
            String[] te=tempor.split(" ");
            
            for(String str:te) {
                
                String[] te2=str.split("-");
                
                if(Integer.valueOf(score)>=Integer.valueOf(te2[0])
                        && Integer.valueOf(score)<=Integer.valueOf(removeEq(te2[1],"value"))) {
                    
                    grade=removeEq(te2[1],"grade");
                }
                
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return grade;
    }
    
    /**
     * Removes the equals sign and returns the Value
     * @param value
     * @param target
     * @return 
     */
    private String removeEq(String value, String target) {
        
        String[] w=value.split("=");
        
        String v=null;
        
        if(target.equals("value")) {
            v=w[0];
        } else 
            if(target.equals("grade")) {
                v=w[1];
            }
        
        return v;
        
    }
    
    /**
     * Returns the total number of Aggregates
     * @param list
     * @return 
     */
    public String getTotalAgg(List<String> list) {
        
        String tt; temp=0;
        
        list.forEach((grade)->{
            
            char[] ch=grade.toCharArray();
            
            temp+=Integer.valueOf(ch[1]);
            
        });
        
        tt=String.valueOf(temp);
        
        return tt;
        
    }
    
    private static int temp;
    
    /**
     * Returns the total of passed list of integers
     * @param list
     * @return 
     */
    public String getTotal(List<String> list) {
        
        String tt; temp=0;
        
        list.forEach((score)->{
            
            temp+=Integer.valueOf(score);
            
        });
        
        tt=String.valueOf(temp);
        
        return tt;
    }
    
    /**
     * Returns the HM Comment
     * @param totalagg
     * @return 
     */
    public String getHTComment(String totalagg) {
        
        String hm=null; String tem=""; String[] tems;
        
        try {
            
            PreparedStatement ps=new com.rs.scoasoft.Factory().getPS("select headteacher from comments");
            ResultSet set=ps.executeQuery();
            
            if(set.next()) {
                tem=set.getString(1);
            }
            
            tems=tem.split("\n");
            
            for(String t:tems) {
                
                String[] st=t.split("-");
                
                if(Integer.valueOf(totalagg) >= Integer.valueOf(st[0]) 
                        && Integer.valueOf(totalagg)<=Integer.valueOf(removeEq(st[1],"value"))) {
                    
                    hm=removeEq(st[1],"grade");
                }
                
            }
            
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        
        return hm;
        
    }
    
    
    /**
     * Returns the CT Comment
     * @param totalagg
     * @return 
     */
    public String getCTComment(String totalagg) {
        
        String hm="None"; String tem=""; String[] tems;
        
        try {
            
            PreparedStatement ps=new com.rs.scoasoft.Factory().getPS("select classteacher from comments");
            ResultSet set=ps.executeQuery();
            
            if(set.next()) {
                tem=set.getString(1);
            }
            
            tems=tem.split("\n");
            
            for(String t:tems) {
                
                String[] st=t.split("-");
                
                if(Integer.valueOf(totalagg) >= Integer.valueOf(st[0]) 
                        && Integer.valueOf(totalagg)<=Integer.valueOf(removeEq(st[1],"value"))) {
                    
                    hm=removeEq(st[1],"grade");
                }
                
            }
            
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        
        return hm;
        
    }
    
    /**
     * Comments about the score from 0-100
     * @param score
     * @return 
     */
    public String getScoreComment(String score) {
        
        String comment=null;
        
        if(Integer.valueOf(score)>=0 && Integer.valueOf(score)<=24) {
            
            comment="IMPROVE";
            
        }
        
        if(Integer.valueOf(score)>=25 && Integer.valueOf(score)<=39) {
            
            comment="FAIR";
            
        }
        
        if(Integer.valueOf(score)>=40 && Integer.valueOf(score)<=59) {
            
            comment="AVERAGE";
            
        }
        
        if(Integer.valueOf(score)>=60 && Integer.valueOf(score)<=79) {
            
            comment="GOOD";
            
        }
        
        if(Integer.valueOf(score)>=80 && Integer.valueOf(score)<=89) {
            
            comment="IMPRESSIVE";
            
        }
        
        
        if(Integer.valueOf(score)>=90 && Integer.valueOf(score)<=100) {
            
            comment="EXCELLENT";
            
        }
        
        
        return comment;
        
    }
    
    /**
     * Returns the name to the id
     * @param id
     * @return 
     */
    public String getNameById(String id) {
        
        String name=null;
        
        try {
        PreparedStatement ps=new com.rs.scoasoft.Factory().getPS("select fullnames from pupils where id="+id);
        ResultSet set=ps.executeQuery();
        
        if(set.next()) {
            
            name=set.getString(1);
            
        }
        
        } catch (SQLException ew) {
            System.out.println(ew.getMessage());
        }
        return name;
        
    }
    
    /**
     * Dialog to edit performance for pupils
     * @param owner
     * @param profileid
     * @param year
     * @param term
     * @return 
     */
    public smx.jfx.layout.Dialog getPerEditForm(javafx.stage.Stage owner, String profileid, String year, String term) {
        
        dialog=null;
        com.rs.scoasoft.Factory factory=new com.rs.scoasoft.Factory();
        com.rs.scoasoft.profiles.ProfilesFactory prof=new com.rs.scoasoft.profiles.ProfilesFactory();
        
        //Nodes
        try {
            
            //get the profile first
            
            String name=null,pic=null; clas=null; 
            
            rowidt=null; rowidm=null;
            
            PreparedStatement ps=factory.getPS("select *from pupils where id="+profileid);
            ResultSet set=ps.executeQuery();
            
            if(set.next()) {
                
                name=set.getString(2); pic=set.getString("picture"); clas=set.getString("class");
                
            }
            
            //collect midterm and termly
            
            String me="",mm="",ms="",mst="",mre="",
                    te="",tm="",ts="",tst="",tre="";
            aval=false;
            
            PreparedStatement ps1=factory.getPS("select *from midterm where owner=? && term=? && year=?");
            ps1.setString(1,profileid); ps1.setString(2,term); ps1.setString(3,year);
            
            ResultSet set1=ps1.executeQuery();
            if(set1.next()) {
                me=set1.getString("english"); mm=set1.getString("math");
                ms=set1.getString("science"); mst=set1.getString("sst");
                mre=set1.getString("re");
                
                aval=true; rowidm=set1.getString("id");
            }
            
            PreparedStatement ps2=factory.getPS("select *from endterm where owner=? && term=? && year=?");
            ps2.setString(1,profileid); ps2.setString(2,term); ps2.setString(3,year);
            
            ResultSet set2=ps2.executeQuery();
            if(set2.next()) {
                te=set2.getString("english"); tm=set2.getString("math");
                ts=set2.getString("science"); tst=set2.getString("sst");
                tre=set2.getString("re");
                
                aval=true; rowidt=set2.getString("id");
            }
            
            //Form Nodes
            
            Label l=new Label(name+"\n\n"+clas);
            l.setStyle(factory.getLabelBStyle("grey"));
            
            ImageView imv=new ImageView(new Image(new FileInputStream(pic)));
            imv.setFitWidth(172); imv.setFitHeight(182);
            
            smx.jfx.layout.GridPane top=factory.getSGrid(); top.setHgap(51);
            top.add(l,0,0); top.add(imv,1,0);
            
            Label mid=new Label("Mid-term");
            mid.setStyle(factory.getLabelMStyle("grey"));
            
            NumberField meng=new NumberField(me); meng.setPrefWidth(300);
            meng.setStyle(factory.getTFStyle()); meng.setPromptText("English");
            
            NumberField mmat=new NumberField(mm); mmat.setPrefWidth(300);
            mmat.setStyle(factory.getTFStyle()); mmat.setPromptText("Mathematics");
            
            NumberField msc=new NumberField(ms); msc.setPrefWidth(300);
            msc.setStyle(factory.getTFStyle()); msc.setPromptText("Science");
            
            NumberField mss=new NumberField(mst); mss.setPrefWidth(300);
            mss.setStyle(factory.getTFStyle()); mss.setPromptText("SST");
            
            NumberField mrel=new NumberField(mre); mrel.setPrefWidth(300);
            mrel.setStyle(factory.getTFStyle()); mrel.setPromptText("RE");
            
            smx.jfx.layout.GridPane mgr=factory.getSGrid();
            mgr.add(mid,1,0);
            mgr.add(meng,0,1); mgr.add(mmat,1,1);
            mgr.add(msc,0,2); mgr.add(mss,1,2);
            //mgr.add(mrel,2,2);
            
            //termly
            
            Label tid=new Label("Termly");
            tid.setStyle(factory.getLabelMStyle("grey"));
            
            NumberField teng=new NumberField(te); teng.setPrefWidth(300);
            teng.setStyle(factory.getTFStyle()); teng.setPromptText("English");
            
            NumberField tmat=new NumberField(tm); tmat.setPrefWidth(300);
            tmat.setStyle(factory.getTFStyle()); tmat.setPromptText("Mathematics");
            
            NumberField tsc=new NumberField(ts); tsc.setPrefWidth(300);
            tsc.setStyle(factory.getTFStyle()); tsc.setPromptText("Science");
            
            NumberField tss=new NumberField(tst); tss.setPrefWidth(300);
            tss.setStyle(factory.getTFStyle()); tss.setPromptText("SST");
            
            NumberField trel=new NumberField(tre); trel.setPrefWidth(300);
            trel.setStyle(factory.getTFStyle()); trel.setPromptText("RE");
            
            smx.jfx.layout.GridPane mgr1=factory.getSGrid();
            mgr1.add(tid,1,0);
            mgr1.add(teng,0,1); mgr1.add(tmat,1,1);
            mgr1.add(tsc,0,2); mgr1.add(tss,1,2);
            //mgr1.add(trel,2,2);
            
            //TFS
            List<TextField> liu=new ArrayList();
            liu.add(meng); liu.add(mmat);
            liu.add(msc); liu.add(mss); //liu.add(mrel);
            
            liu.add(teng); liu.add(tmat);
            liu.add(tsc); liu.add(tss); //liu.add(trel);
            
            //sub btn
            smx.jfx.control.Button sub=new smx.jfx.control.Button("Submit");
            sub.setDefaultStyle(factory.getNBTStyle2());
            sub.setHoverStyle(factory.getNHBTStyle2());
            mgr1.add(sub,1,3);
            
            smx.jfx.layout.GridPane main=factory.getSGrid();
            main.add(top,0,0); main.add(mgr,0,1); main.add(mgr1,0,2);
           
            dialog=new smx.jfx.layout.Dialog(owner, true, false, 800, 555, new Scene(main), "Performance update", 
                    new Image(new FileInputStream("logo.png")));
            
            //Actions
            sub.setOnAction(act->{
                
                try {
                
                if(factory.validateTF(liu)) {
                    
                    if(aval) {
                        
                        //Midterm update
                        PreparedStatement po=factory.getPS("update midterm set english=?,math=?,science=?,sst=?,re=?"
                                + " where id=?");
                        po.setString(1,meng.getText()); 
                        po.setString(2,mmat.getText()); 
                        po.setString(3,msc.getText()); 
                        po.setString(4,mss.getText()); 
                        po.setString(5,mrel.getText()); 
                        po.setString(6,rowidm); 
                        
                        po.executeUpdate();
                        
                        //Endterm update
                        PreparedStatement po2=factory.getPS("update endterm set english=?,math=?,science=?,sst=?,re=?"
                                + " where id=?");
                        po2.setString(1,teng.getText()); 
                        po2.setString(2,tmat.getText()); 
                        po2.setString(3,tsc.getText()); 
                        po2.setString(4,tss.getText()); 
                        po2.setString(5,trel.getText()); 
                        po2.setString(6,rowidt); 
                        
                        po2.executeUpdate();
                        
                        //close
                        dialog.close();
                        
                    } else {
                        
                        //New midterm inserts
                        PreparedStatement po=factory.getPS("insert into midterm(owner,english,math,"
                                + "science,sst,re,term,year,class) values(?,?,?,?,?,?,?,?,?)");
                        po.setString(1,profileid); 
                        
                        po.setString(2,meng.getText()); 
                        po.setString(3,mmat.getText()); 
                        po.setString(4,msc.getText()); 
                        po.setString(5,mss.getText()); 
                        po.setString(6,mrel.getText()); 
                        po.setString(7,term);
                        po.setString(8,year);
                        po.setString(9,clas);
                        
                        po.executeUpdate();
                        
                        //New endterm inserts
                        PreparedStatement po2=factory.getPS("insert into endterm(owner,english,math,"
                                + "science,sst,re,term,year,class) values(?,?,?,?,?,?,?,?,?)");
                        po2.setString(1,profileid); 
                        
                        po2.setString(2,teng.getText()); 
                        po2.setString(3,tmat.getText()); 
                        po2.setString(4,tsc.getText()); 
                        po2.setString(5,tss.getText()); 
                        po2.setString(6,trel.getText()); 
                        po2.setString(7,term);
                        po2.setString(8,year);
                        po2.setString(9,clas);
                        
                        po2.executeUpdate();
                        
                        //close
                        dialog.close();
                        
                    }
                    
                }
                
                } catch(SQLException ex) {
                    factory.print(ex.getMessage());
                }
                
            });
            
        } catch (SQLException | IOException ex) {
            
        }
        
        return dialog;
        
    }
    
    /**
     * Returns the report dialog!
     * @param owner
     * @param profileid
     * @param year
     * @param term
     * @param clas
     * @return 
     */
    @SuppressWarnings("UseSpecificCatch")
    public smx.jfx.layout.Dialog getFullReport(javafx.stage.Stage owner, String profileid, String year, 
            String term, String clas) {
        
        dialog=null;
        
        com.rs.scoasoft.Factory factory=new com.rs.scoasoft.Factory();
        try {
        
        //web
        javafx.scene.web.WebView web=new javafx.scene.web.WebView();
        web.setPrefWidth(812); web.setPrefHeight(550);
        
        //get
        repo=new Report();
        String rep=repo.getReport(profileid,term,year,clas);
        
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
                
                dialog=new smx.jfx.layout.Dialog(owner, true, false, 795, 555, new Scene(pane), "Full Report", 
                new Image(new FileInputStream("logo.png")));
                
        
        } catch(Exception er) {
            System.out.println(er.getMessage());
        }
        
        return dialog;
        
    }
    
    
    /**
     * Returns the reports dialog for the passed class!
     * @param owner
     * @param year
     * @param term
     * @param clas
     * @param cmd
     * @param btext
     * @return 
     */
    @SuppressWarnings("UseSpecificCatch")
    public smx.jfx.layout.Dialog getAllReports(javafx.stage.Stage owner, String year, 
            String term, String clas, smx.jfx.control.Button cmd, String btext) {
        
        dialog=null;
        
        com.rs.scoasoft.Factory factory=new com.rs.scoasoft.Factory();
        try {
        
        //web
        javafx.scene.web.WebView web=new javafx.scene.web.WebView();
        web.setPrefWidth(812); web.setPrefHeight(550);
        
        //get reports
        String rep=""; repo=new Report();
        
        PreparedStatement pe=factory.getPS("select id from pupils where class=?");
        pe.setString(1,clas);
        
        ResultSet set=pe.executeQuery();
        
        while(set.next()) {
            
            rep+=repo.getReport(set.getString(1), term, year, clas);
            
        }
        
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
                
                
        //Enable button
        cmd.setDisable(false); cmd.setText(btext);
        
                
                dialog=new smx.jfx.layout.Dialog(owner, true, false, 795, 555, new Scene(pane), "Full Report", 
                new Image(new FileInputStream("logo.png")));
                
        
        } catch(Exception er) {
            System.out.println(er.getMessage());
        }
        
        return dialog;
        
    }
    
    /**
     * Returns the midterm performance sheet!
     * @param owner
     * 
     * @param combo
     * @return 
     */
    @SuppressWarnings("UseSpecificCatch")
    public smx.jfx.layout.Dialog getPerformanceSheet(javafx.stage.Stage owner, String combo) {
        
        dialog=null;
        
        com.rs.scoasoft.Factory factory=new com.rs.scoasoft.Factory();
        com.rs.scoasoft.profiles.ProfilesFactory prof=new com.rs.scoasoft.profiles.ProfilesFactory();
        com.rs.scoasoft.performance.PerformanceFactory perf=new 
                com.rs.scoasoft.performance.PerformanceFactory();
        Report report=new Report();
        
        Performance perform=new Performance();
        try {
        
        //web
        javafx.scene.web.WebView web=new javafx.scene.web.WebView();
        web.setPrefWidth(812); web.setPrefHeight(550);
        
        //get
        
        repi="<html>"
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
                
                + "Mid-term Performance Sheet<p/>"+perform.ty()[0]+" : "+perform.ty()[1]+"<p class=\"body\">"+combo+"</p>"
                
                + "<table border=\"5\" cellpadding=\"7\" cellpadding=\"7\" width=\"100%\" bordercolor=\"royalblue\">"
                + "<tr><td align=\"center\">Name</td>"
                + "<td align=\"center\">English</td><td align=\"center\">Mathematics</td>"
                + "<td align=\"center\">Science</td>"
                + "<td align=\"center\">Social studies</td><td align=\"center\">Total</td>"
                + "<td align=\"center\">Agg.</td><td align=\"center\">Pos.</td></tr>";
                
                //Get Data
                
                PreparedStatement pst=
                        factory.getPS("select *from midterm where class=\'"+combo+"\' "
                                + "&& term=? && year=? order by owner asc");
                
                pst.setString(1,(perform.ty()[0]));
                pst.setString(2,perform.ty()[1]);
                
                ResultSet set=pst.executeQuery();
                
                while(set.next()) {
                    
                    tempo=report.getReport(set.getString("owner"), perform.ty()[0], perform.ty()[1], combo);
                    
                    repi+="<tr><td align=\"center\">"+getNameById(set.getString("owner"))+"</td>"
                            
                            + "<td align=\"center\">"+report.midTermPerf()[0]+"<hr/>"
                            + report.midTermPerf()[4]+"</td>"
                            
                            + "<td align=\"center\">"+report.midTermPerf()[1]+"<hr/>"
                            + report.midTermPerf()[5]+"</td>"
                            
                            + "<td align=\"center\">"+report.midTermPerf()[2]+"<hr/>"
                            + report.midTermPerf()[6]+"</td>"
                            
                            + "<td align=\"center\">"+report.midTermPerf()[3]+"<hr/>"
                            + report.midTermPerf()[7]+"</td>"
                            
                            + "<td align=\"center\">"
                            +report.getPMTotal(set.getString("owner"), perform.ty()[0], perform.ty()[1], combo)
                            + "</td>"
                            
                            + "<td align=\"center\">"
                            + report.getAggTotal(report.getMAgg())
                            + "</td>"
                            + "<td align=\"center\">"
                            + report.getMPosition(set.getString("owner"), perform.ty()[0], perform.ty()[1], 
                                    combo)[0]
                            + "</td>";
                    
                }
                
                repi+= "</table></td></tr>"
                + "</table>"
                
                + "</body> </html>";
        
        
        
        //Write data
        factory.writeFile(repi, "temp.html");
        
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
                
                dialog=new smx.jfx.layout.Dialog(owner, true, false, 830, 555, new Scene(pane), "Performance sheet", 
                new Image(new FileInputStream("logo.png")));
                
        
        } catch(Exception er) {
            System.out.println(er.getMessage());
        }
        
        return dialog;
        
    }
    
     /**
     * Returns the midterm performance sheet!
     * @param owner
     * 
     * @param combo
     * @return 
     */
    @SuppressWarnings("UseSpecificCatch")
    public smx.jfx.layout.Dialog getPerformanceSheet2(javafx.stage.Stage owner, String combo) {
        
        dialog=null;
        
        com.rs.scoasoft.Factory factory=new com.rs.scoasoft.Factory();
        com.rs.scoasoft.profiles.ProfilesFactory prof=new com.rs.scoasoft.profiles.ProfilesFactory();
        com.rs.scoasoft.performance.PerformanceFactory perf=new 
                com.rs.scoasoft.performance.PerformanceFactory();
        Report report=new Report();
        
        Performance perform=new Performance();
        try {
        
        //web
        javafx.scene.web.WebView web=new javafx.scene.web.WebView();
        web.setPrefWidth(812); web.setPrefHeight(550);
        
        //get
        
        repi="<html>"
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
                
                + "Termly Performance Sheet<p/>"+perform.ty()[0]+" : "+perform.ty()[1]+"<p class=\"body\">"+combo+"</p>"
                
                + "<table border=\"5\" cellpadding=\"7\" cellpadding=\"7\" width=\"100%\" bordercolor=\"royalblue\">"
                + "<tr><td align=\"center\">Name</td>"
                + "<td align=\"center\">English</td><td align=\"center\">Mathematics</td>"
                + "<td align=\"center\">Science</td>"
                + "<td align=\"center\">Social studies</td><td align=\"center\">Total</td>"
                + "<td align=\"center\">Agg.</td><td align=\"center\">Pos.</td></tr>";
                
                //Get Data
                
                PreparedStatement pst=
                        factory.getPS("select *from endterm where class=\'"+combo+"\' "
                                + "&& term=? && year=? order by owner asc");
                
                pst.setString(1,(perform.ty()[0]));
                pst.setString(2,perform.ty()[1]);
                
                ResultSet set=pst.executeQuery();
                
                while(set.next()) {
                    
                    tempo=report.getReport(set.getString("owner"), perform.ty()[0], perform.ty()[1], combo);
                    
                    repi+="<tr><td align=\"center\">"+getNameById(set.getString("owner"))+"</td>"
                            
                            + "<td align=\"center\">"+report.endTermPerf()[0]+"<hr/>"
                            + report.endTermPerf()[4]+"</td>"
                            
                            + "<td align=\"center\">"+report.endTermPerf()[1]+"<hr/>"
                            + report.endTermPerf()[5]+"</td>"
                            
                            + "<td align=\"center\">"+report.endTermPerf()[2]+"<hr/>"
                            + report.endTermPerf()[6]+"</td>"
                            
                            + "<td align=\"center\">"+report.endTermPerf()[3]+"<hr/>"
                            + report.endTermPerf()[7]+"</td>"
                            
                            + "<td align=\"center\">"
                            +report.getPETotal(set.getString("owner"), perform.ty()[0], perform.ty()[1], combo)
                            + "</td>"
                            
                            + "<td align=\"center\">"
                            + report.getAggTotal(report.getEAgg())
                            + "</td>"
                            + "<td align=\"center\">"
                            + report.getTPosition(set.getString("owner"), perform.ty()[0], perform.ty()[1], 
                                    combo)[0]
                            + "</td>";
                    
                }
                
                repi+= "</table></td></tr>"
                + "</table>"
                
                + "</body> </html>";
        
        
        
        //Write data
        factory.writeFile(repi, "temp.html");
        
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
                
                dialog=new smx.jfx.layout.Dialog(owner, true, false, 830, 555, new Scene(pane), "Performance sheet", 
                new Image(new FileInputStream("logo.png")));
                
        
        } catch(Exception er) {
            System.out.println(er.getMessage());
        }
        
        return dialog;
        
    }
    
    /**
     * Returns the division according to the passed total aggregates!
     * @param totalagg
     * @return 
     */
    public String getDiv(String totalagg) {
        
        String div=null;
        
        try {
            
            PreparedStatement psi=new com.rs.scoasoft.Factory().getPS("select divisions from ranges");
            ResultSet rsi=psi.executeQuery();
            
            if(rsi.next()) {
                
                String dvs=rsi.getString(1);
                
                String[] per=dvs.split(" ");
                
                for(String thes:per) {
                    
                    String[] win=thes.split("-");
                    
                    if(Integer.valueOf(totalagg)>=Integer.valueOf(win[0]) 
                            && Integer.valueOf(totalagg)<=Integer.valueOf(removeEq(win[1],"value"))) {
                        
                        div=removeEq(win[1],"grade");
                        
                    }
                    
                }
                
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return div;
        
    }
    
    /**
     * Returns the report back-page!
     * @param owner
     * @return 
     */
    @SuppressWarnings("UseSpecificCatch")
    public smx.jfx.layout.Dialog getBackPage(javafx.stage.Stage owner) {
        
        dialog=null;
        
        com.rs.scoasoft.Factory factory=new com.rs.scoasoft.Factory();
        try {
        
        //web
        javafx.scene.web.WebView web=new javafx.scene.web.WebView();
        web.setPrefWidth(821); web.setPrefHeight(550);
        
        //get
        String rep="<html><head><title>Back page</title>"
                + "<style>"
                + ".body {"
                + "font-family: Lucida Sans;"
                + "font-size: 19px;"
                + "color: royalblue;"
                + "background-color: white;"
                + "padding: 2%;"
                + "opacity: 1;"
                + "}"
                + "</style>"
                + "</head>"
                + ""
                + "<body class=\'body\'>"
                
                + "<h3>Grading scale</h3>"
                + "<table border=\'7\' bordercolor=\'royalblue\' cellpadding=\'5\' cellspacing=\'5\' width=\'100%\'>";
        
        String scale="<tr><td align=\'center\'>Scale</td>";
        
        String grade="<tr><td align=\'center\'>Grade</td>";
        
        PreparedStatement ps=factory.getPS("select ranges from ranges");
        ResultSet set=ps.executeQuery();
        
        if(set.next()) {
            
            String all=set.getString(1);
            
            String[] spl=all.split(" ");
            
            for(String a:spl) {
                
                String[] sp=a.split("=");
                
                scale+="<td align=\'center\'>"+sp[0]+"</td>";
                
                grade+="<td align=\'center\'>"+sp[1]+"</td>";
                
            }
            
            scale+="</tr>"; grade+="</tr>";
            
            rep+=scale+grade+"</table><p/>";
            
        }
        
        rep+= "<h3>Divisions scale</h3>"
                + "<table border=\'7\' bordercolor=\'royalblue\' cellpadding=\'5\' cellspacing=\'5\' width=\'100%\'>";
        
        String scale2="<tr><td align=\'center\'>Scale</td>";
        
        String grade2="<tr><td align=\'center\'>Division</td>";
        
        PreparedStatement ps2=factory.getPS("select divisions from ranges");
        ResultSet set2=ps2.executeQuery();
        
        if(set2.next()) {
            
            String all=set2.getString(1);
            
            String[] spl=all.split(" ");
            
            for(String a:spl) {
                
                String[] sp=a.split("=");
                
                scale2+="<td align=\'center\'>"+sp[0]+"</td>";
                
                grade2+="<td align=\'center\'>"+sp[1]+"</td>";
                
            }
            
            scale2+="</tr>"; grade2+="</tr>";
            
        }
        
        
            rep+=scale2+grade2+"</table><h3>Additional information</h3>";
            
            PreparedStatement psi=factory.getPS("select content from additionalinfo");
            ResultSet rsi=psi.executeQuery();
            
            if(rsi.next()) {
                
                rep+="<p>"+rsi.getString(1).replaceAll("\n","<br/>")+"</p>";
                
            }
            
            rep+="==================================="
                    + "</body></html>";
            
        
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
                
                dialog=new smx.jfx.layout.Dialog(owner, true, false, 817, 555, new Scene(pane), "Back-Page", 
                new Image(new FileInputStream("logo.png")));
                
        
        } catch(Exception er) {
            System.out.println(er.getMessage());
        }
        
        return dialog;
        
    }
    
    private static Report repo;
    
    private static boolean aval;
    
    private static String rowidm,rowidt,clas,repi,tempo;
    
    private static smx.jfx.layout.Dialog dialog;
    
}
