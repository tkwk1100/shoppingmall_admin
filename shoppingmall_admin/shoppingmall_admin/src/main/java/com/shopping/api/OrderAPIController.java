package com.shopping.api;

import java.util.LinkedHashMap;
import java.util.Map;

import com.shopping.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderAPIController {
    @Autowired OrderService service;
    @PatchMapping("/order/status")
    public Map<String, Object> patchOrderStatus(
        @RequestParam Integer status, @RequestParam Integer oi_seq
    ) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        service.updateDeliveryStatus(status, oi_seq);
        resultMap.put("status", true);

        return resultMap;
    }
}
