package com.shopping.service;

import java.util.List;

import com.shopping.mapper.OrderMapper;
import com.shopping.vo.OrderInfoVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired OrderMapper mapper;
    public List<OrderInfoVo> selectOrderInfo(Integer offset){
        return mapper.selectOrderInfo(offset);
    }
    public Integer selectOrderCount(){
        return mapper.selectOrderCount();
    }
    public void updateDeliveryStatus(Integer status, Integer oi_seq){
        mapper.updateDeliveryStatus(status, oi_seq);
    }
}
