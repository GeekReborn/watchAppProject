package net.sxbang.watchapp.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api-prefix}/user/")
public class UserApiController {

    @RequestMapping("/success")
    public String success() {
        return "login success";
    }
}
