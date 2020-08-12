package cn.com.debuggers.dbmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DBManager {
    static String driver;
    static String url;
    static String username;
    static String password;
    private Connection connection;
    private Statement statement;

    public static void main(String[] args) {
        try{
            DBManager db=new DBManager();
            List<String> CompanyList = db.getCompany();
            System.out.println("Start\n...");
            for (int i = 0; i < CompanyList.size(); i++) {
                String creditCode = CompanyList.get(i).split("\\+")[0];
                String companyName = CompanyList.get(i).split("\\+")[1];
                try{
//                    db.updateCreditCode(creditCode,companyName);
//                    db.updateFinance(creditCode,companyName);
//                    db.updateTopTenHolders(creditCode,companyName);
//                    db.updateAnualReport(creditCode,companyName);
//                    db.updateBidInfo(creditCode,companyName);
//                    db.updateAdminCommen(creditCode,companyName);
//                    db.updateBranchInfo(creditCode,companyName);
//                    db.updateProvinceInfo(creditCod,companyName)''
//                    db.updatePatentInfo(creditCode,companyName);
                    db.updateAdminPunish(creditCode,companyName);
                    db.updateJudgement_document(creditCode,companyName);
                }catch (Exception e){
                    System.out.println(i+"\t"+creditCode+"\t"+companyName);
                    e.printStackTrace();
                    System.out.println("continue ......");
                }
            }
            db.close();


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public DBManager() throws Exception{
        driver="com.mysql.cj.jdbc.Driver";
        url="jdbc:mysql://121.89.181.241:3306/spy";
        username="root";
        password="Debuggers15";

        Class.forName(driver);
        connection = DriverManager.getConnection(url,username,password);
        statement = connection.createStatement();
    }

    public List<String> getCompany()throws Exception{
        ResultSet resultSet=statement.executeQuery("select creditCode,companyName from CompanyBasicInfo;");
        List<String> resultList=new ArrayList<String>();
        while(resultSet.next()){
            resultList.add(resultSet.getString("creditCode")+"+"+resultSet.getString("companyName"));
        }
        return resultList;
    }

    public void updateAdminPunish(String creditCode,String companyName) throws Exception{
        statement.execute("update adminPunish set creditCode =\""+creditCode+"\" where companyName = \""+companyName+"\";");
    }

    public void updateJudgement_document(String creditCode,String companyName) throws Exception{
        statement.execute("update judgement_document set creditCode =\""+creditCode+"\" where companyName = \""+companyName+"\";");
    }

    public void updatePatentInfo(String creditCode,String companyName) throws Exception{
        statement.execute("update patentInfo set creditCode =\""+creditCode+"\" where companyName = \""+companyName+"\";");
    }

    public void updateProvinceInfo(String creditCode,String companyName) throws Exception{
        statement.execute("update provinceInfo set creditCode =\""+creditCode+"\" where companyName = \""+companyName+"\";");
    }

    public void updateBranchInfo(String creditCode,String companyName) throws Exception{
        statement.execute("update branchInfo set creditCode =\""+creditCode+"\" where companyName = \""+companyName+"\";");
    }

    public void updateAdminCommen(String creditCode,String companyName) throws Exception{
        statement.execute("update adminCommen set creditCode =\""+creditCode+"\" where companyName = \""+companyName+"\";");
    }

    public void updateBidInfo(String creditCode,String companyName) throws Exception{
        statement.execute("update bidInfo set creditCode =\""+creditCode+"\" where companyName = \""+companyName+"\";");
    }

    public void updateAnualReport(String creditCode,String companyName) throws Exception{
        statement.execute("update anualReport set creditCode =\""+creditCode+"\" where companyName = \""+companyName+"\";");
    }

    public void updateCreditCode(String creditCode,String companyName) throws Exception{
        statement.execute("update executives set creditCode =\""+creditCode+"\" where companyName = \""+companyName+"\";");
    }

    public void updateFinance(String creditCode, String companyName) throws Exception{
        statement.execute("update finance set creditCode =\""+creditCode+"\" where companyName = \""+companyName+"\";");
    }
    public void updateTopTenHolders(String creditCode,String companyName)throws Exception{
        statement.execute("update topTenHolders set creditCode =\""+creditCode+"\" where companyName = \""+companyName+"\";");
    }

    public void close() throws Exception{
        statement.close();
        connection.close();
    }
}