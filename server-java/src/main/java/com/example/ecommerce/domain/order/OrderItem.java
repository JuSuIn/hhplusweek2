package com.example.ecommerce.domain.order;//  주문에서 상품 단위의 항목을 의미합니다. (1/n)
//  어떤 상품을 몇 개 샀는지, 금액이 얼마인지 등을 포함

import com.example.ecommerce.domain.catalog.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/*
   주문항목 도메인
 */
@Entity
@Getter
@Setter
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private OrderStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order; //주문

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product; // 주문한 상품
    private int orderPrice; // 주문 당시 상품 가격 (변동 방지)
    private Long productId;           // 주문한 상품 ID
    private int quantity;             // 주문 수량
    private String productName;       // 주문 당시의 상품명 (상품명이 바뀌어도 주문 당시 값은 유지)
    private Long userId;

    // 상품 옵션 등도 필드로 추가

    public void setOrder(Order order){
        this.order=order;
    }

    //상품 주문 생성
    public static OrderItem createOrderItem(Product product,int quantity){
        OrderItem item = new OrderItem();
        item.product= product;
        item.quantity= quantity;
        item.orderPrice = product.getPrice().intValue(); //주문 당시 가격 고정

        return item;
    }

    //개별 주문 항목의 총 가격 계산
    public int calculateTotalPrice() {
        return orderPrice * quantity;
    }

}