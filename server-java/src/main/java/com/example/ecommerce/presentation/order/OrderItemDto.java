package com.example.ecommerce.presentation.order;

import com.example.ecommerce.domain.order.OrderItem;

public class OrderItemDto {
    private Long id; //주문한 아이템 ID
    private Long productId;           // 주문한 상품 ID
    private String productName;       // 주문 당시의 상품명
    private int quantity;             // 주문 수량
    private int orderPrice; // 주문 당시 상품 가격 (변동 방지)

    public OrderItemDto(OrderItem orderItem){
        this.id = orderItem.getId();                                     //주문한 아이템 ID
        this.productId = orderItem.getProductId();                        // 주문한 상품 ID
        this.productName = orderItem.getProduct().getProductName();       // 주문 당시의 상품명
        this.quantity = orderItem.getProduct().getQuantity();             // 주문 수량
        this.orderPrice = orderItem.getOrderPrice();                     // 주문 당시 상품 가격 (변동 방지)
    }
}
