package com.yicj.study.component;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import java.util.ArrayList;
import java.util.List;

/**
 * text/html转json对象
 */
public class JacksonFromTextHtmlMessageConverter extends MappingJackson2HttpMessageConverter {

    public JacksonFromTextHtmlMessageConverter(){
        List<MediaType> types = new ArrayList<>() ;
        types.add(MediaType.TEXT_HTML) ;
        setSupportedMediaTypes(types);
    }
}