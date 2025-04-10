package com.example.ecommerce.domain.payment;// 결제 상태를 나타내는 enum
// 결제 대기, 승인됨, 실패됨, 취소됨 등.

/*
  결제 관련 도메인 상태 정보 모음
 */

public enum PaymentStatus {
    READY,      // 결제 대기
    COMPLETED,  // 결제 완료
    FAILED,     // 결제 실패
    CANCELLED   // 결제 취소
}