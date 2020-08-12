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

public class Sqlclassify2{
    public static void main(String[] args){
        DBManager dbManager = new DBManager();
        List<String>companyList=dbManager.selectCompanyName();
        List<String>companytype=dbManager.selectCompanytype();
        //List<String>companyid=dbManager.selectCompanyid();
        //List<String>regcapital=dbManager.selectregcapital();
        //List<String>estaDate=dbManager.selectestaDate();
        //List<String>businessTerm=dbManager.selectbusinessTerm();

        for(int i=1;i<companyList.size();i++) {
            System.out.print("No.");
            System.out.println(i);
            String name=companyList.get(i);
            String type=companytype.get(i);
          //  String id=companyid.get(i);
          //  String reg=regcapital.get(i);
           // String date=estaDate.get(i);         
           // String term=businessTerm.get(i);
             String profit=dbManager.selectnetProfit(name);
             String assets=dbManager.selectnetAssets(name);
            System.out.println(name);
            System.out.println(type);
          //  System.out.println(id);
          //  System.out.println(reg);
           // System.out.println(date);
           // System.out.println(term);

           switch(type){
                 case "黑色金属矿采选业黑色金属冶炼和压延加工业":
                 case "有色金属矿采选业":
                 case "有色金属冶炼和压延加工业":
                 case "其他采矿业":
                 case "非金属矿采选业":
                             //采掘业;
                             dbManager.updatemining(name,profit,assets);
                             System.out.println("update mining success");
                     break;
                case "农副食品加工业":
                case "食品制造业":
                case "酒、饮料和精制茶制造业":
                case "造纸和纸制品业":
                case "通用设备制造业":
                case "文教、工美、体育和娱乐用品制造业":
                case "纺织服装、服饰业":
                case "纺织业":
                case "皮革、毛皮、羽毛及其制品和制鞋业":
                case "家具制造业":
                case "零售业":
                case "批发业":
                case "其他制造业":
                    //轻工业
                             dbManager.updatelight(name,profit,assets);
                    System.out.println("update light success");
                    break;
                case "邮政业":
                case "铁路运输业":
                case "铁路、船舶、航空航天和其他运输设备制造业":
                case "道路运输业":
                case "装卸搬运和运输代理业":
                case "航空运输业":
                case "管道运输业":
                case "水上运输业":
                    //交通运输业
                             dbManager.updatetransport(name,profit,assets);
                    System.out.println("update transport success");
                    break;
                case "软件和信息技术服务业":
                case "计算机、通信和其他电子设备制造业":
                case "互联网和相关服务":
                        // IT行业
                             dbManager.updateIT(name,profit,assets);
                    System.out.println("update IT success");
                    break;  
                case "租赁业":
                case "保险业":
                case "商务服务业":
                case "资本市场服务":
                case "货币金融服务":
                case "其他金融业":
                     //金融业
                             dbManager.updatefinancial(name,profit,assets);
                    System.out.println("update financial success");
                    break;
                case "科技推广和应用服务业":
                case "研究和试验发展":
                     //科学研究业
                             dbManager.updatescience(name,profit,assets);
                    System.out.println("update science success");
                    break;
                case "石油和天然气开采业":
                case "石油加工、炼焦和核燃料加工业":
                case "燃气生产和供应业":
                case "废弃资源综合利用业":
                case "开采辅助活动":
                case "电力、热力生产和供应业":
                   //能源行业
                             dbManager.updateenergy(name,profit,assets);
                    System.out.println("update energy success");
                     break;
                case "卫生	生态保护和环境治理业":
                case "水利管理业":
                case "公共设施管理业":
                case "社会工作":
                case "居民服务业":
                     //公共服务业
                             dbManager.updatepublic(name,profit,assets);
                    System.out.println("update public success");
                     break;
                case "金属制品业":	
                case "金属制品、机械和设备修理业":	
                case "汽车制造业":
                case "机动车、电子产品和日用产品修理业":
                case "橡胶和塑料制品业":
                case "专用设备制造业	化学纤维制造业":
                case "化学原料和化学制品制造业":
                case "仪器仪表制造业":
                case "非金属矿物制品业":
                case "电气机械和器材制造业":
                case "水的生产和供应业":
               case "医药制造业":
                    //重工业
                             dbManager.updateheavy(name,profit,assets);
                    System.out.println("update heavy success");
                    break;
                case "农业":
                case "农、林、牧、渔服务业":
                case "林业":
                case "木材加工和木、竹、藤、棕、草制品业":
                case "畜牧业":
               case "渔业":
                     //第一产业
                             dbManager.updatefirst(name,profit,assets);
                    System.out.println("update first success");
                    break;
                case "电信、广播电视和卫星传输服务":
                case "专业技术服务业":
                case "体育":
                case "教育":
                case "新闻和出版业":
                case "文化艺术业":
                case "广播、电视、电影和影视录音制作业":
                case "娱乐业":
                case "其他服务业":
                case "印刷和记录媒介复制业":
                case "餐饮业":
                case "仓储业":
                case "住宿业":
                     //服务业
                             dbManager.updateservice(name,profit,assets);
                    System.out.println("update service success");
                    break;
                case "土木工程建筑业":
                case "建筑安装业":
                case "建筑装饰和其他建筑业":
                case "房地产业":
                case "房屋建筑业":
                    //建筑业
                             dbManager.updatebuilding(name,profit,assets);
                    System.out.println("update building success");
                  break;
                default:
                   System.out.println("bug!");
}
	
	
}



}


}

