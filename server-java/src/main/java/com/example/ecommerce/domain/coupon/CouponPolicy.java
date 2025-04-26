package com.example.ecommerce.domain.coupon;// 할인 적용 정책에 대한 인터페이스(선택)

/*
   쿠폰 적용 정책 (계산을 책임 분리함)
   할인 금액 계산 책임을 coupon 이 아닌 CouponPolicy 에게 위임함
 */

import com.example.ecommerce.domain.coupon.Coupon;

import java.time.LocalDateTime;

public class CouponPolicy {

    //할인 금액 계산
    public static long calculateDiscountPrice(Coupon coupon, long orderPrice){
        //1. 쿠폰이 사용 가능한지 판단 -쿠폰 발급 날짜가 지나고, 최소금액 초과
        if(!coupon.isAvailable(LocalDateTime.now(),orderPrice)){
            throw new IllegalArgumentException("사용할 수 없는 쿠폰입니다.");
        }

        if(coupon.getType() == CouponType.AMOUNT){
            return Math.min(coupon.getDiscountAmount(),orderPrice);
        }

        if(coupon.getType() == CouponType.RATE){
            return Math.round(orderPrice *(coupon.getDiscountRate() / 100) );
        }

        return 0L;
    }
}
