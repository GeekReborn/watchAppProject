package net.sxbang.watchapp.controller.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/")
public class AdminApiController {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/success")
    public String success() {
        return "admin success";
    }
}
