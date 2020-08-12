package cn.com.debuggers.spiderF;

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

    public DBManager() throws Exception{
        driver="com.mysql.cj.jdbc.Driver";
        url="jdbc:mysql://121.89.181.241:3306/spy";
        username="root";
        password="Debuggers15";

        Class.forName(driver);
        Connection con = DriverManager.getConnection(url,username,password);
        statement = con.createStatement();
    }

    public List<String> getCompany()throws Exception{
        ResultSet resultSet=statement.executeQuery("select code,companyName from company;");
        List<String> resultList=new ArrayList<String>();
        while(resultSet.next()){
            resultList.add(resultSet.getString("code")+"+"+resultSet.getString("companyName"));
        }
        return resultList;
    }

    public void insertProvinceInfo(String companyName,String province)throws Exception{
        statement.execute("insert into provinceInfo(companyName,province) " + "values(\"" + companyName +"\",\""+province+"\");");
    }

    public void close() throws Exception{
        statement.close();
        connection.close();
    }
}
