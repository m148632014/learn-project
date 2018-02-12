package org.mfm.learn.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author MengFanmao
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "Hello World!";
    }
}
