package com.example.ecommerce.domain.wallet;

import com.example.ecommerce.domain.wallet.Wallet;

import java.util.Optional;

/**
 * Wallet 도메인 리포지토리 인터페이스
 * 지갑 데이터 접근을 책임
 */

public interface WalletRepository {

    Wallet save(Wallet wallet);

    Optional<Wallet> findById(Long id);

    Optional<Wallet> findByUserId(Long userId);

}