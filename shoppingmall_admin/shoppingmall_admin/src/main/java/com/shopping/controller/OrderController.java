package com.shopping.controller;

import java.util.List;

import com.shopping.service.OrderService;
import com.shopping.vo.OrderInfoVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {
    @Autowired OrderService service;
    @GetMapping("/order")
    public String getOrder(@RequestParam @Nullable Integer offset, Model model) {
        if(offset == null) offset = 0;
        List<OrderInfoVO> list = service.selectOrderInfo(offset);
        Integer cnt = service.selectOrderCount();
        model.addAttribute("list", list);
        model.addAttribute("cnt", cnt);
        return "/order/order_list";
    }
}
