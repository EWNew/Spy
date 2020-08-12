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
import org.w3c.dom.ls.LSException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class QXBNewsSpider {
    static String START_URL = "https://www.qixin.com/";
    private static Logger logger = LoggerFactory.getLogger(NewsSpider.class);
    static String[] COOKIE=
            {"SESSION=OTU0YmIyZDAtMzNlZS00MGRiLTllOGUtMTYyMWI2MDJlNDAz; token=c84b59d9be736dba817dd198700ae056e68dab1c1af4b75b76235085a9025e7fb366be00787807a1bd7efa68b273fe9b68ad30b97a1d510becd2a752f023734a9e8e95fd500adba2; Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595317079,1595377841,1595377850,1595466255; Hm_lpvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595466263",
                    "SESSION=ZGYyYTczYzEtNzk2Yi00YjIxLWJiZTktODczN2FlZGVkNDQ3; token=bc7a15efa944f88ba89ccbdd4b72c393d4d593805193059404e0686e3431dfae492c619909f6be79da30e7446724e13568ad30b97a1d510becd2a752f023734a8ea17d9b608189eb; Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595377803,1595377811,1595466259,1595466270; Hm_lpvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595466274",
                    "SESSION=NWZjYmE3ZjYtZGU5Ny00ZDE3LTg0MmMtNWJjNjk3Y2U0MjNk; token=01f2175f80defa6425673a6590d1af3d33a031cdcbe68a638685a26c907ae72ccfba74b70f4ca5bfb3b8a9f9b66f4f9d68ad30b97a1d510becd2a752f023734a0ecc26b9c01fb23f; Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595377803,1595377815,1595466264,1595466271; Hm_lpvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595466280",
                    "SESSION=ZDFhYjI5YTMtYWJmZi00N2I5LTlmODMtYzkwY2I4YjlkYTYx; token=4fe1e23a691c85000a727c537a5597f877ae5ef3aa44c9849c4e327a6ab1a320fad096e9e4429195f28c5276fe59cc0a923266595c9c1403b280bb068cdcd33f9c3cb20df5ea7914; Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595377926,1595377948,1595466260,1595466280; Hm_lpvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595466300",
                    "SESSION=YjNkOWJkMzYtOWUxOC00NjNkLWEzM2MtM2E2ZDU1MzkyZDI2; Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595224944,1595290104,1595325559,1595466596; token=002e1fffe4eee91fc97b83450cf1f2fc19d991e888164e643c047d0fc550e95fe5cc48a743bb96dd663e1947f92b0f07dbee2ba60f6619a8379a61f4527a068c; Hm_lpvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595466613"};
    public void requestData() {

        String url = START_URL;
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
            parseHtmlRange(content);

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
    private void parseHtmlRange(String content) {
        Document document = Jsoup.parse(content);
        Elements elements = document.select("div[class=container app-home-hot-articles]").select("div.row").get(1)
        .select("div[class=margin-t-20 padding-h-0-6x]").select("div[class=col-xs-24 col-md-12]");
        List<String> titleList=new ArrayList<String>();
        List<String> titleLinkList=new ArrayList<String>();
        List<String> companyList=new ArrayList<String>();
        List<String> companyLink=new ArrayList<String>();
        List<String> imgList=new ArrayList<String>();
        for(Element element:elements){
            titleList.add(element.select("div.content-container>a").attr("title"));
            titleLinkList.add(element.select("div.content-container>a").attr("href"));
            companyList.add(element.select("div[class=margin-t-10 small related-company-container]>a").first().html());
            companyLink.add(element.select("div[class=margin-t-10 small related-company-container]>a").first().attr("href"));
            imgList.add(element.select("div[class=img-container]").attr("style").substring(22).replace("')",""));
        }
        System.out.println(titleList);
        System.out.println(titleLinkList);
        System.out.println(companyList);
        System.out.println(companyLink);
        System.out.println(imgList);
    }

    public static void main(String[] args){
        QXBNewsSpider qxbNewsSpider=new QXBNewsSpider();
        qxbNewsSpider.requestData();
    }
}
