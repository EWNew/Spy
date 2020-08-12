|    |    |
|:----:|:----|
|**《** **企业大数据征信** **系统》** <br>**详细分析与设计** <br>**V** **1** **.0** |    |
|    |    |



**目   录** 


# **第一部分 引言** 
## **一、编写目的** 

编写本设计的目的是为了准确阐述 企业大数据征信系统 的具体实现思路和方法，即系统的详细架构和实现逻辑，主要包括程序系统的结构以及各层次中每个程序的设计考虑。预期读者为项目全体成员， 以及指导老师 。

 

## **二、项目背景** 


* 系统名称 ：企业大数据征信系统 
* 任务提出者：略。 
* 开发者：略。 
* 用户和运行该程序系统的计算中心：略。 





# **第二部分 项目概述** 

 基于 企业数据 的 企业大数据征信系统 ，通过构建在云端的 数据仓库储存企业的信息信用数据以及经营状况，并通过大数据分析得出整体市场数据，行业数据等等。在这些数据的基础上构建web应用服务，提供给用户进行数据检索，获取，分析等服务。 





# **第三部分 总体设计** 

## **一、技术架构设计** 

vue + springBoot + Mysql + hive架构设置，通过java爬虫程序爬取数据储存在mysql和hive数仓中，部分hive中的数据经过数据处理分析也同步到mysql中，springBoot构成后端，后端直接与myqsl数据库进行联系操作，然后为前端提供接口以与前端联系。前端基于vue构建。 



## **二、核心控制流程** 

### ![图片](https://uploader.shimo.im/f/IgvCcYKTj7mwpvqt.png!thumbnail)



## **三、** **后端SpringBoot** **环境配置** 

### **1、pom.xml** 

```xml
<?xml version="1.0" encoding="UTF-8"?> 
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd"> 
    <modelVersion>4.0.0</modelVersion> 
    <parent> 
        <groupId>org.springframework.boot</groupId> 
        <artifactId>spring-boot-starter-parent</artifactId> 
        <version>2.3.1.RELEASE</version> 
        <relativePath/> <!-- lookup parent from repository --> 
    </parent> 
    <groupId>cn.com.debuggers</groupId> 
    <artifactId>spy</artifactId> 
    <version>0.0.1-SNAPSHOT</version> 
    <name>spy</name> 
    <description>Demo project for Spring Boot</description> 
    <properties> 
        <java.version>1.8</java.version> 
    </properties> 
    <!-- 配置国内maven仓库源 --> 
    <repositories> 
        <repository> 
            <id>ali-maven</id> 
            <url>http://maven.aliyun.com/nexus/content/groups/public</url> 
            <releases> 
                <enabled>true</enabled> 
            </releases> 
            <snapshots> 
                <enabled>true</enabled> 
                <updatePolicy>always</updatePolicy> 
                <checksumPolicy>fail</checksumPolicy> 
            </snapshots> 
        </repository> 
        <repository> 
            <id>central</id> 
            <name>Maven Repository Switchboard</name> 
            <layout>default</layout> 
            <url>http://repo1.maven.org/maven2</url> 
            <snapshots> 
                <enabled>false</enabled> 
            </snapshots> 
        </repository> 
    </repositories> 
    <dependencies> 
        <!-- spring-boot-starter --> 
        <dependency> 
            <groupId>org.springframework.boot</groupId> 
            <artifactId>spring-boot-starter</artifactId> 
        </dependency> 
        <!-- spring boot web --> 
        <dependency> 
            <groupId>org.springframework.boot</groupId> 
            <artifactId>spring-boot-starter-web</artifactId> 
        </dependency> 
        <!-- spring-boot-devtools --> 
        <dependency> 
            <groupId>org.springframework.boot</groupId> 
            <artifactId>spring-boot-devtools</artifactId> 
            <scope>runtime</scope> 
            <optional>true</optional> 
        </dependency> 
        <!-- lombok 实现实体类运行时添加构造器/setter/getter --> 
        <dependency> 
            <groupId>org.projectlombok</groupId> 
            <artifactId>lombok</artifactId> 
            <optional>true</optional> 
        </dependency> 
        <!--    json object对象支持    --> 
        <dependency> 
            <groupId>net.sf.json-lib</groupId> 
            <artifactId>json-lib</artifactId> 
            <version>2.4</version> 
            <classifier>jdk15</classifier> 
        </dependency> 
        <!--   mybatis plus     --> 
        <dependency> 
            <groupId>com.baomidou</groupId> 
            <artifactId>mybatis-plus-boot-starter</artifactId> 
            <version>3.3.0</version> 
        </dependency> 
        <!--    MySQL驱动    --> 
        <dependency> 
            <groupId>mysql</groupId> 
            <artifactId>mysql-connector-java</artifactId> 
            <scope>runtime</scope> 
        </dependency> 
        <!--    数据连接池 druid    --> 
        <dependency> 
            <groupId>com.alibaba</groupId> 
            <artifactId>druid-spring-boot-starter</artifactId> 
            <version>1.1.20</version> 
        </dependency> 
        <!--    代码生成器中使用freemarker    --> 
        <dependency> 
            <groupId>org.springframework.boot</groupId> 
            <artifactId>spring-boot-starter-freemarker</artifactId> 
            <version>2.2.2.RELEASE</version> 
        </dependency> 
        <!-- springboot thymeleaf --> 
        <dependency> 
            <groupId>org.springframework.boot</groupId> 
            <artifactId>spring-boot-starter-thymeleaf</artifactId> 
            <version>2.3.1.RELEASE</version> 
        </dependency> 
        <!--    代码自动生成工具 mybatis-plus    --> 
        <dependency> 
            <groupId>com.baomidou</groupId> 
            <artifactId>mybatis-plus-generator</artifactId> 
            <version>3.3.0</version> 
        </dependency> 
        <!-- junit单元测试 --> 
        <dependency> 
            <groupId>org.springframework.boot</groupId> 
            <artifactId>spring-boot-starter-test</artifactId> 
            <scope>test</scope> 
            <exclusions> 
                <exclusion> 
                    <groupId>org.junit.vintage</groupId> 
                    <artifactId>junit-vintage-engine</artifactId> 
                </exclusion> 
            </exclusions> 
        </dependency> 
    </dependencies> 
    <!-- maven构建，实现打jar包 --> 
    <build> 
        <plugins> 
            <plugin> 
                <groupId>org.springframework.boot</groupId> 
                <artifactId>spring-boot-maven-plugin</artifactId> 
            </plugin> 
        </plugins> 
    </build> 
</project> 
```
### 2、 application.yml 

```xml
Server: 
  port: 8081 
spring: 
  application: 
    name: spy 
  aop: 
    proxy-target-class: true 
  datasource: 
    type: com.alibaba.druid.pool.DruidDataSource 
    driver-class-name: com.mysql.cj.jdbc.Driver 
    url: jdbc:mysql://121.89.181.241:3306/spy?allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false 
    username: root 
    password: Debuggers15 
    druid: 
      validation-query: SELECT 1 FROM DUAL 
      initial-size: 10 
      min-idle: 10 
      max-active: 200 
      min-evictable-idle-time-millis: 300000 
      test-on-borrow: false 
      test-while-idle: true 
      time-between-eviction-runs-millis: 30000 
      pool-prepared-statements: true 
      max-open-prepared-statements: 100 
  mybatis-plus: 
    type-aliases-package: cn.com.debuggers.spy.*.entity 
    global-config: 
      db-config: 
        id-type: auto 
        logic-delete-field: 1 
        logic-not-delete-value: 0 
    configuration: 
      map-underscore-to-camel-case: true 
      cache-enabled: false 
      jdbc-type-for-null: 'null' 
      log-impl: org.apache.ibatis.logging.stdout.StdOutImpl 
  logging: 
    config: 
      classpath: logback.xml 
  logging.level.org.springframework.baoot.autoconfigure: ERROR 
```





# **第四部分 界面设计** 

## **一、界面设计** 

### **1**. **注册界面** 

