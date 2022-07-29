/*
 * Factory class for payments
 */
package com.rs.scoasoft.payments;

/**
 *
 * @author bruce
 */

//import com.rs.scoasoft.performance.Report;
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
import javafx.collections.FXCollections;
import javafx.print.PrinterJob;


public class PaymentsFactory {
    
    /**
     * Initial
     */
    public PaymentsFactory() {
        
        try {
        //entering payments data if does not exist///
        PreparedStatement ps=new com.rs.scoasoft.Factory().getPS("select *from payments where term=? && year=?");
        ps.setString(1,new com.rs.scoasoft.performance.Performance().ty()[0]);
        ps.setString(2,new com.rs.scoasoft.performance.Performance().ty()[1]);
        ResultSet rs=ps.executeQuery();
        
        if(!rs.next()) {
            
            PreparedStatement ps2=new com.rs.scoasoft.Factory().getPS("select *from pupils");
            ResultSet rs2=ps2.executeQuery();
            
            while(rs2.next()) {
                
                //Add also to the payments database////////////////////////////////////////////////
            String q="insert into payments(owner,fullnames,class,pay,fullpay,admission,uniform,transport,term,year) "
                    + "values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pl=new com.rs.scoasoft.Factory().getPS(q);
            pl.setString(1,rs2.getString(1)); pl.setString(2,rs2.getString(2));
            pl.setString(3,rs2.getString("class"));
            pl.setString(4,"0"); pl.setString(5,"0");
            pl.setString(6,"0"); pl.setString(7,"0"); pl.setString(8,"0");
            pl.setString(9,new com.rs.scoasoft.performance.Performance().ty()[0]);
            pl.setString(10,new com.rs.scoasoft.performance.Performance().ty()[1]);
            
            pl.executeUpdate();
            //////////////////////////////////////////////////////////////////////////////////
                System.out.println("New Term and Year Payments data created for pupil : "+rs2.getString(2));
                
            }
            
        }
        
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
    }
    
    /**
     * Dialog to edit payment for pupils
     * @param owner
     * @param payerid
     * @return 
     */
    public smx.jfx.layout.Dialog getPayEditForm(javafx.stage.Stage owner, String payerid) {
        
        dialog=null;
        com.rs.scoasoft.Factory factory=new com.rs.scoasoft.Factory();
        com.rs.scoasoft.performance.PerformanceFactory prof=
                new com.rs.scoasoft.performance.PerformanceFactory();
        com.rs.scoasoft.performance.Performance per=new com.rs.scoasoft.performance.Performance();
        
        //Nodes
        try {
            
            //get the profile first
            
            String name=null,pic=null; clas=null; 
            
            PreparedStatement ps=factory.getPS("select *from pupils where id="+payerid);
            ResultSet set=ps.executeQuery();
            
            if(set.next()) {
                
                name=set.getString(2); pic=set.getString("picture"); clas=set.getString("class");
                
            }
            
            //get data for fields
            String me="",mm="",adm="",uni="",trs="";
            PreparedStatement pr=factory.getPS("select *from payments where owner="+payerid+" && term=? && year=?");
            pr.setString(1,per.ty()[0]);
            pr.setString(2,per.ty()[1]);
            
            ResultSet st=pr.executeQuery();
            
            if(st.next()) {
                me=st.getString("pay");
                mm=st.getString("fullpay");
                adm=st.getString("admission");
                uni=st.getString("uniform");
                trs=st.getString("transport");
            }
            
            //Form Nodes
            
            Label l=new Label(name+"\n\n"+clas);
            l.setStyle(factory.getLabelBStyle("grey"));
            
            ImageView imv=new ImageView(new Image(new FileInputStream(pic)));
            imv.setFitWidth(172); imv.setFitHeight(182);
            
            smx.jfx.layout.GridPane top=factory.getSGrid(); top.setHgap(51);
            top.add(l,0,0); top.add(imv,1,0);
            
            Label mid=new Label("Payments Update!");
            mid.setStyle(factory.getLabelMStyle("grey"));
            
            Label pph=new Label("Paid");
            pph.setStyle(factory.getLabelMStyle("grey"));
            
            Label ph=new Label("Fees");
            ph.setStyle(factory.getLabelMStyle("grey"));
            
            Label admi=new Label("Admission");
            admi.setStyle(factory.getLabelMStyle("grey"));
            
            Label un=new Label("Uniform");
            un.setStyle(factory.getLabelMStyle("grey"));
            
            Label tra=new Label("Transport");
            tra.setStyle(factory.getLabelMStyle("grey"));
            
            NumberField meng=new NumberField(me); meng.setPrefWidth(300);
            meng.setStyle(factory.getTFStyle()); meng.setPromptText("Paid amount");
            
            NumberField mmat=new NumberField(mm); mmat.setPrefWidth(300);
            mmat.setStyle(factory.getTFStyle()); mmat.setPromptText("Full school fees");
            
            NumberField admit=new NumberField(adm); admit.setPrefWidth(300);
            admit.setStyle(factory.getTFStyle()); admit.setPromptText("Admission");
            
            NumberField unif=new NumberField(uni); unif.setPrefWidth(300);
            unif.setStyle(factory.getTFStyle()); unif.setPromptText("Uniform");
            
            NumberField trans=new NumberField(trs); trans.setPrefWidth(300);
            trans.setStyle(factory.getTFStyle()); trans.setPromptText("Transport");
            
            //sub btn
            smx.jfx.control.Button sub=new smx.jfx.control.Button("Submit");
            sub.setDefaultStyle(factory.getNBTStyle2());
            sub.setHoverStyle(factory.getNHBTStyle2());
            
            
            smx.jfx.layout.GridPane mgr=factory.getSGrid();
            mgr.setPadding(new javafx.geometry.Insets(15,15,15,15)); mgr.setVgap(15);
            
            mgr.add(pph,0,1); mgr.add(ph,1,1);
            mgr.add(meng,0,2); mgr.add(mmat,1,2);
            
            mgr.add(admi,0,3); mgr.add(un,1,3);
            mgr.add(admit,0,4); mgr.add(unif,1,4);
            
            mgr.add(tra,0,5);
            mgr.add(trans,0,6); mgr.add(sub,1,6);
            
            
            //TFS
            List<TextField> liu=new ArrayList();
            liu.add(meng); liu.add(mmat); liu.add(admit); liu.add(unif); liu.add(trans);
            
            smx.jfx.layout.GridPane main=factory.getSGrid();
            main.add(top,0,0);main.add(mid,0,1); main.add(mgr,0,2);
           
            dialog=new smx.jfx.layout.Dialog(owner, true, false, 700, 595, new Scene(main), "Payment update", 
                    new Image(new FileInputStream("logo.png")));
            
            //Actions
            sub.setOnAction(act->{
                
                try {
                
                if(factory.validateTF(liu)) {
                    
                    PreparedStatement pol=
                            factory.getPS("update payments set pay=?,fullpay=?,admission=?,uniform=?,"
                                    + "transport=? where owner=?");
                    pol.setString(1,meng.getText()); 
                    pol.setString(2,mmat.getText());
                    pol.setString(3,admit.getText());
                    pol.setString(4,unif.getText());
                    pol.setString(5,trans.getText());
                    pol.setString(6,payerid);
                    
                    pol.executeUpdate();
                    
                    dialog.close();
                    
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
     * Returns amount paid by arg pupil
     * @param payerid
     * @return 
     */
    public long getPay(String payerid) {
        long i=0;
        
        try {
            com.rs.scoasoft.performance.Performance per=new com.rs.scoasoft.performance.Performance();
            
            PreparedStatement ps=new com.rs.scoasoft.Factory().getPS("select pay from payments where owner=? "
                    + "&& term=? && year=?");
            ps.setString(2,per.ty()[0]);
            ps.setString(3,per.ty()[1]);
            
            ps.setString(1,payerid);
            
            ResultSet set=ps.executeQuery();
            
            if(set.next()) i=Integer.valueOf(set.getString(1));
            
        } catch (SQLException ew) {
            System.out.println(ew.getMessage());
        }
        
        return i;
    }
    
    /**
     * Returns the current payments list!
     * @param owner
     * @param lv
     * @param ser
     * @param clas
     * @return 
     */
    @SuppressWarnings("UseSpecificCatch")
    public smx.jfx.layout.Dialog getPayList(javafx.stage.Stage owner, List<String> lv, String ser, String clas) {
        
        dialog=null;
        
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
                
                + "<table border=\"10\" cellpadding=\"7\" cellpadding=\"7\" width=\"758\" bordercolor=\"royalblue\">"
                + "<tr><td align=\"center\" class=\"body\">"
                + "<p align=\"center\"><img src=\"logo.png\" width=\"175\" height=\"182\" alt=\"LOGO\" /></p>"
                
                + "<p class=\"body\">"+clas+"</p>"+per.ty()[0]+" "+per.ty()[1]+""+ "<p class=\"body\">"+ser+"</p>"
                
                + "<table border=\"5\" cellpadding=\"7\" cellpadding=\"7\" width=\"100%\" bordercolor=\"royalblue\">"
                + "<tr><td align=\"center\">Full names</td><td align=\"center\">Admission</td>"
                + "<td align=\"center\">Uniform</td><td align=\"center\">Transport</td>"
                + "<td align=\"center\">Fees</td><td align=\"center\">Paid</td><td align=\"center\">Balance</td></tr>";
                
                //Get Data
                
                for(String a:lv) {
                
                PreparedStatement ps=
                        new com.rs.scoasoft.Factory().getPS("select *from payments where owner=? && term=? && year=?");
                ps.setString(1,new com.rs.scoasoft.profiles.ProfilesFactory().getLogDa(a, "id"));
                ps.setString(2,per.ty()[0]);
                ps.setString(3,per.ty()[1]);
                
                ResultSet set=ps.executeQuery();
                
                if(set.next()) {
                    
                    rep+="<tr>"
                            + "<td align=\"center\">"+a+"</td>"
                            + "<td align=\"center\">"+set.getString("admission")+"</td>"
                            + "<td align=\"center\">"+set.getString("uniform")+"</td>"
                            + "<td align=\"center\">"+set.getString("transport")+"</td>"
                            + "<td align=\"center\">"+set.getString("fullpay")+"</td>"
                            + "<td align=\"center\">"+set.getString("pay")+"</td>"
                            
                            + "<td align=\"center\">"
                            +(Integer.valueOf(set.getString("fullpay"))-Integer.valueOf(set.getString("pay")))+"</td>"
                            + "</tr>";
                    
                }
                
                }
                
                rep+= "</td></tr>"
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
                
                dialog=new smx.jfx.layout.Dialog(owner, true, false, 825, 555, new Scene(pane), "Payments selection", 
                new Image(new FileInputStream("logo.png")));
                
        
        } catch(Exception er) {
            System.out.println(er.getMessage());
        }
        
        return dialog;
        
    }
    
    /**
     * Dialog to enter transactions for pupils
     * @param owner
     * @param payerid
     * @return 
     */
    public smx.jfx.layout.Dialog getTransactionForm(javafx.stage.Stage owner, String payerid) {
        
        dialog=null;
        com.rs.scoasoft.Factory factory=new com.rs.scoasoft.Factory();
        com.rs.scoasoft.performance.PerformanceFactory prof=
                new com.rs.scoasoft.performance.PerformanceFactory();
        
        //Nodes
        try {
            
            //get the profile first
            
            String pic=null; clas=null; namey=null;
            
            PreparedStatement ps=factory.getPS("select *from pupils where id="+payerid);
            ResultSet set=ps.executeQuery();
            
            if(set.next()) {
                
                namey=set.getString(2); pic=set.getString("picture"); clas=set.getString("class");
                
            }
            
            
            //Form Nodes
            
            Label l=new Label(namey+"\n\n"+clas);
            l.setStyle(factory.getLabelBStyle("grey"));
            
            ImageView imv=new ImageView(new Image(new FileInputStream(pic)));
            imv.setFitWidth(172); imv.setFitHeight(182);
            
            smx.jfx.layout.GridPane top=factory.getSGrid(); top.setHgap(51);
            top.add(l,0,0); top.add(imv,1,0);
            
            Label mid=new Label("Transaction entry!");
            mid.setStyle(factory.getLabelMStyle("grey"));
            
            TextField amount=new NumberField(); amount.setPrefWidth(200);
            amount.setStyle(factory.getTFStyle()); amount.setPromptText("Paid amount");
            
            DatePicker date=new DatePicker(); date.setPrefWidth(200);
            date.setStyle(factory.getTFStyle()); date.setPromptText("Date");
            
            ListView lv=new ListView(); lv.setStyle(factory.getLVStyle());
            lv.setPrefWidth(1000); lv.setPrefHeight(800);
            
            //get Data for the list view
            PreparedStatement pst=factory.getPS("select *from transactions where owner=\'"+payerid+"\'");
            ResultSet rs=pst.executeQuery();
            
            List<String> lvst=new ArrayList();
            
            while(rs.next()) {
                
                temp="ID : "+rs.getString("id")+" | Amount : "+rs.getString("pay")+" | Date : "+rs.getString("date");
                lvst.add(temp);
                
            }
            
            lv.setItems(FXCollections.observableArrayList(lvst));
            
            //sub btn
            smx.jfx.control.Button sub=new smx.jfx.control.Button("Submit");
            sub.setDefaultStyle(factory.getNBTStyle2());
            sub.setHoverStyle(factory.getNHBTStyle2());
            
            
            smx.jfx.layout.GridPane mgr=factory.getSGrid();
            mgr.setPadding(new javafx.geometry.Insets(15,15,15,15)); mgr.setVgap(15);
            
            mgr.add(amount,0,0); mgr.add(date,1,0);
            mgr.add(sub,2,0);
            
            
            //TFS
            List<TextField> liu=new ArrayList();
            liu.add(amount); 
            
            smx.jfx.layout.GridPane main=factory.getSGrid();
            main.add(top,0,0); main.add(mid,0,1); main.add(mgr,0,2); main.add(lv,0,3);
           
            dialog=new smx.jfx.layout.Dialog(owner, true, false, 700, 521, new Scene(main), "Transactions", 
                    new Image(new FileInputStream("logo.png")));
            
            //Actions
            sub.setOnAction(act->{
                
                com.rs.scoasoft.performance.Performance per=new com.rs.scoasoft.performance.Performance();
        
                try {
                
                if(factory.validateTF(liu) && date.getValue().toString().length()>7) {
                    
                    PreparedStatement pol=
                            factory.getPS("insert into transactions(owner,fullnames,class,pay,date,term,year) "
                                    + "values(?,?,?,?,?,?,?)");
                    pol.setString(1,payerid);
                    pol.setString(2,namey);
                    pol.setString(3,clas);
                    pol.setString(4,amount.getText());
                    pol.setString(5,date.getValue().toString());
                    pol.setString(6,per.ty()[0]);
                    pol.setString(7,per.ty()[1]);
                    
                    pol.executeUpdate();
                    
                    //Add to totals
                    int paid=0;
                    PreparedStatement pl=factory.getPS("select *from payments where owner=\'"+payerid+"\' "
                            + "&& term=? && year=?");
                    pl.setString(1,per.ty()[0]);
                    pl.setString(2,per.ty()[1]);
                    
                    ResultSet seto=pl.executeQuery();
                    
                    if(seto.next()) {
                        
                        paid+=Integer.valueOf(seto.getString("pay"));
                        
                    }
                    
                    int tot=paid+Integer.valueOf(amount.getText());
                    
                    PreparedStatement pst1=factory.getPS("update payments set pay=? where owner=\'"+payerid+"\'");
                    pst1.setString(1,String.valueOf(tot));
                    pst1.executeUpdate();
                    //////////////////////////////////////////////////////
                    
                    dialog.close();
                    
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
     * Returns the current payments list!
     * @param owner
     * @param proid
     * @param clas
     * @return 
     */
    @SuppressWarnings("UseSpecificCatch")
    public smx.jfx.layout.Dialog getPayDetails(javafx.stage.Stage owner, String proid, String clas) {
        
        dialog=null;
        
        com.rs.scoasoft.Factory factory=new com.rs.scoasoft.Factory();
        com.rs.scoasoft.performance.Performance per=new com.rs.scoasoft.performance.Performance();
        com.rs.scoasoft.profiles.ProfilesFactory prof=new com.rs.scoasoft.profiles.ProfilesFactory();
        
        
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
                
                + "<table border=\"10\" cellpadding=\"7\" cellpadding=\"7\" width=\"758\" bordercolor=\"royalblue\">"
                + "<tr><td align=\"center\" class=\"body\">"
                + "<p align=\"center\"><img src=\"logo.png\" width=\"175\" height=\"182\" alt=\"LOGO\" /></p>"
                
                + "<p>"+prof.getLogDa(proid, "name")+"<p/>"
                + "<p class=\"body\">"+clas+"</p>"+per.ty()[0]+" "+per.ty()[1]+"<p/>"
                + "__________________________________________________";
                
                //Get Data
                PreparedStatement psu=factory.getPS("select *from payments where owner=\'"+prof.getLogDa(proid,"id")
                +"\'");
                ResultSet rsu=psu.executeQuery();
                
                if(rsu.next()) {
                    
                    rep+="<p>Admission : "+rsu.getString("admission")+"</p>"
                            + "<p>Uniform : "+rsu.getString("uniform")+"</p>"
                            + "<p>Transport : "+rsu.getString("transport")+"</p>"
                            + "__________________________________________________"
                            + "<p>Tution fees : "+rsu.getString("fullpay")+"</p>"
                            + "<p>Paid : "+rsu.getString("pay")+"</p>"
                            + "<p>Balance : "
                            +(Integer.valueOf(rsu.getString("fullpay"))-Integer.valueOf(rsu.getString("pay")))
                            +"</p>";
                    
                }
                
                rep+="__________________________________________________";
                
                rep+="<h3>Transactions</h3>"
                        + "<table border=\'7\' bordercolor=\'royalblue\' cellpadding=\'7\' cellpadding=\'7\' width=\'100%\'>"
                        + "<tr><th>Name</th><th>Class</th><th>Term</th><th>Year</th><th>Payment</th><th>Date</th></tr>";
                
                PreparedStatement psu2=factory.getPS("select *from transactions where owner=\'"+prof.getLogDa(proid,"id")
                +"\'");
                ResultSet rsu2=psu2.executeQuery();
                
                while(rsu2.next()) {
                    
                    rep+="<tr><td align=\'center\'>"+rsu2.getString("fullnames")+"</td>"
                            + "<td align=\'center\'>"+rsu2.getString("class")+"</td>"
                            + "<td align=\'center\'>"+rsu2.getString("term")+"</td>"
                            + "<td align=\'center\'>"+rsu2.getString("year")+"</td>"
                            + "<td align=\'center\'>"+rsu2.getString("pay")+"</td>"
                            + "<td align=\'center\'>"+rsu2.getString("date")+"</td></tr>";
                    
                }
                
                rep+="</table>";
                
                rep+= "</td></tr>"
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
                
                dialog=new smx.jfx.layout.Dialog(owner, true, false, 825, 555, new Scene(pane), "Payments selection", 
                new Image(new FileInputStream("logo.png")));
                
        
        } catch(Exception er) {
            System.out.println(er.getMessage());
        }
        
        return dialog;
        
    }
    
    private static boolean aval;
    
    private static String clas,rep,temp,namey;
    
    private static smx.jfx.layout.Dialog dialog;
    
    
}
