package com.shopping.controller;

import java.util.List;

import com.shopping.service.CategoryService;
import com.shopping.service.DeliveryService;
import com.shopping.service.SellerService;
import com.shopping.vo.CategoryVO;
import com.shopping.vo.DeliveryInfoVO;
import com.shopping.vo.SellerInfoVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
    @Autowired
    CategoryService cate_service;
    @Autowired
    DeliveryService delivery_service;
    @Autowired
    SellerService seller_service;

    @GetMapping("/product")
    public String getProduct(Model model) {
        List<CategoryVO> clist = cate_service.selectCategoryAll();
        List<DeliveryInfoVO> dlist = delivery_service.selectDeliveryAll();
        List<SellerInfoVO> slist = seller_service.selectSellerAll();

        model.addAttribute("clist", clist);
        model.addAttribute("dlist", dlist);
        model.addAttribute("slist", slist);


        return "/product/product_list";
    }
}
