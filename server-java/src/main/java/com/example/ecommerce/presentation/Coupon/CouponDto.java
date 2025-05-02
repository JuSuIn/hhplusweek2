package com.example.ecommerce.presentation.Coupon;

import com.example.ecommerce.domain.coupon.Coupon;
import com.example.ecommerce.domain.coupon.CouponStatus;
import com.example.ecommerce.domain.coupon.CouponType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

import java.time.LocalDateTime;

/*
   쿠폰 정보 응답용
 */
@Getter
public class CouponDto {
    private Long id;

    private String name;
    private Long discountAmount; //정액 할인 금액
    private Double discountRate; //정률 할인 금액

    private LocalDateTime issuedAt;           // 쿠폰발급일
    private LocalDateTime expiredAt;          // 쿠폰만료일

    @Enumerated(EnumType.STRING)
    private CouponStatus status;    //쿠폰의 상태를 말함

    @Enumerated(EnumType.STRING)
    private CouponType type;        //쿠폰상태할인(정액,정률)

    private Long minimumOrderPrice;           // 사용 조건 (최소 주문 금액)

    public CouponDto(Coupon coupon){
        this.id=coupon.getId();
        this.name= coupon.getName();
        this.discountAmount=coupon.getDiscountAmount();
        this.discountRate=coupon.getDiscountRate();
        this.issuedAt=coupon.getIssuedAt();
        this.expiredAt=coupon.getExpiredAt();
        this.status=coupon.getStatus();
        this.type=coupon.getType();
    }

}
