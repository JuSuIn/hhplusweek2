package com.example.ecommerce.domain.order;//  주문에서 상품 단위의 항목을 의미합니다. (1/n)
//  어떤 상품을 몇 개 샀는지, 금액이 얼마인지 등을 포함

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import org.springframework.data.annotation.Id;
import com.example.ecommerce.domain.order.Order;

/*
   주문항목 도메인
 */
@Entity
public class OrderItem {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    private Long productId;           // 주문한 상품 ID
    private int quantity;             // 주문 수량
    private Long orderPrice;          // 해당 상품의 주문 가격 (단가 * 수량, 할인 적용 가능)
    private String productName;       // 주문 당시의 상품명 (상품명이 바뀌어도 주문 당시 값은 유지)

    // 상품 옵션 등도 필드로 추가

    public void setOrder(Order order){
        this.order=order;
    }
}