// 쿠폰 관련 처리 & 조회 관련  -> 쿠폰 정보,쿠폰이력, 쿠폰발급이력, 쿠폰 발급요청등
package com.example.ecommerce.application.coupon;
/*
   쿠폰 조회 로직 관련
   -- 쿠폰 정보,쿠폰이력, 쿠폰발급이력, 쿠폰 발급요청등
 */

import com.example.ecommerce.domain.coupon.Coupon;
import com.example.ecommerce.domain.coupon.CouponRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponQueryService {

    private final CouponRepository couponRepository;

    //쿠폰 전체 조회
    public List<Coupon> findAll(){
        return couponRepository.findAll();
    }

    //특정 쿠폰 찾기
    public Coupon findById(Long id){
        return couponRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("쿠폰을 찾을 수 없습니다."));
    }

    //편재 사용 가능한 쿠폰을 조회
    public List<Coupon> findAvailableCoupons(){
        return couponRepository.findAvailableCoupons(LocalDateTime.now());
    }

    //모든 쿠폰 이력 조회
    public List<Coupon> findAllCouponsByUser(Long userId){
        return couponRepository.findByUserId(userId);
    }

}
