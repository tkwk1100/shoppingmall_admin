package com.shopping.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.shopping.mapper.CategoryMapper;
import com.shopping.mapper.DeliveryMapper;
import com.shopping.mapper.ProductMapper;
import com.shopping.mapper.SellerMapper;
import com.shopping.vo.ProductImageVO;
import com.shopping.vo.ProductVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductMapper mapper;
    @Autowired
    CategoryMapper cate_mapper;
    @Autowired
    DeliveryMapper d_mapper;
    @Autowired
    SellerMapper s_mapper;

    public Map<String, Object> insertProduct(ProductVO vo) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(vo.getPi_name() == "" || vo.getPi_name() == null) {
            resultMap.put("status", "failed");
            resultMap.put("message", "제품 명이 입력되지 않았습니다.");
            return resultMap;
        }
        if(vo.getPi_cate_seq() == null) {
            resultMap.put("status", "failed");
            resultMap.put("message", "카테고리가 설정되지 않았습니다.");
            return resultMap;
        }
        if(vo.getPi_si_seq() == null) {
            resultMap.put("status", "failed");
            resultMap.put("message", "판매자가 설정되지 않았습니다.");
            return resultMap;
        }
        if(vo.getPi_di_seq() == null) {
            resultMap.put("status", "failed");
            resultMap.put("message", "배송업체가 설정되지 않았습니다.");
            return resultMap;
        }
        if(vo.getPi_price() == null) {
            resultMap.put("status", "failed");
            resultMap.put("message", "제품가격이 설정되지 않았습니다.");
            return resultMap;
        }
        if(vo.getPi_stock() == null) vo.setPi_stock(0);
        if(vo.getPi_discount_rate() == null) vo.setPi_discount_rate(0);
        if(vo.getPi_weight() == null) vo.setPi_weight(0);
        if(vo.getPi_point_rate() == null) vo.setPi_point_rate(0);

        mapper.insertProduct(vo);
        
        resultMap.put("status", "success");
        resultMap.put("message", "상품이 추가되었습니다.");

        return resultMap;
    }

    public List<ProductVO> selectProducts(Integer offset, String keyword, Integer category) {
        if(offset == null) offset = 0;
        if(keyword == null){ keyword = "%%";}
        else {keyword = "%"+keyword+"%"; }

        List<ProductVO> list = mapper.selectProducts(offset, keyword, category);

        for(int i=0; i<list.size(); i++) {
            Integer cate_seq = list.get(i).getPi_cate_seq();
            String cate_name = cate_mapper.selectCategoryName(cate_seq);
            list.get(i).setCategory_name(cate_name);

            Integer di_seq = list.get(i).getPi_di_seq();
            String di_name = d_mapper.selectDeliveryName(di_seq);
            list.get(i).setDelivery_name(di_name);

            Integer si_seq = list.get(i).getPi_si_seq();
            String si_name = s_mapper.selectSellerName(si_seq);
            list.get(i).setSeller_name(si_name);
        }

        return list;
    }
    public void deleteProduct(Integer seq) {
        mapper.deleteProduct(seq);
    }
    public void insertProductImage(ProductImageVO vo) {
        mapper.insertProductImage(vo);
    }
    public String selectProductImagePath(String uri) {
        return mapper.selectProductImagePath(uri);
    }
    public List<ProductVO> selectRecommandProducts(){
        List<ProductVO> list = mapper.selectRecommandProducts();
        for(int i=0; i<list.size(); i++) {
            Integer cate_seq = list.get(i).getPi_cate_seq();
            String cate_name = cate_mapper.selectCategoryName(cate_seq);
            list.get(i).setCategory_name(cate_name);

            Integer si_seq = list.get(i).getPi_si_seq();
            String si_name = s_mapper.selectSellerName(si_seq);
            list.get(i).setSeller_name(si_name);
        }
        return list;
    }
    public List<ProductVO> selectNotRecommandProducts(Integer cate_seq, Integer si_seq, String keyword){
        if(keyword == null) {
            // % 기호를 넣는 이유는 SQL 쿼리에서 사용하는 형태로 바꿔주기 위함.
            // ex> %커피 (~~~~ 커피, 믹스 커피)
            // ex> 커피% (커피 ~~~~, 커피 우유)
            // ex> %커피% (~~~ 커피 ~~~)
            keyword = "%%";
        }
        else {
            keyword = "%"+keyword+"%";
        }

        List<ProductVO> list = mapper.selectNotRecommandProducts(cate_seq, si_seq, keyword);

        for(int i=0; i<list.size(); i++) {
            Integer c_seq = list.get(i).getPi_cate_seq();
            String cate_name = cate_mapper.selectCategoryName(c_seq);
            list.get(i).setCategory_name(cate_name);

            Integer s_seq = list.get(i).getPi_si_seq();
            String si_name = s_mapper.selectSellerName(s_seq);
            list.get(i).setSeller_name(si_name);
        }
        return list;
    }
    public void deleteRecommandProduct(Integer prod_seq){
        mapper.deleteRecommandProduct(prod_seq);
    }
    public void insertRecommandProduct(Integer prod_seq){
        mapper.insertRecommandProduct(prod_seq);
    }

    public ProductVO selectProductBySeq(Integer seq){
        return mapper.selectProductBySeq(seq);
    }
    public void updateProduct(ProductVO vo) {
        mapper.updateProduct(vo);
    }
}
