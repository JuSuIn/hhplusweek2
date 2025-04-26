package com.example.ecommerce.domain.coupon;// 정액 할인, 정률 할인 등 구분 (선택)

/*
   쿠폰 상태 지정 : 정액확인,정률확인
 */
public enum CouponType {
    AMOUNT,      // 정액 할인
    RATE        // 정률 할인

    /*
    *  현재 타입이 정액 할인 타입인지 여부 반환
    *   @return ture - 정액 할인 , false - 정률 할인
    * */
    public boolean isAmountDiscount(){
        return this == AMOUNT;
    }

    /**
     * 현재 타입이 정률 할인 타입인지 여부 반환
     * @return true - 정률 할인, false - 정액 할인
     */
    public boolean isRateDiscount(){
        return this == RATE;
    }

}