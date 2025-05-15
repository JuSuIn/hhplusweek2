package com.example.ecommerce.infrastructure.persistence.wallet;

import com.example.ecommerce.domain.wallet.Wallet;
import com.example.ecommerce.domain.wallet.WalletRepository;
import com.example.ecommerce.domain.wallet.WalletTransaction;
import com.example.ecommerce.domain.wallet.WalletTransactionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 *  지갑관련
 *  *  WalletRepository  ,WalletTransactionRepository implements
 * Wallet 지갑 데이터 접근을 책임
 */
@Repository
public class WalletRepositoryImpl implements WalletRepository, WalletTransactionRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Wallet save(Wallet wallet) {
        if (wallet.getId() == null) {
            em.persist(wallet);
            return wallet;
        } else {
            return em.merge(wallet);
        }
    }

    @Override
    public Optional<Wallet> findById(Long id) {
        return Optional.ofNullable(em.find(Wallet.class, id));
    }

    @Override
    public Optional<Wallet> findByUserId(Long userId) {
        return em.createQuery("SELECT w FROM Wallet w WHERE w.userId = :userId", Wallet.class)
                .setParameter("userId", userId)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public WalletTransaction save(WalletTransaction transaction) {
        if (transaction.getId() == null) {
            em.persist(transaction);
            return transaction;
        } else {
            return em.merge(transaction);
        }
    }

    @Override
    public List<WalletTransaction> findByWalletId(Long walletId) {
        return em.createQuery("SELECT t FROM WalletTransaction t WHERE t.wallet.id = :walletId", WalletTransaction.class)
                .setParameter("walletId", walletId)
                .getResultList();
    }
}
