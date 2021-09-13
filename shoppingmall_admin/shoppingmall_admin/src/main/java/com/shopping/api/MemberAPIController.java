package com.shopping.api;

import java.util.LinkedHashMap;
import java.util.Map;

import com.shopping.service.MemberService;
import com.shopping.vo.MemberInfoVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberAPIController {
    @Autowired
    MemberService service;
    @PostMapping("/member/join")
    public Map<String, Object> postMemberJoin(@RequestBody MemberInfoVO vo) {
        return service.insertMember(vo);
    }
    @GetMapping("/member/id_check")
    public Map<String, Object> getIdCheck(@RequestParam String id) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        // isDuplicatedId (아이디가 중복이 되는가?) - true 그렇다 / false 아니다
        if(service.isDuplicatedId(id) == true){
            resultMap.put("status", false);
            resultMap.put("message", "["+id+"] 는 이미 사용중입니다.");
            return resultMap;
        }
        resultMap.put("status", true);
        resultMap.put("message", "["+id+"] 는 사용하실 수 있습니다.");
        return resultMap;
    }

    @GetMapping("/member/email_check")
    public Map<String, Object> getEmailCheck(@RequestParam String email) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        // isDuplicatedId (아이디가 중복이 되는가?) - true 그렇다 / false 아니다
        if(service.isDuplicatedEmail(email) == true){
            resultMap.put("status", false);
            resultMap.put("message", "["+email+"] 은 이미 사용중입니다.");
            return resultMap;
        }
        resultMap.put("status", true);
        resultMap.put("message", "["+email+"] 은 사용하실 수 있습니다.");
        return resultMap;
    }

    @DeleteMapping("/member/delete")
    public Map<String, Object> deleteMember(@RequestParam Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        service.deleteMember(seq);
        resultMap.put("status", true);
        resultMap.put("message", "삭제완료");
        return resultMap;
    }
}
