/*
 * The performance report class
 */
package com.rs.scoasoft.performance;

/**
 * so!
 * @author Remote Station
 */

//import javafx.scene.control.*;
//import javafx.scene.image.*;
//import javafx.geometry.*;
//import javafx.animation.*;
//import javafx.util.*;
//import javafx.scene.Scene;

//import java.io.*;
import java.util.*;
import java.sql.*;

public class Report {
    
    /**
     * Initializing
     */
    public Report() {
        
    }
    
    private String pic,name,me,mm,ms,mst,mre,
                te,tm,ts,tst,tre,
                
                eg,mg,sg,stg,reg,eg2,mg2,sg2,stg2,reg2;
        
    
    /**
     * Returns midterm scores and aggregates in a string array
     * @return 
     */
    public String[] midTermPerf() {
        
        String[] rtn=new String[8];
        
        rtn[0]=me; rtn[1]=mm; rtn[2]=ms; rtn[3]=mst;
        rtn[4]=eg; rtn[5]=mg; rtn[6]=sg; rtn[7]=stg;
        
        return rtn;
    }
    
    
    /**
     * Returns end-term scores and aggregates in a string array
     * @return 
     */
    public String[] endTermPerf() {
        
        String[] rtn=new String[8];
        
        rtn[0]=te; rtn[1]=tm; rtn[2]=ts; rtn[3]=tst;
        rtn[4]=eg2; rtn[5]=mg2; rtn[6]=sg2; rtn[7]=stg2;
        
        return rtn;
    }
    
