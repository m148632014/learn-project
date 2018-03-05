package org.mfm.learn.springboot.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author MengFanmao
 */
@Controller
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "Hello World!";
    }

    @RequestMapping("/log")
    @ResponseBody
    public String log() {
        this.logger.debug("This is a debug message");
        this.logger.info("This is an info message");
        this.logger.warn("This is a warn message");
        this.logger.error("This is an error message");
        return "logs";
    }

}
