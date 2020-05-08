package com.yicj.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QQOauthApplication {

    public static void main(String[] args) {
       // SpringApplication.run(QQOauthApplication.class, args) ;
        String str = "{" +
                "\"data\": {" +
                "\"product\": [" +
                " {" +
                "\"productId\": \"10838\"," +
                "\"carrCode\": \"CA\"," +
                "\"subcode\": \"0DL\"," +
                "\"serviceType\": \"P\"," +
                "\"group\": \"BG\"," +
                "\"subGroup\": \"XS\"," +
                "\"description1\": \"WT\"," +
                "\"commercialName\": \"WEIGHT SYSTEM CHARGE\"," +
                "\"rfic\": \"C\"," +
                "\"ssrCode\": \"PDBG\"," +
                "\"ssimCode\": \"862\"," +
                "\"industryCarrier\": \"I\"," +
                "\"inventoryCtrl\": \"N\"," +
                "\"priceCtrl\": \"Y\"," +
                "\"emdType\": \"2\"," +
                "\"remark\": \"e\"," +
                "\"distributionStatus\": \"1\"," +
                "\"distributionTime\": \"20200506205550765\"," +
                "\"lastUpdateUser\": \"MX\"," +
                "\"lastUpdateDate\": \"20200506155005052\"," +
                "\"languageTableNo\": \"10498\"," +
                "\"attributionTableNo\": \"11858\"," +
                "\"groupDefinition\": \"Baggage\"," +
                "\"subgroupDefinition\": \"Baggage Excess\"" +
                "}" +
                "]," +
                "\"attribution\": [" +
                "{" +
                "\"tableNo\": \"11858\"," +
                "\"carrCode\": \"CA\"," +
                "\"pbgModel\": \"W\"," +
                "\"pbgWeightConceptDiff\": \"0\"," +
                "\"pbgWeightConceptMin\": \"1\"," +
                "\"pbgWeightConceptMax\": \"1\"," +
                "\"pbgWeightConceptUnit\": \"KG\"," +
                "\"lastUpdateUser\": \"MX\"," +
                "\"lastUpdateDate\": \"20200506155005046\"" +
                "}" +
                "]," +
                "\"language\": [" +
                "{" +
                "\"tableNo\": \"10498\"," +
                "\"carrCode\": \"CA\"," +
                "\"productName\": \"预付费行李\"," +
                "\"productDescription\": \"预付费行李\"," +
                "\"languageType\": \"zh_CN\"," +
                "\"lastUpdateUser\": \"MX\"," +
                "\"lastUpdateDate\": \"20200506155005035\"" +
                "}," +
                "{" +
                "\"tableNo\": \"10498\"," +
                "\"carrCode\": \"CA\"," +
                "\"productName\": \"preference seat\"," +
                "\"productDescription\": \"preference seat\"," +
                "\"languageType\": \"en_US\"," +
                "\"lastUpdateUser\": \"MX\"," +
                "\"lastUpdateDate\": \"20200506155005038\"" +
                "}" +
                "]," +
                "\"bundle\": [" +
                "" +
                "]," +
                "\"content\": [" +
                "" +
                "]" +
                "}" +
                "}" ;
    }

}
