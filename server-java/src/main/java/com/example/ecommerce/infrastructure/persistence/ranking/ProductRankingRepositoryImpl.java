package com.example.ecommerce.infrastructure.persistence.ranking;

import com.example.ecommerce.domain.ranking.ProductRanking;
import com.example.ecommerce.domain.ranking.ProductRankingRepository;
import com.example.ecommerce.domain.ranking.RankingType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 *  인기 상품 랭킹 관련
 *  ProductRankingRepository implements
 *
 * */
@Repository
public class ProductRankingRepositoryImpl implements ProductRankingRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * 특정 랭킹 타입별 상위 N개의 랭킹 데이터를 조회.
     * 예: 판매량 기준 상위 10개 상품 랭킹
     *
     * @param rankingType 랭킹 기준 타입 (예: SALES, VIEWS)
     * @param limit 가져올 최대 개수
     * @return 상위 랭킹 리스트
     */
    @Override
    public List<ProductRanking> findTopRankingByType(RankingType rankingType, int limit) {
        return em.createQuery("SELECT pr FROM ProductRanking pr WHERE pr.rankingType = :rankingType ORDER BY pr.rank ASC", ProductRanking.class)
                .setParameter("rankingType", rankingType)
                .setMaxResults(limit)
                .getResultList();
    }

    /**
     * 특정 상품 ID와 랭킹 타입에 해당하는 랭킹 데이터를 조회.
     *
     * @param productId 상품 ID
     * @param rankingType 랭킹 기준 타입
     * @return 해당 상품의 랭킹 정보 (없을 경우 Optional.empty)
     */
    @Override
    public Optional<ProductRanking> findByProductIdAndType(Long productId, RankingType rankingType) {
        return em.createQuery("SELECT pr FROM ProductRanking pr WHERE pr.product.id = :productId AND pr.rankingType = :rankingType", ProductRanking.class)
                .setParameter("productId", productId)
                .setParameter("rankingType", rankingType)
                .getResultList()
                .stream()
                .findFirst();
    }

    /**
     * 랭킹 데이터를 저장 또는 갱신.
     *
     * @param productRanking 저장할 ProductRanking 엔티티
     */
    @Override
    public void save(ProductRanking productRanking) {
        if (productRanking.getId() == null) {
            em.persist(productRanking);
        } else {
            em.merge(productRanking);
        }
    }

    /**
     * 랭킹 데이터를 삭제.
     *
     * @param productRanking 삭제할 ProductRanking 엔티티
     */
    @Override
    public void delete(ProductRanking productRanking) {
        em.remove(em.contains(productRanking) ? productRanking : em.merge(productRanking));
    }

    /**
     * 특정 시점 이전에 계산된 모든 랭킹 데이터를 조회.
     * 주로 오래된 랭킹 데이터를 청소하거나 업데이트할 때 사용.
     *
     * @param dateTime 기준 시각
     * @return 기준 시각 이전의 랭킹 리스트
     */
    @Override
    public List<ProductRanking> findAllCalculatedBefore(LocalDateTime datetime) {
        return em.createQuery("SELECT pr FROM ProductRanking pr WHERE pr.calculatedAt < :datetime", ProductRanking.class)
                .setParameter("datetime", datetime)
                .getResultList();
    }
}