    /**
     * Returns the report in html
     * @param profileid
     * @param term
     * @param year
     * @param clas
     * @return 
     */
    public String getReport(String profileid, String term, String year, String clas) {
        
        String report;
        
        if(isResultAvailable(profileid,term,year,clas) && isMidAvailable(profileid,term,year,clas)) {
        
        //values needed!
        pic=null; name=null; me=null; mm=null; ms=null; mst=null; mre=null;
                te=null; tm=null; ts=null; tst=null; tre=null;
                
        //factory
        com.rs.scoasoft.Factory factory=new com.rs.scoasoft.Factory();
        com.rs.scoasoft.performance.PerformanceFactory pfactory=
                new com.rs.scoasoft.performance.PerformanceFactory();
        
        try {
            
            //get profile name
            PreparedStatement ps=factory.getPS("select *from pupils where id="+profileid);
            ResultSet set=ps.executeQuery();
            
            if(set.next()) {
                
                name=set.getString(2); pic=set.getString("picture");
                
            }
            
            //get Mid-term scores
            PreparedStatement ps1=
                    new com.rs.scoasoft.Factory()
                            .getPS("select *from midterm where owner=? && year=? && term=? && class=?");
            ps1.setString(1,profileid); ps1.setString(2,year); ps1.setString(3,term);
            ps1.setString(4,clas);
            
            ResultSet set1=ps1.executeQuery();
            
            if(set1.next()) {
                
                me=set1.getString(3);
                mm=set1.getString(4);
                ms=set1.getString(5);
                mst=set1.getString(6);
                //mre=set1.getString(7);
                
            }
            
            //get End-term scores
            PreparedStatement ps2=
                    new com.rs.scoasoft.Factory()
                            .getPS("select *from endterm where owner=? && year=? && term=? && class=?");
            ps2.setString(1,profileid); ps2.setString(2,year); ps2.setString(3,term);
            ps2.setString(4,clas);
            
            ResultSet set2=ps2.executeQuery();
            
            if(set2.next()) {
                
                te=set2.getString(3);
                tm=set2.getString(4);
                ts=set2.getString(5);
                tst=set2.getString(6);
                //tre=set2.getString(7);
                
            }
            
        } catch (SQLException ex) {
            factory.print(ex.getMessage());
        }
        
        
        eg=pfactory.getGrade(me); mg=pfactory.getGrade(mm);
        sg=pfactory.getGrade(ms); stg=pfactory.getGrade(mst);
        //reg=pfactory.getGrade("re", mre);
        
        liu=new ArrayList();
        liu.add(eg); liu.add(mg); liu.add(sg); liu.add(stg); //liu.add(reg);
        
        eg2=pfactory.getGrade(te); mg2=pfactory.getGrade(tm);
        sg2=pfactory.getGrade(ts); stg2=pfactory.getGrade(tst);
        //reg2=pfactory.getGrade("re", tre);
        
        liu2=new ArrayList();
        liu2.add(eg2); liu2.add(mg2); liu2.add(sg2); liu2.add(stg2); //liu2.add(reg2);
        
        String[] cred=getCreditials(clas);
        
        report="<html>"
                
                + "<head>"
                + "<title>REPORT</title>"
                + "<style>"
                + ".body {"
                + "font-family: Lucida Sans;"
                + "font-size: 19px;"
                + "color: royalblue;"
                + "background-color: white;"
                + "opacity: 1;"
                + "}"
                + ".water {"
                + "font-family: Lucida Sans;"
                + "font-size: 19px;"
                + "color: royalblue;"
                + "background-image: url(logo.png);"
                + "background-position: center center;"
                + "background-repeat: no-repeat;"
                + "background-color: white;"
                + "background-size: auto auto"
                + "}"
                + ".body1 {"
                + "font-family: Lucida Sans;"
                + "font-size: 19px;"
                + "color: royalblue;"
                + "background-image: url(bgfile.png);"
                + "}"
                + ""
                + ".heading {"
                + "font-family: felix titling;"
                + "font-size: 24px;"
                + "color: royalblue;"
                + "font-weight: bold;"
                + "}"
                + ".min {"
                + "font-family: felix titling;"
                + "font-size: 15px;"
                + "color: royalblue;"
                + "font-weight: bold;"
                + ""
                + "}"
                + "</style>"
                + "</head>"
                
                + "<body class=\"body1\" align=\"center\">"
                
                //+ "<div class=\"water\">"
                
                + "<table border=\"12\" cellpadding=\"7\" cellspacing=\"7\" width=\"731\" bordercolor=\"royalblue\">"
                
                + "<tr><td align=\"center\" valign=\"top\" class=\"water\">"
                + "<div class=\"body\">"
                
                + "<div class=\"min\"><img src=\"smx.png\" width=\"80\" height=\"80\" alt=\"LOGO\"/><br/>"
                + "</div>"
                
                + "<div class=\"heading\">"+factory.getSchoolDetails()[0]+"</div>"
                + "<p><i><b>\""+factory.getSchoolDetails()[1]+"\"</i></b></p>"
                /*+ "<p>PHONE: 0750 894 217 / 0704 810 476<br/>"
                + "Email: ebenezerstandardprimaryschool@gmail.com</p>"*/
                
                + "<table border=\"0\" cellspacing=\"4\" cellpadding=\"4\" width=\"100%\">"
                + "<tr><td align=\"center\"><img src=\"client/logo.png\" width=\"150\" height=\"150\" alt=\"logo\" /></td>"
                
                + "<td class=\"body\" align=\"center\">"
                + "<p>"+term+" "+year+"</p>"
                + "<p>"+clas+"</p>"
                + "<h4>"+name+"</h4>"
                + "Position: "+getTPosition(profileid,term,year,clas)[0]+" out of "
                +getTPosition(profileid,term,year,clas)[1]+"</td>";
                
                report+= "<td align=\"center\"><img src=\""+pic+"\" width=\"150\" height=\"150\" alt=\"logo\" /></td>"
                + "</tr></table>"
                
                + "<div>MID-TERM PERFORMANCE</div>"
                + "<table border=\"5\" cellspacing=\"4\" cellpadding=\"4\" width=\"100%\" bordercolor=\"royalblue\">"
                
                + "<tr><td align=\"center\">ENG</td><td align=\"center\">MATHS</td>"
                + "<td align=\"center\">SCI</td><td align=\"center\">S.S.T</td>"//<td align=\"center\">R.E</td>"
                + "<td align=\"center\">TOTAL</td><td align=\"center\">AGG</td><td align=\"center\">POS</td>"
                + "</tr>"
                
                + "<tr><td align=\"center\">"+me+"</td><td align=\"center\">"+mm+"</td>"
                + "<td align=\"center\">"+ms+"</td><td align=\"center\">"+mst+"</td>"
                //+ "<td align=\"center\">"+mre+"</td>"
                + "<td align=\"center\">"+getPMTotal(profileid,term,year,clas)+"</td>"
                +" <td align=\"center\"\">Div. "+pfactory.getDiv(getAggTotal(liu))+"</td><td align=\"center\"></td>"
                + "</tr>";
                
                report+= "<tr><td align=\"center\">"+pfactory.getGrade(me)+"</td>"
                + "<td align=\"center\">"+pfactory.getGrade(mm)+"</td>"
                + "<td align=\"center\">"+pfactory.getGrade(ms)+"</td>"
                + "<td align=\"center\">"+pfactory.getGrade(mst)+"</td>";
                //+ "<td align=\"center\">"+pfactory.getGrade("re", mre)+"</td>";
                
                report+= "<td align=\"center\"></td>"
                +" <td align=\"center\">"
                + getAggTotal(liu)
                + "</td>"
                
                + "<td align=\"center\">"
                + getMPosition(profileid,term,year,clas)[0]
                + "</td>"
                + "</tr>"
                + "</table>";
                
                report+= "<p/><div>TERMLY PERFORMANCE</div>"
                + "<table border=\"5\" cellspacing=\"4\" cellpadding=\"4\" width=\"100%\" bordercolor=\"royalblue\">"
                
                + "<tr><td align=\"center\">SUBJECT</td><td align=\"center\">FULL MARKS</td>"
                + "<td align=\"center\">MARKS SCORED</td><td align=\"center\">AGG</td>"
                + "<td align=\"center\">REMARKS</td><td align=\"center\">INITIALS</td></tr>"
                
                + "<tr><td align=\"center\">ENGLISH</td><td align=\"center\">100</td>"
                + "<td align=\"center\">"+te+"</td><td align=\"center\">"+eg2+"</td>"
                + "<td align=\"center\">"+pfactory.getScoreComment(te)+"</td>"
                + "<td align=\"center\">"+cred[0]+"</td>";
                
                report+= "<tr><td align=\"center\">MATHEMATICS</td><td align=\"center\">100</td>"
                + "<td align=\"center\">"+tm+"</td><td align=\"center\">"+mg2+"</td>"
                + "<td align=\"center\">"+pfactory.getScoreComment(tm)+"</td>"
                + "<td align=\"center\">"+cred[1]+"</td>"
                
                + "<tr><td align=\"center\">SCIENCE</td><td align=\"center\">100</td>"
                + "<td align=\"center\">"+ts+"</td><td align=\"center\">"+sg2+"</td>"
                + "<td align=\"center\">"+pfactory.getScoreComment(ts)+"</td>"
                + "<td align=\"center\">"+cred[2]+"</td>"
                
                + "<tr><td align=\"center\">SOCIAL_STUDIES</td><td align=\"center\">100</td>"
                + "<td align=\"center\">"+tst+"</td><td align=\"center\">"+stg2+"</td>"
                + "<td align=\"center\">"+pfactory.getScoreComment(tst)+"</td>"
                + "<td align=\"center\">"+cred[3]+"</td>";
                
                /*+ "<tr><td align=\"center\">RELIGIOUS_EDUCATION</td><td align=\"center\">100</td>"
                + "<td align=\"center\">"+tre+"</td><td align=\"center\">"+reg2+"</td>"
                + "<td align=\"center\">"+pfactory.getScoreComment(tre)+"</td>"
                + "<td align=\"center\">"+cred[4]+"</td>";*/
                
                report+= "<tr><td align=\"center\">TOTAL</td><td align=\"center\">400</td>"
                + "<td align=\"center\">"+getPETotal(profileid,term,year,clas)+"</td>"
                + "<td align=\"center\">"+getAggTotal(liu2)+"</td>"
                + "<td align=\"center\">Div. "+pfactory.getDiv(getAggTotal(liu2))+"</td>"
                + "<td align=\"center\"></td>";
                
                report+= "</table>"
                
                + "<p><b>Class teacher's comment: </b><br>"+pfactory.getCTComment(getAggTotal(liu2))+"</br></p>"
                
                + "<p><b>Head teacher's comment: </b><br>"+pfactory.getHTComment(getAggTotal(liu2))+"</br></p>";
                
                report+= "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"
                        
                        + "<tr><td align=\"left\">"
                        + "<i><b>\"Invalid without a school stamp\"</b></i></td>"
                        
                        + "<td align=\"right\">"
                        + "<i>Powered by Remote Station</i>"
                        + "</td></tr>"
                        
                        + "</table>"
                
                + "</div></td></tr></table>"
                
                //+ "</div>"
                        + "</body>"
                
                + "</html> <P style=\"page-break-before: always\">";
                
                System.out.println("Report str generated!");
        } 
        
        else {
            report="";
            System.out.println("Some values (either midterm or end-term) are missing!");
        }
        return report;
        
    }
    
