package com.shopping.api;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.shopping.service.DeliveryService;
import com.shopping.vo.DeliveryInfoVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeliveryAPIController {
    @Autowired
    DeliveryService service;

    @PostMapping("/delivery/api/add")
    public Map<String, Object> postDeliveryApiAdd(@RequestBody DeliveryInfoVO vo) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        // 방법1 - if~else를 이용하는 방법
        boolean b = service.addDelivery(vo);
        resultMap.put("status", b);
        if(b) {
            resultMap.put("message", "배송업체 등록에 성공했습니다.");
        }
        else {
            resultMap.put("message", "배송업체 등록에 실패했습니다.");
        }

        // 방법2 - ?: (조건선택연산자)를 이용하는 방법
        // String msg = service.addDelivery(vo)?"배송업체 등록에 성공했습니다.":"배송업체 등록에 실패했습니다";
        // resultMap.put("status", b);
        // resultMap.put("message", msg);

        return resultMap;
    }
    @GetMapping("/delivery/api/check")
    public Map<String, Object> getDeliveryApiCheck(@RequestParam String name) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        boolean b = service.isExistDelivery(name);
        resultMap.put("status", b);
        if(b) {
            resultMap.put("message", "["+name+"] 은/는 이미 존재합니다.");
        }
        else {
            resultMap.put("message", "["+name+"] 은/는 등록 가능합니다.");
        }
        return resultMap;
    }
    // http://localhost:8024/delivery/api/list
    @GetMapping("/delivery/api/list")
    public Map<String, Object> getDeliveryList() {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<DeliveryInfoVO> list = service.selectDeliveryAll();
        resultMap.put("data", list);
        return resultMap;
    }
    // http://localhost:8024/delivery/api/delete?seq=1
    @DeleteMapping("/delivery/api/delete")
    public Map<String, Object> deleteDelivery(@RequestParam Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        service.deleteDelivery(seq);
        resultMap.put("message", "삭제되었습니다.");
        return resultMap;
    }
}
