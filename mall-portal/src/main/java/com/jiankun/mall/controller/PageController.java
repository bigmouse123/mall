package com.jiankun.mall.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/28 11:32
 */
@Controller
@RequestMapping("/page")
public class PageController {
    @RequestMapping("/**")
    public String path(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String[] paths = requestURI.split("/");
        if (paths.length == 4) {
            return paths[2] + "_" + paths[3];
        } else if (paths.length == 3) {
            return paths[2];
        } else {
            return "index";
        }
    }
}
