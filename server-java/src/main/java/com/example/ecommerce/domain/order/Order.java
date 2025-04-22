package com.example.ecommerce.domain.order;//주문 엔티티입니다. 하나의 주문을 표현합니다.
// 고객, 주문일시, 주문 상태, 주문 항목 리스트 등의 정보를 포함
// 주문 생성, 취소 등 비즈니스 로직을 가질 수 있습니다

/*
   기본 주문 엔터티 도메인 입니다.
 */

import com.example.ecommerce.domain.coupon.Coupon;
import com.example.ecommerce.domain.order.OrderItem;
import com.example.ecommerce.domain.order.OrderStatus;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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