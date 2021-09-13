package com.shopping.controller;

import java.util.List;

import com.shopping.service.MemberService;
import com.shopping.vo.MemberInfoVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
    @Autowired
    MemberService service;
    @GetMapping("/member/join")
    public String getMemberJoin() {
        return "/member/join";
    }
    @GetMapping("/member/list")
    public String getMemberList(Model model) {
        List<MemberInfoVO> resultList = service.selectMemberAll();
        model.addAttribute("list", resultList);
        return "/member/list";
    }
}