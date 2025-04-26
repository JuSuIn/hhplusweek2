package com.example.ecommerce.domain.payment;// 결제 수단을 표현하는 열거형입니다.

/*
* 결제 수단
* */

import java.time.LocalTime;

public enum PaymentMethod {

    CARD {
        @Override
        public void validateAvailability() {
            // 카드 결제는 항상 가능하다고 가정
        }
    },
    KAKAO_PAY {
        @Override
        public void validateAvailability() {
            if (!isKakaoPayEnabled()) {
                throw new IllegalStateException("카카오페이 결제가 현재 불가능합니다.");
            }
        }

        private boolean isKakaoPayEnabled() {
            // 외부 설정이나 상태 체크 (예: DB, 환경변수, API)
            return true; // 예시
        }
    },
    NAVER_PAY {
        @Override
        public void validateAvailability() {
            // 예시로 시간 제한
            LocalTime now = LocalTime.now();
            if (now.isBefore(LocalTime.of(6, 0)) || now.isAfter(LocalTime.of(23, 0))) {
                throw new IllegalStateException("네이버페이 결제는 06:00~23:00 사이에만 가능합니다.");
            }
        }
    },
    BANK_TRANSRER {
        @Override
        public void validateAvailability(){
            //시간 제한
            LocalTime now = LocalTime.now();
            if(now.isBefore(LocalTime.of(24,30)) || now.isAfter(LocalTime.of(23,50))){
                throw new IllegalStateException("계좌이체는 23:50~0:30 사이에는 계좌이체 사용이 불가능합니다.");
            }
        }
    },
    MOBILE_PAYMENT {
        @Override
        public void validateAvailability() {
            //휴대폰 결제는 항상 사용가능하다는 가정
        }
    };

    public abstract void validateAvailability();
}
