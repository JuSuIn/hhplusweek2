// 도메인 관점의 리포지토리 인터페이스
package com.example.ecommerce.domain.coupon;

/*
   쿠폰 리포지 토리
 */

import com.example.ecommerce.domain.coupon.Coupon;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CouponRepository {

    Optional<Coupon> findById(Long id); //특정 사용자 쿠폰 조회
    List<Coupon> findAll(); //쿠폰 전체 검색

    List<Coupon> findAvailableCoupons(LocalDateTime now); //현재발행된 쿠폰 조회
    List<Coupon> findByUserId(Long userId); //특정 사용자 쿠폰 조회

    List<Coupon> findAllCouponsByUser(Long userId);//모든 쿠폰 이력 조회

    List<Coupon> findByUserIdAndStatus(Long userId,CouponStatus couponStatus); // 사용자가 발급받은 쿠폰 목록

    Coupon save(Coupon coupon); //수정

    void delete(Coupon coupon); //삭제

    boolean existsById(Long userId);
     boolean existsByName(String name);    //유효성 검사

}