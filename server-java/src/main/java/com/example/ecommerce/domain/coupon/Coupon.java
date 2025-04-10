package com.example.ecommerce.domain.coupon;// 쿠폰 엔티티

import com.example.ecommerce.domain.coupon.CouponStatus;
import com.example.ecommerce.domain.coupon.CouponType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
public class Coupon
{
    @Id @GeneratedValue
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

    public Coupon() {
    }

    //쿠폰이 사용 가능한지 판단하는 로직 처리 (쿠폰의 책임이 크기 때문에 ) domain 로직에 넣음
    public boolean isAvailable(LocalDateTime now, Long orderTotal){
        return this.status == CouponStatus.UNUSED &&
                now.isBefore(this.expiredAt) &&
                orderTotal >= this.minimumOrderPrice;
    }

    //쿠폰이 "실제로 사용" 되었을때 쿠폰의 상태를 "사용일"로 변경
    public void markAsUsed(){
        this.status = CouponStatus.USED;
    }
}