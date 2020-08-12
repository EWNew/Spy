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
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BidSpider {
    public String companyName = null;
    public void setName(String Cname) {
        companyName = Cname;
    }
    static String START_URL = "https://www.11315.com/newsearch?regionMc=选择地区&regionDm=&searchType=1&searchTypeHead=1&name=";
    private static Logger logger = LoggerFactory.getLogger(NewsSpider.class);
    static String[] COOKIE=
            {"SESSION=N2JmYWE4NjQtNjA5OC00MjUzLTg3ODYtM2ZiZTcyMWZkMTQz; token=c84b59d9be736dba817dd198700ae056e68dab1c1af4b75b76235085a9025e7fb366be00787807a1bd7efa68b273fe9b68ad30b97a1d510b1fe2de15782516c550dc5979f4e801b1; Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595377841,1595377850,1595466255,1595489530; Hm_lpvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595489530",
            "SESSION=M2MyMzk4ODQtMDI0NS00MTMzLWE2YzMtYTAxMzE4MjJkZWY5; token=bc7a15efa944f88ba89ccbdd4b72c393d4d593805193059404e0686e3431dfae492c619909f6be79da30e7446724e13568ad30b97a1d510b484f51740b008a81a97f0babacf0d848; Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595466259,1595466270,1595489576,1595489585; Hm_lpvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595489594",
            "SESSION=NTk3ZTI1NmYtYmM5OS00YTk5LWFkNmYtMjlmOTRiOTkxYTgw; token=4fe1e23a691c85000a727c537a5597f877ae5ef3aa44c9849c4e327a6ab1a320fad096e9e4429195f28c5276fe59cc0a923266595c9c1403038a4b318a179e3698ae7fc0395be507; Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595466260,1595466280,1595489569,1595489581; Hm_lpvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595489597",
            "Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595224944,1595290104,1595325559,1595466596; SESSION=YzllZTBhNjktMTQ4My00MTEwLWFlZjQtMGVkZWYzMTJjOTFj; token=002e1fffe4eee91fc97b83450cf1f2fc19d991e888164e643c047d0fc550e95fe5cc48a743bb96dd663e1947f92b0f075421275ba9fd9cafb00b2c90f53db44b; Hm_lpvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595489712",
            "Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595377803,1595377815,1595466264,1595466271; SESSION=MTc0M2Y4MGQtOTVkYy00ODE2LTk1NzgtODQ5ZGM1NjA1Zjcx; token=01f2175f80defa6425673a6590d1af3d33a031cdcbe68a638685a26c907ae72ccfba74b70f4ca5bfb3b8a9f9b66f4f9d68ad30b97a1d510b77e2b008ff2567a5080d79e70a0b4991"};

    public void requestData(String name) {
        String url = START_URL+name;
        // 1. 创建HttpClient对象，相当于浏览器对象

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 2. 封装请求
        HttpGet httpGet = new HttpGet(url);
//        System.out.println(url);
        RequestConfig defaultConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
        httpGet.setConfig(defaultConfig);
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
        RequestConfig defaultConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
        httpGet.setConfig(defaultConfig);
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
        String next_Url=document.select("div[class=cont_lcen cont_l_v1]").select("h4[class=v1_title v1_subordinate]").first().select("a").attr("href");
        return next_Url;
    }

    private void requestData3(String next_url) {
        // 1. 创建HttpClient对象，相当于浏览器对象

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 2. 封装请求
        HttpGet httpGet = new HttpGet(next_url);
//        System.out.println(next_url);
        RequestConfig defaultConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
        httpGet.setConfig(defaultConfig);
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
            List<String> Namelist=new ArrayList<String>();
            List<String> NameLink=new ArrayList<String>();
            List<String> DanWei=new ArrayList<String>();
            List<String> RiQi=new ArrayList<String>();
            Document document=Jsoup.parse(content);
            Elements elements=document.select("div.tab01").select("tbody").select("tr");
            DBManager dbManager=new DBManager();
            for(int i=1;i<elements.size();i++){
                dbManager.addBid(companyName,elements.get(i).select("a").html(),"https://www.11315.com"+elements.get(i).select("a").attr("href"),elements.get(i).select("td").get(1).attr("title"),elements.get(i).select("td").last().html());
//                Namelist.add(elements.get(i).select("a").html());
//                NameLink.add("https://www.11315.com"+elements.get(i).select("a").attr("href"));
//                DanWei.add(elements.get(i).select("td").get(1).attr("title"));
//                RiQi.add(elements.get(i).select("td").last().html());
            }
//            System.out.println(Namelist);
//            System.out.println(NameLink);
//            System.out.println(DanWei);
//            System.out.println(RiQi);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        try {
            DBManager dbManager = new DBManager();
            List<String> companyList=dbManager.selectCompany();
            for(int i=3431;i<companyList.size();i++) {
                System.out.println("第"+i+"家公司: "+companyList.get(i));
                BidSpider bidSpider = new BidSpider();
                bidSpider.setName(companyList.get(i));
                bidSpider.requestData(companyList.get(i));
                try{
                    TimeUnit.SECONDS.sleep(1);//秒
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