![图片](https://uploader.shimo.im/f/F0QQ3k90o0S13I3N.png!thumbnail)



### **2. 登录界面设计** 

![图片](https://uploader.shimo.im/f/y72sPuNjclpcc6zq.png!thumbnail)



### 3. 验证码界面设计

![图片](https://uploader.shimo.im/f/peTbXnSWoqsNj6E8.png!thumbnail)



### 4. 主界面界面设计

### ![图片](https://uploader.shimo.im/f/ZtImemO1IjcNywyV.png!thumbnail)

![图片](https://uploader.shimo.im/f/LMtctxEw1LbngOSL.png!thumbnail)

![图片](https://uploader.shimo.im/f/xTaysAyDCyXa6sbn.png!thumbnail)



### **5. 地图查** **界面设计** ![图片](https://uploader.shimo.im/f/IZ806fDbsDQGe40n.png!thumbnail)



### 6. 市场总览 界面设计

![图片](https://uploader.shimo.im/f/H5jvk6u1FomIP9jY.png!thumbnail)



### **7. 搜索结果界面** **设计** ![图片](https://uploader.shimo.im/f/X5HvBKoa2BG5UmVR.png!thumbnail)



### 8. 公司信息详情界面 

### ![图片](https://uploader.shimo.im/f/1QWDGYsVboByVKC9.png!thumbnail)

![图片](https://uploader.shimo.im/f/EUmbjTlaNT6PhLH5.png!thumbnail)

![图片](https://uploader.shimo.im/f/WpqADQLzl4ichIWP.png!thumbnail)

![图片](https://uploader.shimo.im/f/gMRFmS5cqrz8Ynqa.png!thumbnail)


![图片](https://uploader.shimo.im/f/IYgZS6e21vcBukB1.png!thumbnail)



### 9. 省份数据分析界面 

![图片](https://uploader.shimo.im/f/TEktk3UQiZzYp5YV.png!thumbnail)


1. 行业数据分析 

![图片](https://uploader.shimo.im/f/goCFZ9Da10qNyGxn.png!thumbnail)





# **第五部分 单元模块设计** 

## 一、总体类结构图设计 

### **1.地图查类结构图** 

![图片](https://uploader.shimo.im/f/7fE8IjDouwq1p04z.png!thumbnail)

MapController:控制类，用于进行在法眼查地图上的操作，包含获得特定城市的定位和获得圆形覆盖物的圆心与半径信息等 

MapUI：界面类，提供一系列用户操作的功能，可以刷新，添加圆形覆盖物，删除圆形覆盖物，扩大圆形覆盖物，缩小圆形覆盖物，地图的拖动滑动等 

MapManager：通信类，用于与后端服务器相通信，发送圆形覆盖物内数据 

MapDetailUI：内嵌界面类，用于显示坐标点的更详细信息 

User：用户登录者，包含用户id 

Map：地图类，基础地图提供，调用百度地图API 

![图片](https://uploader.shimo.im/f/8Xoz7kpX0nda5OuS.png!thumbnail)


### **2.** **查询公司（通过名字，信用代码）类结构图** 

 ![图片](https://uploader.shimo.im/f/b6hIVs4eFmg2XYeB.png!thumbnail)

**类功能描述：** 

SearchByNameUI：界面类，获取用户在界面输入的信息，并发送给下一个类。 

SearchByNameRequest：把web获取的信息整合通过网络发送给后端服务器，并把返回的数据显示在网页上。 

SearchByNameController：获取到用户请求后，发送给数据库访问类，等待接收返回的数据，并在处理后返还给发送请求的对象。 

SearchByNameManager：用于与数据库进行数据交流的类。 

CompanyBasicInfo：实体类，将数据封装成一个类，用于在数据传输过程中保存数据。 

DB：数据库。 

**时序图：** 

![图片](https://uploader.shimo.im/f/D5g9HKRHob0lJz8Z.png!thumbnail)

#### 

### **3.用户登陆（密码/手机验证码）类结构图** 

![图片](https://uploader.shimo.im/f/UsiuRHZMOLNrQrj3.png!thumbnail)

类功能描述： 

LoginUI：界面类，获取用户在界面输入的登陆信息，并发送给后端类。 

LoginRequest：接受前端发送的用户登陆信息并发送给LoginBiz验证登陆。 

LoginBiz：获取到登陆请求后，发送给数据库访问类进行验证，并将验证结果返回。 

LoginDAO：用于与数据库进行数据交流的类，验证账户密码是否正确并返回结果。 

DB：数据库。 

**时序图：** 

![图片](https://uploader.shimo.im/f/2suzUoOsPcJOprtM.png!thumbnail)


### **4.用户注册(手机验证码+设置密码）类结构图** 

![图片](https://uploader.shimo.im/f/v98s5UTZBXOpFIsn.png!thumbnail)

类功能描述： 

RigisterUI：界面类，获取用户在界面输入的注册信息和发送手机验证码请求，并发送给后端类。 

RigisteRequest：接受前端发送的发送手机验证码请求并调度相关api发送验证码得到正确的验证码储存。接受前端发送的用户注册信息并校验手机验证码正确后发送给RigisterBiz注册请求。 

RigisteBiz：获取到注册请求后，发送给数据库访问类进行添加用户。 

RigisteDAO：用于与数据库进行数据交流的类，进行向数据库添加用户的直接操作。 

DB：数据库。 

**时序图：** 

![图片](https://uploader.shimo.im/f/oiUL6xpYdJlXEMvb.png!thumbnail)


### **5.（新闻类）信息展示类结构图** 

![图片](https://uploader.shimo.im/f/bCkRFBnpGie96g6w.png!thumbnail)

类功能描述： 

InformationShowUI：界面类，展示相关新闻类信息标题，可以通过点击来发送请求。 

InformationShowControl：获取到点击的新闻的信息后，发送给数据库访问类，等待接收返回的数据，并在处理后返还给发送请求的对象。 

InformationShowManager：用于与数据库进行数据交流的类，从数据库获取相关新闻类详细信息。 

DB：数据库。 

时序图： 

![图片](https://uploader.shimo.im/f/6mPq1DSyIHrHjfU9.png!thumbnail)


### **6.企业综合信息展示类结构图** 

![图片](https://uploader.shimo.im/f/B3uGhoqYNVVokjuX.png!thumbnail)

类功能描述： 

dataShowUI: 界面类，展示各种信息图表 

dataShowBiz: 业务控制类，得到数据后绘制echarts等图表 

dataShowDao: 数据库访问类，从数据库得到各种数据 

DB：数据库 

时序图： 

![图片](https://uploader.shimo.im/f/BNsazNZmkDs8Ey5e.png!thumbnail)



## **二、模块接口服务设计** 

### A. 用户模块 

#### 1.注册 

1)请求方式：POST(http) 

2)请求地址/userInfo/userRegis 

3)功能描述：用户注册 

4)请求参数 


|**编号** |**参数名** |**说明** |
|:----|:----|:----|:----|:----|:----|
|1 |phone |手机号 |
|2 |name |用户名 |
|3 |pwd |密码 |


5)返回数据 

```json
{ 
    "errcode": "0", 
    "data": "18851652279 nyww 123456" 
} 
```
```plain
{ 
    "errcode": "20001", 
    "errmsg": "Regis error" 
} 
```
6)返回结果说明 

#### 2.登陆 

1) 请求方式：post（http） 

2）请求地址：/userInfo/userLogin 

3）功能描述：用户登陆 

4）请求参数 

|**编号** |**参数名** |**说明** |
|:----|:----|:----|:----|:----|:----|
|1 |name |用户名 |
|2 |pwd |密码 |

5) 返回数据 

```json
{ 
    "errcode": "0", 
    "data": "EWnb登陆成功！" 
} 
```
```json
{ 
    "errcode": "1", 
    "data": "密码错误！" 
} 
```


### B. 搜索模块 

#### 3.公司名搜索 

1)请求方式：POST(http) 

2)请求地址：/Search/nameSearch 

3)功能描述：通过检索前端发送的公司名，返回公司信息列表 

4)请求参数 


|编号 |参数名 |说明 |
|:----|:----|:----|
|1 |name |名字 |

5)返回数据 

```json
{ 
    "errcode": "0", 
    "result": [ 
        { 
            "businessTerm": "2003-06-24", 
            "companyName": "北京时代亿信科技股份有限公司", 
            "companyType": "", 
            "creditCode": "911101027521951625", 
            "estabDate": "2003-06-24", 
            "industry": "软件和信息技术服务业", 
            "legalRepresentative": "章勇", 
            "phone": "010-82055353", 
            "postcode": "北京市海淀区西直门外大街32号枫蓝国际A座804100088", 
            "regCapital": "", 
            "regisAdress": "北京市西城区新街口外大街 28 号 B 座 115 号(德胜园区)", 
            "website": "www.eetrust.com" 
        }, 
        { 
            "businessTerm": "2013-07-30", 
            "companyName": "北京时代光影文化传媒股份有限公司", 
            "companyType": "份有限公司(非上市)", 
            "creditCode": "9111010607413081XK", 
            "estabDate": "2013-07-30", 
            "industry": "广播、电视、电影和影视录音制作业", 
            "legalRepresentative": "王锦", 
            "phone": "010-85175355转8033", 
            "postcode": "北京市朝阳建外街道郎家园10号东郎电影创意产业园二期D102 邮编:100022", 
            "regCapital": "", 
            "regisAdress": "北京市朝阳区建国门外郎家园10号52幢一层01号", 
            "website": "http://www.sdgytvdrama.com/" 
        }, 
        { 
            "businessTerm": "2001-02-18", 
            "companyName": "北京时代科技股份有限公司", 
            "companyType": "其他股份有限公司(非上市)", 
            "creditCode": "91110108700243927B", 
            "estabDate": "2000-03-02", 
            "industry": "通用设备制造业", 
            "legalRepresentative": "彭伟民", 
            "phone": "010-62980823", 
            "postcode": "北京市海淀区东北旺西路尚东数字谷B区3号楼5层  100091", 
            "regCapital": "", 
            "regisAdress": "北京市海淀区上地信息产业基地开拓路17号", 
            "website": "http://www.timewelder.com" 
        }, 
        { 
            "businessTerm": "2004-09-23", 
            "companyName": "北京时代正邦科技股份有限公司", 
            "companyType": "股份有限公司(非上市、自然人投资或控股)", 
            "creditCode": "911101087675324321", 
            "estabDate": "2004-09-23", 
            "industry": "软件和信息技术服务业", 
            "legalRepresentative": "姜伟斌", 
            "phone": "010-62119336", 
            "postcode": "北京市海淀区清河西三旗东新都东站南27幢平房046。邮编:100096", 
            "regCapital": "", 
            "regisAdress": "北京市海淀区清河西三旗东新都东站南27幢平房046", 
            "website": "www.transino.net" 
        }, 
        { 
            "businessTerm": "2005-04-07", 
            "companyName": "北京时代星盟科技股份有限公司", 
            "companyType": "股份有限公司(非上市、自然人投资或控股)", 
            "creditCode": "91110108774097803W", 
            "estabDate": "2005-04-07", 
            "industry": "批发业", 
            "legalRepresentative": "李枫", 
            "phone": "010-62168288", 
            "postcode": "北京市海淀区中关村南大街甲12号寰太大厦603", 
            "regCapital": "", 
            "regisAdress": "北京市海淀区苏州街20号1号楼801", 
            "website": "www.jianbansoft.com" 
        } 
    ] 
} 
```

```json
{ 
    "errcode": "30001", 
    "errmsg": "名称查询不能为空" 
} 
```

```json
{ 
    "errcode": "30002", 
    "errmsg": "按名称查询失败" 
} 
```

```json
{ 
    "errcode": "0", 
    "result": [] 
} 
```
6)返回结果说明 

#### 4.code搜索 

1)请求方式：POST(http) 

2)请求地址：/Search/codeSearch 

3)功能描述：通过检索前端发送的信用代码，返回公司信息列表 

4)请求参数 


|编号 |参数名 |说明 |
|:----|:----|:----|
|1 |code |代码 |

5)返回数据 

```json
{ 
    "errcode": "0", 
    "result": [ 
        { 
            "businessTerm": "2000-03-14", 
            "companyName": "北京科能腾达信息技术股份有限公司", 
            "companyType": "", 
            "creditCode": "911101017214323992", 
            "estabDate": "2000-03-14", 
            "industry": "软件和信息技术服务业", 
            "legalRepresentative": "刘庆", 
            "phone": "010-82685838", 
            "postcode": "北京市石景山区实兴大街30号中关村科技园石景山区7号楼（京西科技金融大厦）15层 100041", 
            "regCapital": "", 
            "regisAdress": "北京市东城区后永康胡同 17 号 593A 室", 
            "website": "www.cngate.net" 
        } 
    ] 
} 
```
```json
{ 
    "errcode": "30003", 
    "errmsg": "查询代码不能为空" 
} 
```
```json

    "errcode": "30004", 
    "errmsg": "按代码查询失败" 
} 
```
6)返回结果说明 



### C. 公司基本信息模块 

#### 5.获得公司的基本概况信息 

1)请求方式：POST(http) 

2)请求地址：/GeneralInfo/getBasicInfo 

3)功能描述：请求某一公司的基本概况信息 

4)请求参数 


