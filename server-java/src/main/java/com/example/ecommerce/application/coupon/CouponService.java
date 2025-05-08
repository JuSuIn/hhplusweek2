
// 쿠폰 도메인 관련 호출,트랜잭션 제어
package com.example.ecommerce.application.coupon;// 쿠폰 도메인 관련 호출,트랜잭션 제어


/*
   쿠폰 조회 로직 관련
   -- 쿠폰 정보 생성,쿠폰 사용, 쿠폰 삭제등
 */

import com.example.ecommerce.domain.coupon.Coupon;
import com.example.ecommerce.domain.coupon.CouponRepository;
import com.example.ecommerce.domain.coupon.CouponStatus;
import com.example.ecommerce.presentation.Coupon.CouponDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    //4.쿠폰 발급
    /**
     * 쿠폰 발급
     *
     * @param userId    발급받을 사용자 ID
     * @param couponId  발급할 쿠폰 ID
     * @return 발급된 Coupon 객체
     */
    public Coupon issueCoupon(Long userId,Long couponId){
        //쿠폰 조회
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow( () ->  new IllegalArgumentException("쿠폰을 찾을 수 없습니다."));

        //쿠폰 상태 및 유효성 검사
        if(! coupon.getStatus().equals(CouponStatus.AVAILABLE)){
            throw new IllegalStateException("이 쿠폰은 발급할 수 없습니다.");
        }

        //사용자 ID 기록 (또는 발급 이력 추가)
        coupon.assignToUser(userId);

        //쿠폰 상태 변경
        coupon.markAsUsed();

        //저장
        couponRepository.save(coupon);

        return coupon;
    }

    /**
     * 사용자에게 발급된 사용할 수 있는 쿠폰 목록을 조회
     *
     * @param userId 사용자 ID
     * @return 사용자가 사용할 수 있는 쿠폰 목록
     */
    public List<CouponDto> findAvailableCoupons(Long userId){
        //사용자가 존재하는지 확인
        if(!couponRepository.existsById(userId)){
            throw new IllegalArgumentException("사용자가 존재하지 않습니다.");
        }

        //사용자가 발급 받은 쿠폰 목록에서 상태가 AVAILABLE인 쿠폰을 필터링
        List<Coupon> availableCoupons = couponRepository.findByUserIdAndStatus(userId,CouponStatus.AVAILABLE);

        //쿠폰을 dto 로 변환하여 반환
        return availableCoupons.stream()
                .map( coupon -> new CouponDto(coupon))
                .collect(Collectors.toList());
    }

}

