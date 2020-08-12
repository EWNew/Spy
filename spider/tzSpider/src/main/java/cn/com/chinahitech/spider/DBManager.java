package cn.com.chinahitech.spider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
        statement = connection.createStatement();}catch(Exception e){
            e.printStackTrace();
            }

    }

    public void addCompany(String code,String shortName, String name) throws Exception{
        statement.execute("insert into company values(\"" + code + "\",\""+shortName+"\",\""+name+"\");");
    }
    public void addBasicInfo (String name, String industry, String legalRepresentative,String phone,String address,String postcode,String website)throws Exception
    {
        statement.execute("insert into CompanyBasicInfo(companyName,industry,legalRepresentative,phone,regisAdress,postcode,website) " +
                "values(\"" + name + "\",\""+industry+"\",\""+legalRepresentative+"\",\""+phone+"\",\""+address+"\",\""+postcode+"\",\""+website+"\");");
    }
    public void addSpfInfo(String companyName,String creditCode,String businessRegisCode,String regCapital,String estabDate,String companyType,String businessTerm)throws Exception{
        statement.execute("update CompanyBasicInfo set creditCode = "+creditCode+"where companyName="+companyName);
    }
    public List<String> selectCompany()throws Exception{
        ResultSet resultSet=null;
        resultSet=statement.executeQuery("select companyName from company;");
        String result;
        List<String> resultList=new ArrayList<String>();
        while(resultSet.next()){
            result=resultSet.getString("companyName");
            resultList.add(result);
        }
        return resultList;
    }

    public List<String> selectCompanyName(){
try{
        ResultSet resultSet=null;
        resultSet=statement.executeQuery("select companyName from CompanyBasicInfo;");
        String result;
        List<String> resultList=new ArrayList<String>();
        while(resultSet.next()){
            result=resultSet.getString("companyName");
            resultList.add(result);
        }
        return resultList;
}catch(Exception e){
         e.printStackTrace();
         return null;}
    }


    public List<String> selectCompanytype(){
try{
        ResultSet resultSet=null;
        resultSet=statement.executeQuery("select industry from CompanyBasicInfo;");
        String result;
        List<String> resultList=new ArrayList<String>();
        while(resultSet.next()){
            result=resultSet.getString("industry");
            resultList.add(result);
        }
        return resultList;
}catch(Exception e){
         e.printStackTrace();
         return null;}
    }

    public List<String> selectCompanyid(){
try{
        ResultSet resultSet=null;
        resultSet=statement.executeQuery("select creditCode from CompanyBasicInfo;");
        String result;
        List<String> resultList=new ArrayList<String>();
        while(resultSet.next()){
            result=resultSet.getString("creditCode");
            resultList.add(result);
        }
        return resultList;
}catch(Exception e){
         e.printStackTrace();
         return null;}
    }

    public List<String> selectregcapital(){
try{
        ResultSet resultSet=null;
        resultSet=statement.executeQuery("select regCapital from CompanyBasicInfo;");
        String result;
        List<String> resultList=new ArrayList<String>();
        while(resultSet.next()){
            result=resultSet.getString("regCapital");
            resultList.add(result);
        }
        return resultList;
}catch(Exception e){
         e.printStackTrace();
         return null;}
    }

    public String selectnetProfit(String name){
try{
        ResultSet resultSet=null;
        resultSet=statement.executeQuery("select netProfit from finance where companyName =\""+name+"\";");
    String result=null;
    while(resultSet.next()) {
         result = resultSet.getString("netProfit");
        return result;
    }
    return result;
}catch(Exception e){
         e.printStackTrace();
         return null;}
}

    public String selectnetAssets(String name){
try{
        ResultSet resultSet=null;
        resultSet=statement.executeQuery("select netAssets from finance where companyName =\""+name+"\";");
    String result=null;
    while(resultSet.next()) {
         result=resultSet.getString("netAssets");
        return result;}
    return result;
}catch(Exception e){
         e.printStackTrace();
         return null;}
}

    public List<String> selectestaDate(){
try{
        ResultSet resultSet=null;
        resultSet=statement.executeQuery("select estabDate from CompanyBasicInfo;");
        String result;
        List<String> resultList=new ArrayList<String>();
        while(resultSet.next()){
            result=resultSet.getString("estabDate");
            resultList.add(result);
        }
        return resultList;
}catch(Exception e){
         e.printStackTrace();
         return null;}
    }

    public List<String> selectbusinessTerm(){
try{
        ResultSet resultSet=null;
        resultSet=statement.executeQuery("select businessTerm from CompanyBasicInfo;");
        String result;
        List<String> resultList=new ArrayList<String>();
        while(resultSet.next()){
            result=resultSet.getString("businessTerm");
            resultList.add(result);
        }
        return resultList;
}catch(Exception e){
         e.printStackTrace();
         return null;}
    }

    public List<String> selectIp()throws Exception{
        ResultSet resultSet=null;
        resultSet=statement.executeQuery("select ip from ip_agent;");
        String result;
        List<String> resultList=new ArrayList<String>();
        while(resultSet.next()){
            result=resultSet.getString("ip");
            resultList.add(result);
        }
        return resultList;
    }

    public List<String> selectPort()throws Exception{
        ResultSet resultSet=null;
        resultSet=statement.executeQuery("select port from ip_agent;");
        String result;
        List<String> resultList=new ArrayList<String>();
        while(resultSet.next()){
            result=resultSet.getString("port");
            resultList.add(result);
        }
        return resultList;
    }

    public void addJudgement (String companyName, String title, String link,String court,String Riqi)throws Exception
    {
        statement.execute("insert into judgement_document(companyname,namelist,namelink,court,Riqi) " +
                "values(\"" + companyName + "\",\""+title+"\",\""+link+ "\",\""+court+ "\",\""+Riqi+"\");");
    }

    public void addmining (String creditCode, String companyName, String regCapital,String estabDate,String businessTerm){
   try
    {
        statement.execute("insert into Industry_mining(creditCode,companyname,regCapital,estabDate,businessTerm) " +
                "values(\"" + creditCode + "\",\""+companyName+"\",\""+regCapital+ "\",\""+estabDate+ "\",\""+businessTerm+"\");");
    }catch(Exception e){
         e.printStackTrace();
    }
    }

    public void addfirst (String creditCode, String companyName, String regCapital,String estabDate,String businessTerm){
   try
    {
        statement.execute("insert into Industry_first(creditCode,companyname,regCapital,estabDate,businessTerm) " +
                "values(\"" + creditCode + "\",\""+companyName+"\",\""+regCapital+ "\",\""+estabDate+ "\",\""+businessTerm+"\");");
    }catch(Exception e){
         e.printStackTrace();
    }
    }

    public void addIT (String creditCode, String companyName, String regCapital,String estabDate,String businessTerm){
   try
    {
        statement.execute("insert into Industry_IT(creditCode,companyname,regCapital,estabDate,businessTerm) " +
                "values(\"" + creditCode + "\",\""+companyName+"\",\""+regCapital+ "\",\""+estabDate+ "\",\""+businessTerm+"\");");
    }catch(Exception e){
         e.printStackTrace();
    }
    }


    public void addbuilding (String creditCode, String companyName, String regCapital,String estabDate,String businessTerm){
   try
    {
        statement.execute("insert into Industry_building(creditCode,companyname,regCapital,estabDate,businessTerm) " +
                "values(\"" + creditCode + "\",\""+companyName+"\",\""+regCapital+ "\",\""+estabDate+ "\",\""+businessTerm+"\");");
    }catch(Exception e){
         e.printStackTrace();
    }
    }

    public void addlight (String creditCode, String companyName, String regCapital,String estabDate,String businessTerm){
   try
    {
        statement.execute("insert into Industry_light(creditCode,companyname,regCapital,estabDate,businessTerm) " +
                "values(\"" + creditCode + "\",\""+companyName+"\",\""+regCapital+ "\",\""+estabDate+ "\",\""+businessTerm+"\");");
    }catch(Exception e){
         e.printStackTrace();
    }
    }

    public void addheavy (String creditCode, String companyName, String regCapital,String estabDate,String businessTerm){
   try
    {
        statement.execute("insert into Industry_heavy(creditCode,companyname,regCapital,estabDate,businessTerm) " +
                "values(\"" + creditCode + "\",\""+companyName+"\",\""+regCapital+ "\",\""+estabDate+ "\",\""+businessTerm+"\");");
    }catch(Exception e){
         e.printStackTrace();
    }
    }

    public void addpublic (String creditCode, String companyName, String regCapital,String estabDate,String businessTerm){
   try
    {
        statement.execute("insert into Industry_public(creditCode,companyname,regCapital,estabDate,businessTerm) " +
                "values(\"" + creditCode + "\",\""+companyName+"\",\""+regCapital+ "\",\""+estabDate+ "\",\""+businessTerm+"\");");
    }catch(Exception e){
         e.printStackTrace();
    }
    }

    public void addservice (String creditCode, String companyName, String regCapital,String estabDate,String businessTerm){
   try
    {
        statement.execute("insert into Industry_service(creditCode,companyname,regCapital,estabDate,businessTerm) " +
                "values(\"" + creditCode + "\",\""+companyName+"\",\""+regCapital+ "\",\""+estabDate+ "\",\""+businessTerm+"\");");
    }catch(Exception e){
         e.printStackTrace();
    }
    }

    public void addscience (String creditCode, String companyName, String regCapital,String estabDate,String businessTerm){
   try
    {
        statement.execute("insert into Industry_science(creditCode,companyname,regCapital,estabDate,businessTerm) " +
                "values(\"" + creditCode + "\",\""+companyName+"\",\""+regCapital+ "\",\""+estabDate+ "\",\""+businessTerm+"\");");
    }catch(Exception e){
         e.printStackTrace();
    }
    }

    public void addenergy (String creditCode, String companyName, String regCapital,String estabDate,String businessTerm){
   try
    {
        statement.execute("insert into Industry_energy(creditCode,companyname,regCapital,estabDate,businessTerm) " +
                "values(\"" + creditCode + "\",\""+companyName+"\",\""+regCapital+ "\",\""+estabDate+ "\",\""+businessTerm+"\");");
    }catch(Exception e){
         e.printStackTrace();
    }
    }

    public void addfinancial (String creditCode, String companyName, String regCapital,String estabDate,String businessTerm){
   try
    {
        statement.execute("insert into Industry_financial(creditCode,companyname,regCapital,estabDate,businessTerm) " +
                "values(\"" + creditCode + "\",\""+companyName+"\",\""+regCapital+ "\",\""+estabDate+ "\",\""+businessTerm+"\");");
    }catch(Exception e){
         e.printStackTrace();
    }
    }

    public void addtransport (String creditCode, String companyName, String regCapital,String estabDate,String businessTerm){
   try
    {
        statement.execute("insert into Industry_transport(creditCode,companyname,regCapital,estabDate,businessTerm) " +
                "values(\"" + creditCode + "\",\""+companyName+"\",\""+regCapital+ "\",\""+estabDate+ "\",\""+businessTerm+"\");");
    }catch(Exception e){
         e.printStackTrace();
    }
    }

    public void updatetransport (String companyName, String netProfit,String netAssets){
   try
    {
        statement.execute("update Industry_transport set netProfit=\""+netProfit+"\"where companyName=\""+companyName+"\";");
        statement.execute("update Industry_transport set netAssets=\""+netAssets+"\"where companyName=\""+companyName+"\";");
    }catch(Exception e){
         e.printStackTrace();
    }
    }


    public void updateservice (String companyName, String netProfit,String netAssets){
   try
    {
        statement.execute("update Industry_service set netProfit=\""+netProfit+"\"where companyName=\""+companyName+"\";");
        statement.execute("update Industry_service set netAssets=\""+netAssets+"\"where companyName=\""+companyName+"\";");
    }catch(Exception e){
         e.printStackTrace();
    }
    }

    public void updatescience (String companyName, String netProfit,String netAssets){
   try
    {
        statement.execute("update Industry_science set netProfit=\""+netProfit+"\"where companyName=\""+companyName+"\";");
        statement.execute("update Industry_science set netAssets=\""+netAssets+"\"where companyName=\""+companyName+"\";");
    }catch(Exception e){
         e.printStackTrace();
    }
    }

    public void updatepublic (String companyName, String netProfit,String netAssets){
   try
    {
        statement.execute("update Industry_public set netProfit=\""+netProfit+"\"where companyName=\""+companyName+"\";");
        statement.execute("update Industry_public set netAssets=\""+netAssets+"\"where companyName=\""+companyName+"\";");
    }catch(Exception e){
         e.printStackTrace();
    }
    }

    public void updatemining (String companyName, String netProfit,String netAssets){
   try
    {
        statement.execute("update Industry_mining set netProfit=\""+netProfit+"\"where companyName=\""+companyName+"\";");
        statement.execute("update Industry_mining set netAssets=\""+netAssets+"\"where companyName=\""+companyName+"\";");
    }catch(Exception e){
         e.printStackTrace();
    }
    }

    public void updatelight (String companyName, String netProfit,String netAssets){
   try
    {
        statement.execute("update Industry_light set netProfit=\""+netProfit+"\"where companyName=\""+companyName+"\";");
        statement.execute("update Industry_light set netAssets=\""+netAssets+"\"where companyName=\""+companyName+"\";");
    }catch(Exception e){
         e.printStackTrace();
    }
    }

    public void updateIT (String companyName, String netProfit,String netAssets){
   try
    {
        statement.execute("update Industry_IT set netProfit=\""+netProfit+"\"where companyName=\""+companyName+"\";");
        statement.execute("update Industry_IT set netAssets=\""+netAssets+"\"where companyName=\""+companyName+"\";");
    }catch(Exception e){
         e.printStackTrace();
    }
    }

    public void updateheavy (String companyName, String netProfit,String netAssets){
   try
    {
        statement.execute("update Industry_heavy set netProfit=\""+netProfit+"\"where companyName=\""+companyName+"\";");
        statement.execute("update Industry_heavy set netAssets=\""+netAssets+"\"where companyName=\""+companyName+"\";");
    }catch(Exception e){
         e.printStackTrace();
    }
    }

    public void updatefirst (String companyName, String netProfit,String netAssets){
   try
    {
        statement.execute("update Industry_first set netProfit=\""+netProfit+"\"where companyName=\""+companyName+"\";");
        statement.execute("update Industry_first set netAssets=\""+netAssets+"\"where companyName=\""+companyName+"\";");
    }catch(Exception e){
         e.printStackTrace();
    }
    }

    public void updatefinancial (String companyName, String netProfit,String netAssets){
   try
    {
        statement.execute("update Industry_financial set netProfit=\""+netProfit+"\"where companyName=\""+companyName+"\";");
        statement.execute("update Industry_financial set netAssets=\""+netAssets+"\"where companyName=\""+companyName+"\";");
    }catch(Exception e){
         e.printStackTrace();
    }
    }

    public void updateenergy (String companyName, String netProfit,String netAssets){
   try
    {
        statement.execute("update Industry_energy set netProfit=\""+netProfit+"\"where companyName=\""+companyName+"\";");
        statement.execute("update Industry_energy set netAssets=\""+netAssets+"\"where companyName=\""+companyName+"\";");
    }catch(Exception e){
         e.printStackTrace();
    }
    }

    public void updatebuilding (String companyName, String netProfit,String netAssets){
   try
    {
        statement.execute("update Industry_building set netProfit=\""+netProfit+"\"where companyName=\""+companyName+"\";");
        statement.execute("update Industry_building set netAssets=\""+netAssets+"\"where companyName=\""+companyName+"\";");
    }catch(Exception e){
         e.printStackTrace();
    }
    }

 public void addipagent (String ip, String port)throws Exception
    {
        statement.execute("insert into ip_agent(ip,port) " +
                "values(\"" + ip + "\",\""+port+"\");");
    }

