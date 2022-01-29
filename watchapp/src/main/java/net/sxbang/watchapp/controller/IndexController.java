package net.sxbang.watchapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: Emoker
 * @create: 2019-12-06 16:28
 **/
@Controller
@CrossOrigin("*")
public class IndexController {

    @RequestMapping("/")
    public String index () {
        return "index";
    }

    @ResponseBody
    @RequestMapping("/hello")
    public String hello () {
        return "hello";
    }
}
