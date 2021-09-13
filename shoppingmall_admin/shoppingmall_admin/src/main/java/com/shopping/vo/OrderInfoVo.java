package com.shopping.vo;

import java.sql.Date;

import lombok.Data;
@Data
public class OrderInfoVo {
    private Integer oi_seq;
    private Date oi_reg_dt;
    private String oi_pay_info;
    private String oi_delivery_no ;
    private Integer oi_delivery_status; // jsp 오타확인
    private Integer oi_prod_count;
    private Integer mi_seq;
    private String mi_id;
    private String mi_name;
    private String mi_email ;
    private String mi_address ;
    private String mi_phone;
    private String pi_seq;
    private String pi_name ;
    private Integer pi_discount_rate;
    private Integer pi_point_rate;
    private Integer pi_price;
    private String pi_img_uri;
    private Integer si_seq;
    private String si_name ;
    private String si_address ;
    private String si_email;
    private String si_phone ;
    private Integer final_price;
    private Integer final_point;
}
