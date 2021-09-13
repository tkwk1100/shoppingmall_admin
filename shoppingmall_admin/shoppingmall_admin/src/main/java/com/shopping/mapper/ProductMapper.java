package com.shopping.mapper;

import java.util.List;

import com.shopping.vo.ProductImageVO;
import com.shopping.vo.ProductVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    public void insertProduct(ProductVO vo);
    public List<ProductVO> selectProducts(Integer offset, String keyword, Integer category);
    public void deleteProduct(Integer seq);
    public void insertProductImage(ProductImageVO vo);
    public String selectProductImagePath(String uri);

    public ProductVO selectProductBySeq(Integer seq);
    public void updateProduct(ProductVO vo);

    public List<ProductVO> selectRecommandProducts();
    public List<ProductVO> selectNotRecommandProducts(Integer cate_seq, Integer si_seq, String keyword);
    public void deleteRecommandProduct(Integer prod_seq);
    public void insertRecommandProduct(Integer prod_seq);
}
