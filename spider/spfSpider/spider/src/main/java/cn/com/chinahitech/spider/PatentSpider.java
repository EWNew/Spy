package cn.com.chinahitech.spider;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.StatusLine;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.ls.LSException;

import javax.print.Doc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PatentSpider {
    public String companyName = null;
    public void setName(String Cname) {
        companyName = Cname;
    }
    static String START_URL = "https://www.11315.com/newsearch?regionMc=选择地区&regionDm=&searchType=1&searchTypeHead=1&name=";
    private static Logger logger = LoggerFactory.getLogger(NewsSpider.class);
    static String[] COOKIE={"SESSION=Mjg0ZmMyNzItMjZiMC00OTM1LThhM2YtNWJlMjg5M2NlMWY0; token=c84b59d9be736dba817dd198700ae056e68dab1c1af4b75b76235085a9025e7fb366be00787807a1bd7efa68b273fe9b68ad30b97a1d510b94c18f691089a1f28ea17d9b608189eb; Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595489530,1595489538,1595552481,1595552488; Hm_lpvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595552488",
            "Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595290104,1595325559,1595466596,1595552484; token=002e1fffe4eee91fc97b83450cf1f2fc19d991e888164e643c047d0fc550e95fe5cc48a743bb96dd663e1947f92b0f07abe9aad3502d7dc04998e8bcc5fd8ac9; SESSION=YmYwYjQ5MGItOGRlNC00YWNkLTk4MjMtNjk5MTA1Y2JjM2Uz; Hm_lpvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595552545",
            "SESSION=NTJiNjI0ODktM2M1MC00ZWZjLWIzMDAtM2NkOWQ4MWUyNTU2; token=4fe1e23a691c85000a727c537a5597f877ae5ef3aa44c9849c4e327a6ab1a320fad096e9e4429195f28c5276fe59cc0a923266595c9c140334904c6e56dded06e748aa84a625ee48; Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595516764,1595516943,1595552498,1595552531; Hm_lpvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595552537",
            "SESSION=ZDU2YmMzZTUtMzU5Ni00YjdiLTk2NGMtODc4MGI2NTQ2Nzkz; token=bc7a15efa944f88ba89ccbdd4b72c393d4d593805193059404e0686e3431dfae492c619909f6be79da30e7446724e13568ad30b97a1d510b0752df5d3bf84c13d25206d722577815; Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595491763,1595493176,1595552496,1595552551; Hm_lpvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595552557",
            "SESSION=NzU1NTVmMWEtN2ZhYS00NzhiLWExMDctYjFiMDgyMDA1N2Uw; token=01f2175f80defa6425673a6590d1af3d33a031cdcbe68a638685a26c907ae72ccfba74b70f4ca5bfb3b8a9f9b66f4f9d68ad30b97a1d510b13f24a6ca252e14bc27846977cd3e30e; Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595466264,1595466271,1595552643,1595552648; Hm_lpvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595552660"};

    DBManager dbManager2=new DBManager();


    public void requestData(String name) {

        String url = START_URL + name;
        // 1. 创建HttpClient对象，相当于浏览器对象

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 2. 封装请求
        HttpGet httpGet = new HttpGet(url);
//        System.out.println(url);
//        Random random2=new Random();
//        int randnum2=random2.nextInt(20);
//        String ip=ipPool.get(randnum2);
//        int port=Integer.parseInt(portPool.get(randnum2));
//        HttpHost proxy=new HttpHost(ip,port);
//        RequestConfig defaultConfig = RequestConfig.custom().setProxy(proxy).build();
//        httpGet.setConfig(defaultConfig);

        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3766.400 QQBrowser/10.6.4163.400");
        Random random = new Random();
        int randnum = random.nextInt(5);
        System.out.println(randnum);
        httpGet.setHeader("Cookie", COOKIE[randnum]);
        // 3. 执行请求
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);

            // 4. 获取respnose的状态码
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() != 200) {
                System.out.println(response.getStatusLine());
                return;
            }

            // 5. 获取响应数据
            HttpEntity httpEntity = response.getEntity();
            // 6. 数据格式转换
            String content = EntityUtils.toString(httpEntity);
            // 7. 数据抽取
            String next_Url = parseHtmlRange(content);

            requestData2(next_Url);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private String parseHtmlRange(String content) {
        Document document = Jsoup.parse(content);
        String next_Url = document.select("div[id=main]>div.content").select("div[class=search_company clearfix]").first().select("h2").first().select("a").attr("href");
        return next_Url;
    }

    private void requestData2(String next_url) {
        // 1. 创建HttpClient对象，相当于浏览器对象

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 2. 封装请求
        HttpGet httpGet = new HttpGet(next_url);
//        System.out.println(next_url);
//        Random random2=new Random();
//        int randnum2=random2.nextInt(20);
//        String ip=ipPool.get(randnum2);
//        int port=Integer.parseInt(portPool.get(randnum2));
//        HttpHost proxy=new HttpHost(ip,port);
//        RequestConfig defaultConfig = RequestConfig.custom().setProxy(proxy).build();
//        httpGet.setConfig(defaultConfig);

        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3766.400 QQBrowser/10.6.4163.400");
        Random random=new Random();
        int randnum=random.nextInt(5);
        httpGet.setHeader("Cookie", COOKIE[randnum]);
        System.out.println(randnum);    // 3. 执行请求
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);

            // 4. 获取respnose的状态码
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() != 200) {
                System.out.println(response.getStatusLine());
                return;
            }

            // 5. 获取响应数据
            HttpEntity httpEntity = response.getEntity();
            // 6. 数据格式转换
            String content = EntityUtils.toString(httpEntity);
            // 7. 数据抽取
            String next_Url = parseHtmlRange2(content);
            requestData3(next_Url);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private String parseHtmlRange2(String content) {
        Document document=Jsoup.parse(content);
        String next_Url=document.select("div[class=cont_lcen cont_l_v1]").select("h4[class=v1_title ]").get(15).select("a").attr("href");
        return next_Url;
    }

    private void requestData3(String next_url) {
        // 1. 创建HttpClient对象，相当于浏览器对象

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 2. 封装请求
        HttpGet httpGet = new HttpGet(next_url);
//        System.out.println(next_url);
//        Random random2=new Random();
//        int randnum2=random2.nextInt(20);
//        String ip=ipPool.get(randnum2);
//        int port=Integer.parseInt(portPool.get(randnum2));
//        HttpHost proxy=new HttpHost(ip,port);
//        RequestConfig defaultConfig = RequestConfig.custom().setProxy(proxy).build();
//        httpGet.setConfig(defaultConfig);

        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3766.400 QQBrowser/10.6.4163.400");
        Random random=new Random();
        int randnum=random.nextInt(5);
        httpGet.setHeader("Cookie", COOKIE[randnum]);
        System.out.println(randnum);        // 3. 执行请求
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);

            // 4. 获取respnose的状态码
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() != 200) {
                System.out.println(response.getStatusLine());
                return;
            }

            // 5. 获取响应数据
            HttpEntity httpEntity = response.getEntity();
            // 6. 数据格式转换
            String content = EntityUtils.toString(httpEntity);
            // 7. 数据抽取
            parseHtmlRange3(content);



        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void parseHtmlRange3(String content) {
        try {
//            List<String> Namelist=new ArrayList<String>();
//            List<String> NameLink=new ArrayList<String>();
//            List<String> DangAn=new ArrayList<String>();
//            List<String> XingZhi=new ArrayList<String>();
//            List<String> RiQi=new ArrayList<String>();
            Document document=Jsoup.parse(content);
            Elements elements=document.select("div.tab01").select("tbody").select("tr");
            DBManager dbManager=new DBManager();
            for(int i=1;i<elements.size();i++){
                dbManager.addPatent(companyName,elements.get(i).select("a").html(),"https://www.11315.com"+elements.get(i).select("a").attr("href"),
                        elements.get(i).select("td").get(1).html(),elements.get(i).select("td").get(2).html(),elements.get(i).select("td").get(3).html());
//                Namelist.add(elements.get(i).select("a").html());
//                NameLink.add("https://www.11315.com"+elements.get(i).select("a").attr("href"));
//                DangAn.add(elements.get(i).select("td").get(1).html());
//                XingZhi.add(elements.get(i).select("td").get(2).html());
//                RiQi.add(elements.get(i).select("td").get(3).html());
            }
//            System.out.println(Namelist);
//            System.out.println(NameLink);
//            System.out.println(DangAn);
//            System.out.println(XingZhi);
//            System.out.println(RiQi);
            dbManager.shutDown();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        DBManager dbManager = new DBManager();
        try {
            List<String> companyList=dbManager.selectCompany();
            for(int i=6660;i<companyList.size();i++) {
                System.out.println("第"+i+"家公司: "+companyList.get(i));
                PatentSpider patentSpider = new PatentSpider();
                patentSpider.setName(companyList.get(i));
                patentSpider.requestData(companyList.get(i));
                try{
                    TimeUnit.SECONDS.sleep(1);      //秒
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            dbManager.shutDown();
        }
    }
}