|编号 |参数名 |说明 |
|:----|:----|:----|
|1 |creditCode |企业统一信用代码(唯一标识符) |

5)返回数据 

```json
{ 
    "errcode": "0", 
    "CompanyBasicInfo": { 
        "businessTerm": "2000-03-14", 
        "companyName": "北京科能腾达信息技术股份有限公司", 
        "companyType": "", 
        "creditCode": "911101017214323992", 
        "estabDate": "2000-03-14", 
        "industry": "软件和信息技术服务业", 
        "legalRepresentative": "刘庆", 
        "phone": "010-82685838", 
        "postcode": "北京市石景山区实兴大街30号中关村科技园石景山区7号楼（京西科技金融大厦）15层 100041", 
        "regCapital": "", 
        "regisAdress": "北京市东城区后永康胡同 17 号 593A 室", 
        "website": "www.cngate.net" 
    } 
} 
```
```json
{ 
    "errcode": "40001", 
    "errmsg": "公司代码不能为空" 
} 
```
```json
{ 
    "errcode": "40002", 
    "errmsg": "公司基本信息查询错误" 
} 
```
6)返回结果说明 
#### 6.获取公司股东信息 

1)请求方式：POST(http) 

2)请求地址：/GeneralInfo/getTopTenHolders 

3)功能描述：请求获得某一公司股东信息 

4)请求参数 


|编号 |参数名 |说明 |
|:----|:----|:----|
|1 |creditCode |企业统一信用代码(唯一标识符) |


5)返回数据 

