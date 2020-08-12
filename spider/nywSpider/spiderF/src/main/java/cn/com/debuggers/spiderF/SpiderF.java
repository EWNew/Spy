package cn.com.debuggers.spiderF;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

public class SpiderF {


    public static void main(String[] args) {
        SpiderF spider = new SpiderF();
        spider.start();
    }

    public void start() {
        try {
            DBManager db = new DBManager();
            List<String> CompanyList = db.getCompany();
            System.out.println("Start\n...");
            for (int i = 0; i < CompanyList.size(); i++) {
                String code = CompanyList.get(i).split("\\+")[0];
                String companyName = CompanyList.get(i).split("\\+")[1];
                String url = "http://www.neeq.com.cn/nqhqController/detailCompany.do?&zqdm=" + code;
                String html = askUrl(url);
                String province = null;
                try {
                    html = html.substring(5, html.length() - 1);
                    JSONObject jsonObj = JSONObject.fromObject(html);
                    JSONObject baseinfo = jsonObj.getJSONObject("baseinfo");
                    province = baseinfo.getString("area").replace("\"", "");
//                    System.out.println(i + "\t" + code + "\t" + companyName + "\tprovince:" + province);
//                System.out.println(i + "\t" + code + "\t" + companyName + "\tincome:" + income + "\tprofit:" + profit + "\tnetProfit:" +
//                        netProfit + "\ttotalAssets:" + totalAssets + "\ttotalLiability:" + totalLiability + "\tnetAssets:" + netAssets);
                } catch (Exception e) {
                    System.out.println(i + "\t" + code + "\t" + companyName);
                    System.out.println(html);
                    e.printStackTrace();
                    System.out.println("continue ... ...");
                }
                try {
                    db.insertProvinceInfo(companyName, province);
                } catch (Exception e) {
                    System.out.println(i + "\t" + code + "\t" + companyName + "\tprovince:" + province);
                    e.printStackTrace();
                    System.out.println("continue SpiderC\t ......");
                }

            }
            db.close();
            System.out.println("Finish spiderF.");
        } catch (Exception e) {

            e.printStackTrace();
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