package cn.com.chinahitech.spider;

import org.apache.http.HttpEntity;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class NianBaoSpider {
    public String companyName = null;
    public void setName(String Cname) {
        companyName = Cname;
    }
    static String START_URL = "https://www.11315.com/newsearch?regionMc=选择地区&regionDm=&searchType=1&searchTypeHead=1&name=";
    private static Logger logger = LoggerFactory.getLogger(NewsSpider.class);
    static String[] COOKIE=
            {"SESSION=OTU0YmIyZDAtMzNlZS00MGRiLTllOGUtMTYyMWI2MDJlNDAz; token=c84b59d9be736dba817dd198700ae056e68dab1c1af4b75b76235085a9025e7fb366be00787807a1bd7efa68b273fe9b68ad30b97a1d510becd2a752f023734a9e8e95fd500adba2; Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595317079,1595377841,1595377850,1595466255; Hm_lpvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595466263",
                    "SESSION=ZGYyYTczYzEtNzk2Yi00YjIxLWJiZTktODczN2FlZGVkNDQ3; token=bc7a15efa944f88ba89ccbdd4b72c393d4d593805193059404e0686e3431dfae492c619909f6be79da30e7446724e13568ad30b97a1d510becd2a752f023734a8ea17d9b608189eb; Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595377803,1595377811,1595466259,1595466270; Hm_lpvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595466274",
                    "SESSION=NWZjYmE3ZjYtZGU5Ny00ZDE3LTg0MmMtNWJjNjk3Y2U0MjNk; token=01f2175f80defa6425673a6590d1af3d33a031cdcbe68a638685a26c907ae72ccfba74b70f4ca5bfb3b8a9f9b66f4f9d68ad30b97a1d510becd2a752f023734a0ecc26b9c01fb23f; Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595377803,1595377815,1595466264,1595466271; Hm_lpvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595466280",
                    "SESSION=ZDFhYjI5YTMtYWJmZi00N2I5LTlmODMtYzkwY2I4YjlkYTYx; token=4fe1e23a691c85000a727c537a5597f877ae5ef3aa44c9849c4e327a6ab1a320fad096e9e4429195f28c5276fe59cc0a923266595c9c1403b280bb068cdcd33f9c3cb20df5ea7914; Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595377926,1595377948,1595466260,1595466280; Hm_lpvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595466300",
                    "SESSION=YjNkOWJkMzYtOWUxOC00NjNkLWEzM2MtM2E2ZDU1MzkyZDI2; Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595224944,1595290104,1595325559,1595466596; token=002e1fffe4eee91fc97b83450cf1f2fc19d991e888164e643c047d0fc550e95fe5cc48a743bb96dd663e1947f92b0f07dbee2ba60f6619a8379a61f4527a068c; Hm_lpvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595466613"};

    public void requestData(String name) {

        String url = START_URL + name;
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
//          System.out.println(next_Url);
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
            Document document = Jsoup.parse(content);
            List<String> nianbaoTitle = new ArrayList<String>();
            Elements Linkelements = document.select("div.tab01").first().select("tbody").select("td.taLeft").select("a");
            List<String> nianbaoLink = new ArrayList<String>();
            DBManager dbManager=new DBManager();
            for (Element element : Linkelements) {
                dbManager.addReport(companyName,element.select("a").html(),"https://www.11315.com" + element.select("a").attr("href"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String parseHtmlRange2(String content) {
        Document document=Jsoup.parse(content);
        String next_Url=document.select("div[class=cont_lcen cont_l_v1]>div.v1-business>ul>li").get(12).select("a").attr("href");
        return next_Url;
    }

    public static void main(String[] args){
        try {
            DBManager dbManager = new DBManager();
            List<String> companyList=dbManager.selectCompany();
            for(int i=6875;i<companyList.size();i++) {
                System.out.println("第"+i+"家公司: "+companyList.get(i));
                NianBaoSpider nianbaoSpider = new NianBaoSpider();
                nianbaoSpider.setName(companyList.get(i));
                nianbaoSpider.requestData(companyList.get(i));
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