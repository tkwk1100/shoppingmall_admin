package com.shopping.mapper;

import java.util.List;

import com.shopping.vo.OrderInfoVo;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    public List<OrderInfoVo> selectOrderInfo (Integer offset);
    public Integer selectOrderCount();
    public void updateDeliveryStatus(Integer status, Integer oi_seq);
}
