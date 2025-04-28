package com.example.ecommerce.domain.wallet;// 사용자 지갑 (잔액 상태 포함)
// 잔액만 다룸

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Wallet{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; //사용자 ID
    private Long balance; //지갑 잔액(원 단위)

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private WalletType type;//지갑 타입( 예 ) 기본 , 포인트 등 )

    public Wallet(){}

    public Wallet(Long userId, Long initialBalance) {
        this.userId = userId;
        this.balance = initialBalance;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    //충전 메서드 (해당 부분이 잔액 관련 도메인 처리 하는 부분이라 "잔액" 도메인에서 책임져야 되는 사항 일것
    //같아 도메인에 넣었습니다.
    public void deposit(Long amount){
        if(amount <= 0){
            throw new IllegalArgumentException("충전 금액은 0 보다 커야 합니다.");
        }
        this.balance += amount;
        this.updatedAt=LocalDateTime.now();
    }

    //출금 메서드
    public void withdraw(Long amount){
        if(amount <= 0){
            throw new IllegalArgumentException("충전 금액은 0 보다 커야 합니다.");
        }
        if(this.balance < amount){
            throw new IllegalStateException("잔액이 부족합니다.");
        }
        this.balance -= amount;
        this.updatedAt=LocalDateTime.now();
    }

    //잔액 조회
    public Long getBalance(){
        return this.balance;
    }

    //사용자 ID 조회
    public Long getUserId(){
        return this.userId;
    }

    public Long getId() {
        return id;
    }

}