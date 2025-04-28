package com.example.ecommerce.domain.wallet;// 잔액 변경 내역 (충전/사용 등)
// 잔액의 이력을 다룸

import com.example.ecommerce.domain.wallet.Wallet;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

/**
 * 잔액(지갑) 거래 기록
 */

@Entity
@Getter
public class WalletTransaction {
    @Id
    @GeneratedValue
    private Long id; //잔액의 ID

    private Long walletId; //지갑 ID

    private Long amount; //거래 금액( 양수 : 충전, 음수 : 사용)

    @ManyToOne(fetch = FetchType.LAZY)
    private Wallet wallet; //잔액 정보

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;      // 입출금 타입

    private String description;                   // 예: "주문 결제", "잔액 충전"

    @CreatedDate
    private LocalDateTime createdAt;

    private LocalDateTime transactionAt;

    protected WalletTransaction() {}

    public WalletTransaction(Long walletId,Long amount,String description){
        this.walletId=walletId;
        this.amount=amount;
        this.description=description;
        this.transactionAt=LocalDateTime.now();
    }

    //입금 상태 인지  ..
    public boolean isDeposit(){
        return this.transactionType == TransactionType.DEPOSIT;
    }
    //출금 상태 인지..
    public boolean isWithdrawal(){
        return this.transactionType == TransactionType.WITHDRAWAL;
    }
}