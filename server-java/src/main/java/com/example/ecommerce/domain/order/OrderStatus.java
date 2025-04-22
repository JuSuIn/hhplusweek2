package com.example.ecommerce.domain.order;// 주문 상태를 나타내는 enum
// 주문 처리 단계(예: 주문생성, 결제 완료, 주문취소 등)를 표현

/*
   주문 처리 단계
 */
public enum OrderStatus {
    CREATED,    // 생성됨
    PAID,       // 결제 완료
    CANCELLED   // 주문 취소
}