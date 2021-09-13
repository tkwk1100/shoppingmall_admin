package com.shopping.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SellerInfoVO {
    // DB테이블로부터 바로 가져오는 값
    private Integer si_seq;
    private String si_id;
    private String si_pwd;
    private String si_name;
    private String si_address;
    private String si_email;
    private String si_phone;
    private Integer si_grade;
    // Service에서 가공한 결과를 저장할 용도
    // DB테이블에 없는 컬럼이므로, 값을 가져왔을 때 null로 세팅
    private Integer seller_prod_cnt;
}
