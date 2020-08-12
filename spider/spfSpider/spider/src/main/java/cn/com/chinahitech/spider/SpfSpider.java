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
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import cn.com.chinahitech.spider.DBManager;

public class SpfSpider {
    public String companyName=null;
    static String START_URL = "https://www.qixin.com/search?key=";
    private static Logger logger = LoggerFactory.getLogger(NewsSpider.class);

    public void setName(String Cname){
        companyName=Cname;
    }

    public void requestData(String name) {

        String url = START_URL+name+"&page=1";
        // 1. 创建HttpClient对象，相当于浏览器对象

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 2. 封装请求
        HttpGet httpGet = new HttpGet(url);
        System.out.println(url);
        RequestConfig defaultConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
        httpGet.setConfig(defaultConfig);
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3766.400 QQBrowser/10.6.4163.400");
        httpGet.setHeader("Cookie","jsid=SEM-SOUGOU-PP-VIS-212505; TYCID=32271f90c0e511eab5a4bb605c70e2b0; undefined=32271f90c0e511eab5a4bb605c70e2b0; ssuid=7038476379; _ga=GA1.2.1207205759.1594190119; show_activity_id_2=2; RTYCID=83d500d1cc6e402398f11225d4d97cab; CT_TYCID=f861e13e28164a4e83d33532543ff03a; _gid=GA1.2.566135655.1594989140; aliyungf_tc=AQAAAO6lACccSQwAkQe+3sXi5JyGxkh/; csrfToken=lQ8w5itk0lggUcLD6bAqOM1l; Hm_lvt_e92c8d65d92d534b0fc290df538b4758=1594798294,1594882334,1594989140,1595038167; bannerFlag=false; tyc-user-phone=%255B%252217156203122%2522%252C%2522159%25205190%25205466%2522%255D; cloud_token=5fdf17f6b6014bfa90efcbc893cf043c; token=10761bbe0d134ef6a0308e60a2d734cd; _utm=81de7bacd0004bd6a4330f51e6d7833c; tyc-user-info=%257B%2522claimEditPoint%2522%253A%25220%2522%252C%2522contactNumber%2522%253A%252213559658708%2522%252C%2522explainPoint%2522%253A%25220%2522%252C%2522vipToMonth%2522%253A%2522false%2522%252C%2522personalClaimType%2522%253A%2522none%2522%252C%2522integrity%2522%253A%252220%2525%2522%252C%2522state%2522%253A%25224%2522%252C%2522surday%2522%253A%2522321%2522%252C%2522announcementPoint%2522%253A%25220%2522%252C%2522bidSubscribe%2522%253A%2522-1%2522%252C%2522vipManager%2522%253A%25220%2522%252C%2522monitorUnreadCount%2522%253A%25220%2522%252C%2522discussCommendCount%2522%253A%25220%2522%252C%2522onum%2522%253A%252237%2522%252C%2522showPost%2522%253Anull%252C%2522claimPoint%2522%253A%25220%2522%252C%2522token%2522%253A%2522eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNzE1NjIwMzEyMiIsImlhdCI6MTU5NTA0MjIyNywiZXhwIjoxNjI2NTc4MjI3fQ.TAVmUTmRZkr2YiLocRi12__MQ21jxQbLuNBtf1RaKYclrHBh7X2ZBaB4Bl7j8TJx7CmSjmZP3Drh77NvphIsTw%2522%252C%2522schoolAuthStatus%2522%253A%25222%2522%252C%2522vipToTime%2522%253A%25221622735999999%2522%252C%2522companyAlias%2522%253A%2522%25E4%25B8%25AD%25E9%25BE%2599%25E7%25BA%25A2%25E9%25BA%25BB%2522%252C%2522redPoint%2522%253A%25220%2522%252C%2522myTidings%2522%253A%25220%2522%252C%2522companyAuthStatus%2522%253A%25222%2522%252C%2522myAnswerCount%2522%253A%25220%2522%252C%2522myQuestionCount%2522%253A%25220%2522%252C%2522signUp%2522%253A%25220%2522%252C%2522privateMessagePointWeb%2522%253A%25220%2522%252C%2522nickname%2522%253A%2522%25E7%25BA%25A6%25E7%25BF%25B0%25C2%25B7%25E9%2581%2593%25E5%25B0%2594%25E9%25A1%25BF%2522%252C%2522privateMessagePoint%2522%253A%25220%2522%252C%2522bossStatus%2522%253A%25222%2522%252C%2522isClaim%2522%253A%25220%2522%252C%2522companyName%2522%253A%2522%25E6%25BC%25B3%25E6%25B5%25A6%25E4%25B8%25AD%25E9%25BE%2599%25E7%25BA%25A2%25E9%25BA%25BB%25E7%25B1%25BD%25E8%25B4%25B8%25E6%2598%2593%25E6%259C%2589%25E9%2599%2590%25E5%2585%25AC%25E5%258F%25B8%2522%252C%2522yellowDiamondEndTime%2522%253A%25220%2522%252C%2522isExpired%2522%253A%25220%2522%252C%2522yellowDiamondStatus%2522%253A%2522-1%2522%252C%2522companyLogo%2522%253A%2522https%253A%252F%252Fimg5.tianyancha.com%252Flogo%252Flll%252F04b7c7fd8ab8bc3ff0661cc6b286fbd8.png%2540!f_200x200%2522%252C%2522realName%2522%253A%2522%25E8%25AE%25B8%25E7%2592%2590%25E4%25BC%259F%2522%252C%2522pleaseAnswerCount%2522%253A%25220%2522%252C%2522bizCardUnread%2522%253A%25220%2522%252C%2522vnum%2522%253A%25220%2522%252C%2522companyGid%2522%253A%25221632099901%2522%252C%2522mobile%2522%253A%252217156203122%2522%257D; auth_token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNzE1NjIwMzEyMiIsImlhdCI6MTU5NTA0MjIyNywiZXhwIjoxNjI2NTc4MjI3fQ.TAVmUTmRZkr2YiLocRi12__MQ21jxQbLuNBtf1RaKYclrHBh7X2ZBaB4Bl7j8TJx7CmSjmZP3Drh77NvphIsTw; Hm_lpvt_e92c8d65d92d534b0fc290df538b4758=1595042226; _gat_gtag_UA_123487620_1=1");
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
            System.out.println(content);
            String next_Url=parseHtmlRange(content);
//            System.out.println(next_Url);
            //requestData2(next_Url);

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
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3766.400 QQBrowser/10.6.4163.400");
        httpGet.setHeader("Cookie","acw_tc=707c9fde15941895706935212e61d3705284c10d3d24a2761f4a62d4d459af; Hm_lvt_52d64b8d3f6d42a2e416d59635df3f71=1594900962,1594988420,1595037991,1595139735; cookieShowLoginTip=1; sid=s%3AjyGISSeSSucUfHf9ngX0SCHMX0QraOVy.sE35s8LWm0koGum4mbqQMQyUFZWNx3npABBH7SvtzBg; Hm_lpvt_52d64b8d3f6d42a2e416d59635df3f71=1595142485");
        //       httpGet.setHeader("Cookie","jsid=SEM-SOUGOU-PP-VIS-212505; TYCID=32271f90c0e511eab5a4bb605c70e2b0; undefined=32271f90c0e511eab5a4bb605c70e2b0; ssuid=7038476379; _ga=GA1.2.1207205759.1594190119; show_activity_id_2=2; _gid=GA1.2.566135655.1594989140; RTYCID=abf4bde2d35c47e2a2529a279c413857; CT_TYCID=51a80cec8d264e5596a48f27c0b3b6c7; aliyungf_tc=AQAAAFZQZ1ydAgIAkQe+3kicDtGgrNBX; csrfToken=odN9MJMYzh-6UckKrIABs_7Q; Hm_lvt_e92c8d65d92d534b0fc290df538b4758=1594882334,1594989140,1595038167,1595068765; cloud_token=c26a7060db13443ab2aebb1e71d72ab5; bannerFlag=false; Hm_lpvt_e92c8d65d92d534b0fc290df538b4758=1595082113; _gat_gtag_UA_123487620_1=1; token=efda27a14cb24198969ff45d1fbb8ea1; _utm=aa45c75d2bcc438597166fd6238fc949; tyc-user-info=%257B%2522claimEditPoint%2522%253A%25220%2522%252C%2522vipToMonth%2522%253A%2522false%2522%252C%2522explainPoint%2522%253A%25220%2522%252C%2522personalClaimType%2522%253A%2522none%2522%252C%2522integrity%2522%253A%252210%2525%2522%252C%2522state%2522%253A%25220%2522%252C%2522announcementPoint%2522%253A%25220%2522%252C%2522bidSubscribe%2522%253A%2522-1%2522%252C%2522vipManager%2522%253A%25220%2522%252C%2522onum%2522%253A%25220%2522%252C%2522monitorUnreadCount%2522%253A%25220%2522%252C%2522discussCommendCount%2522%253A%25220%2522%252C%2522showPost%2522%253Anull%252C%2522claimPoint%2522%253A%25220%2522%252C%2522token%2522%253A%2522eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNTk1MTkwNTQ2NiIsImlhdCI6MTU5NTA4MjE2NSwiZXhwIjoxNjI2NjE4MTY1fQ.NQqZfgRTfHZkqm1jXMj9VTdzEW2LEQPA4pruo-EOxhFq7uYWvjviu9q9d5YKId5OaOd-IK1-Smf6hea2CD_OQQ%2522%252C%2522schoolAuthStatus%2522%253A%25222%2522%252C%2522redPoint%2522%253A%25220%2522%252C%2522myTidings%2522%253A%25220%2522%252C%2522companyAuthStatus%2522%253A%25222%2522%252C%2522myAnswerCount%2522%253A%25220%2522%252C%2522myQuestionCount%2522%253A%25220%2522%252C%2522signUp%2522%253A%25220%2522%252C%2522privateMessagePointWeb%2522%253A%25220%2522%252C%2522nickname%2522%253A%2522%25E7%258F%258D%25E5%25A6%25AE%25E5%25BC%2597%25C2%25B7%25E5%258A%25A0%25E7%25BA%25B3%2522%252C%2522privateMessagePoint%2522%253A%25220%2522%252C%2522bossStatus%2522%253A%25222%2522%252C%2522isClaim%2522%253A%25220%2522%252C%2522yellowDiamondEndTime%2522%253A%25220%2522%252C%2522yellowDiamondStatus%2522%253A%2522-1%2522%252C%2522pleaseAnswerCount%2522%253A%25220%2522%252C%2522vnum%2522%253A%25220%2522%252C%2522bizCardUnread%2522%253A%25220%2522%252C%2522mobile%2522%253A%252215951905466%2522%257D; auth_token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNTk1MTkwNTQ2NiIsImlhdCI6MTU5NTA4MjE2NSwiZXhwIjoxNjI2NjE4MTY1fQ.NQqZfgRTfHZkqm1jXMj9VTdzEW2LEQPA4pruo-EOxhFq7uYWvjviu9q9d5YKId5OaOd-IK1-Smf6hea2CD_OQQ; tyc-user-phone=%255B%252215951905466%2522%252C%2522171%25205620%25203122%2522%255D");
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
            parseHtmlRange2(content);


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

    private void parseHtmlRange2(String content) {
        Document document=Jsoup.parse(content);
        Elements basic=document.select("div.detail-list").first().select("div.block-data").select("table.table.-striped-col.-border-top-none.-breakall");
        String jingYingFanWei=basic.select("tr").get(10).select("span").html();
        String zhuCeZiBen=basic.select("tr").first().select("div").first().html();
        String tongYiXinYongDaiMa=basic.select("tr").get(2).select("td").get(1).html();;
        String jingYingZhuangTai=basic.select("tr").get(1).select("td").get(3).html().split("\n")[0];
        String gongShangZhuCe=basic.select("tr").get(2).select("td").last().html();
        String chengLiRiQi=basic.select("tr").get(1).select("div[title= ]").html();
        String gongSiLeiXing=basic.select("tr").get(4).select("td").get(1).html();
        String yingYeQiXian=basic.select("tr").get(6).select("span").html();
        try{
            DBManager db=new DBManager();
            db.addSpfInfo(this.companyName,tongYiXinYongDaiMa,chengLiRiQi,gongSiLeiXing,yingYeQiXian);
            System.out.println("Inserting success!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String parseHtmlRange(String content) {
        System.out.println("parse");
        Document document = Jsoup.parse(content);
        System.out.println(document.html());
        String next="https://www.qixin.com/"+document.select("div[class=padding-h-1x border-h-b4 border-t-b4 app-list-items]").first().html();
        return next;
    }

    public static void main(String[] args){
        try {
            DBManager dbManager = new DBManager();
            List<String>companyList=dbManager.selectCompany();
            for(int i=0;i<companyList.size();i++) {
                System.out.println(companyList.get(i));
                SpfSpider spfSpider = new SpfSpider();
                spfSpider.setName(companyList.get(i));
                spfSpider.requestData(companyList.get(i));
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