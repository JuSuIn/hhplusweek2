package com.example.ecommerce.domain.wallet;//지갑유형 (카드,포인트,충전금등)
/*
 *  잔액의 종류를 다루는
 *
 * */

public enum WalletType {
    CASH,        // 현금성 자산
    POINT,       // 적립 포인트
    COUPON       // 쿠폰성 자산 (optional)
}