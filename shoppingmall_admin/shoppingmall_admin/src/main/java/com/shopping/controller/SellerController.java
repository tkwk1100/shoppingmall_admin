package com.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SellerController {
    // localhost:8024/seller/regist
    @GetMapping("/seller/regist")
    public String getSellerRegist() {
        return "/seller/seller";
    }
}
