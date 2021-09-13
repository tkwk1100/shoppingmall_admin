package com.shopping.mapper;

import java.util.List;

import com.shopping.vo.MemberInfoVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    public void insertMember(MemberInfoVO vo);
    public Integer selectMemberById(String id);
    public Integer selectMemberByEmail(String email);
    public List<MemberInfoVO> selectMemberAll();
    public void deleteMember(Integer seq);
}
