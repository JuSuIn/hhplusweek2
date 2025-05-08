package com.example.ecommerce.application.facade;//쿠폰 발급 정책 + 중복 발급 방지 등

import com.example.ecommerce.application.coupon.CouponService;
import com.example.ecommerce.domain.coupon.Coupon;
import com.example.ecommerce.presentation.Coupon.CouponDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  쿠폰 관련 정책 파사드
 */
@Service
@RequiredArgsConstructor
public class CouponIssueFacade {
    private final CouponService couponService;

    //쿠폰 발급
    @Transactional
    public Coupon issueCoupon(Long userId,Long couponId){
        return couponService.issueCoupon(userId,couponId);
    }

    //사용자가 사용할 수 있는 쿠폰 조회
    @Transactional(readOnly = true)
    public List<CouponDto> getAvailableCoupons(Long userId){
        return couponService.findAvailableCoupons(userId);
    }
}