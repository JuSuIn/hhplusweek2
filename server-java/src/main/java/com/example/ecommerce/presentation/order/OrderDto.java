package com.example.ecommerce.presentation.order;

import com.example.ecommerce.domain.order.Order;
import com.example.ecommerce.domain.order.OrderItem;
import com.example.ecommerce.domain.order.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDto {
    private Long id;                            //주문 ID
    private Long userId;                      // 주문한 사용자 ID
    private List<OrderItemDto> orderItems;      // 주문항목 도메인
    private LocalDateTime orderedAt;         // 주문 일시
    private String status;                  // 주문 상태 (예: 주문 생성CREATED, PAID, 주문 취소 CANCELLED)
    private Long totalPrice;                // 총 주문 금액 (할인 적용 후)
    public OrderDto(Order order){
        this.id = order.getId();                                //주문 ID
        this.userId = order.getUserId();                        // 주문한 사용자 ID
        this.orderItems = order.getOrderItems().stream()
                .map( OrderItemDto::new)
                .collect(Collectors.toList());                // 주문항목 도메인
        this.orderedAt = order.getOrderedAt();                  // 주문 일시
        this.status = order.getStatus().name();                 // 주문 상태 (예: 주문 생성CREATED, PAID, 주문 취소 CANCELLED)
        this.totalPrice=order.getTotalPrice();                  // 총 주문 금액 (할인 적용 후)
    }
}
