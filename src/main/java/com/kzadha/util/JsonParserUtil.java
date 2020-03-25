package com.kzadha.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

public class JsonParserUtil {
    private static final Logger log = LoggerFactory.getLogger(JsonParserUtil.class);


    public static HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setExpires(0);
        return headers;
    }
}