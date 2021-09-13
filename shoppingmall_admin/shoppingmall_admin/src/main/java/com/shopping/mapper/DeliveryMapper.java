package com.shopping.mapper;

import java.util.List;

import com.shopping.vo.DeliveryInfoVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeliveryMapper {
    public void insertDeliveryInfo(DeliveryInfoVO vo);
    public Integer selectDeliveryByName(String name);
    public List<DeliveryInfoVO> selectDeliveryAll();
    public void deleteDelivery(Integer seq);
    public String selectDeliveryName(Integer seq);
    public Integer selectDeliveryProdCnt(Integer seq);
}