    /**
     * Returns pupils position in midterm exams
     * @param id
     * @param term
     * @param year
     * @param clas
     * @return 
     */
    public String[] getMPosition(String id,String term, String year, String clas) {
        
        String[] pos=new String[2];
        
        List<String> lst=getAllMScores(term,year,clas);
        
        Collections.sort(lst); Collections.reverse(lst);
        
        String score=getPMTotal(id,term,year,clas);
        
        pos[0]=getPosition(score,lst); pos[1]=String.valueOf(lst.size());
        
        return pos;
    }
    
    
    /**
     * Returns pupils position in end-term exams
     * @param id
     * @param term
     * @param year
     * @param clas
     * @return 
     */
    public String[] getTPosition(String id,String term, String year, String clas) {
        
        String[] pos=new String[2];
        
        List<String> lst=getAllEScores(term,year,clas);
        
        Collections.sort(lst); Collections.reverse(lst);
        
        String score=getPETotal(id,term,year,clas);
        
        pos[0]=getPosition(score,lst); pos[1]=String.valueOf(lst.size());
        
        return pos;
    }
    
    /**
     * Returns total midterm score
     * @param id
     * @param term
     * @param year
     * @param clas
     * @return 
     */
    public String getPMTotal(String id, String term, String year, String clas) {
        
        String tt; int ttt=0;
        
        try {
            
            PreparedStatement ps=
                    new com.rs.scoasoft.Factory()
                            .getPS("select *from midterm where owner=? && year=? && term=? && class=?");
            ps.setString(1,id); ps.setString(2,year); ps.setString(3,term);
            ps.setString(4,clas);
            
            ResultSet set=ps.executeQuery();
            
            if(set.next()) {
                
                ttt+=Integer.valueOf(set.getString("english"));
                ttt+=Integer.valueOf(set.getString("math"));
                ttt+=Integer.valueOf(set.getString("science"));
                ttt+=Integer.valueOf(set.getString("sst"));
                //ttt+=Integer.valueOf(set.getString("re"));
                
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        tt=String.valueOf(ttt);
        
        return tt;
        
    }
    
    
    /**
     * Returns total end-term score
     * @param id
     * @param term
     * @param year
     * @param clas
     * @return 
     */
    public String getPETotal(String id, String term, String year, String clas) {
        
        String tt; int ttt=0;
        
        try {
            
            PreparedStatement ps=
                    new com.rs.scoasoft.Factory()
                            .getPS("select *from endterm where owner=? && year=? && term=? && class=?");
            ps.setString(1,id); ps.setString(2,year); ps.setString(3,term);
            ps.setString(4,clas);
            
            ResultSet set=ps.executeQuery();
            
            if(set.next()) {
                
                ttt+=Integer.valueOf(set.getString("english"));
                ttt+=Integer.valueOf(set.getString("math"));
                ttt+=Integer.valueOf(set.getString("science"));
                ttt+=Integer.valueOf(set.getString("sst"));
                //ttt+=Integer.valueOf(set.getString("re"));
                
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        tt=String.valueOf(ttt);
        
        return tt;
        
    }
    
    /**
     * Returns list of all midterm scores
     * @param term
     * @param year
     * @param clas
     * @return 
     */
    public List<String> getAllMScores(String term, String year, String clas) {
        
        List<String> lst=new ArrayList();
        
        try {
            
            PreparedStatement ps=
                    new com.rs.scoasoft.Factory().getPS("select *from midterm where term=? && year=? && class=?");
            ps.setString(1,term); ps.setString(2,year); ps.setString(3,clas);
            
            ResultSet set=ps.executeQuery();
            
            while(set.next()) {
                
                lst.add(getPMTotal(set.getString("owner"),term,year,clas));
                
            }
            
        } catch(SQLException er) {
            System.out.println(er.getMessage());
        }
        
        return lst;
        
    }
    
    
    /**
     * Returns list of all end-term scores
     * @param term
     * @param year
     * @param clas
     * @return 
     */
    public List<String> getAllEScores(String term, String year, String clas) {
        
        List<String> lst=new ArrayList();
        
        try {
            
            PreparedStatement ps=
                    new com.rs.scoasoft.Factory().getPS("select *from endterm where term=? && year=? && class=?");
            ps.setString(1,term); ps.setString(2,year); ps.setString(3,clas);
            
            ResultSet set=ps.executeQuery();
            
            while(set.next()) {
                
                lst.add(getPETotal(set.getString("owner"),term,year,clas));
                
            }
            
        } catch(SQLException er) {
            System.out.println(er.getMessage());
        }
        
        return lst;
        
    }
    
    /**
     * Returns agg passed total
     * @param list
     * @return 
     */
    public String getAggTotal(List<String> list) {
        
        String tt; tttt=0;
        
        list.forEach((value)->{
            
            String[] st=value.split("");
            
            tttt+=Integer.valueOf(st[1]);
            
        });
        
        tt=String.valueOf(tttt);
        
        return tt;
    }
    
    private int tttt;
    
    /**
     * Returns teacher credentials
     * @param clas
     * @return 
     */
    public String[] getCreditials(String clas) {
        
        String[] str=null;
        
        try {
            
            PreparedStatement ps=new com.rs.scoasoft.Factory().getPS("select creditials from creditials where class=?");
            ps.setString(1,clas);
            
            ResultSet set=ps.executeQuery();
            
            if(set.next()) {
                
                str=set.getString(1).split(" ");
                
            }
            
        } catch (SQLException er) {
            System.out.println(er.getMessage());
        }
        
        return str;
        
    }
    
    private List<String> liu,liu2;
    
    public List<String> getMAgg() {
        
        return liu;
        
    }
    
    public List<String> getEAgg() {
        
        return liu2;
        
    }
    
    /**
     * See if end-term values for this pupil is available
     * @param profileid
     * @param term
     * @param year
     * @param clas
     * @return 
     */
    public boolean isResultAvailable(String profileid,String term,String year,String clas) {
        
        boolean retu=false;
        
        try {
        
        PreparedStatement ps1=
                    new com.rs.scoasoft.Factory()
                            .getPS("select *from endterm where owner=? && year=? && term=? && class=?");
            ps1.setString(1,profileid); ps1.setString(2,year); ps1.setString(3,term);
            ps1.setString(4,clas);
            
            ResultSet set1=ps1.executeQuery();
            
            retu = set1.next();
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return retu;
            
    }
    
    /**
     * Check if mid term values for this pupil are available
     * @param profileid
     * @param term
     * @param year
     * @param clas
     * @return 
     */
    public boolean isMidAvailable(String profileid,String term,String year,String clas) {
        
        boolean retu=false;
        
        try {
            
            
            PreparedStatement ps2=
                    new com.rs.scoasoft.Factory()
                            .getPS("select *from midterm where owner=? && year=? && term=? && class=?");
            ps2.setString(1,profileid); ps2.setString(2,year); ps2.setString(3,term);
            ps2.setString(4,clas);
            
            ResultSet set2=ps2.executeQuery();
            
            retu = set2.next();
        
            
        } catch(SQLException er) {
            System.out.println(er.getMessage());
        }
        
        return retu;
        
    }
    
    /**
     * Returns the position of the passed value in the passed list
     * @param value
     * @param allvalues
     * @return 
     */
    protected String getPosition(String value, List<String> allvalues) {
        
        String pos=null;
        
        List<Integer> inte=toIntegers(allvalues);
        
        java.util.Collections.sort(inte); java.util.Collections.reverse(inte);
        
        int i=0,j=1,posi=0; boolean finished=false;
        
        while(j<inte.size()) {
            
            if(finished) {
                break;
            }
            
            posi++; 
            
            if(inte.get(i).equals(Integer.valueOf(value))) {
                pos=String.valueOf(posi); finished=true;
            }
            
            i++; j++;
            
            if(j==inte.size() && !finished) {
                posi++; pos=String.valueOf(posi);
            }
            
        }
        
        return pos;
        
    }
    
    /**
     * Converts String list to Integer List
     * @param list
     * @return 
     */
    protected List<Integer> toIntegers(List<String> list) {
        
        List<Integer> rtn=new ArrayList();
        
        list.forEach((item)->{
            
            rtn.add(Integer.valueOf(item));
            
        });
        
        return rtn;
        
    }
    
}
