/*
 * Profiles Factory Class!
 */
package com.rs.scoasoft.profiles;

/**
 *
 * @author Remote Station
 */

import javafx.print.PrinterJob;
import javafx.scene.control.*;
import javafx.scene.image.*;
//import javafx.geometry.*;
import javafx.animation.*;
import javafx.util.*;
import javafx.scene.Scene;

import java.io.*;
//import java.util.*;
import java.sql.*;
import java.text.DateFormat;

public class ProfilesFactory {
    
    /**
     * Initialization!
     */
    public ProfilesFactory() {
        
        
    }
    
    private static
        smx.jfx.layout.Dialog form=null;
        
    
    /**
     * This is to make the registration form for the teacher profile!
     * @param owner
     * @return 
     */
    public smx.jfx.layout.Dialog getTeacherRegForm(javafx.stage.Stage owner) {
        
        //Start arranging the nodes
        com.rs.scoasoft.Factory factory =new com.rs.scoasoft.Factory();
        
        //Label Up!
        Label l=new Label("Create a new teacher profile!\t====================");
        l.setStyle(factory.getLabelBStyle("grey"));
        
        //TextFields
        TextField fname=new TextField(); fname.setStyle(factory.getTFStyle());
        fname.setPromptText("First Name"); fname.setMaxWidth(400);
        
        TextField lname=new TextField(); lname.setStyle(factory.getTFStyle());
        lname.setPromptText("Last Name"); lname.setMaxWidth(400);
        
        TextField phone=new TextField(); phone.setStyle(factory.getTFStyle());
        phone.setPromptText("Phone"); phone.setMaxWidth(400);
        
        TextField email=new TextField(); email.setStyle(factory.getTFStyle());
        email.setPromptText("Email"); email.setMaxWidth(400);
        
        TextField gender=new TextField(); gender.setStyle(factory.getTFStyle());
        gender.setPromptText("Gender"); gender.setMaxWidth(400);
        
        DatePicker dob=new DatePicker(); dob.setStyle(factory.getTFStyle());
        dob.setPromptText("Date of birth"); dob.setMaxWidth(400);
        
        TextField title=new TextField(); title.setStyle(factory.getTFStyle());
        title.setPromptText("Title"); title.setMaxWidth(400);
        
        TextField address=new TextField(); address.setStyle(factory.getTFStyle());
        address.setPromptText("Address"); address.setMaxWidth(400);
        
        TextField nationality=new TextField(); nationality.setStyle(factory.getTFStyle());
        nationality.setPromptText("Nationality"); nationality.setMaxWidth(400);
        
        TextField prevrole=new TextField(); prevrole.setStyle(factory.getTFStyle());
        prevrole.setPromptText("Previous role"); prevrole.setMaxWidth(400);
        
        TextField resume=new TextField(); resume.setStyle(factory.getTFStyle());
        resume.setPromptText("Resume path"); resume.setMaxWidth(400);
        
        TextField education=new TextField(); education.setStyle(factory.getTFStyle());
        education.setPromptText("Education"); education.setMaxWidth(400);
        
        TextField subjects=new TextField(); subjects.setStyle(factory.getTFStyle());
        subjects.setPromptText("Subjects"); subjects.setMaxWidth(400);
        
        TextField classes=new TextField(); classes.setStyle(factory.getTFStyle());
        classes.setPromptText("Classes"); classes.setMaxWidth(400);
        
        TextField picture=new TextField(); picture.setStyle(factory.getTFStyle());
        picture.setPromptText("Picture url"); picture.setMaxWidth(131);
        smx.jfx.control.Button sel=new smx.jfx.control.Button("+");
        
        TextField nokname=new TextField(); nokname.setStyle(factory.getTFStyle());
        nokname.setPromptText("Next Of Kin names"); nokname.setMaxWidth(400);
        
        TextField nokcontact=new TextField(); nokcontact.setStyle(factory.getTFStyle());
        nokcontact.setPromptText("NOK contact"); nokcontact.setMaxWidth(400);
        
        TextField nokrelat=new TextField(); nokrelat.setStyle(factory.getTFStyle());
        nokrelat.setPromptText("NOK relation"); nokrelat.setMaxWidth(400);
        
        TextField nokaddr=new TextField(); nokaddr.setStyle(factory.getTFStyle());
        nokaddr.setPromptText("NOK address"); nokaddr.setMaxWidth(400);
        
        TextField nin=new TextField(); nin.setStyle(factory.getTFStyle());
        nin.setPromptText("NIN"); nin.setMaxWidth(400);
        
        TextField pname=new TextField(); pname.setStyle(factory.getTFStyle());
        pname.setPromptText("Parents names"); pname.setMaxWidth(400);
        
        TextField pcontact=new TextField(); pcontact.setStyle(factory.getTFStyle());
        pcontact.setPromptText("Parents contact"); pcontact.setMaxWidth(400);
        
        TextField paddr=new TextField(); paddr.setStyle(factory.getTFStyle());
        paddr.setPromptText("Parents address"); paddr.setMaxWidth(400);
        
        TextField otherskills=new TextField(); otherskills.setStyle(factory.getTFStyle());
        otherskills.setPromptText("Other skills"); otherskills.setMaxWidth(400);
        
        TextField mstatus=new TextField(); mstatus.setStyle(factory.getTFStyle());
        mstatus.setPromptText("Marital status"); mstatus.setMaxWidth(400);
        
        TextField partner=new TextField(); partner.setStyle(factory.getTFStyle());
        partner.setPromptText("Partner's Name"); partner.setMaxWidth(400);
        
        TextField regnum=new TextField(); regnum.setStyle(factory.getTFStyle());
        regnum.setPromptText("Reg. number"); regnum.setMaxWidth(400);
        
        DatePicker joind=new DatePicker(); joind.setStyle(factory.getTFStyle());
        joind.setPromptText("Joining date"); joind.setMaxWidth(400);
        
        TextArea bcnames=new TextArea(); bcnames.setStyle(factory.getTFStyle());
        bcnames.setPromptText("Other Details"); bcnames.setMaxWidth(400); bcnames.setMaxHeight(300);
        bcnames.setWrapText(true);
        
        TextArea about=new TextArea(); about.setStyle(factory.getTFStyle());
        about.setPromptText("About info"); about.setMaxWidth(400); about.setMaxHeight(300);
        about.setWrapText(true);
        
        smx.jfx.control.Button sub=new smx.jfx.control.Button("Submit");
        sub.setDefaultStyle(factory.getNBTStyle2()); sub.setHoverStyle(factory.getNHBTStyle2());
        
        smx.jfx.control.Button cancel=new smx.jfx.control.Button("Cancel");
        cancel.setDefaultStyle(factory.getNBTStyle2()); cancel.setHoverStyle(factory.getNHBTStyle2());
        
        //Go to the pane level
        smx.jfx.layout.GridPane sd=factory.getSGrid();
        sd.add(fname,0,0); sd.add(lname,1,0); sd.add(phone,2,0); sd.add(email,3,0); sd.add(gender,4,0);
        sd.add(dob,0,1); sd.add(title,1,1); sd.add(address,2,1); sd.add(nationality,3,1); sd.add(prevrole,4,1);
        /*sd.add(resume,0,2); */sd.add(education,1,2); sd.add(subjects,2,2); sd.add(classes,3,2); 
        sd.add(new javafx.scene.layout.HBox(picture,sel),4,2);
        sd.add(nokname,0,3); sd.add(nokcontact,1,3); sd.add(nokrelat,2,3); sd.add(nokaddr,3,3); sd.add(nin,4,3);
        sd.add(pname,0,4); sd.add(pcontact,1,4); sd.add(paddr,2,4); sd.add(otherskills,3,4); sd.add(mstatus,4,4);
        sd.add(partner,0,5); sd.add(regnum,1,5); sd.add(joind,2,5);
        
        smx.jfx.layout.GridPane ds=factory.getSGrid();
        ds.add(bcnames,0,0); ds.add(about,1,0);
        ds.add(cancel,0,1); ds.add(sub,1,1);
        
        //Progress
        com.gluonhq.charm.glisten.control.ProgressIndicator ind=new 
                com.gluonhq.charm.glisten.control.ProgressIndicator();
        ind.setVisible(false);
        
        ds.add(ind,2,1);
        
        smx.jfx.layout.GridPane mn=factory.getSGrid();
        mn.add(l,0,0); mn.add(sd,0,1); mn.add(ds,0,2);
        
        //make list for validation
        
                java.util.List<TextField> list=new java.util.ArrayList();
                list.add(fname); list.add(lname); list.add(phone); list.add(email); 
                list.add(address); list.add(gender); list.add(title); list.add(nationality); 
                list.add(prevrole); list.add(education); list.add(picture); list.add(nokname); 
                list.add(nokcontact); list.add(nokrelat); list.add(nokaddr); 
                list.add(nin); list.add(pname); list.add(pcontact); list.add(paddr); 
                list.add(otherskills); list.add(mstatus); list.add(partner);
                list.add(regnum); list.add(classes); list.add(subjects);
        
        //Actions
        sub.setOnAction(te->{
            
            if(factory.validateTF(list) && new java.io.File(picture.getText()).exists()) {
            
            //Initial step
            sub.setDisable(true); cancel.setDisable(true); ind.setVisible(true);
            
            //Add available data to database
            String query="insert into teachers(fname,lname,phone,email,gender,dob,title,address,nationality,"
                    + "previousrole,aboutinfo,resume,education,subjects,classes,picture,nokname,nokcontact,nokrelation,"
                    + "nokaddress,status,nin,parentsname,parentscontact,parentsaddress,bcnames,otherskills,"
                    + "maritalstatus,partnername,regnumber,joindate) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            try {
                
                PreparedStatement ps=factory.getPS(query);
                
                ps.setString(1,fname.getText()); ps.setString(2,lname.getText());
                ps.setString(3,phone.getText()); ps.setString(4,email.getText());
                ps.setString(5,gender.getText()); ps.setString(6,dob.getValue().toString());
                ps.setString(7,title.getText()); ps.setString(8,address.getText());
                ps.setString(9,nationality.getText()); ps.setString(10,prevrole.getText());
                ps.setString(11,about.getText()); ps.setString(12,/*resume.getText()*/"TEMP");
                ps.setString(13,education.getText()); ps.setString(14,subjects.getText());
                ps.setString(15,classes.getText()); ps.setString(16,picture.getText());
                ps.setString(17,nokname.getText()); ps.setString(18,nokcontact.getText());
                ps.setString(19,nokrelat.getText()); ps.setString(20,nokaddr.getText());
                ps.setString(21,"true"); ps.setString(22,nin.getText());
                ps.setString(23,pname.getText()); ps.setString(24,pcontact.getText());
                ps.setString(25,paddr.getText()); ps.setString(26,bcnames.getText());
                ps.setString(27,otherskills.getText()); ps.setString(28,mstatus.getText());
                ps.setString(29,partner.getText()); ps.setString(30,regnum.getText());
                ps.setString(31,joind.getValue().toString());
                
                //Let's create a tr. profile!
                ps.executeUpdate();
                //GOOD!
                
                //copy the profile and resume files
                
                //get profile id from database
                String id=null;
                PreparedStatement ps2=factory.getPS("select id from teachers");
                ResultSet set=ps2.executeQuery();
                
                if(set.last()) {
                    id=set.getString(1);
                }
                
                //Create directory for profile & CV
                File file=new File(factory.trdf+"/"+id);
                file.mkdir();
                
                com.rs.scoasoft.UploadThread upt=new com.rs.scoasoft.UploadThread();
                upt.setData(picture.getText(), file.getPath()+"/profile.png");
                /*
                com.rs.scoasoft.UploadThread upt2=new com.rs.scoasoft.UploadThread();
                upt2.setData(resume.getText(), file.getPath()+"\\resume.png");*/
                
                //start the threads
                upt.start(); //upt2.start();
                
                //Update Profile Image
                updateProf("teachers",id,file.getPath()+"\\profile.png");
                //************************************************************
                /*/Update Profile Image
                updateResume("teachers",id,file.getPath()+"\\resume.png");
                //*************************************************************/
                
                
                //Wait for the upload to complete!
                FadeTransition fd=new FadeTransition(Duration.millis(1214));
                fd.setAutoReverse(false); fd.setCycleCount(1); fd.setFromValue(0.3);
                fd.setToValue(1); fd.setNode(ind);
                
                fd.play();
                
                //Check on the threads!
                fd.setOnFinished(er->{
                    
                    if(!upt.isAlive()/* && !upt2.isAlive()*/) {
                        form.close();
                    }
                    
                    else {
                        fd.play();
                    }
                    
                });
                
                
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            
            }
            
        });
        
        cancel.setOnAction(te->{
            form.close();
        });
        
        sel.setOnAction(act->{
            
            javafx.stage.FileChooser ch=new javafx.stage.FileChooser();
            ch.setInitialDirectory(new File("client")); ch.setTitle("Choose profile picutre");
            ch.getExtensionFilters().addAll(
            new javafx.stage.FileChooser.ExtensionFilter("JPG Image","*.jpg"),
            new javafx.stage.FileChooser.ExtensionFilter("PNG Image","*.png"));
            
            String path=ch.showOpenDialog(form).toString();
            
            picture.setText(path);
            
        });
        
        try {
        form=new smx.jfx.layout.Dialog(owner, true, true, 981, 600, new Scene(mn), "Teaccher Form", 
        new Image(new FileInputStream("logo.png")));
        
        form.setMaximized(true);
        
        } catch (IOException ex) {
            factory.print(ex.getMessage());
        }
        
        return form;
        
    }
    
    
    /**
     * This is to make the registration form for the other staff profile!
     * @param owner
     * @return 
     */
    public smx.jfx.layout.Dialog getOStaffRegForm(javafx.stage.Stage owner) {
        
        //Start arranging the nodes
        com.rs.scoasoft.Factory factory =new com.rs.scoasoft.Factory();
        
        //Label Up!
        Label l=new Label("Create a new staff profile!\t\t====================");
        l.setStyle(factory.getLabelBStyle("grey"));
        
        //TextFields
        TextField fname=new TextField(); fname.setStyle(factory.getTFStyle());
        fname.setPromptText("First Name"); fname.setMaxWidth(400);
        
        TextField lname=new TextField(); lname.setStyle(factory.getTFStyle());
        lname.setPromptText("Last Name"); lname.setMaxWidth(400);
        
        TextField phone=new TextField(); phone.setStyle(factory.getTFStyle());
        phone.setPromptText("Phone"); phone.setMaxWidth(400);
        
        TextField email=new TextField(); email.setStyle(factory.getTFStyle());
        email.setPromptText("Email"); email.setMaxWidth(400);
        
        TextField gender=new TextField(); gender.setStyle(factory.getTFStyle());
        gender.setPromptText("Gender"); gender.setMaxWidth(400);
        
        DatePicker dob=new DatePicker(); dob.setStyle(factory.getTFStyle());
        dob.setPromptText("Date of birth"); dob.setMaxWidth(400);
        
        TextField title=new TextField(); title.setStyle(factory.getTFStyle());
        title.setPromptText("Title"); title.setMaxWidth(400);
        
        TextField address=new TextField(); address.setStyle(factory.getTFStyle());
        address.setPromptText("Address"); address.setMaxWidth(400);
        
        TextField nationality=new TextField(); nationality.setStyle(factory.getTFStyle());
        nationality.setPromptText("Nationality"); nationality.setMaxWidth(400);
        
        TextField prevrole=new TextField(); prevrole.setStyle(factory.getTFStyle());
        prevrole.setPromptText("Previous role"); prevrole.setMaxWidth(400);
        
        TextField education=new TextField(); education.setStyle(factory.getTFStyle());
        education.setPromptText("Education"); education.setMaxWidth(400);
        
        TextField picture=new TextField(); picture.setStyle(factory.getTFStyle());
        picture.setPromptText("Picture url"); picture.setMaxWidth(400);
        smx.jfx.control.Button sel=new smx.jfx.control.Button("+");
        
        TextField nokname=new TextField(); nokname.setStyle(factory.getTFStyle());
        nokname.setPromptText("Next of kin names"); nokname.setMaxWidth(400);
        
        TextField nokcontact=new TextField(); nokcontact.setStyle(factory.getTFStyle());
        nokcontact.setPromptText("NOK contact"); nokcontact.setMaxWidth(400);
        
        TextField nokrelat=new TextField(); nokrelat.setStyle(factory.getTFStyle());
        nokrelat.setPromptText("NOK relation"); nokrelat.setMaxWidth(400);
        
        TextField nokaddr=new TextField(); nokaddr.setStyle(factory.getTFStyle());
        nokaddr.setPromptText("NOK address"); nokaddr.setMaxWidth(400);
        
        TextField nin=new TextField(); nin.setStyle(factory.getTFStyle());
        nin.setPromptText("NIN"); nin.setMaxWidth(400);
        
        TextField pname=new TextField(); pname.setStyle(factory.getTFStyle());
        pname.setPromptText("Parents names"); pname.setMaxWidth(400);
        
        TextField pcontact=new TextField(); pcontact.setStyle(factory.getTFStyle());
        pcontact.setPromptText("Parents contact"); pcontact.setMaxWidth(400);
        
        TextField paddr=new TextField(); paddr.setStyle(factory.getTFStyle());
        paddr.setPromptText("Parents address"); paddr.setMaxWidth(400);
        
        TextField otherskills=new TextField(); otherskills.setStyle(factory.getTFStyle());
        otherskills.setPromptText("Other skills"); otherskills.setMaxWidth(400);
        
        TextField mstatus=new TextField(); mstatus.setStyle(factory.getTFStyle());
        mstatus.setPromptText("Marital status"); mstatus.setMaxWidth(400);
        
        TextField partner=new TextField(); partner.setStyle(factory.getTFStyle());
        partner.setPromptText("Partner's Name"); partner.setMaxWidth(400);
        
        DatePicker joind=new DatePicker(); joind.setStyle(factory.getTFStyle());
        joind.setPromptText("Joining date"); joind.setMaxWidth(400);
        
        TextArea bcnames=new TextArea(); bcnames.setStyle(factory.getTFStyle());
        bcnames.setPromptText("Other details"); bcnames.setMaxWidth(400); bcnames.setMaxHeight(300);
        bcnames.setWrapText(true);
        
        TextArea about=new TextArea(); about.setStyle(factory.getTFStyle());
        about.setPromptText("About info"); about.setMaxWidth(400); about.setMaxHeight(300);
        about.setWrapText(true);
        
        smx.jfx.control.Button sub=new smx.jfx.control.Button("Submit");
        sub.setDefaultStyle(factory.getNBTStyle2()); sub.setHoverStyle(factory.getNHBTStyle2());
        
        smx.jfx.control.Button cancel=new smx.jfx.control.Button("Cancel");
        cancel.setDefaultStyle(factory.getNBTStyle2()); cancel.setHoverStyle(factory.getNHBTStyle2());
        
        //Go to the pane level
        smx.jfx.layout.GridPane sd=factory.getSGrid();
        sd.add(fname,0,0); sd.add(lname,1,0); sd.add(phone,2,0); sd.add(email,3,0); sd.add(gender,4,0);
        sd.add(dob,0,1); sd.add(title,1,1); sd.add(address,2,1); sd.add(nationality,3,1); sd.add(prevrole,4,1);
        sd.add(education,0,2); sd.add(new javafx.scene.layout.HBox(picture,sel),1,2);
        sd.add(nokname,0,3); sd.add(nokcontact,1,3); sd.add(nokrelat,2,3); sd.add(nokaddr,3,3); sd.add(nin,4,3);
        sd.add(pname,0,4); sd.add(pcontact,1,4); sd.add(paddr,2,4); sd.add(otherskills,3,4); sd.add(mstatus,4,4);
        sd.add(partner,0,5); sd.add(joind,1,5);
        
        smx.jfx.layout.GridPane ds=factory.getSGrid();
        ds.add(bcnames,0,0); ds.add(about,1,0);
        ds.add(cancel,0,1); ds.add(sub,1,1);
        
        //Progress
        com.gluonhq.charm.glisten.control.ProgressIndicator ind=new 
                com.gluonhq.charm.glisten.control.ProgressIndicator();
        ind.setVisible(false);
        
        ds.add(ind,2,1);
        
        smx.jfx.layout.GridPane mn=factory.getSGrid();
        mn.add(l,0,0); mn.add(sd,0,1); mn.add(ds,0,2);
        
        //make list for validation
        
                java.util.List<TextField> list=new java.util.ArrayList();
                list.add(fname); list.add(lname); list.add(phone); list.add(email); 
                list.add(address); list.add(gender); list.add(title); list.add(nationality); 
                list.add(prevrole); list.add(education); list.add(picture); list.add(nokname); 
                list.add(nokcontact); list.add(nokrelat); list.add(nokaddr); 
                list.add(nin); list.add(pname); list.add(pcontact); list.add(paddr); 
                list.add(otherskills); list.add(mstatus); list.add(partner);
                
        
        //Actions
        sub.setOnAction(te->{
            
            if(factory.validateTF(list)  && new java.io.File(picture.getText()).exists()) {
            
            //Initial step
            sub.setDisable(true); cancel.setDisable(true); ind.setVisible(true);
            
            //Add available data to database
            String query="insert into otherstaff(fname,lname,phone,email,gender,dob,title,address,nationality,"
                    + "previousrole,aboutinfo,education,picture,nokname,nokcontact,nokrelation,"
                    + "nokaddress,status,nin,parentsname,parentscontact,parentsaddress,bcnames,otherskills,"
                    + "maritalstatus,partnername,joindate) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            try {
                
                PreparedStatement ps=factory.getPS(query);
                
                ps.setString(1,fname.getText()); ps.setString(2,lname.getText());
                ps.setString(3,phone.getText()); ps.setString(4,email.getText());
                ps.setString(5,gender.getText()); ps.setString(6,dob.getValue().toString());
                ps.setString(7,title.getText()); ps.setString(8,address.getText());
                ps.setString(9,nationality.getText()); ps.setString(10,prevrole.getText());
                ps.setString(11,about.getText());
                ps.setString(12,education.getText());
                ps.setString(13,picture.getText());
                ps.setString(14,nokname.getText()); ps.setString(15,nokcontact.getText());
                ps.setString(16,nokrelat.getText()); ps.setString(17,nokaddr.getText());
                ps.setString(18,"true"); ps.setString(19,nin.getText());
                ps.setString(20,pname.getText()); ps.setString(21,pcontact.getText());
                ps.setString(22,paddr.getText()); ps.setString(23,bcnames.getText());
                ps.setString(24,otherskills.getText()); ps.setString(25,mstatus.getText());
                ps.setString(26,partner.getText());
                ps.setString(27,joind.getValue().toString());
                
                //Let's create a tr. profile!
                ps.executeUpdate();
                //GOOD!
                
                //copy the profile and resume files
                
                //get profile id from database
                String id=null;
                PreparedStatement ps2=factory.getPS("select id from otherstaff");
                ResultSet set=ps2.executeQuery();
                
                if(set.last()) {
                    id=set.getString(1);
                }
                
                //Create directory for profile
                File file=new File(factory.odf+"/"+id);
                file.mkdir();
                
                com.rs.scoasoft.UploadThread upt=new com.rs.scoasoft.UploadThread();
                upt.setData(picture.getText(), file.getPath()+"\\profile.png");
                
                //start the thread
                upt.start();
                
                //Update Profile Image
                updateProf("otherstaff",id,file.getPath()+"\\profile.png");
                //************************************************************
                
                
                //Wait for the upload to complete!
                FadeTransition fd=new FadeTransition(Duration.millis(1214));
                fd.setAutoReverse(false); fd.setCycleCount(1); fd.setFromValue(0.3);
                fd.setToValue(1); fd.setNode(ind);
                
                fd.play();
                
                //Check on the threads!
                fd.setOnFinished(er->{
                    
                    if(!upt.isAlive()) {
                        form.close();
                    }
                    
                    else {
                        fd.play();
                    }
                    
                });
                
                
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            
            }
        });
        
        cancel.setOnAction(te->{
            form.close();
        });
        
        sel.setOnAction(act->{
            
            javafx.stage.FileChooser ch=new javafx.stage.FileChooser();
            ch.setInitialDirectory(new File("client")); ch.setTitle("Choose profile picture");
            ch.getExtensionFilters().addAll(
            new javafx.stage.FileChooser.ExtensionFilter("JPG Image","*.jpg"),
            new javafx.stage.FileChooser.ExtensionFilter("PNG Image","*.png"));
            
            String path=ch.showOpenDialog(form).toString();
            
            picture.setText(path);
            
        });
        
        try {
        form=new smx.jfx.layout.Dialog(owner, true, true, 981, 600, new Scene(mn), "Other staff Form", 
        new Image(new FileInputStream("logo.png")));
        
        form.setMaximized(true);
        
        } catch (IOException ex) {
            factory.print(ex.getMessage());
        }
        
        return form;
        
    }
    
     
    /**
     * This is to make the registration form for the pupil profile!
     * @param owner
     * @return 
     */
    public smx.jfx.layout.Dialog getPupilRegForm(javafx.stage.Stage owner) {
        
        //Start arranging the nodes
        com.rs.scoasoft.Factory factory =new com.rs.scoasoft.Factory();
        
        //Label Up!
        Label l=new Label("Create a new pupil profile!\n=================================================");
        l.setStyle(factory.getLabelBStyle("grey"));
        
        //TextFields
        TextField fname=new TextField(); fname.setStyle(factory.getTFStyle());
        fname.setPromptText("Full names"); fname.setMaxWidth(400);
        
        DatePicker dob=new DatePicker(); dob.setStyle(factory.getTFStyle());
        dob.setPromptText("Date of birth"); dob.setMaxWidth(400);
        
        TextField gender=new TextField(); gender.setStyle(factory.getTFStyle());
        gender.setPromptText("Gender"); gender.setMaxWidth(400);
        
        TextField addr=new TextField(); addr.setStyle(factory.getTFStyle());
        addr.setPromptText("Address"); addr.setMaxWidth(400);
        
        smx.jfx.control.ComboBox clas=new smx.jfx.control.ComboBox(); 
        clas.setDefStyle(factory.getTFStyle()); 
        clas.setHoverStyle(factory.getTFStyle()+"-fx-border-width: 5px; "
                + "-fx-border-color: slateblue;");
        
        clas.getItems().addAll("Nursery", "Primary one", "Primary two", "Primary three", "Primary four"
        , "Primary five", "Primary six", "Primary seven");
        
        clas.setPromptText("Class"); clas.setMaxWidth(400);
        
        smx.jfx.control.ComboBox trans=new smx.jfx.control.ComboBox(); 
        trans.setDefStyle(factory.getTFStyle()); 
        trans.setHoverStyle(factory.getTFStyle()+"-fx-border-width: 5px; "
                + "-fx-border-color: slateblue;");
        
        trans.getItems().addAll("true", "false");
        
        trans.setPromptText("Transport"); trans.setMaxWidth(400);
        
        
        TextField nationality=new TextField(); nationality.setStyle(factory.getTFStyle());
        nationality.setPromptText("Nationality"); nationality.setMaxWidth(400);
        
        TextField fathername=new TextField(); fathername.setStyle(factory.getTFStyle());
        fathername.setPromptText("Father's name"); fathername.setMaxWidth(400);
        
        TextField ftcontact=new TextField(); ftcontact.setStyle(factory.getTFStyle());
        ftcontact.setPromptText("Father's contact"); ftcontact.setMaxWidth(400);
        
        TextField mothername=new TextField(); mothername.setStyle(factory.getTFStyle());
        mothername.setPromptText("Mother's name"); mothername.setMaxWidth(400);
        
        TextField mtcontact=new TextField(); mtcontact.setStyle(factory.getTFStyle());
        mtcontact.setPromptText("Mother's contact"); mtcontact.setMaxWidth(400);
        
        TextField oconame=new TextField(); oconame.setStyle(factory.getTFStyle());
        oconame.setPromptText("Other contact person"); oconame.setMaxWidth(400);
        
        TextField ococont=new TextField(); ococont.setStyle(factory.getTFStyle());
        ococont.setPromptText("OCP contact"); ococont.setMaxWidth(400);
        
        TextField ocoaddr=new TextField(); ocoaddr.setStyle(factory.getTFStyle());
        ocoaddr.setPromptText("OCP address"); ocoaddr.setMaxWidth(400);
        
        DatePicker joind=new DatePicker(); joind.setStyle(factory.getTFStyle());
        joind.setPromptText("Date of joining"); joind.setMaxWidth(400);
        
        TextField pic=new TextField(); pic.setStyle(factory.getTFStyle());
        pic.setPromptText("Picture url"); pic.setMaxWidth(400);
        smx.jfx.control.Button sel=new smx.jfx.control.Button("+");
        
        TextField pupilnum=new TextField(); pupilnum.setStyle(factory.getTFStyle());
        pupilnum.setPromptText("Stream"); pupilnum.setMaxWidth(400);
        
        TextField smode=new TextField(); smode.setStyle(factory.getTFStyle());
        smode.setPromptText("Mode of study"); smode.setMaxWidth(400);
        
        TextField hstat=new TextField(); hstat.setStyle(factory.getTFStyle());
        hstat.setPromptText("Healthy status"); hstat.setMaxWidth(400);
        
        //buttons
        
        smx.jfx.control.Button sub=new smx.jfx.control.Button("Submit");
        sub.setDefaultStyle(factory.getNBTStyle2()); sub.setHoverStyle(factory.getNHBTStyle2());
        
        smx.jfx.control.Button cancel=new smx.jfx.control.Button("Cancel");
        cancel.setDefaultStyle(factory.getNBTStyle2()); cancel.setHoverStyle(factory.getNHBTStyle2());
        
        // Panes
        
        smx.jfx.layout.GridPane sd=factory.getSGrid();
        sd.add(fname,0,0);sd.add(dob,1,0); sd.add(gender,2,0); sd.add(addr,3,0);
        sd.add(nationality,0,1); sd.add(fathername,1,1); sd.add(ftcontact,2,1); sd.add(mothername,3,1);
        sd.add(mtcontact,0,2); sd.add(oconame,1,2); sd.add(ococont,2,2); sd.add(ocoaddr,3,2);
        sd.add(joind,0,3); sd.add(new javafx.scene.layout.HBox(pic,sel),1,3); 
        sd.add(pupilnum,2,3); sd.add(smode,3,3);
        sd.add(hstat,0,4); sd.add(clas,1,4); sd.add(trans,2,4);
        
        sd.add(cancel,0,5); sd.add(sub,1,5);
        
        //Progress
        com.gluonhq.charm.glisten.control.ProgressIndicator ind=new 
                com.gluonhq.charm.glisten.control.ProgressIndicator();
        ind.setVisible(false);
        
        sd.add(ind,2,5);
        
        //Main pane
        
        smx.jfx.layout.GridPane mn=factory.getSGrid();
        mn.add(l,0,0); mn.add(sd,0,1);
        
        //list for validation
        
                java.util.List<TextField> list=new java.util.ArrayList();
                list.add(fname); 
                list.add(addr); list.add(gender); list.add(nationality); 
                list.add(pic); list.add(fathername); list.add(ftcontact);
                list.add(mothername); list.add(mtcontact); list.add(oconame);
                list.add(ococont); list.add(ocoaddr); list.add(pupilnum);
                list.add(smode); list.add(hstat);
                
        
        //Actions
        sub.setOnAction(te->{
            com.rs.scoasoft.performance.Performance per=new com.rs.scoasoft.performance.Performance();
        
            if(factory.validateTF(list) && new java.io.File(pic.getText()).exists()) {
            
            //Initial step
            sub.setDisable(true); cancel.setDisable(true); ind.setVisible(true);
            
            //Insert data
            
            String query="insert into pupils(fullnames,dob,gender,address,class,nationality,fathername,fathercontact,"
                    + "mothername,mothercontact,othercontactname,othercontactcontact,othercontactaddress,"
                    + "joindate,picture,status,stream,pupilrelations,modeofstudy,healthystatus,transport) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            try {
            
            PreparedStatement ps=factory.getPS(query);
            ps.setString(1,fname.getText()); ps.setString(2,dob.getValue().toString()); 
            ps.setString(3,gender.getText()); ps.setString(4,addr.getText()); 
            ps.setString(5,clas.getValue().toString()); ps.setString(6,nationality.getText()); 
            ps.setString(7,fathername.getText()); ps.setString(8,ftcontact.getText()); 
            ps.setString(9,mothername.getText()); ps.setString(10,mtcontact.getText()); 
            ps.setString(11,oconame.getText()); ps.setString(12,ococont.getText()); 
            ps.setString(13,ocoaddr.getText()); ps.setString(14,joind.getValue().toString()); 
            ps.setString(15,pic.getText()); ps.setString(16,"true"); 
            ps.setString(17,pupilnum.getText()); ps.setString(18,"false"); 
            ps.setString(19,smode.getText()); ps.setString(20,hstat.getText()); 
            ps.setString(21,trans.getValue().toString());
            
            //Let's Move!
            ps.executeUpdate();
            //Done!
            
            //copy the profile and resume files
                
                //get profile id from database
                String id=null;
                PreparedStatement ps2=factory.getPS("select id from pupils");
                ResultSet set=ps2.executeQuery();
                
                if(set.last()) {
                    id=set.getString(1);
                }
                
            //Add also to the payments database////////////////////////////////////////////////
            String q="insert into payments(owner,fullnames,class,pay,fullpay,admission,uniform,transport,term,year) "
                    + "values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pl=factory.getPS(q);
            pl.setString(1,id); pl.setString(2,fname.getText());
            pl.setString(3,clas.getValue().toString());
            pl.setString(4,"0"); pl.setString(5,"0");
            pl.setString(6,"0"); pl.setString(7,"0"); pl.setString(8,"0");
            pl.setString(9,per.ty()[0]);
            pl.setString(10,per.ty()[1]);
            
            pl.executeUpdate();
            //////////////////////////////////////////////////////////////////////////////////
                
                //Create directory for profile
                File file=new File(factory.pupdf+"/"+id);
                file.mkdir();
                
                com.rs.scoasoft.UploadThread upt=new com.rs.scoasoft.UploadThread();
                upt.setData(pic.getText(), file.getPath()+"\\profile.png");
                
                //start the thread
                upt.start();
                
                //Update Profile Image
                updateProf("pupils",id,file.getPath()+"\\profile.png");
                //************************************************************
                
                //Wait for the upload to complete!
                FadeTransition fd=new FadeTransition(Duration.millis(1214));
                fd.setAutoReverse(false); fd.setCycleCount(1); fd.setFromValue(0.3);
                fd.setToValue(1); fd.setNode(ind);
                
                fd.play();
                
                //Check on the threads!
                fd.setOnFinished(er->{
                    
                    if(!upt.isAlive()) {
                        form.close();
                    }
                    
                    else {
                        fd.play();
                    }
                    
                });
                
            
            } catch (SQLException er) {
                factory.print(er.getMessage());
            }
            
            }
        });
        
