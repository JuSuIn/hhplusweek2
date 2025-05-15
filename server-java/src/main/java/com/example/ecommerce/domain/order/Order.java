package com.example.ecommerce.domain.order;//주문 엔티티입니다. 하나의 주문을 표현합니다.
// 고객, 주문일시, 주문 상태, 주문 항목 리스트 등의 정보를 포함
// 주문 생성, 취소 등 비즈니스 로직을 가질 수 있습니다

/*
   기본 주문 엔터티 도메인 입니다.
   1.기본 엔터티
   2. 주문 항목 추가
   3. 주문 생성시 호출
   4. 총주문 금액 계산
   5. 주문 취소
   6. 주문 완료
 */


import com.example.ecommerce.domain.coupon.Coupon;
import com.example.ecommerce.domain.coupon.CouponType;
import com.example.ecommerce.domain.order.OrderItem;
import com.example.ecommerce.domain.order.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Order {

    @Id @GeneratedValue
    private Long id;                            //주문 ID

    private Long userId;                      // 주문한 사용자 ID

    @Enumerated(EnumType.STRING)
    private OrderStatus status;              // 주문 상태 (예: 주문 생성CREATED, PAID, 주문 취소 CANCELLED)

    private LocalDateTime orderedAt;         // 주문 일시
    private Long totalPrice;                 // 총 주문 금액 (할인 적용 후)

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    //기타 주문 필드
    @OneToOne(fetch = FetchType.LAZY)
    private Coupon coupon; //쿠폰 관련 이벤트

    //주문과 직접적으로 연관되는 메서드

    //주문 정보 넣기
    /*public void addOrderItem(OrderItem item){
        orderItems.add(item);
        item.setOrder(this);
    }*/

    //주문 항목 추가
    public void addOrderItem(OrderItem item){
        orderItems.add(item);
        item.setOrder(this);
    }

    //주문 생성시 호출 - 기본정보와 아이템,쿠폰
    public void create(Long userId,List<OrderItem> items,Coupon coupon){
        this.userId=userId;
        this.status=OrderStatus.CREATED;
        this.orderedAt=LocalDateTime.now();
        this.coupon=coupon;
        items.forEach(this::addOrderItem);

        //총 주문 금액
        calculateTotalPrice();
    }

    //총 주문 금액 계산 - 주문 금액 합산 + 쿠폰 적용
    private void calculateTotalPrice(){
        long sum = orderItems.stream()
                .mapToLong(OrderItem::calculateTotalPrice)
                .sum();

        //쿠폰이 이미 있거나 쿠폰을 오늘 발급받았는지 확인
        if(coupon != null && coupon.isAvailable(LocalDateTime.now(),sum))
        {
            if(coupon.getType() == CouponType.AMOUNT){
                sum -= coupon.getDiscountAmount();
            }else if(coupon.getType() == CouponType.RATE){
                sum -= (long)(sum * coupon.getDiscountRate());
            }
            coupon.markAsUsed(); //쿠폰 상태 변경
        }
        this.totalPrice = Math.max( sum , 0L);
    }

    //주문 상태 변경용
    //주문 취소
    public void cancel() {
        this.status = OrderStatus.CANCELLED;
        //  아이템 취소 로직 등 포함 가능
    }
    //주문 완료
    public void complete() {
        this.status = OrderStatus.PAID;
    }
}