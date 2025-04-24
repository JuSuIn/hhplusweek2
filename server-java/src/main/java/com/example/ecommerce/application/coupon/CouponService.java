package com.example.ecommerce.application.coupon;// 쿠폰 도메인 관련 호출,트랜잭션 제어


/*
   쿠폰 조회 로직 관련
   -- 쿠폰 정보 생성,쿠폰 사용, 쿠폰 삭제등
 */

import com.example.ecommerce.domain.coupon.Coupon;
import com.example.ecommerce.domain.coupon.CouponRepository;
import com.example.ecommerce.presentation.Coupon.CouponDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;

    //1.쿠폰 정보 생성
    public Coupon createCoupon(CouponDto dto){
        Coupon coupon = Coupon.create(dto.getName(),
                dto.getDiscountAmount(),
                dto.getDiscountRate(),
                dto.getIssuedAt(),
                dto.getExpiredAt(),
                dto.getType(),
                dto.getMinimumOrderPrice());

        return couponRepository.save(coupon);
    }

    //2.쿠폰 사용
    public void useCoupon(Long couponId,long orderPrice){
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow( () -> new EntityNotFoundException("쿠폰이 존재하지 않습니다."));

        if(!coupon.isAvailable(LocalDateTime.now(),orderPrice)){
            throw new IllegalStateException("쿠폰을 사용할 수 없습니다.");
        }
        coupon.markAsUsed();
        couponRepository.save(coupon);
    }

    //3.쿠폰 삭제
    public void deleteCoupon(Long id){
        Coupon coupon = couponRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("삭제할 쿠폰이 존재하지 않습니다."));
        couponRepository.delete(coupon);
    }
}