```json
{ 
    "errcode": "0", 
    "executives": [ 
        { 
            "companyName": "北京科能腾达信息技术股份有限公司", 
            "creditCode": "911101017214323992", 
            "name": "刘庆", 
            "num": "1", 
            "quantity": "11193500", 
            "ratio": "0.3377" 
        }, 
        { 
            "companyName": "北京科能腾达信息技术股份有限公司", 
            "creditCode": "911101017214323992", 
            "name": "东北证券股份有限公司", 
            "num": "2", 
            "quantity": "2583000", 
            "ratio": "0.0779" 
        }, 
        { 
            "companyName": "北京科能腾达信息技术股份有限公司", 
            "creditCode": "911101017214323992", 
            "name": "李凤云", 
            "num": "3", 
            "quantity": "2255200", 
            "ratio": "0.0680" 
        }, 
        { 
            "companyName": "北京科能腾达信息技术股份有限公司", 
            "creditCode": "911101017214323992", 
            "name": "熊瑛", 
            "num": "4", 
            "quantity": "2167100", 
            "ratio": "0.0653" 
        }, 
        { 
            "companyName": "北京科能腾达信息技术股份有限公司", 
            "creditCode": "911101017214323992", 
            "name": "谭秋雨", 
            "num": "5", 
            "quantity": "1761000", 
            "ratio": "0.0531" 
        }, 
        { 
            "companyName": "北京科能腾达信息技术股份有限公司", 
            "creditCode": "911101017214323992", 
            "name": "赵艳", 
            "num": "6", 
            "quantity": "1470902", 
            "ratio": "0.0443" 
        }, 
        { 
            "companyName": "北京科能腾达信息技术股份有限公司", 
            "creditCode": "911101017214323992", 
            "name": "宁波棠樾久鼎创新股权投资合伙企业 (有限合伙)", 
            "num": "7", 
            "quantity": "1374000", 
            "ratio": "0.0414" 
        }, 
        { 
            "companyName": "北京科能腾达信息技术股份有限公司", 
            "creditCode": "911101017214323992", 
            "name": "北京天星银河股权投资中心（有限合伙）", 
            "num": "8", 
            "quantity": "1090700", 
            "ratio": "0.0329" 
        }, 
        { 
            "companyName": "北京科能腾达信息技术股份有限公司", 
            "creditCode": "911101017214323992", 
            "name": "高永福", 
            "num": "9", 
            "quantity": "1064400", 
            "ratio": "0.0321" 
        }, 
        { 
            "companyName": "北京科能腾达信息技术股份有限公司", 
            "creditCode": "911101017214323992", 
            "name": "常磊", 
            "num": "10", 
            "quantity": "960000", 
            "ratio": "0.0289" 
        } 
    ] 
} 
```
```json
{ 
    "errcode": "40001", 
    "errmsg": "公司代码不能为空" 
} 
```
```json
{ 
    "errcode": "40003", 
    "errmsg": "公司主要股东查询错误" 
} 
```
6)返回数据说明 

#### 7.获得公司高管信息 

1)请求方式：POST(http) 

2)请求地址：/GeneralInfo/getExecutives 

3)功能描述：获取某个公司的高管信息 

4)请求参数 


|编号 |参数名 |说明 |
|:----|:----|:----|
|1 |creditCode |企业统一信用代码(唯一标识符) |


5)返回数据 

```json
{ 
    "errcode": "0", 
    "executives": [ 
        { 
            "companyName": "北京科能腾达信息技术股份有限公司", 
            "creditCode": "911101017214323992", 
            "job": "董事长、董事、总经理", 
            "name": "刘庆" 
        }, 
        { 
            "companyName": "北京科能腾达信息技术股份有限公司", 
            "creditCode": "911101017214323992", 
            "job": "董事", 
            "name": "肖驰" 
        }, 
        { 
            "companyName": "北京科能腾达信息技术股份有限公司", 
            "creditCode": "911101017214323992", 
            "job": "董事", 
            "name": "吴卫东" 
        }, 
        { 
            "companyName": "北京科能腾达信息技术股份有限公司", 
            "creditCode": "911101017214323992", 
            "job": "董事", 
            "name": "赵巍" 
        }, 
        { 
            "companyName": "北京科能腾达信息技术股份有限公司", 
            "creditCode": "911101017214323992", 
            "job": "董事", 
            "name": "杨勤涛" 
        }, 
        { 
            "companyName": "北京科能腾达信息技术股份有限公司", 
            "creditCode": "911101017214323992", 
            "job": "监事会主席", 
            "name": "郭涉洋" 
        }, 
        { 
            "companyName": "北京科能腾达信息技术股份有限公司", 
            "creditCode": "911101017214323992", 
            "job": "职工代表监事", 
            "name": "王继超" 
        }, 
        { 
            "companyName": "北京科能腾达信息技术股份有限公司", 
            "creditCode": "911101017214323992", 
            "job": "监事", 
            "name": "安芳" 
        }, 
        { 
            "companyName": "北京科能腾达信息技术股份有限公司", 
            "creditCode": "911101017214323992", 
            "job": "董事会秘书", 
            "name": "田畯" 
        } 
    ] 
} 
```
```json
{ 
    "errcode": "40001", 
    "errmsg": "公司代码不能为空" 
} 
```
```json
{ 
    "errcode": "40004", 
    "errmsg": "公司高管查询错误" 
} 
```
6)返回结果说明 

#### 8.获得公司财务指标数据 

1)请求方式：POST(http) 

2)请求地址：/GeneralInfo/getFinance 

3)功能描述：获取公司财务指标数据 

4)请求参数 



|编号 |参数名 |说明 |
|:----|:----|:----|
|1 |creditCode |企业统一信用代码(唯一标识符) |


5)返回数据 

```json
{ 
    "errcode": "0", 
    "finance": { 
        "companyName": "北京科能腾达信息技术股份有限公司", 
        "creditCode": "911101017214323992", 
        "income": "null", 
        "netAssets": "36634531.31", 
        "netProfit": "-5507001.37", 
        "profit": "-6404983.08", 
        "totalAssets": "87349733.56", 
        "totalLiability": "50715202.25" 
    } 
} 
```
```json
{ 
    "errcode": "40001", 
    "errmsg": "公司代码不能为空" 
} 
```
```json
{ 
    "errcode": "40005", 
    "errmsg": "财务指标数据查询失败" 
} 
```
6)返回数据说明 


|编号 |key |含义 |
|:----|:----|:----|
|1 |income |公司营业收入(元) |
|2 |profit |公司营业利润（元） |
|3 |netProfit |公司 净利润（元） |
|4 |totalLiability |公司 总负债（元） |
|5 |totalAssets |公司 总资产（元） |
|6 |netAssets |公司 净资产（元） |



### D. 公司主体经营信息模块 

#### 9.企业年报 anualReport 

1)请求方式：POST(http) 

2)请求地址：/MainInfo/getAnualReport 

3)功能描述：通过企业信用代码返回某一企业的年报信息 

4)请求参数 


|编号 |参数名 |说明 |
|:----|:----|:----|
|1 |creditCode |企业社会信用代码 |


5)返回数据 

