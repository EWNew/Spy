package cn.com.debuggers.spiderA;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Iterator;

public class SpiderA {

    public static void main(String[] args) {
        SpiderA spider = new SpiderA();
        try {
            spider.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getAll() throws Exception {
        DBManager db = new DBManager();

        for (int i = 0; i < 427; i++) {
            String html = askUrl("http://www.neeq.com.cn/nqxxController/nqxx.do?&page=" + i + "&typejb=T");
            html = html.substring(6, html.length() - 2);
            JSONObject jsonObj = JSONObject.fromObject(html);
            JSONArray s = jsonObj.getJSONArray("content");
            Iterator<JSONObject> iterator = s.iterator();
            while (iterator.hasNext()) {
                JSONObject item = iterator.next();
                //获取代码
                String code = item.getString("xxzqdm");
                System.out.println(code);
                //获取简称
                String shortName = item.getString("xxzqjc");
                System.out.println(shortName);

                //访问解析详细资料
                String nextURL = "http://www.neeq.com.cn/nqhqController/detailCompany.do?&zqdm=" + code;
                String nextHtml = askUrl(nextURL);
                nextHtml = nextHtml.substring(5, nextHtml.length() - 1);
                JSONObject jsonObj2 = JSONObject.fromObject(nextHtml);
                JSONObject baseInfo = jsonObj2.getJSONObject("baseinfo");
                String name = baseInfo.getString("name");
                System.out.println(name);
                //db.addCompany(code,shortName,name);
                String industry = baseInfo.getString("industry");
                String legalRepresentative = baseInfo.getString("legalRepresentative");
                String phone = baseInfo.getString("phone");
                String address = baseInfo.getString("address");
                String postcode = baseInfo.getString("postcode").replace("\"", "");
                String website = "null";
                try {
                    website = baseInfo.getString("website").replace("\"", "");
                } catch (Exception e) {
                    ;
                }
                System.out.println(name + industry + legalRepresentative + phone + address + postcode + website);
                db.addBasicInfo(name, industry, legalRepresentative, phone, address, postcode, website);


            }
        }

    }

    private String askUrl(String url) {
        // 1. 创建HttpClient对象，相当于浏览器对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 2. 封装请求
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36 Edg/83.0.478.61");
        // 3. 执行请求
        CloseableHttpResponse response = null;
        String content = null;
        try {
            response = httpClient.execute(httpGet);
            // 4. 获取respnose的状态码
            StatusLine statusLine = response.getStatusLine();
            //System.out.println(statusLine);
            if (statusLine.getStatusCode() != 200) {
                return "error";
            }

            // 5. 获取响应数据
            HttpEntity httpEntity = response.getEntity();
            // 6. 数据格式转换
            content = EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
/*        finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/
        //System.out.println(content);
        return content;
    }
}
