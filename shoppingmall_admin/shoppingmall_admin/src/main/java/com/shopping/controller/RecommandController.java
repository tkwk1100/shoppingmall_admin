package com.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecommandController {
    @GetMapping("/recommand")
    public String getRecommand() {
        return "/recommand/list";
    }
}
