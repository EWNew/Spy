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

import javax.print.Doc;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsSpider {
    static String START_URL="https://www.11315.com";
    private static Logger logger = LoggerFactory.getLogger(NewsSpider.class);
    //  https://www.11315.com/
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
            parseHtmlNews(content);
            parseHtmlPics(content);
            parseHtmlLaoLai(content);
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

    private void parseHtmlLaoLai(String content) {
        Document document=Jsoup.parse(content);
        Elements elements=document.select("div.mt10").get(2).select("ul>li");
        for(Element item:elements){
            String title=item.select("span").html();
            String company=item.select("a").attr("title");
            String link=START_URL+item.select("a").attr("href");
            System.out.println(title);
            System.out.println(company);
            System.out.println(link);
        }
    }

    private void parseHtmlPics(String content) {
        Document document=Jsoup.parse(content);
        Elements elements=document.select("div.pic").first().select("li");
        for(Element item:elements){
            String src=item.select("img").attr("src");
            String link=item.select("a").attr("href");
//            System.out.println(src);
//            System.out.println(link);
        }
    }

    private void parseHtmlNews(String content) {
        // 1. 数据封装document对象
        Document document = Jsoup.parse(content);
        // 2. jsoup选择器实现数据抽取
        Elements elements = document.select("ul.g-newul").first().select("li");
        // 3. 数据解析
        for(Element item : elements){
            String news=item.select("a").first().html();
//            System.out.println(news);
            String newsLink=START_URL+item.select("a").attr("href");
//            System.out.println(newsLink);
        }
    }

    public static void main(String[] args) {
        NewsSpider newsSpider = new NewsSpider();
        newsSpider.requestData(START_URL);
    }
}
