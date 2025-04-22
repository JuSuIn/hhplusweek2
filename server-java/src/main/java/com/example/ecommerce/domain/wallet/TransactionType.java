package com.example.ecommerce.domain.wallet;
/*
*  잔액의 입출금을  상황을 관리하는 enum
* */

public enum TransactionType {
    DEPOSIT,     // 입금 (충전)
    WITHDRAWAL   // 출금 (결제, 환불 등)
}