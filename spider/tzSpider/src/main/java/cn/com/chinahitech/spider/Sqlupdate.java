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

public class Sqlupdate{
    public static void main(String[] args){
        DBManager dbManager = new DBManager();
       dbManager.updatetotal("mining");
       System.out.println("update mining success!");

       dbManager.updatetotal("building");
       System.out.println("update building success!");

       dbManager.updatetotal("energy");
       System.out.println("update energy success!");

       dbManager.updatetotal("financial");
       System.out.println("update financial success!");

       dbManager.updatetotal("first");
       System.out.println("update first success!");

       dbManager.updatetotal("heavy");
       System.out.println("update heavy success!");

       dbManager.updatetotal("IT");
       System.out.println("update IT success!");

       dbManager.updatetotal("light");
       System.out.println("update light success!");
       dbManager.updatetotal("mining");
       System.out.println("update mining success!");

       dbManager.updatetotal("mining");
       System.out.println("update mining success!");

       dbManager.updatetotal("public");
       System.out.println("update public success!");

       dbManager.updatetotal("science");
       System.out.println("update science success!");

       dbManager.updatetotal("service");
       System.out.println("update service success!");

       dbManager.updatetotal("transport");
       System.out.println("update transport success!");
}
}

