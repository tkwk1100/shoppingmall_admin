package com.shopping.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.shopping.mapper.MemberMapper;
import com.shopping.vo.MemberInfoVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    MemberMapper mapper;

    public Map<String, Object> insertMember(MemberInfoVO vo) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        boolean duplicated = isDuplicatedId(vo.getMi_id());

        if(duplicated) {
            // false - 회원가입에 실패했다.
            // 프론트나, arc 쪽에서 확인
            resultMap.put("status", false);
            resultMap.put("message", "아이디가 중복됩니다.");
            return resultMap;
        }

        boolean email_dup = isDuplicatedEmail(vo.getMi_email());
        if(email_dup) {
            resultMap.put("status", false);
            resultMap.put("message", "이메일 중복됩니다.");
            return resultMap;
        }

        mapper.insertMember(vo);

        resultMap.put("status", true);
        resultMap.put("message", "회원가입이 완료되었습니다.");

        return resultMap;
    }

    public boolean isDuplicatedId(String id) {
        return mapper.selectMemberById(id) > 0;
    }
    public boolean isDuplicatedEmail(String email) {
        return mapper.selectMemberByEmail(email) > 0;
    }
    public List<MemberInfoVO> selectMemberAll() {
        return mapper.selectMemberAll();
    }
    public void deleteMember(Integer seq) {
        mapper.deleteMember(seq);
    }
}
