package com.example.ecommerce.application.wallet;//충전 잔액 관련 도메인 관련 호출,트랜잭션 제어

import com.example.ecommerce.domain.wallet.Wallet;
import com.example.ecommerce.domain.wallet.WalletRepository;
import com.example.ecommerce.domain.wallet.WalletTransaction;
import com.example.ecommerce.domain.wallet.WalletTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *  Wallet 관련 비즈니스 로직 처리
 * */

@Service
@RequiredArgsConstructor
@Transactional
public class WalletService {

    private final WalletRepository walletRepository;
    private final WalletTransactionRepository walletTransactionRepository;

    /*
    *  지갑 잔액 충전
    *
    * */
    public void deposit(Long userId,Long amount,String description){
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow( () -> new IllegalArgumentException("지갑을 찾을 수 없습니다."));

        wallet.deposit(amount);
        walletRepository.save(wallet);

        WalletTransaction transaction = new WalletTransaction(wallet.getId(), amount,description);
        walletTransactionRepository.save(transaction);
    }

    /**
     *  자갑 잔액 출금
     * */
    public void withraw(Long userId,Long amount,String description){
        Wallet wallet =walletRepository.findByUserId(userId)
                .orElseThrow( () -> new IllegalArgumentException("지갑을 찾을 수 없습니다."));

        wallet.withdraw(amount);
        walletRepository.save(wallet);

        WalletTransaction transaction=new WalletTransaction(wallet.getId(),-amount,description);
        walletTransactionRepository.save(transaction);
    }


    /**
     * 지갑 잔액 조회
     */
    @Transactional(readOnly = true)
    public Long getBalance(Long userId){
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow( () -> new IllegalArgumentException("지갑을 찾을 수 없습니다."));
        return wallet.getBalance();
    }

}