```json
{ 
    "errcode": "0", 
    "annualReport": [ 
        { 
            "companyName": "新疆德康慈惠健康服务集团股份有限公司", 
            "creditCode": "916501005605186765", 
            "nianBaoTitle": "2015年度报告", 
            "nianBaoUrl": "https://www.11315.com/cii/annualjiben/1440425312080/3471e903-d501-4ef9-81e3-22ee0a6881cf" 
        }, 
        { 
            "companyName": "新疆德康慈惠健康服务集团股份有限公司", 
            "creditCode": "916501005605186765", 
            "nianBaoTitle": "2016年度报告", 
            "nianBaoUrl": "https://www.11315.com/cii/annualjiben/1440425312080/84a5fe50-dc0a-4b6b-9aca-9bda8bcc01fb" 
        }, 
        { 
            "companyName": "新疆德康慈惠健康服务集团股份有限公司", 
            "creditCode": "916501005605186765", 
            "nianBaoTitle": "2017年度报告", 
            "nianBaoUrl": "https://www.11315.com/cii/annualjiben/1440425312080/9ac6852a-d1e8-4a82-8faa-8c73fcc00e25" 
        }, 
        { 
            "companyName": "新疆德康慈惠健康服务集团股份有限公司", 
            "creditCode": "916501005605186765", 
            "nianBaoTitle": "2018年度报告", 
            "nianBaoUrl": "https://www.11315.com/cii/annualjiben/1440425312080/9d976b84-01f2-4992-afa0-e0e83d3a1309" 
        }, 
        { 
            "companyName": "新疆德康慈惠健康服务集团股份有限公司", 
            "creditCode": "916501005605186765", 
            "nianBaoTitle": "2014年度报告", 
            "nianBaoUrl": "https://www.11315.com/cii/annualjiben/1440425312080/afa9c654-45a4-49b6-9177-254cf397c74d" 
        }, 
        { 
            "companyName": "新疆德康慈惠健康服务集团股份有限公司", 
            "creditCode": "916501005605186765", 
            "nianBaoTitle": "2013年度报告", 
            "nianBaoUrl": "https://www.11315.com/cii/annualjiben/1440425312080/c2950d3c-6a94-4003-b4e6-89bfbbc8d9af" 
        }, 
        { 
            "companyName": "新疆德康慈惠健康服务集团股份有限公司", 
            "creditCode": "916501005605186765", 
            "nianBaoTitle": "2018年度报告", 
            "nianBaoUrl": "https://www.11315.com/cii/annualjiben/1440425312080/9d976b84-01f2-4992-afa0-e0e83d3a1309" 
        }, 
        { 
            "companyName": "新疆德康慈惠健康服务集团股份有限公司", 
            "creditCode": "916501005605186765", 
            "nianBaoTitle": "2017年度报告", 
            "nianBaoUrl": "https://www.11315.com/cii/annualjiben/1440425312080/9ac6852a-d1e8-4a82-8faa-8c73fcc00e25" 
        }, 
        { 
            "companyName": "新疆德康慈惠健康服务集团股份有限公司", 
            "creditCode": "916501005605186765", 
            "nianBaoTitle": "2016年度报告", 
            "nianBaoUrl": "https://www.11315.com/cii/annualjiben/1440425312080/84a5fe50-dc0a-4b6b-9aca-9bda8bcc01fb" 
        }, 
        { 
            "companyName": "新疆德康慈惠健康服务集团股份有限公司", 
            "creditCode": "916501005605186765", 
            "nianBaoTitle": "2015年度报告", 
            "nianBaoUrl": "https://www.11315.com/cii/annualjiben/1440425312080/3471e903-d501-4ef9-81e3-22ee0a6881cf" 
        }, 
        { 
            "companyName": "新疆德康慈惠健康服务集团股份有限公司", 
            "creditCode": "916501005605186765", 
            "nianBaoTitle": "2014年度报告", 
            "nianBaoUrl": "https://www.11315.com/cii/annualjiben/1440425312080/afa9c654-45a4-49b6-9177-254cf397c74d" 
        }, 
        { 
            "companyName": "新疆德康慈惠健康服务集团股份有限公司", 
            "creditCode": "916501005605186765", 
            "nianBaoTitle": "2013年度报告", 
            "nianBaoUrl": "https://www.11315.com/cii/annualjiben/1440425312080/c2950d3c-6a94-4003-b4e6-89bfbbc8d9af" 
        } 
    ] 
} 
```
```json
{ 
    "errcode": "50001", 
    "errmsg": "企业信用代码不能为空" 
} 
```
```json
{ 
    "errcode": "50002", 
    "errmsg": "查询企业年报信息失败" 
} 
```
#### 10.招标信息 bid info 

1)请求方式：POST(http) 

2)请求地址：/MainInfo/getBidInfo 

3)功能描述：获取公司招标信息 

4)请求参数 


|编号 |参数名 |说明 |
|:----|:----|:----|
|1 |creditCode |企业统一信用代码(唯一标识符) |


5)返回数据 

```json
{ 
    "errcode": "0", 
    "bidding_info": [ 
        { 
            "companyName": "新疆利丰智能科技股份有限公司", 
            "creditCode": "91650202710758326H", 
            "date": "2020-01-21", 
            "department": "中国国际招标网", 
            "link": "https://www.11315.com/ac/bid/1579622400048", 
            "title": "【竞争性磋商中标公示】克拉玛依市公安局独..." 
        }, 
        { 
            "companyName": "新疆利丰智能科技股份有限公司", 
            "creditCode": "91650202710758326H", 
            "date": "2019-07-31", 
            "department": "中国国际招标网", 
            "link": "https://www.11315.com/ac/bid/1564521600003", 
            "title": "【竞争性谈判中标公告】独山子第二中学新建..." 
        } 
    ] 
} 
```
```json
{ 
    "errcode": "50001", 
    "errmsg": "企业信用代码不能为空" 
} 
```

```json
{ 
    "errcode": "50003", 
    "errmsg": "查询企业招标信息失败" 
} 
```
6)返回数据说明 



#### 11. adminCommen获得行政表彰 

1)请求方式：POST(http) 

2)请求地址：/MainInfo/getAdminCommen 

3)功能描述：获取公司行政表彰信息 

4)请求参数 


|编号 |参数名 |说明 |
|:----|:----|:----|
|1 |creditCode |企业统一信用代码(唯一标识符) |


5)返回数据 

```json
{ 
    "errocode": "0", 
    "adminCommen": [ 
        { 
            "companyName": "北京科能腾达信息技术股份有限公司", 
            "creditCode": "911101017214323992", 
            "date": "2016-01-25", 
            "department": "全国高新技术企业认定管理工作领导小组办公室", 
            "link": "https://www.11315.com/ac/am/1484556839139", 
            "title": "2015年北京市第二批复审高新技术企业备..." 
        }, 
        { 
            "companyName": "北京科能腾达信息技术股份有限公司", 
            "creditCode": "911101017214323992", 
            "date": "2013-07-04", 
            "department": "北京市科学技术委员会", 
            "link": "https://www.11315.com/ac/am/1395039266382", 
            "title": "2013年北京市科技型中小企业技术创新资..." 
        }, 
        { 
            "companyName": "北京科能腾达信息技术股份有限公司", 
            "creditCode": "911101017214323992", 
            "date": "2012-12-13", 
            "department": "北京市科学技术委员会", 
            "link": "https://www.11315.com/ac/am/1394696243607", 
            "title": "2012年北京市第二批认定高新技术企业公..." 
        } 
    ] 
} 
```
```json
{ 
    "errcode": "50001", 
    "errmsg": "企业信用代码不能为空" 
} 
```

```json
{ 
    "errcode": "50001", 
    "errmsg": "企业信用代码不能为空" 
} 
```
```json
{ 
    "errcode": "50004", 
    "errmsg": "查询企业行政表彰错误" 
} 
```
6返回数据说明 

#### 12.branchInfo 分支机构信息 

1)请求方式：POST(http) 

2)请求地址：/MainInfo/getBranchInfo 

3)功能描述：获取公司分支机构信息 

4)请求参数 


|编号 |参数名 |说明 |
|:----|:----|:----|
|1 |creditCode |企业统一信用代码(唯一标识符) |


5)返回数据 

```json
{ 
    "errcode": "0", 
    "branchInfo": [ 
        { 
            "companyName": "新疆锦棉种业科技股份有限公司", 
            "creditCode": "91654000754573753K", 
            "department": "塔城地区乌苏市工商行政管理局", 
            "link": "https://www.11315.comhttps://60583779.11315.com", 
            "title": "新疆锦棉种业科技股份有限公司良种加工二厂" 
        }, 
        { 
            "companyName": "新疆锦棉种业科技股份有限公司", 
            "creditCode": "91654000754573753K", 
            "department": "塔城地区乌苏市工商行政管理局", 
            "link": "https://www.11315.comhttps://59687246.11315.com", 
            "title": "新疆锦棉种业科技股份有限公司良种加工一厂" 
        } 
    ] 
} 
```

```json
{ 
    "errcode": "50001", 
    "errmsg": "企业信用代码不能为空" 
} 
```

```json
{ 
    "errcode": "50005", 
    "errmsg": "查询企业分支机构信息错误" 
} 
```

6.返回结构说明 

#### 13.patent info 专利信息 

1)请求方式：POST(http) 

2)请求地址：/MainInfo/getPatentInfo 

3)功能描述：获取公司专利信息 

4)请求参数 


|编号 |参数名 |说明 |
|:----|:----|:----|
|1 |creditCode |企业统一信用代码(唯一标识符) |


5)返回数据 

```json
{ 
    "errcode": "0", 
    "patent_info": [ 
        { 
            "classify": "专利权", 
            "companyName": "北京中标新亚节能工程股份有限公司", 
            "creditCode": "91110000101209567E", 
            "date": "2019-09-13", 
            "link": "https://www.11315.com/ac/p/1570683613498", 
            "property": "良好信息", 
            "title": "项目服务系统" 
        }, 
        { 
            "classify": "专利权", 
            "companyName": "北京中标新亚节能工程股份有限公司", 
            "creditCode": "91110000101209567E", 
            "date": "2019-09-06", 
            "link": "https://www.11315.com/ac/p/1570680003019", 
            "property": "良好信息", 
            "title": "综合调整配及信息管理系统" 
        } 
    ] 
} 
```
```json
{ 
    "errcode": "50001", 
    "errmsg": "企业信用代码不能为空" 
} 
```

