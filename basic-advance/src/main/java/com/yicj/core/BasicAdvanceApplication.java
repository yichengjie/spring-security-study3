package com.yicj.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BasicAdvanceApplication {
    public static void main(String[] args)  {
        //dXNlciUzQTEyMw==
        //Basic dXNlcjoxMjM=
        //SpringApplication.run(BasicAdvanceApplication.class, args);

        String str = "{" +
                "\"data\":{" +
                "\"product\":[" +
                "{" +
                "\"productId\":\"2284\"," +
                "\"carrCode\":\"CA\"," +
                "\"sequenceNo\":\"63\"," +
                "\"subcode\":\"0B5\"," +
                "\"subProduct\":\"A,G\"," +
                "\"serviceType\":\"F\"," +
                "\"group\":\"SA\"," +
                "\"subGroup\":\"\"," +
                "\"description1\":\"\"," +
                "\"description2\":\"\"," +
                "\"commercialName\":\"PRE RESERVED SEAT ASSIGNMENT\"," +
                "\"rfic\":\"A\"," +
                "\"ssrCode\":\"SEAT\"," +
                "\"ssimCode\":\"860\"," +
                "\"industryCarrier\":\"I\"," +
                "\"inventoryCtrl\":\"Y\"," +
                "\"priceCtrl\":\"Y\"," +
                "\"emdType\":\"2\"," +
                "\"remark\":\"eQWE1111\"," +
                "\"distributionStatus\":\"1\"," +
                "\"distributionTime\":\"20200507093929822\"," +
                "\"lastUpdateUser\":\"like\"," +
                "\"lastUpdateDate\":\"20200507093930588\"," +
                "\"languageTableNo\":\"2584\"," +
                "\"contentTableNo\":\"725\"," +
                "\"groupDefinition\":\"Pre-reserved Seat Assignment\"" +
                "}" +
                "]," +
                "\"attribution\":[" +
                "" +
                "]," +
                "\"language\":[" +
                "{" +
                "\"tableNo\":\"2584\"," +
                "\"carrCode\":\"CA\"," +
                "\"productName\":\"预付费行李\"," +
                "\"productDescription\":\"预付费行李\"," +
                "\"languageType\":\"zh_CN\"," +
                "\"lastUpdateUser\":\"like\"," +
                "\"lastUpdateDate\":\"20200507093929825\"" +
                "}," +
                "{" +
                "\"tableNo\":\"2584\"," +
                "\"carrCode\":\"CA\"," +
                "\"productName\":\"preference seat\"," +
                "\"productDescription\":\"preference seat\"," +
                "\"languageType\":\"en_US\"," +
                "\"lastUpdateUser\":\"like\"," +
                "\"lastUpdateDate\":\"20200507093929977\"" +
                " }" +
                "]," +
                "\"bundle\":[" +
                "" +
                "]," +
                " \"content\":[" +
                "{" +
                "\"tableNo\":\"725\"," +
                "\"carrCode\":\"CA\"," +
                "\"sequenceNo\":\"1\"," +
                "\"mediaType\":\"4\"," +
                "\"mediaUrl\":\"[图片][图片][图片]http://www.airchina.com.cn/cn/images/aaaa.jpg\"," +
                "\"lastUpdateUser\":\"like\"," +
                "\"lastUpdateDate\":\"20200507093930276\"" +
                "}," +
                "{" +
                "\"tableNo\":\"725\"," +
                "\"carrCode\":\"CA\"," +
                "\"sequenceNo\":\"2\"," +
                "\"mediaType\":\"4\"," +
                "\"mediaUrl\":\"[图片][图片][图片]http://www.airchina.com.cn/cn/images/bbbb.jpg\"," +
                "\"lastUpdateUser\":\"like\"," +
                "\"lastUpdateDate\":\"20200507093930426\"" +
                "}" +
                "]" +
                "}" +
                "}" ;
        System.out.println(str);
    }
}
