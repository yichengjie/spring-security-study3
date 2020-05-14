package com.yicj.client.component;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import java.util.ArrayList;
import java.util.List;


/**
 *restTemplate解析模板
 */
public class JacksonFromTextHttpMessageConverter extends MappingJackson2HttpMessageConverter {

    //添加对text/html的支持
    public JacksonFromTextHttpMessageConverter(){
        List<MediaType> mediaTypes = new ArrayList<>() ;
        mediaTypes.add(MediaType.TEXT_HTML) ;
        setSupportedMediaTypes(mediaTypes);
    }

}