```json
{ 
    "errcode": "50006", 
    "errmsg": "查询企业专利信息错误" 
} 
```
6.返回信息说明 

#### 14.法院判决信息  judgement document 

1)请求方式：POST(http) 

2)请求地址：/MainInfo/getJudgementDocument 

3)功能描述：获取公司法院判决信息 

4)请求参数 


|编号 |参数名 |说明 |
|:----|:----|:----|
|1 |creditCode |企业统一信用代码(唯一标识符) |


5)返回数据 

```json
{ 
    "errcode": "0", 
    "judgement_document": [ 
        { 
            "companyname": "西安曲江智造文化旅游产业股份有限公司", 
            "court": "重庆市第一中级人民法院", 
            "creditCode": "91610133311107868T", 
            "namelink": "https://www.11315.com/ac/v/1585893494547", 
            "namelist": "西安曲江智造文化旅游产业股份有限公司与大...", 
            "riqi": "2019-04-11" 
        }, 
        { 
            "companyname": "西安曲江智造文化旅游产业股份有限公司", 
            "court": "重庆市大足区人民法院", 
            "creditCode": "91610133311107868T", 
            "namelink": "https://www.11315.com/ac/v/1576011600286", 
            "namelist": "西安曲江智造文化旅游产业股份有限公司与大...", 
            "riqi": "2019-01-03" 
        } 
    ] 
} 
```

```json
{ 
    "errcode": "50001", 
    "errmsg": "企业信用代码不能为空" 
} 
```

```json
{ 
    "errcode": "50007", 
    "errmsg": "查询企业法院判决信息失败" 
} 
```
6.返回数据说明 

#### 15.获取行政处罚信息 adminPunish 

1)请求方式：POST(http) 

2)请求地址：/MainInfo/getAdminPunish 

3)功能描述：获取公司行政处罚信息 

4)请求参数 


|编号 |参数名 |说明 |
|:----|:----|:----|
|1 |creditCode |企业统一信用代码(唯一标识符) |


5)返回数据 

```json
{ 
    "errcode": "0", 
    "adminPunish": [ 
        { 
            "companyName": "宁夏新华物流股份有限公司", 
            "creditCode": "91640100763203439G", 
            "date": "2019-02-28", 
            "department": "国家税务总局银川经济技术开发区税务局", 
            "link": "https://www.11315.com/ac/pu/1580136297872", 
            "title": "银开税罚〔2019〕33075号" 
        }, 
        { 
            "companyName": "宁夏新华物流股份有限公司", 
            "creditCode": "91640100763203439G", 
            "date": "2019-01-29", 
            "department": "宁东能源化工基地道路运输管理局", 
            "link": "https://www.11315.com/ac/pu/1564454400968", 
            "title": "宁东运罚罚（2019）第00019号" 
        } 
    ] 
} 
```

```json
{ 
    "errcode": "50001", 
    "errmsg": "企业信用代码不能为空" 
} 
```

```json
{ 
    "errcode": "50008", 
    "errmsg": "查询企业行政处罚信息失败" 
} 
```
6.返回数据说明 



### E. 分析数据模块 

#### 16.获取省份分析数据 provinceInfo 

1)请求方式：POST(http) 

2)请求地址：/Analysis/getProvinceInfo 

3)功能描述：获取公司省份分析数据 

4)请求参数 


|编号 |参数名 |说明 |
|:----|:----|:----|
|1 |creditCode |企业统一信用代码(唯一标识符) |


5)返回数据 

```json
{ 
    "errcode": "0", 
    "provinceInfo": { 
        "assetsRank": "5", 
        "capitalRank": "22", 
        "companyName": "三羊马（重庆）物流股份有限公司", 
        "companyNumber": "104", 
        "creditCode": "915001067784797538", 
        "dateRank": "45", 
        "estabDate": "2005-09-06", 
        "netAssets": "375367161.56", 
        "netProfit": "67412047.40", 
        "profitRank": "1", 
        "province": "重庆市", 
        "regCapital": "6003.0" 
    } 
```
} 
```json
{ 
    "errcode": "60001", 
    "errmsg": "企业信用代码不能为空" 
} 
```
```json
{ 
    "errcode": "60002", 
    "errmsg": "企业省份分析信息查询失败" 
} 
```
6.返回数据说明 


#### 17.获取行业分析数据 getIndustryAnalysisInfo 

1)请求方式：POST(http) 

2)请求地址：/Analysis/getIndustryAnalysisInfo 

3)功能描述：获取公司行业分析数据 

4)请求参数 


|编号 |参数名 |说明 |
|:----|:----|:----|
|1 |creditCode |企业统一信用代码(唯一标识符) |


5)返回数据 

```json
{ 
    "errcode": "0", 
    "industry": "公告服务业", 
    "industryAnalysisInfo": { 
        "assetsrank": "42", 
        "businessTerm": "2012-04-26", 
        "capitalrank": "28", 
        "companyName": "北京北方智能科技股份有限公司", 
        "creditCode": "91110107599644557E", 
        "estabDate": "2012-04-26", 
        "estabrank": "45", 
        "netAssets": "24257826.21", 
        "netProfit": "-5351388.35", 
        "profitrank": "44", 
        "regCapital": "3600.0", 
        "total": "50" 
    } 
} 
```
```json
{ 
    "errcode": "60001", 
    "errmsg": "企业信用代码不能为空" 
} 
```
```json
{ 
    "errcode": "60003", 
    "errmsg": "企业行业分析数据查询失败" 
} 
```
```json
{ 
    "errcode": "60004", 
    "errmsg": "未找到该公司行业分析数据" 
} 
```
6.返回数据说明 



### F. 反馈意见模块 

#### 18.反馈意见 

1)请求方式：POST(http) 

2)请求地址：/Feedback/advice 

3)功能描述：反馈意见 

4)请求参数 


|编号 |参数名 |说明 |
|:----|:----|:----|
|1 |advicearea |反馈意见 |
|2 |connectionarea |反馈者联系方式 |

5)返回数据 

```json
{ 
    "errcode": "0", 
    "feedback": "success" 
} 
```
```json
{ 
    "errcode": "70002", 
    "errmsg": "反馈意见联系方式不能为空" 
} 
```
```json
{ 
    "errcode": "70001", 
    "errmsg": "反馈意见不能为空" 
} 
```
```json
{ 
    "errcode": "70003", 
    "errmsg": "反馈过程出现错误" 
} 
```
6.返回数据说明 





# **第六部分 数据库设计** 
## **spy系统数据库表整体设计** 

