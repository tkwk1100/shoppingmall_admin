package com.shopping.api;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.shopping.service.ProductService;
import com.shopping.vo.ProductVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductAPIController {
    @Autowired
    ProductService service;
    @PostMapping("/product/api/add")
    public Map<String, Object> postProductAdd(@RequestBody ProductVO vo) {
        return service.insertProduct(vo);
    }
    @GetMapping("/product/api/list")
    public Map<String, Object> getProductList(
        @RequestParam @Nullable String keyword,
        @RequestParam @Nullable Integer offset,
        @RequestParam @Nullable Integer category
    ) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<ProductVO> list = service.selectProducts(offset, keyword, category);
        resultMap.put("data", list);
        return resultMap;
    }
    @DeleteMapping("/product/api/delete")
    public Map<String, Object> deleteProduct(@RequestParam Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        service.deleteProduct(seq);
        resultMap.put("message", "삭제되었습니다.");

        return resultMap;
    }

    @PatchMapping("/product/api/update")
    public Map<String, Object> postProductUpdateAPI(@RequestBody ProductVO vo) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        service.updateProduct(vo);
        resultMap.put("status", true);
        resultMap.put("message", "수정되었습니다.");
        return resultMap;
    }
    @GetMapping("/product/api/get")
    public Map<String, Object> getProduct(@RequestParam Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        ProductVO vo = service.selectProductBySeq(seq);
        resultMap.put("status", true);
        resultMap.put("data", vo);
        return resultMap;
    }

    // 추천이 되어있는 상품 가져오기
    @GetMapping("/product/api/recommand")
    public Map<String, Object> getRecommandProdAPI() {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        List<ProductVO> list = service.selectRecommandProducts();
        resultMap.put("status", true);
        resultMap.put("list", list);

        return resultMap;
    }
    // 추천이 되어있지 않은 상품 가져오기
    // http://localhost:8024/product/api/not_recommand/1/1?keyword=asd
    @GetMapping("/product/api/not_recommand/{cate_seq}/{si_seq}")
    public Map<String, Object> getNotRecommandProdAPI(
        @PathVariable Integer cate_seq,
        @PathVariable Integer si_seq,
        @RequestParam @Nullable String keyword
    ) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        List<ProductVO> list = service.selectNotRecommandProducts(cate_seq, si_seq, keyword);
        
        resultMap.put("status", true);
        resultMap.put("list", list);

        return resultMap;
    }
    // 추천상품 삭제
    @DeleteMapping("/product/api/recommand")
    public Map<String, Object> deleteRecommandProdAPI(@RequestParam Integer prod_seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        service.deleteRecommandProduct(prod_seq);
        resultMap.put("status", true);
        resultMap.put("message", "추천상품이 삭제되었습니다.");
        return resultMap;
    }
    // 추천상품 추가
    @PutMapping("/product/api/recommand")
    public Map<String, Object> putRecommandProdAPI(@RequestParam Integer prod_seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        service.insertRecommandProduct(prod_seq);
        resultMap.put("status", true);
        resultMap.put("message", "추천상품이 추가되었습니다.");
        return resultMap;
    }
}
