package cn.com.chinahitech.spider;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import cn.com.chinahitech.spider.DBManager;


public class JudgementSpider {
    public String companyName=null;
    static String START_URL = "https://www.11315.com/newsearch?regionMc=%E9%80%89%E6%8B%A9%E5%9C%B0%E5%8C%BA&searchType=1&regionDm=&searchTypeHead=1&name=";
    private static Logger logger = LoggerFactory.getLogger(JudgementSpider.class);
    public void setName(String Cname){
        companyName=Cname;
    }

    public void requestData(String name) {

        String url = START_URL + name;
        // 1. 创建HttpClient对象，相当于浏览器对象





        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 2. 封装请求
        HttpGet httpGet = new HttpGet(url);
        System.out.println(url);
      //  HttpHost proxy = new HttpHost(ip, port);
        RequestConfig defaultConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
        httpGet.setConfig(defaultConfig);

        double d=Math.random();
        int j = (int)(d*5);
        String cookie[]=new String[5];
        cookie[0]="SESSION=NTA5YjEzNmMtZmI5Ny00YzI3LWJkNmYtMDgwNmU2ZTkyZDk0; Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595466596,1595552484,1595570390,1595725803; token=002e1fffe4eee91fc97b83450cf1f2fc19d991e888164e643c047d0fc550e95fe5cc48a743bb96dd663e1947f92b0f07c001a61064743dfb36d64c2f3c1d528b; Hm_lpvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595725848";
        cookie[1]="SESSION=YWY1ODQwZWYtYzc2ZC00YmMwLTgxMGQtMzIzY2E1OWU0ZGVl; token=c84b59d9be736dba817dd198700ae056e68dab1c1af4b75b76235085a9025e7fb366be00787807a1bd7efa68b273fe9b68ad30b97a1d510bf53e38f63d6b4c4888d7f5ad06408ea2; Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595640384,1595640405,1595726177,1595726184; Hm_lpvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595726184";
        cookie[2]="SESSION=MzY5OWI4YmItOTc3Zi00Mzk0LWIzZWMtMzNlYzU0NjQ2MDE3; token=bc7a15efa944f88ba89ccbdd4b72c393d4d593805193059404e0686e3431dfae492c619909f6be79da30e7446724e13568ad30b97a1d510bf53e38f63d6b4c48323fd032bac95539; Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595570897,1595640395,1595640430,1595726208; Hm_lpvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595726215";
        cookie[3]="SESSION=YzcyNTBmZWItZDVjYy00MzFkLTg3YzYtMDIyM2ZiNWY4ZDc2; token=01f2175f80defa6425673a6590d1af3d33a031cdcbe68a638685a26c907ae72ccfba74b70f4ca5bfb3b8a9f9b66f4f9d68ad30b97a1d510bf53e38f63d6b4c4891408c48c976202a; Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595581538,1595582227,1595726202,1595726216; Hm_lpvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595726226";
        cookie[4]="SESSION=NzU1NTVmMWEtN2ZhYS00NzhiLWExMDctYjFiMDgyMDA1N2Uw; token=01f2175f80defa6425673a6590d1af3d33a031cdcbe68a638685a26c907ae72ccfba74b70f4ca5bfb3b8a9f9b66f4f9d68ad30b97a1d510b13f24a6ca252e14bc27846977cd3e30e; Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595466264,1595466271,1595552643,1595552648; Hm_lpvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595552660";
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36");
        httpGet.setHeader("Cookie", cookie[j]);
        System.out.print("cookie id:");
        System.out.println(j);
        // 3. 执行请求
        CloseableHttpResponse response = null;
        try{
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
            String next_Url = parseHtmlRange(content);
            System.out.println("next_Url:");
            System.out.println(next_Url);
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

    private void requestData2(String next_url) {
        // 1. 创建HttpClient对象，相当于浏览器对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 2. 封装请求
        HttpGet httpGet = new HttpGet(next_url);
        double d=Math.random();
        int j = (int)(d*5);
        String cookie[]=new String[5];
        cookie[0]="SESSION=NTA5YjEzNmMtZmI5Ny00YzI3LWJkNmYtMDgwNmU2ZTkyZDk0; Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595466596,1595552484,1595570390,1595725803; token=002e1fffe4eee91fc97b83450cf1f2fc19d991e888164e643c047d0fc550e95fe5cc48a743bb96dd663e1947f92b0f07c001a61064743dfb36d64c2f3c1d528b; Hm_lpvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595725848";
        cookie[1]="SESSION=YWY1ODQwZWYtYzc2ZC00YmMwLTgxMGQtMzIzY2E1OWU0ZGVl; token=c84b59d9be736dba817dd198700ae056e68dab1c1af4b75b76235085a9025e7fb366be00787807a1bd7efa68b273fe9b68ad30b97a1d510bf53e38f63d6b4c4888d7f5ad06408ea2; Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595640384,1595640405,1595726177,1595726184; Hm_lpvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595726184";
        cookie[2]="SESSION=MzY5OWI4YmItOTc3Zi00Mzk0LWIzZWMtMzNlYzU0NjQ2MDE3; token=bc7a15efa944f88ba89ccbdd4b72c393d4d593805193059404e0686e3431dfae492c619909f6be79da30e7446724e13568ad30b97a1d510bf53e38f63d6b4c48323fd032bac95539; Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595570897,1595640395,1595640430,1595726208; Hm_lpvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595726215";
        cookie[3]="SESSION=YzcyNTBmZWItZDVjYy00MzFkLTg3YzYtMDIyM2ZiNWY4ZDc2; token=01f2175f80defa6425673a6590d1af3d33a031cdcbe68a638685a26c907ae72ccfba74b70f4ca5bfb3b8a9f9b66f4f9d68ad30b97a1d510bf53e38f63d6b4c4891408c48c976202a; Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595581538,1595582227,1595726202,1595726216; Hm_lpvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595726226";
        cookie[4]="SESSION=NzU1NTVmMWEtN2ZhYS00NzhiLWExMDctYjFiMDgyMDA1N2Uw; token=01f2175f80defa6425673a6590d1af3d33a031cdcbe68a638685a26c907ae72ccfba74b70f4ca5bfb3b8a9f9b66f4f9d68ad30b97a1d510b13f24a6ca252e14bc27846977cd3e30e; Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595466264,1595466271,1595552643,1595552648; Hm_lpvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595552660";
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36");
        httpGet.setHeader("Cookie", cookie[j]);
        System.out.print("cookie id:");
        System.out.println(j);

        //httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36");
        //httpGet.setHeader("Cookie","Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595224944,1595290104,1595325559,1595466596; token=002e1fffe4eee91fc97b83450cf1f2fc19d991e888164e643c047d0fc550e95fe5cc48a743bb96dd663e1947f92b0f07dbee2ba60f6619a8379a61f4527a068c; SESSION=MTMzYWRlNjktMzllZS00ZmYyLThhZDctYzg3ZDM0YzRlYjdm; Hm_lpvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595485297");

        // 3. 执行请求
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            // 4. 获取respnose的状态码
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() != 200) {
                return;
            }

            // 5. 获取响应数据
            HttpEntity httpEntity = response.getEntity();
            // 6. 数据格式转换
            String content = EntityUtils.toString(httpEntity);
            // 7. 数据抽取
             String next_Url=parseHtmlRange2(content);
            System.out.println("next_Url2");
            System.out.println(next_Url);
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
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36");
        httpGet.setHeader("Cookie","SESSION=MThlYjVhODMtNTg0Yi00MzUzLWJkZWYtM2U0N2VlMmFkMzE0; Hm_lvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595161964,1595205299,1595224944,1595290104; token=002e1fffe4eee91fc97b83450cf1f2fc19d991e888164e643c047d0fc550e95fe5cc48a743bb96dd663e1947f92b0f07426decbdedecea572dfe1897a97ee55f; Hm_lpvt_9aafda2b2c13a2bee5ddfdb4a72ca711=1595318044");

        // 3. 执行请求
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            // 4. 获取respnose的状态码
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() != 200) {
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
        try{
          //  db.addSpfInfo(this.companyName,tongYiXinYongDaiMa,gongShangZhuCe,zhuCeZiBen,chengLiRiQi,gongSiLeiXing,yingYeQiXian);
            Document document=Jsoup.parse(content);
            Elements elements=document.select("div.tab01").select("tbody").select("tr");
            DBManager dbManager=new DBManager();
            for(int i=1;i<elements.size();i++){
                 dbManager.addJudgement(companyName,elements.get(i).select("a").html(),"https://www.11315.com"+elements.get(i).select("a").attr("href"),elements.get(i).select("td").get(1).attr("title"),elements.get(i).select("td").get(2).html());
     //          String Namelist=elements.get(i).select("a").html();
      //         String NameLink="https://www.11315.com"+elements.get(i).select("a").attr("href");
       //        String Court=elements.get(i).select("td").get(1).attr("title");
        //       String RiQi=elements.get(i).select("td").get(2).html();
        //       System.out.println(Namelist);
         //      System.out.println(NameLink);
           //     System.out.println(Court);
          //     System.out.println(RiQi);
            }
            System.out.println("Inserting success!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String parseHtmlRange2(String content) {
        Document document=Jsoup.parse(content);
         String next=document.select("div[class=cont_lcen cont_l_v1]")
                 .select("h4[class=v1_title]")
                 .get(7)
                 .select("a")
                 .attr("href");
        return next;
        }

    private String parseHtmlRange(String content) {
        //System.out.println("parse");
        Document document = Jsoup.parse(content);
        //System.out.println(document.html());
        String next = document.select("div[id=main]>div.content").select("div[class=search_company clearfix]").first().select("h2").first().select("a").attr("href");
        return next;
    }
    public static void main(String[] args){
        try {
            DBManager dbManager = new DBManager();
            List<String>companyList=dbManager.selectCompany();


            for(int i=7314;i<companyList.size();i++) {
                System.out.print("No:");
                System.out.println(i);
                System.out.println(companyList.get(i));


              //  List<String>ipList=dbManager.selectIp();
              //  List<String>portList=dbManager.selectPort();
               // double d = Math.random();
               // int j = (int)(d*20+1);
            //    System.out.print("ip id:");
            //    System.out.println(j);
             //   System.out.print(ipList.get(j));
              //  System.out.println(Integer.parseInt(portList.get(j)));

               JudgementSpider judgementSpider = new JudgementSpider();
                judgementSpider.setName(companyList.get(i));
                judgementSpider.requestData(companyList.get(i));
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