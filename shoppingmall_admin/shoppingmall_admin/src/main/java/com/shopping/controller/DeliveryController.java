package com.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeliveryController {
    @GetMapping("/delivery/add")
    public String getDeliveryAdd() {
        return "/delivery/add";
    }
}
