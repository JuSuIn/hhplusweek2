package com.example.ecommerce.domain.coupon;// 쿠폰 상태 Enum (발급됨, 사용됨, 만료됨 등)


/*
   쿠폰 상태 지정 : 판매중,품절,판매중지
 */
public enum CouponStatus {
    UNUSED, //발급됨
    USED,  //사용일
    EXPIRED  //만료일s
}