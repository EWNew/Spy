package cn.com.chinahitech.spider;

import com.sun.xml.internal.ws.util.xml.CDATA;
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

public class Sqlsort{
    public static void main(String[] args){
       DBManager db=new DBManager();
      // db.rankmining();
db.rank("public");
System.out.println("public rank success!");

db.rank("science");
System.out.println("science rank success!");

db.rank("building");
System.out.println("building rank success!");

db.rank("IT");
System.out.println("IT rank success!");

db.rank("energy");
System.out.println("energy rank success!");

db.rank("transport");
System.out.println("transport rank success!");

db.rank("first");
System.out.println("first rank success!");

db.rank("financial");
System.out.println("financial rank success!");

db.rank("heavy");
System.out.println("heavy rank success!");

db.rank("light");
System.out.println("light rank success!");

db.rank("service");
System.out.println("service rank success!");


}

}