        cancel.setOnAction(te->{
            form.close();
        });
        
        sel.setOnAction(act->{
            
            javafx.stage.FileChooser ch=new javafx.stage.FileChooser();
            ch.setInitialDirectory(new File("client")); ch.setTitle("Choose profile picture");
            ch.getExtensionFilters().addAll(
            new javafx.stage.FileChooser.ExtensionFilter("JPG Image","*.jpg"),
            new javafx.stage.FileChooser.ExtensionFilter("PNG Image","*.png"));
            
            String path=ch.showOpenDialog(form).toString();
            
            pic.setText(path);
            
        });
        
        try {
        form=new smx.jfx.layout.Dialog(owner, true, false, 879, 450, new Scene(mn), "Pupil Form", 
        new Image(new FileInputStream("logo.png")));
        
        } catch (IOException ex) {
            factory.print(ex.getMessage());
        }
        
        return form;
    }
    
    /**
     * Sets the path to id given
     * @param id
     * @param path 
     * @param who
     */
    public void updateProf(String who, String id, String path)  {
        
        com.rs.scoasoft.Factory f=new com.rs.scoasoft.Factory();
        
        f.print(who+"\n"+id+"\n"+path);
        
        try {
        PreparedStatement ps=f.getPS("update "+who+" set picture=\""+path.replace("\\","/")+"\" where id="+id);
        
        ps.executeUpdate();
        
        } catch(SQLException ex) {
            f.print(ex.getMessage());
        }
        
    }
    
    
    /**
     * Sets the path to id given
     * @param id
     * @param path 
     * @param who
     */
    public void updateResume(String who, String id, String path)  {
        
        com.rs.scoasoft.Factory f=new com.rs.scoasoft.Factory();
        
        f.print(who+"\n"+id+"\n"+path);
        
        try {
        PreparedStatement ps=f.getPS("update "+who+" set resume=\""+path.replace("\\","/")+"\" where id="+id);
        
        ps.executeUpdate();
        
        } catch(SQLException ex) {
            f.print(ex.getMessage());
        }
        
    }
    
    /**
     * Returns either the full name or profile id
     * @param str
     * @param which
     * @return 
     */
    public String getLogDa(String str, String which) {
        
        String rtn="";
        
        String[] things=str.split(" ");
        
        if(which.equals("name")) {
            
            int a=2;
            
            while(a<things.length) {
                rtn+=things[a]+" ";
                a++;
            }
            
        }
        
        if(which.equals("id")) {
            
            rtn=things[0];
            
        }
        
        return rtn;
        
    }
    
    /**
     * To view a teacher profile
     * @param owner
     * @param profileid
     * @return 
     */
    public smx.jfx.layout.Dialog getTeacherProfile(javafx.stage.Stage owner, String profileid) {
        
        com.rs.scoasoft.Factory factory=new com.rs.scoasoft.Factory();
        
        try {
            
            //get Data
            PreparedStatement ps=new com.rs.scoasoft.Factory().getPS("select *from teachers where id ="+profileid);
            ResultSet set=ps.executeQuery();
            
            if(set.next()) {
                
                String page="<html><head><title>Profile Page</title></head>"
                        
                        + "<style>"
                        + ".body {"
                        + "font-family: Lucida Sans; "
                        + "font-size: 21px; "
                        + "color: royalblue;"
                        + "}"
                        + "</style>"
                        
                        + "<body class=\"body\">"
                        + "<table border=\"0\" cellpadding=\"7\" cellspacing=\"7\" width=\"100%\">"
                        
                        + "<tr><td width=\"70%\" align=\"left\" class=\"body\" valign=\"top\">"
                        + "<h1>"+set.getString(2)+" "+set.getString(3)+"</h1>"
                        + "<h2>"+set.getString("title")+"</h2>"
                        + "<h3>"+set.getString("regnumber")+"</h3><hr class=\"body\" />"
                        + set.getString("aboutinfo").replaceAll("\n","<p/>")+"<hr class=\"body\" />"
                        + "<p>Gender : "+set.getString("gender")+"</p>"
                        + "<p>Phone : "+set.getString("phone")+"</p>"
                        + "<p>Email : "+set.getString("email")+"</p>"
                        + "<p>Date of birth : "+set.getString("dob")+"</p>"
                        + "<p>Address : "+set.getString("address")+"</p>"
                        + "<p>Nationality : "+set.getString("nationality")+"</p>"
                        + "<p>NIN : "+set.getString("nin")+"</p><hr/>"
                        + "<p>Previous Role : "+set.getString("previousrole")+"</p>"
                        + "<p>Education : "+set.getString("education")+"</p>"
                        + "<p>Subjects : "+set.getString("subjects")+"</p>"
                        + "<p>Classes : "+set.getString("classes")+"</p>"
                        + "<p>Other skills : "+set.getString("otherskills")+"</p><hr/>"
                        + "<p>Next of kin : "+set.getString("nokname")+"</p>"
                        + "<p>Next of kin contact : "+set.getString("nokcontact")+"</p>"
                        + "<p>Next of kin address : "+set.getString("nokaddress")+"</p>"
                        + "<p>Next of kin relation : "+set.getString("nokrelation")+"</p>"
                        + "<p>Status : "+set.getString("status")+"</p><hr/>"
                        + "<p>Parent's name : "+set.getString("parentsname")+"</p>"
                        + "<p>Parent's contact : "+set.getString("parentscontact")+"</p>"
                        + "<p>Parent's address : "+set.getString("parentsaddress")+"</p>"
                        + "<p>Other details : <p/>"+set.getString("bcnames").replaceAll("\n","<p/>")+"</p><hr/>"
                        + "<p>Marital status : "+set.getString("maritalstatus")+"</p>"
                        + "<p>Partner : "+set.getString("partnername")+"</p><hr/>"
                        + "<p>Staff member since : "+set.getString("joindate")+"</p><hr/>"
                        
                        + "</td>"
                        
                        + "<td width=\"30%\"align=\"center\" class=\"body\" valign=\"top\">"
                        + "<img src=\""+set.getString("picture")+"\" width=\"190\" height=\"200\" alt=\"photo\" />"
                        
                        + "</td>"
                        
                        + "</tr></table></body></html>";
                
                //Write the files
                factory.writeFile(page,"temp.html");
                
                //WebView here
                javafx.scene.web.WebView wb=new javafx.scene.web.WebView();
                wb.getEngine().load(new File("temp.html").toURL().toURI().toString());
                wb.setPrefWidth(878); wb.setPrefHeight(500);
                
                //Print button
                smx.jfx.control.Button print=new smx.jfx.control.Button("Print");
                print.setDefaultStyle(factory.getNBTStyle2()); 
                print.setHoverStyle(factory.getNHBTStyle2());
                
                smx.jfx.layout.GridPane pane=factory.getSGrid();
                pane.add(wb,0,0); pane.add(print,0,1);
                
                //Actions
                print.setOnAction(er->{
                    
                    PrinterJob job=PrinterJob.createPrinterJob();
                    if(job!=null && job.showPrintDialog(owner)) {
                        
                        wb.getEngine().print(job);
                        job.endJob(); form.close();
                        
                    }
                    
                });
                
                form=new smx.jfx.layout.Dialog(owner, true, false, 878, 555, new Scene(pane), "Full Profile", 
                new Image(new FileInputStream("logo.png")));
                
                
            }
            
        } catch (SQLException | IOException | java.net.URISyntaxException er) {
            System.out.println(er.getMessage());
        }
        
        return form;
        
    }
    
    
    /**
     * To view a staff profile
     * @param owner
     * @param profileid
     * @return 
     */
    public smx.jfx.layout.Dialog getStaffProfile(javafx.stage.Stage owner, String profileid) {
        
        com.rs.scoasoft.Factory factory=new com.rs.scoasoft.Factory();
        
        try {
            
            //get Data
            PreparedStatement ps=new com.rs.scoasoft.Factory().getPS("select *from otherstaff where id ="+profileid);
            ResultSet set=ps.executeQuery();
            
            if(set.next()) {
                
                String page="<html><head><title>Profile Page</title></head>"
                        
                        + "<style>"
                        + ".body {"
                        + "font-family: Lucida Sans; "
                        + "font-size: 21px; "
                        + "color: royalblue;"
                        + "}"
                        + "</style>"
                        
                        + "<body class=\"body\">"
                        + "<table border=\"0\" cellpadding=\"7\" cellspacing=\"7\" width=\"100%\">"
                        
                        + "<tr><td width=\"70%\" align=\"left\" class=\"body\" valign=\"top\">"
                        + "<h1>"+set.getString(2)+" "+set.getString(3)+"</h1>"
                        + "<h2>"+set.getString("title")+"</h2>"
                        + "<hr class=\"body\" />"
                        + set.getString("aboutinfo").replaceAll("\n","<p/>")+"<hr class=\"body\" />"
                        + "<p>Gender : "+set.getString("gender")+"</p>"
                        + "<p>Phone : "+set.getString("phone")+"</p>"
                        + "<p>Email : "+set.getString("email")+"</p>"
                        + "<p>Date of birth : "+set.getString("dob")+"</p>"
                        + "<p>Address : "+set.getString("address")+"</p>"
                        + "<p>Nationality : "+set.getString("nationality")+"</p>"
                        + "<p>NIN : "+set.getString("nin")+"</p><hr/>"
                        + "<p>Previous Role : "+set.getString("previousrole")+"</p>"
                        + "<p>Education : "+set.getString("education")+"</p>"
                        
                        + "<p>Other skills : "+set.getString("otherskills")+"</p><hr/>"
                        + "<p>Next of kin : "+set.getString("nokname")+"</p>"
                        + "<p>Next of kin contact : "+set.getString("nokcontact")+"</p>"
                        + "<p>Next of kin address : "+set.getString("nokaddress")+"</p>"
                        + "<p>Next of kin relation : "+set.getString("nokrelation")+"</p>"
                        + "<p>Status : "+set.getString("status")+"</p><hr/>"
                        + "<p>Parent's name : "+set.getString("parentsname")+"</p>"
                        + "<p>Parent's contact : "+set.getString("parentscontact")+"</p>"
                        + "<p>Parent's address : "+set.getString("parentsaddress")+"</p>"
                        + "<p>Other details : <p/>"+set.getString("bcnames").replaceAll("\n","<p/>")+"</p><hr/>"
                        + "<p>Marital status : "+set.getString("maritalstatus")+"</p>"
                        + "<p>Partner : "+set.getString("partnername")+"</p><hr/>"
                        + "<p>Staff member since : "+set.getString("joindate")+"</p><hr/>"
                        
                        + "</td>"
                        
                        + "<td width=\"30%\"align=\"center\" class=\"body\" valign=\"top\">"
                        + "<img src=\""+set.getString("picture")+"\" width=\"190\" height=\"200\" alt=\"photo\" />"
                        
                        + "</td>"
                        
                        + "</tr></table></body></html>";
                
                //Write the files
                factory.writeFile(page,"temp.html");
                
                //WebView here
                javafx.scene.web.WebView wb=new javafx.scene.web.WebView();
                wb.getEngine().load(new File("temp.html").toURL().toURI().toString());
                wb.setPrefWidth(878); wb.setPrefHeight(500);
                
                //Print button
                smx.jfx.control.Button print=new smx.jfx.control.Button("Print");
                print.setDefaultStyle(factory.getNBTStyle2()); 
                print.setHoverStyle(factory.getNHBTStyle2());
                
                smx.jfx.layout.GridPane pane=factory.getSGrid();
                pane.add(wb,0,0); pane.add(print,0,1);
                
                //Actions
                print.setOnAction(er->{
                    
                    PrinterJob job=PrinterJob.createPrinterJob();
                    if(job!=null && job.showPrintDialog(owner)) {
                        
                        wb.getEngine().print(job);
                        job.endJob(); form.close();
                        
                    }
                    
                });
                
                form=new smx.jfx.layout.Dialog(owner, true, false, 878, 555, new Scene(pane), "Full Profile", 
                new Image(new FileInputStream("logo.png")));
                
                
            }
            
        } catch (SQLException | IOException | java.net.URISyntaxException er) {
            System.out.println(er.getMessage());
        }
        
        return form;
        
    }
    
    
    /**
     * To view a pupil profile
     * @param owner
     * @param profileid
     * @return 
     */
    public smx.jfx.layout.Dialog getPupilProfile(javafx.stage.Stage owner, String profileid) {
        
        com.rs.scoasoft.Factory factory=new com.rs.scoasoft.Factory();
        
        try {
            
            //get Data
            PreparedStatement ps=new com.rs.scoasoft.Factory().getPS("select *from pupils where id ="+profileid);
            ResultSet set=ps.executeQuery();
            
            if(set.next()) {
                
                String page="<html><head><title>Profile Page</title></head>"
                        
                        + "<style>"
                        + ".body {"
                        + "font-family: Lucida Sans; "
                        + "font-size: 21px; "
                        + "color: royalblue;"
                        + "}"
                        + "</style>"
                        
                        + "<body class=\"body\">"
                        + "<table border=\"0\" cellpadding=\"7\" cellspacing=\"7\" width=\"100%\">"
                        
                        + "<tr><td width=\"70%\" align=\"left\" class=\"body\" valign=\"top\">"
                        + "<h1>"+set.getString(2)+"</h1>"
                        + "<h2>Pupil</h2>"
                        + "<hr class=\"body\" />"
                        
                        + "<p>Gender : "+set.getString("gender")+"</p>"
                        
                        + "<p>Date of birth : "+set.getString("dob")+"</p>"
                        + "<p>Address : "+set.getString("address")+"</p>"
                        + "<p>Nationality : "+set.getString("nationality")+"</p><hr/>"
                        
                        + "<p>Father's name : "+set.getString("fathername")+"</p>"
                        + "<p>Father's contact : "+set.getString("fathercontact")+"</p>"
                        + "<p>Mother's name : "+set.getString("mothername")+"</p>"
                        + "<p>Mother's contact : "+set.getString("mothercontact")+"</p>"
                        
                        + "<p>Other contact person : "+set.getString("othercontactname")+"</p>"
                        + "<p>Other person's contact : "+set.getString("othercontactcontact")+"</p>"
                        + "<p>Other person's address : "+set.getString("othercontactaddress")+"</p>"
                        
                        + "<p>Status : "+set.getString("status")+"</p>"
                        + "<p>Transport : "+set.getString("transport")+"</p><hr/>"
                        
                        + "<p>Stream : "+set.getString("stream")+"</p>"
                        + "<p>Health : "+set.getString("healthystatus")+"</p>"
                        + "<p>Mode of study : "+set.getString("modeofstudy")+"</p>"
                        
                        
                        + "<p>Pupil since : "+set.getString("joindate")+"</p><hr/>"
                        
                        + "</td>"
                        
                        + "<td width=\"30%\"align=\"center\" class=\"body\" valign=\"top\">"
                        + "<img src=\""+set.getString("picture")+"\" width=\"190\" height=\"200\" alt=\"photo\" />"
                        
                        + "</td>"
                        
                        + "</tr></table></body></html>";
                
                //Write the files
                factory.writeFile(page,"temp.html");
                
                //WebView here
                javafx.scene.web.WebView wb=new javafx.scene.web.WebView();
                wb.getEngine().load(new File("temp.html").toURL().toURI().toString());
                wb.setPrefWidth(878); wb.setPrefHeight(500);
                
                //Print button
                smx.jfx.control.Button print=new smx.jfx.control.Button("Print");
                print.setDefaultStyle(factory.getNBTStyle2()); 
                print.setHoverStyle(factory.getNHBTStyle2());
                
                smx.jfx.layout.GridPane pane=factory.getSGrid();
                pane.add(wb,0,0); pane.add(print,0,1);
                
                //Actions
                print.setOnAction(er->{
                    
                    PrinterJob job=PrinterJob.createPrinterJob();
                    if(job!=null && job.showPrintDialog(owner)) {
                        
                        wb.getEngine().print(job);
                        job.endJob(); form.close();
                        
                    }
                    
                });
                
                form=new smx.jfx.layout.Dialog(owner, true, false, 878, 555, new Scene(pane), "Full Profile", 
                new Image(new FileInputStream("logo.png")));
                
                
            }
            
        } catch (SQLException | IOException | java.net.URISyntaxException er) {
            System.out.println(er.getMessage());
        }
        
        return form;
        
    }
    
    /**
     * Returns the current payments list!
     * @param owner
     * @param combo
     * @return 
     */
    @SuppressWarnings("UseSpecificCatch")
    public smx.jfx.layout.Dialog getTheseProiles(javafx.stage.Stage owner, String combo) {
        
        form=null;
        
        //get Table name
        String tname;
        
        switch(combo)
        {
            
            case "Teachers" : 
                tname="teachers";
                break;
                
            case "Other Staff" :
                tname="otherstaff";
                break;
                
            default : 
                tname="pupils";
                break;
            
        }
        
        com.rs.scoasoft.Factory factory=new com.rs.scoasoft.Factory();
        com.rs.scoasoft.performance.Performance per=new com.rs.scoasoft.performance.Performance();
        
        try {
        
        //web
        javafx.scene.web.WebView web=new javafx.scene.web.WebView();
        web.setPrefWidth(975); web.setPrefHeight(550);
        
        //get
        
        rep="<html>"
                + "<head>"
                + "<title>All Profiles</title>"
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
                
                + "List of all profiles<p/>"+per.ty()[0]+" "+per.ty()[1]+"<p class=\"body\">"+combo+"</p>"
                
                + "<table border=\"5\" cellpadding=\"7\" cellpadding=\"7\" width=\"100%\" bordercolor=\"royalblue\">"
                + "<tr><th align=\"center\">Full names</th>"
                + "<th align=\"center\">Gender</th><th align=\"center\">Date of birth</th>"
                + "<th align=\"center\">Contact</th>"
                + "<th align=\"center\">Headline</th><th align=\"center\">Address</th>"
                + "<th align=\"center\">Category</th></tr>";
                
                //Get Data
                int total;
                
                if(tname.equals("pupils")) {
                    
                PreparedStatement pst=
                        factory.getPS("select *from "+tname+" where class=\'"+combo+"\' order by fullnames asc");
                
                ResultSet set=pst.executeQuery();
                total=0;
                while(set.next()) {
                    
                    rep+="<tr><td align=\"center\">"+set.getString(2)+"</td>";
                    
                    rep+="<td align=\"center\">"+set.getString("gender")+"</td>"
                            + "<td align=\"center\">"+set.getString("dob")+"</td>"
                            
                            + "<td align=\"center\">"+set.getString("fathercontact")+" | "
                            +set.getString("mothercontact")+"</td>"
                            
                            + "<td align=\"center\">"+set.getString("class")+"</td>"
                            + "<td align=\"center\">"+set.getString("address")+"</td>"
                            
                            + "<td align=\"center\">"+set.getString("stream")+"</td>"
                            
                            + "</tr>";
                    total++;
                }
                
                }
                
                else {
                    
                       
                PreparedStatement pst=
                        factory.getPS("select *from "+tname+" order by fname asc");
                System.out.print(tname);
                ResultSet set=pst.executeQuery();
                total=0;
                while(set.next()) {
                    
                    rep+="<tr><td align=\"center\">"+set.getString(2)+" "+set.getString(3)+"</td>";
                    
                    rep+="<td align=\"center\">"+set.getString("gender")+"</td>"
                            + "<td align=\"center\">"+set.getString("dob")+"</td>"
                            
                            + "<td align=\"center\">"+set.getString("phone")+"</td>"
                            
                            + "<td align=\"center\">"+set.getString("title")+"</td>"
                            + "<td align=\"center\">"+set.getString("address")+"</td>"
                            
                            + "<td align=\"center\">"+set.getString("id")+"</td>"
                            
                            + "</tr>";
                    total++;
                }
                
                    
                }
                
                
                rep+= "</table>Total : "+total+"</td></tr>"
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
                        job.endJob(); form.close();
                        
                    }
                    
                });
                
                form=new smx.jfx.layout.Dialog(owner, true, false, 971, 575, new Scene(pane), "View profiles", 
                new Image(new FileInputStream("logo.png")));
                
        
        } catch(Exception er) {
            System.out.println(er.getMessage());
        }
        
        return form;
        
    }
    
    private static String rep;
    
}