![图片](https://uploader.shimo.im/f/xP7G0EmaMpwUtlWJ.png!thumbnail)

## 一、账号信息 

### **1、账号信息User表结构** 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|:----|:----|
|1 |phoneNumber（主键） |VARCHAR(16) |手机号 |
|2 |userName |VARCHAR(16) |用户昵称 |
|3 |password |VARCHAR（16） |密码（已MD5加密） |
|4 |permission |int |权限（0用户，1管理员，2VIP） |


## 二、公司基本概况信息 

### **1. CompanyBasicInfo** 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|
|1 |companyName |VARCHAR(60) |公司名 |
|2 |creditCode |VARCHAR(60) |统一社会信用代码 |
|3 |industry |VARCHAR(60) |行业 |
|4 |regisAdress |VARCHAR(60) |注册地址 |
|5 |regCapital |VARCHAR(60) |注册资本 |
|6 |estabDate |DATE() |成立日期 |
|7 |website |VARCHAR(120) |公司网址 |
|8 |postcode |VARCHAR(255) |邮政编码 |
|9 |legalRepresentative |VARCHAR(60) |法人 |
|10 |phone |VARCHAR(60) |公司电话 |
|11 |companyType |VARCHAR(60) |公司类型 |
|12 |businessTerm |VARCHAR(60) |营业期限 |

### **2. topTenHolders 主要股东** 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|
|1 |creditCode |VARCHAR(60) |统一社会信用代码 |
|2 |companyName |VARCHAR(60) |公司名 |
|3 |num |VARCHAR(10) |股东编号(排名) |
|4 |name |VARCHAR(60) |股东名字 |
|5 |quantity |VARCHAR(60) |持股数 |
|6 |ratio |VARCHAR(60) |持股比例 |

### **3. executives 公司高管** 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|
|1 |creditCode |VARCHAR(60) |统一社会信用代码 |
|2 |companyName |VARCHAR(60) |公司名 |
|4 |name |VARCHAR(60) |股东名字 |
|5 |job |VARCHAR(60) |职位 |

### **4. finance 财务指标** 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|
|1 |creditCode |VARCHAR(60) |统一社会信用代码 |
|2 |companyName |VARCHAR(60) |公司名 |
|3 |income |VARCHAR(40) |营业收入（元） |
|4 |profit |VARCHAR(40) |营业利润（元） |
|5 |netProfit |VARCHAR(40) |净利润（元） |
|6 |totalAssets |VARCHAR(40) |总资产（元） |
|7 |totalLiability |VARCHAR(40) |总负债（元） |
|8 |netAssets |VARCHAR(40) |净资产（元） |


## 三、公司主体状况信息 

### **1. 行政处罚  adminPunish** 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|
|1 |creditCode |VARCHAR(60) |统一社会信用代码 |
|2 |companyName |VARCHAR(60) |公司名 |
|3 |title |VARCHAR(60) |处罚文档名 |
|4 |link |VARCHAR(60) |文档链接 |
|5 |department |VARCHAR(60) |单位 |
|6 |date |VARCHAR（16） |处罚日期 |

### 2.行政表彰 **adminCommen** 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|
|1 |creditCode |VARCHAR(60) |统一社会信用代码 |
|2 |companyName |VARCHAR(60) |公司名 |
|3 |title |VARCHAR(60) |标题 |
|4 |link |VARCHAR(60) |文档链接 |
|5 |department |VARCHAR(60) |单位 |
|6 |date |VARCHAR（16） |处罚日期 |

### 3. 企业年报 anualReport 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|
|1 |creditCode |VARCHAR(60) |统一社会信用代码 |
|2 |companyName |VARCHAR(60) |公司名 |
|3 |nianBaoTitle |VARCHAR(60) |年报标题 |
|4 |nianBaoUrl |VARCHAR(60) |年报链接 |

### **4.招投标 bidInfo** 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|
|1 |creditCode |VARCHAR(60) |统一社会信用代码 |
|2 |companyName |VARCHAR(60) |公司名 |
|3 |title |VARCHAR(60) |标题 |
|4 |date |DATE() |发布日期 |
|5 |department |VARCHAR(60) |发布机关 |
|6 |link |VARCHAR(60) |详细链接 |

### 5. branchInfo 分支机构 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|
|1 |creditCode |VARCHAR(60) |统一社会信用代码 |
|2 |companyName |VARCHAR(60) |公司名 |
|4 |title |VARCHAR（60） |子公司名 |
|5 |link |VARCHAR(60) |子公司链接 |
|6 |department |VARCHAR(60) |登记机关 |

### 6. patentInfo 专利信息 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|
|1 |creditCode |VARCHAR(60) |统一社会信用代码 |
|2 |companyName |VARCHAR(60) |公司名 |
|3 |title |VARCHAR(60) |标题 |
|4 |date |DATE() |日期 |
|5 |classify |VARCHAR(60) |分类 |
|6 |link |VARCHAR(60) |详细链接 |
|7 |property |VARCHAR(60) |财产 |

### 7. 法院判决信息  judgement document 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|
|1 |creditCode |VARCHAR(60) |统一社会信用代码 |
|2 |companyName |VARCHAR(60) |公司名 |
|3 |namelist |VARCHAR(255) |链接 |
|4 |namelink |VARCHAR(255) |标题 |
|5 |court |VARCHAR(60) |法院 |
|6 |Riqi |VARCHAR(60) |日期 |

### **8. 税务评级 taxRating** 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|
|1 |creditCode |VARCHAR(60) |统一社会信用代码 |
|2 |companyName |VARCHAR(60) |公司名 |
|3 |evaluationYear |YEAR() |评价年度 |
|4 |creditRating |CHAR(1) |纳税人信用级别 |
|5 |type |VARCHAR(60) |类型 |
|6 |taxpayerNumber |VARCHAR(60) |纳税人识别号 |
|7 |evaluationUnit |VARCHAR(60) |评价单位 |

### 9. 资质证书 qualification_certificate 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|
|1 |creditCode |VARCHAR(60) |统一社会信用代码 |
|2 |companyName |VARCHAR(60) |公司名 |
|3 |certificateNumber |VARCHAR(60) |证书编号 |
|4 |beginDate |DATE() |发证日期 |
|5 |endDate |DATE() |截止日期 |
|6 |type |VARCHAR(60) |证书类型 |

### **10. 相关公告  announcement_info** 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|
|1 |creditCode |VARCHAR(60) |统一社会信用代码 |
|2 |companyName |VARCHAR(60) |公司名 |
|3 |content |VARCHAR(60) |公告内容 |
|4 |date |DATE() |发布日期 |
|5 |type |VARCHAR(10) |发布类型 |
|6 |contentURL |VARCHAR(40) |公告内容文件url |

### **11. 企业研报** Enterprise_Research_Report 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|
|1 |creditCode |VARCHAR(60) |统一社会信用代码 |
|2 |companyName |VARCHAR(60) |公司名 |
|3 |content |VARCHAR(60) |研报内容 |
|4 |date |DATE() |发布日期 |
|5 |type |VARCHAR(10) |发布类型 |
|6 |contentURL |VARCHAR(40) |研报内容文件url |

### **12. 供应商  supplier_info** 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|
|1 |creditCode |VARCHAR(60) |统一社会信用代码 |
|2 |companyName |VARCHAR(60) |公司名 |
|3 |name |VARCHAR(40) |供应商名字 |
|4 |proportion |DOUBLE(4, 4) |采购占比 |
|5 |amount |DOUBLE(9, 3) |采购金额（万元） |
|6 |date |DATE() |报告期 |
|7 |source |VARCHAR(10) |数据来源 |
|8 |association |VARCHAR(10) |关联关系 |

### **13. 客户 client_info** 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|
|1 |creditCode |VARCHAR(60) |统一社会信用代码 |
|2 |companyName |VARCHAR(60) |公司名 |
|3 |name |VARCHAR(40) |客户名字 |
|4 |proportion |DOUBLE(4, 4) |销售占比 |
|5 |amount |DOUBLE(9, 3) |销售金额（万元） |
|6 |date |DATE() |报告期 |
|7 |source |VARCHAR(10) |数据来源 |
|8 |association |VARCHAR(10) |关联关系 |

### **14. 新闻舆情 news_info** 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|
|1 |creditCode |VARCHAR(60) |统一社会信用代码 |
|2 |companyName |VARCHAR(60) |公司名 |
|3 |title |VARCHAR(40) |新闻标题 |
|4 |date |DATE() |发布日期 |
|5 |source |VARCHAR(10) |新闻来源 |
|6 |linkURL |VARCHAR(40) |新闻链接URL |

### **15. investment_info表结构** 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|:----|:----|:----|
|1 |creditCode |VARCHAR(60) |统一社会信用代码 |
|2 |companyName |VARCHAR(60) |公司名 |
|3 |investCompanyName |VARCHAR(60) |对外投资的公司名 |
|4 |legalRepresentative |VARCHAR(60) |投资的公司的法定代表人 <br>|
|5 |regCapital |VARCHAR(60) |注册资本 |


## 四、数据分析信息 

### **1. 企业行业分析表** 

#### **（1）采掘业类表（Industry_mining）** 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|
|1 |creditCode |VARCHAR(60) |统一社会信用代码 |
|2 |companyName |VARCHAR(60) |公司名 |
|3 |regCapital |VARCHAR(60) |注册资本 |
|4 |estabDate |VARCHAR(60) |成立日期 |
|5 |businessTerm |VARCHAR(60) |营业期限 |
|6 |capitalRank |VARCHAR(60) |资本排名 |
|7 |dateRank |VARCHAR(60) |日期排名 |
|8 |termRank |VARCHAR(60) |期限排名 |

#### **（2）轻工业类表（Industry_light）** 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|
|1 |creditCode |VARCHAR(60) |统一社会信用代码 |
|2 |companyName |VARCHAR(60) |公司名 |
|3 |regCapital |VARCHAR(60) |注册资本 |
|4 |estabDate |VARCHAR(60) |成立日期 |
|5 |businessTerm |VARCHAR(60) |营业期限 |
|6 |capitalRank |VARCHAR(60) |资本排名 |
|7 |dateRank |VARCHAR(60) |日期排名 |
|8 |termRank |VARCHAR(60) |期限排名 |

#### **（3）交通运输业类表（Industry_transport）** 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|
|1 |creditCode |VARCHAR(60) |统一社会信用代码 |
|2 |companyName |VARCHAR(60) |公司名 |
|3 |regCapital |VARCHAR(60) |注册资本 |
|4 |estabDate |VARCHAR(60) |成立日期 |
|5 |businessTerm |VARCHAR(60) |营业期限 |
|6 |capitalRank |VARCHAR(60) |资本排名 |
|7 |dateRank |VARCHAR(60) |日期排名 |
|8 |termRank |VARCHAR(60) |期限排名 |

#### **（4）IT行业类表（Industry_IT）** 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|
|1 |creditCode |VARCHAR(60) |统一社会信用代码 |
|2 |companyName |VARCHAR(60) |公司名 |
|3 |regCapital |VARCHAR(60) |注册资本 |
|4 |estabDate |VARCHAR(60) |成立日期 |
|5 |businessTerm |VARCHAR(60) |营业期限 |
|6 |capitalRank |VARCHAR(60) |资本排名 |
|7 |dateRank |VARCHAR(60) |日期排名 |
|8 |termRank |VARCHAR(60) |期限排名 |

#### **（5）金融业类表（Industry_financial）** 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|
|1 |creditCode |VARCHAR(60) |统一社会信用代码 |
|2 |companyName |VARCHAR(60) |公司名 |
|3 |regCapital |VARCHAR(60) |注册资本 |
|4 |estabDate |VARCHAR(60) |成立日期 |
|5 |businessTerm |VARCHAR(60) |营业期限 |
|6 |capitalRank |VARCHAR(60) |资本排名 |
|7 |dateRank |VARCHAR(60) |日期排名 |
|8 |termRank |VARCHAR(60) |期限排名 |

#### **（6）科学研究业类表（Industry_science）** 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|
|1 |creditCode |VARCHAR(60) |统一社会信用代码 |
|2 |companyName |VARCHAR(60) |公司名 |
|3 |regCapital |VARCHAR(60) |注册资本 |
|4 |estabDate |VARCHAR(60) |成立日期 |
|5 |businessTerm |VARCHAR(60) |营业期限 |
|6 |capitalRank |VARCHAR(60) |资本排名 |
|7 |dateRank |VARCHAR(60) |日期排名 |
|8 |termRank |VARCHAR(60) |期限排名 |

#### **（7）能源业类表（Industry_energy）** 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|
|1 |creditCode |VARCHAR(60) |统一社会信用代码 |
|2 |companyName |VARCHAR(60) |公司名 |
|3 |regCapital |VARCHAR(60) |注册资本 |
|4 |estabDate |VARCHAR(60) |成立日期 |
|5 |businessTerm |VARCHAR(60) |营业期限 |
|6 |capitalRank |VARCHAR(60) |资本排名 |
|7 |dateRank |VARCHAR(60) |日期排名 |
|8 |termRank |VARCHAR(60) |期限排名 |

#### **（8）公共服务业类表（Industry_public）** 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|
|1 |creditCode |VARCHAR(60) |统一社会信用代码 |
|2 |companyName |VARCHAR(60) |公司名 |
|3 |regCapital |VARCHAR(60) |注册资本 |
|4 |estabDate |VARCHAR(60) |成立日期 |
|5 |businessTerm |VARCHAR(60) |营业期限 |
|6 |capitalRank |VARCHAR(60) |资本排名 |
|7 |dateRank |VARCHAR(60) |日期排名 |
|8 |termRank |VARCHAR(60) |期限排名 |

#### **（9）重工业类表（Industry_heavy）** 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|
|1 |creditCode |VARCHAR(60) |统一社会信用代码 |
|2 |companyName |VARCHAR(60) |公司名 |
|3 |regCapital |VARCHAR(60) |注册资本 |
|4 |estabDate |VARCHAR(60) |成立日期 |
|5 |businessTerm |VARCHAR(60) |营业期限 |
|6 |capitalRank |VARCHAR(60) |资本排名 |
|7 |dateRank |VARCHAR(60) |日期排名 |
|8 |termRank |VARCHAR(60) |期限排名 |

#### **（10）第一产业类表（Industry_first）** 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|
|1 |creditCode |VARCHAR(60) |统一社会信用代码 |
|2 |companyName |VARCHAR(60) |公司名 |
|3 |regCapital |VARCHAR(60) |注册资本 |
|4 |estabDate |VARCHAR(60) |成立日期 |
|5 |businessTerm |VARCHAR(60) |营业期限 |
|6 |capitalRank |VARCHAR(60) |资本排名 |
|7 |dateRank |VARCHAR(60) |日期排名 |
|8 |termRank |VARCHAR(60) |期限排名 |

#### **（11）服务业类表（Industry_service）** 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|
|1 |creditCode |VARCHAR(60) |统一社会信用代码 |
|2 |companyName |VARCHAR(60) |公司名 |
|3 |regCapital |VARCHAR(60) |注册资本 |
|4 |estabDate |VARCHAR(60) |成立日期 |
|5 |businessTerm |VARCHAR(60) |营业期限 |
|6 |capitalRank |VARCHAR(60) |资本排名 |
|7 |dateRank |VARCHAR(60) |日期排名 |
|8 |termRank |VARCHAR(60) |期限排名 |

#### **（12）建筑业类表（Industry_building）** 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|
|1 |creditCode |VARCHAR(60) |统一社会信用代码 |
|2 |companyName |VARCHAR(60) |公司名 |
|3 |regCapital |VARCHAR(60) |注册资本 |
|4 |estabDate |VARCHAR(60) |成立日期 |
|5 |businessTerm |VARCHAR(60) |营业期限 |
|6 |capitalRank |VARCHAR(60) |资本排名 |
|7 |dateRank |VARCHAR(60) |日期排名 |
|8 |termRank |VARCHAR(60) |期限排名 |

### 2. 省份分析数据表 provinceInfo 

|序号 |列名 |数据类型 |注释 |
|:----|:----:|:----|:----:|:----|:----|
|1 |creditCode |VARCHAR(60) |统一社会信用代码 |
|2 |companyName |VARCHAR(60) |公司名 |
|3 |regCapital |VARCHAR(60) |注册资本 |
|4 |estabDate |VARCHAR(60) |成立日期 |
|5 |businessTerm |VARCHAR(60) |营业期限 |
|6 |province |VARCHAR(60) |省份 |
|7 |capitalRank |VARCHAR(60) |资本排名 |
|8 |dateRank |VARCHAR(60) |日期排名 |
|9 |termRank |VARCHAR(60) |期限排名 |

## 五、反馈意见 

### 1. advice表 

|序号 |列名 |数据类型 |注释 |
|:----|:----|:----|:----|
|1 |advicearea |VARCHAR(255) |反馈意见 |
|2 |connectionarea |VARCHAR(255) |联系方式 |







# **第七部分 补充设计和说明** 
## **一、** **项目部署环境说明** 

CentOS 8.2 64位         CPU： 2核       内存： 4 GiB 

 Mysql版本： 8.0.19 

 前端部署： Nginx 

 后端部署： JDK 1.8 




