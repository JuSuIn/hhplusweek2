package com.example.ecommerce.domain.wallet;

import com.example.ecommerce.domain.wallet.WalletTransaction;

import java.util.List;

/**
 * WalletTransaction 리포지토리
 */

public interface WalletTransactionRepository {
    WalletTransaction save(WalletTransaction transaction);

    List<WalletTransaction> findByWalletId(Long walletId);
}