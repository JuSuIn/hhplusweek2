package com.example.ecommerce.domain.ranking;// 인기 상품 조회용 리포지토리

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 *
 * ProductRankingRepository
 *
 * 인기 상품 랭킹 데이터를 관리하는 도메인 레이어 인터페이스.
 * */

public interface ProductRankingRepository
{
    /**
     * 특정 랭킹 타입별 상위 N개의 랭킹 데이터를 조회.
     * 예: 판매량 기준 상위 10개 상품 랭킹
     *
     * @param rankingType 랭킹 기준 타입 (예: SALES, VIEWS)
     * @param limit 가져올 최대 개수
     * @return 상위 랭킹 리스트
     */
    List<ProductRanking> findTopRankingByType(RankingType rankingType, int limit);

    /**
     * 특정 상품 ID와 랭킹 타입에 해당하는 랭킹 데이터를 조회.
     *
     * @param productId 상품 ID
     * @param rankingType 랭킹 기준 타입
     * @return 해당 상품의 랭킹 정보 (없을 경우 Optional.empty)
     */
    Optional<ProductRanking> findByProductIdAndType(Long productId, RankingType rankingType);


    /**
     * 랭킹 데이터를 저장 또는 갱신.
     *
     * @param productRanking 저장할 ProductRanking 엔티티
     */
    void save(ProductRanking productRanking);


    /**
     * 랭킹 데이터를 삭제.
     *
     * @param productRanking 삭제할 ProductRanking 엔티티
     */
    void delete(ProductRanking productRanking);

    /**
     * 특정 시점 이전에 계산된 모든 랭킹 데이터를 조회.
     * 주로 오래된 랭킹 데이터를 청소하거나 업데이트할 때 사용.
     *
     * @param dateTime 기준 시각
     * @return 기준 시각 이전의 랭킹 리스트
     */
    List<ProductRanking> findAllCalculatedBefore(LocalDateTime datetime);

}
