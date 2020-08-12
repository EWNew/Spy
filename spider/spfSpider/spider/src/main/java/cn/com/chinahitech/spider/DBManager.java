package cn.com.chinahitech.spider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;


public class DBManager {
    static String driver;
    static String url;
    static String username;
    static String password;
    private Connection connection;
    private Statement statement;

    public DBManager(){
        try{
        driver="com.mysql.cj.jdbc.Driver";
        url="jdbc:mysql://121.89.181.241:3306/spy";
        username="root";
        password="Debuggers15";

        Class.forName(driver);
        connection = DriverManager.getConnection(url,username,password);
        if(!connection.isClosed())
            System.out.println("Connecting success!");
        statement = connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void shutDown(){
        try {
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addSpfInfo(String companyName,String creditCode,String estabDate,String companyType,String businessTerm){
        try {
            statement.execute("update CompanyBasicInfo set creditCode = \"" + creditCode + "\"where companyName=\"" + companyName + "\"");
            statement.execute("update CompanyBasicInfo set estabDate = \"" + estabDate + "\"where companyName=\"" + companyName + "\"");
            statement.execute("update CompanyBasicInfo set companyType = \"" + companyType + "\"where companyName=\"" + companyName + "\"");
            statement.execute("update CompanyBasicInfo set businessTerm = \"" + businessTerm + "\"where companyName=\"" + companyName + "\"");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addCapital(String companyName,String capital){
        try {
            statement.execute("update CompanyBasicInfo set regCapital = \"" + capital + "\"where companyName=\"" + companyName + "\"");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<String> selectCompany(){
        try{
        ResultSet resultSet=null;
        resultSet=statement.executeQuery("select companyName from CompanyBasicInfo;");
        String result;
        List<String> resultList=new ArrayList<String>();
        while(resultSet.next()){
            result=resultSet.getString("companyName");
            resultList.add(result);
        }
        return resultList;}
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void addReport (String companyName, String nianBaoTitle, String nianBaoUrl)
    {
        try {
            statement.execute("insert into anualReport(companyName,nianBaoTitle,nianBaoUrl) " +
                    "values(\"" + companyName + "\",\"" + nianBaoTitle + "\",\"" + nianBaoUrl + "\");");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addPatent (String companyName, String title, String link,String classify,String property,String date) {
        try {
            statement.execute("insert into patentInfo(companyName,title,link,classify,property,date) " +
                    "values(\"" + companyName + "\",\"" + title + "\",\"" + link + "\",\"" + classify + "\",\"" + property + "\",\"" + date + "\");");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addAdmin (String companyName, String title, String link,String department,String date) {
        try {
            statement.execute("insert into adminCommen(companyName,title,link,department,date) " +
                    "values(\"" + companyName + "\",\"" + title + "\",\"" + link + "\",\"" + department + "\",\"" + date + "\");");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void addBid (String companyName, String title, String link,String department,String date) {
        try {
            statement.execute("insert into bidInfo(companyName,title,link,department,date) " +
                    "values(\"" + companyName + "\",\"" + title + "\",\"" + link + "\",\"" + department + "\",\"" + date + "\");");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addPunish(String companyName, String title, String link,String department,String date) {
        try {
            statement.execute("insert into adminPunish(companyName,title,link,department,date) " +
                    "values(\"" + companyName + "\",\"" + title + "\",\"" + link + "\",\"" + department + "\",\"" + date + "\");");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addBranch(String companyName, String title, String link,String department) {
        try {
            statement.execute("insert into branchInfo(companyName,title,link,department) " +
                    "values(\"" + companyName + "\",\"" + title + "\",\"" + link + "\",\"" + department + "\");");
        }catch (Exception e){
            e.printStackTrace();
        }
    }



//    public static void main(String[] args){
//        try {
//            DBManager dbManager = new DBManager();
//            dbManager.selectCompany();
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }
}

