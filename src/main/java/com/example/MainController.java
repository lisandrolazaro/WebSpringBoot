package com.example;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
/**
 * Created by dev on 16/09/16.
 */
@Controller
public class MainController {

    @RequestMapping(value = "restCall" , method = RequestMethod.GET)
    @ResponseBody
    public String callSomeRestService() {

        RestTemplate rt = new RestTemplate();
        rt.getMessageConverters().add(new StringHttpMessageConverter());

        String uri = new String("someUrl");
        return rt.getForObject(uri, String.class);

    }
}
