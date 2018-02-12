package org.mfm.learn.ajax.web;


import org.mfm.learn.ajax.model.HelloDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

@Controller
@ControllerAdvice
public class SampleController  extends AbstractJsonpResponseBodyAdvice {
    public SampleController() {
        // 这样如果请求中带 callback 参数，Spring 就知道这个是 jsonp 的请求了
        super("callback");
    }
    @GetMapping(value = { "/", "/index" })
    public String home() {
        return "index";
    }

    @ResponseBody
    @GetMapping(value = "/hello/jsonp",produces = "application/json;charset=UTF-8")
    public HelloDto helloJsonp() {
        HelloDto dto = new HelloDto();
        dto.setUsername("zs");
        dto.setPassword("123");
        return dto;
    }

    @ResponseBody
    @PostMapping("/hello/cors")
    public HelloDto helloCors() {
        HelloDto dto = new HelloDto();
        dto.setUsername("ls");
        dto.setPassword("123");
        return dto;
    }

}
