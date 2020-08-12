package cn.com.debuggers.spiderA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


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

    public void addCompany(String code,String shortName, String name) throws Exception{
        statement.execute("insert into company values(\"" + code + "\",\""+shortName+"\",\""+name+"\");");
    }
    public void addBasicInfo (String name, String industry, String legalRepresentative,String phone,String address,String postcode,String website)throws Exception
    {
        statement.execute("insert into CompanyBasicInfo(companyName,industry,legalRepresentative,phone,regisAdress,postcode,website) " +
                "values(\"" + name + "\",\""+industry+"\",\""+legalRepresentative+"\",\""+phone+"\",\""+address+"\",\""+postcode+"\",\""+website+"\");");
    }
}