public void shutDown(){
        try {
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


public void rankmining() {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            ResultSet resultSet=statement.executeQuery("select companyName,regCapital,estabDate,netProfit,netAssets from Industry_mining;");
            List<String> nameList=new ArrayList<String>();
            List<Float>  capitalList=new ArrayList<Float>();
            List<Date> dateList= new ArrayList<Date>();
            List<Float> profitList=new ArrayList<Float>();
            List<Float> assetsList=new ArrayList<Float>();
            while(resultSet.next()) {
                if (resultSet.getString("estabDate").equals("null"))
                    continue;
                if (resultSet.getString("netProfit").equals("null"))
                    continue;
                if (resultSet.getString("netAssets").equals("null"))
                    continue;
                if (resultSet.getString("regCapital").equals("null"))
                    continue;
                Float capital = Float.parseFloat(resultSet.getString("regCapital"));
                Float profit = Float.parseFloat(resultSet.getString("netProfit"));
                Float assets = Float.parseFloat(resultSet.getString("netAssets"));
                Date date = formatter.parse(resultSet.getString("estabDate"));
                String name = resultSet.getString("companyName");
                nameList.add(name);
                capitalList.add(capital);
                profitList.add(profit);
                dateList.add(date);
                assetsList.add(assets);
            }
            for(int j=0;j<nameList.size();j++) {
                System.out.println(j+"家公司："+nameList.get(j));
                int capitalCount = 1;
                int profitCount = 1;
                int assetsCount = 1;
                int dateCount = 1;
                Statement statement1=connection.createStatement();
                ResultSet resultSet1=statement1.executeQuery("select regCapital,estabDate,netProfit,netAssets from Industry_mining;");
                while (resultSet1.next()) {
                    if(resultSet1.getString("estabDate").equals("null"))
                        continue;
                    if(resultSet1.getString("netProfit").equals("null"))
                        continue;
                    if(resultSet1.getString("netAssets").equals("null"))
                        continue;
                    if (resultSet1.getString("regCapital").equals("null"))
                        continue;
                    if (Float.parseFloat(resultSet1.getString("regCapital")) >capitalList.get(j))
                        capitalCount++;
                    Date tempDate=formatter.parse(resultSet1.getString("estabDate"));
                    if(tempDate.before(dateList.get(j)))
                        dateCount++;
                    if(Float.parseFloat(resultSet1.getString("netProfit"))>profitList.get(j))
                        profitCount++;
                    if(Float.parseFloat(resultSet1.getString("netAssets"))>assetsList.get(j))
                        assetsCount++;
                }
                statement1.execute("update Industry_mining set CapitalRank = \""+capitalCount+"\"where companyName=\""+nameList.get(j)+"\";");
                statement1.execute("update Industry_mining set estabRank = \""+dateCount+"\"where companyName=\""+nameList.get(j)+"\";");
                statement1.execute("update Industry_mining set ProfitRank = \""+profitCount+"\"where companyName=\""+nameList.get(j)+"\";");
                statement1.execute("update Industry_mining set AssetsRank = \""+assetsCount+"\"where companyName=\""+nameList.get(j)+"\";");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

public void rank(String industry) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            ResultSet resultSet=statement.executeQuery("select companyName,regCapital,estabDate,netProfit,netAssets from Industry_"+industry+";");
            List<String> nameList=new ArrayList<String>();
            List<Float>  capitalList=new ArrayList<Float>();
            List<Date> dateList= new ArrayList<Date>();
            List<Float> profitList=new ArrayList<Float>();
            List<Float> assetsList=new ArrayList<Float>();
            while(resultSet.next()) {
                if (resultSet.getString("estabDate").equals("null"))
                    continue;
                if (resultSet.getString("netProfit").equals("null"))
                    continue;
                if (resultSet.getString("netAssets").equals("null"))
                    continue;
                if (resultSet.getString("regCapital").equals("null"))
                    continue;
                Float capital = Float.parseFloat(resultSet.getString("regCapital"));
                Float profit = Float.parseFloat(resultSet.getString("netProfit"));
                Float assets = Float.parseFloat(resultSet.getString("netAssets"));
                Date date = formatter.parse(resultSet.getString("estabDate"));
                String name = resultSet.getString("companyName");
                nameList.add(name);
                capitalList.add(capital);
                profitList.add(profit);
                dateList.add(date);
                assetsList.add(assets);
            }
            for(int j=0;j<nameList.size();j++) {
                System.out.println(j+"家公司："+nameList.get(j));
                int capitalCount = 1;
                int profitCount = 1;
                int assetsCount = 1;
                int dateCount = 1;
                Statement statement1=connection.createStatement();
                ResultSet resultSet1=statement1.executeQuery("select regCapital,estabDate,netProfit,netAssets from Industry_"+industry+";");
                while (resultSet1.next()) {
                    if(resultSet1.getString("estabDate").equals("null"))
                        continue;
                    if(resultSet1.getString("netProfit").equals("null"))
                        continue;
                    if(resultSet1.getString("netAssets").equals("null"))
                        continue;
                    if (resultSet1.getString("regCapital").equals("null"))
                        continue;
                    if (Float.parseFloat(resultSet1.getString("regCapital")) >capitalList.get(j))
                        capitalCount++;
                    Date tempDate=formatter.parse(resultSet1.getString("estabDate"));
                    if(tempDate.before(dateList.get(j)))
                        dateCount++;
                    if(Float.parseFloat(resultSet1.getString("netProfit"))>profitList.get(j))
                        profitCount++;
                    if(Float.parseFloat(resultSet1.getString("netAssets"))>assetsList.get(j))
                        assetsCount++;
                }
                statement1.execute("update Industry_"+industry+" set CapitalRank = \""+capitalCount+"\"where companyName=\""+nameList.get(j)+"\";");
                statement1.execute("update Industry_"+industry+" set estabRank = \""+dateCount+"\"where companyName=\""+nameList.get(j)+"\";");
                statement1.execute("update Industry_"+industry+" set ProfitRank = \""+profitCount+"\"where companyName=\""+nameList.get(j)+"\";");
                statement1.execute("update Industry_"+industry+" set AssetsRank = \""+assetsCount+"\"where companyName=\""+nameList.get(j)+"\";");
            }

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

