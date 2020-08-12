package cn.com.chinahitech.spider;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
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

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Spider {
    private static int num=1;

    private static Logger logger = LoggerFactory.getLogger(Spider.class);
//  https://movie.douban.com/top250?start=25&filter=
    @Test
    public void requestData(String url){
        // 1. 创建HttpClient对象，相当于浏览器对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 2. 封装请求
        HttpGet httpGet = new HttpGet(url);

        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_3) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.5 Safari/605.1.15");

        // 3. 执行请求
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);

            // 4. 获取respnose的状态码
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() != 200){
                return;
            }

            // 5. 获取响应数据
            HttpEntity httpEntity = response.getEntity();
            // 6. 数据格式转换
            String content = EntityUtils.toString(httpEntity);
            // 7. 数据抽取
            parseHtml(content);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void requestData2(String url){
        // 1. 创建HttpClient对象，相当于浏览器对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 2. 封装请求
        HttpGet httpGet = new HttpGet(url);

        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_3) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.5 Safari/605.1.15");

        // 3. 执行请求
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);

            // 4. 获取respnose的状态码
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() != 200){
                return;
            }

            // 5. 获取响应数据
            HttpEntity httpEntity = response.getEntity();
            // 6. 数据格式转换
            String content = EntityUtils.toString(httpEntity);
            // 7. 数据抽取
            parseHtml2(content);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void parseHtml2(String content) {
        // 1. 数据封装document对象
        Document document = Jsoup.parse(content);
        // 2. jsoup选择器实现数据抽取
        Element element = document.select("div#wrapper").first();
        // 3. 数据解析
        String name = "片名："+element.select("div#content>h1>span").first().html();
        String director ="导演："+ element.select("div#info>span").first().select("span.attrs>a").html();
        String scriptWriters ="编剧："+element.select("div#info>span").get(1).select("span.attrs>a").html();
        String actors ="演员："+ element.select("div#info>span.actor>span.attrs>a").html();
        String types ="类型："+ element.select("div#info>span[property=v:genre]").html();
        String info = document.select("div#info").first().html();
        String region="制片国家/地区:";
        String language="语言:";
        Pattern patternRegion=Pattern.compile("制片国家/地区:</span> (.*)");
        Matcher matcherRegion=patternRegion.matcher(info);
        region+=matcherRegion.find()?matcherRegion.group(1):"无";
        Pattern patternLanguage=Pattern.compile("语言:</span> (.*)");
        Matcher matcherLanguage=patternLanguage.matcher(info);
        language+=matcherLanguage.find()?matcherLanguage.group(1):"无";
        String releaseDate="上映时间："+element.select("div#info>span[property=v:initialReleaseDate]").html();
        String runTime="片长："+element.select("div#info>span[property=v:runtime]").html();
        String summary=null;
        if(element.select("span.all.hidden").isEmpty()) {
            summary = element.select("span[property=v:summary]").html();
            summary = "简介："+summary.replaceAll("<br> ", "\n");
        }else {
            summary = element.select("span.all.hidden").first().html();
            summary = "简介："+summary.replaceAll("<br> ", "\n");
        }
//        System.out.println("第"+num+"部影片");
//        num++;
//        System.out.println(name);
//        System.out.println(director);
//        System.out.println(scriptWriters);
//        System.out.println(actors);
//        System.out.println(types);
//        System.out.println(region);
//        System.out.println(language);
//        System.out.println(releaseDate);
//        System.out.println(runTime);
//        System.out.println(summary);
//        System.out.println("\n");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("第").append(num++).append("部影片").append(name).append("\n").append(director).append("\n").append(scriptWriters).append("\n").append(actors).append("\n").append(types)
                .append("\n").append(region).append("\n").append(language).append("\n").append(releaseDate).append("\n").append(runTime).append("\n").
                append(summary).append("\n");
        logger.info(stringBuilder.toString());
    }

    private void parseHtml(String content) {
        // 1. 数据封装document对象
        Document document = Jsoup.parse(content);
        // 2. jsoup选择器实现数据抽取
        Elements elements = document.select("div.item");
        // 3. 数据解析
        for(Element item : elements){
//            String name = item.select("div.hd > a > span").first().html();
//            String score = item.select("div.star > span.rating_num").html();
//            String number = item.select("div.star > span").last().html();
//
//            // 4. 数据处理
//            Pattern pattern = Pattern.compile("\\d*");
//            Matcher matcher = pattern.matcher(number);
//            number = matcher.find() ? matcher.group() : "0";
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append(name).append("\t").append(score).append("\t").append(number);
//            logger.info(stringBuilder.toString());
            String next_Url = item.select("div.pic>a").attr("href");
            requestData2(next_Url);
        }
    }


    public static void main(String[] args) {
        Spider spider = new Spider();
        String START_URL=null;
        for(int i=0;i<=250;i+=25) {
            START_URL="https://movie.douban.com/top250"+"?start="+i+"&filter=";
            spider.requestData(START_URL);
        }
    }

}
