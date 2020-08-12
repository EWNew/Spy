package cn.com.chinahitech.spider;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.print.Doc;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import cn.com.chinahitech.spider.DBManager;


public class IpSpider {
    public String companyName=null;
    static String START_URL = "https://www.kuaidaili.com/free/inha/";
    private static Logger logger = LoggerFactory.getLogger(IpSpider.class);
    public void requestData(int i) {
        String url = START_URL + String.valueOf(i)+"/";
        System.out.println(url);
        // 1. 创建HttpClient对象，相当于浏览器对象

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 2. 封装请求
        HttpGet httpGet = new HttpGet(url);
        System.out.println(url);
        RequestConfig defaultConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
        httpGet.setConfig(defaultConfig);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36");
        httpGet.setHeader("Cookies","channelid=0; sid=1595325202381302; _ga=GA1.2.1854432789.1595325930; _gid=GA1.2.996084322.1595325930; Hm_lvt_7ed65b1cc4b810e9fd37959c9bb51b31=1595325930; Hm_lpvt_7ed65b1cc4b810e9fd37959c9bb51b31=1595398210");
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
           // System.out.println(content);
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
        //System.out.println("parse");
        Document document = Jsoup.parse(content);
        //System.out.println(document.html());
            Elements elements=document.select("div[class=body]").select("div[id=content]").select("div[id=list]").select("tbody").select("tr");
 for(int i=1;i<elements.size();i++){
        String ip = elements.get(i).select("td[data-title=IP]").html();
        String port = elements.get(i).select("td[data-title=PORT]").html();
        System.out.println(ip);
        System.out.println(port);
        Integer Port=Integer.parseInt(port);
        Boolean usable=test(ip,Port);
        System.out.println(usable);
        if(usable) {
          try{
          DBManager dbManager = new DBManager();
          dbManager.addipagent(ip,port);
          System.out.println("Insert success!");
          }catch (Exception e){
            e.printStackTrace();
          }
        }
        }
    }
    private boolean test(String ip,Integer port) {
    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip,port));
        try {
            URLConnection httpCon = new URL("https://www.baidu.com/").openConnection(proxy);
            httpCon.setConnectTimeout(5000);
            httpCon.setReadTimeout(5000);
            int code = ((HttpURLConnection) httpCon).getResponseCode();
            System.out.println(code);
            return code == 200;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
}

    public static void main(String[] args){
        try {
            //DBManager dbManager = new DBManager();
            //List<String>companyList=dbManager.selectCompany();
            for(int i=1;i<3546;i++) {
                System.out.print("No:");
                System.out.println(i);
                IpSpider ipSpider=new IpSpider();
                ipSpider.requestData(i);
                try{
                    TimeUnit.SECONDS.sleep(10);//秒
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}