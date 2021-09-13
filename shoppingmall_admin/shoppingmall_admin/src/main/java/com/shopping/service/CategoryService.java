package com.shopping.service;

import java.util.List;

import com.shopping.mapper.CategoryMapper;
import com.shopping.vo.CategoryVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    CategoryMapper mapper;
    public boolean addCategory(String name) {
        // 등록된 카테고리 명이 있는가?
        Integer n = mapper.isDuplicateCategory(name);
        if(n != 0) {
            // 이미 존재하는 카테고리이므로, 실패 처리
            return false;
        }
        // 카테고리가 존재하지 않으므로, 추가를 하고, 성공 처리
        mapper.insertCategory(name);
        return true;
    }

    public List<CategoryVO> selectCategoryAll() {
        List<CategoryVO> list = mapper.selectCategoryAll();
        for(int i = 0; i < list.size(); i++) {
            Integer cnt = mapper.selectCateProdCnt(list.get(i).getCate_seq());
            list.get(i).setCate_prod_cnt(cnt);
        }
        return list;
    }

    public void deleteCategory(Integer seq){
        mapper.deleteCategory(seq);
    }
}
