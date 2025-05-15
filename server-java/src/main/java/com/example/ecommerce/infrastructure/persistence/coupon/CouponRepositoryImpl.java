package com.example.ecommerce.infrastructure.persistence.coupon;

import com.example.ecommerce.domain.coupon.Coupon;
import com.example.ecommerce.domain.coupon.CouponRepository;
import com.example.ecommerce.domain.coupon.CouponStatus;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 *  쿠폰관련
 *  CouponRepository implements
 *
 * 특정 사용자 쿠폰 조회
 * 쿠폰 전체 검색
 * 현재발행된 쿠폰 조회
 * 특정 사용자 쿠폰 조회
 * 모든 쿠폰 이력 조회
 * 사용자가 발급받은 쿠폰 목록
 * 수정
 * 삭제
 * 유효성 검사
 * */
@Repository
@RequiredArgsConstructor
public class CouponRepositoryImpl implements CouponRepository {

    private final EntityManager em;

    /**
     * 특정 사용자 쿠폰 조회
     * */
    @Override
    public Optional<Coupon> findById(Long id) {
        return Optional.ofNullable(em.find(Coupon.class,id));
    }

    /**
     * 쿠폰 전체 검색
     * */
    @Override
    public List<Coupon> findAll() {
        return em.createQuery("""
                SELECT c FROM Coupon c
                """,Coupon.class)
                .getResultList();
    }

    /**
     * 현재발행된 쿠폰 조회
     * */
    @Override
    public List<Coupon> findAvailableCoupons(Long userId,LocalDateTime now,Long orderTotal) {
        return em.createQuery("""
                SELECT c FROM Coupon c
                WHERE c.status = :status
                  AND c.expireAt > :now
                  AND c.minimumOrderPrice <= :orderTotal
                """,Coupon.class)
                .setParameter("status", CouponStatus.UNUSED)
                .setParameter("now", now)
                .setParameter("orderTotal", orderTotal)
                .getResultList();
    }

    /**
     * 특정 사용자 쿠폰 조회
     * */
    @Override
    public List<Coupon> findByUserId(Long userId) {
        return em.createQuery("SELECT c FROM Coupon c WHERE c.user.id = :userId", Coupon.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    /**
     * 모든 쿠폰 이력 조회
     * */
    @Override
    public List<Coupon> findAllCouponsByUser(Long userId) {
        return em.createQuery("SELECT c FROM Coupon c WHERE c.userId = :userId", Coupon.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    /**
     * 사용자가 발급받은 쿠폰 목록
     * */
    @Override
    public List<Coupon> findByUserIdAndStatus(Long userId, CouponStatus couponStatus) {
        return em.createQuery("""
            SELECT c FROM Coupon c
            WHERE c.userId = :userId AND c.status = :status
        """, Coupon.class)
                .setParameter("userId", userId)
                .setParameter("status", couponStatus)
                .getResultList();
    }

    /**
     * 수정
     * */
    @Override
    public Coupon save(Coupon coupon) {
        if (coupon.getId() == null) {
            em.persist(coupon);
        } else {
            em.merge(coupon);
        }

        return coupon;
    }

    /**
     * 삭제
     * */
    @Override
    public void delete(Coupon coupon) {
        em.remove(em.contains(coupon) ? coupon : em.merge(coupon));
    }

    /**
     * 유효성 검사 - id
     * */
    @Override
    public boolean existsById(Long userId) {
        Long count = em.createQuery("""
                SELECT COUNT(c) FROM Coupon c WHERE c.id = :id
            """, Long.class)
                .setParameter("id", userId)
                .getSingleResult();
        return count > 0;
    }

    /**
     * 유효성 검사 - 이름
     * */
    @Override
    public boolean existsByName(String name) {
        Long count = em.createQuery("""
                SELECT COUNT(c) FROM Coupon c WHERE c.name = :name
            """, Long.class)
                .setParameter("name", name)
                .getSingleResult();
        return count > 0;
    }
}
