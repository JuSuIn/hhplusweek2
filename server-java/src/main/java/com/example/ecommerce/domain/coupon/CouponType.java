package com.example.ecommerce.domain.coupon;// 정액 할인, 정률 할인 등 구분 (선택)

/*
   쿠폰 상태 지정 : 정액확인,정률확인
 */
public enum CouponType {
    FIXED,      // 정액 할인
    RATE        // 정률 할인
}