package com.shopping.api;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.shopping.service.SellerService;
import com.shopping.vo.SellerInfoVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SellerAPIController {
    @Autowired
    SellerService s_service;
    
    @PostMapping("/seller/regist")
    public Map<String, Object> postSellerRegist(@RequestBody SellerInfoVO vo) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        // System.out.println(vo);
        Boolean r = s_service.insertSeller(vo);
        if(r) {
            resultMap.put("result", "success");
            resultMap.put("message", "등록성공");
        }
        else {
            resultMap.put("result", "failed");
            resultMap.put("message", "등록실패");
        }
        return resultMap;
    }
    // http://localhost:8024/seller/isDuplicateId?id=asdasd
    @GetMapping("/seller/isDuplicateId")
    public Map<String, Object> getIsDuplicateId(@RequestParam String id) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(s_service.isDuplicateId(id)){
            resultMap.put("status", true);
            resultMap.put("message", "아이디가 중복됩니다.");
        }
        else {
            resultMap.put("status", false);
            resultMap.put("message", "가입 가능한 아이디입니다.");
        }
        return resultMap;
    }
    // http://localhost:8024/seller/api/list
    @GetMapping("/seller/api/list")
    public Map<String, Object> getSellerList() {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<SellerInfoVO> list = s_service.selectSellerAll();
        resultMap.put("data", list);
        return resultMap;
    }
    // http://localhost:8024/seller/api/delete?seq=1
    @DeleteMapping("/seller/api/delete")
    public Map<String, Object> deleteSeller(@RequestParam Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        s_service.deleteSeller(seq);
        resultMap.put("message", "삭제되었습니다.");
        return resultMap;
    }
}
