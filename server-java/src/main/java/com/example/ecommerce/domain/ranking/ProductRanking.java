package com.example.ecommerce.domain.ranking;// 인기 상품 랭킹 도메인 객체


import com.example.ecommerce.domain.catalog.Product;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

/*
  인기 상품 관련 도메인 처리
 */
@Entity
@Getter
public class ProductRanking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product; //랭킹 대상 상품

    @Enumerated(EnumType.STRING)
    private RankingType rankingType; //판매량 기준의 상태

    private int rank; // 순위 (1위, 2위 ...)

    private LocalDateTime calculatedAt; // 랭킹 계산 시점

    protected  ProductRanking(){}

    public  ProductRanking(Product product,
        RankingType rankingType,
        int rank,
        LocalDateTime calculatedAt)
    {
        this.product = product;
        this.rankingType = rankingType;
        this.rank = rank;
        this.calculatedAt = calculatedAt;
    }

    /**
     * 랭킹 갱신 메서드 - 새로운 순위 + 계산 시점을 업데이트
     * @param newRank 새로운 순위
     * @param now 계산 시점
     */
    public void updateRank(int newRank,LocalDateTime calculatedAt){
        this.rank=newRank;
        this.calculatedAt=calculatedAt;
    }

    /**
     * 랭킹 타입 변경 메서드 - 랭킹 타입 변경
     * @param newRankingType 새로운 랭킹 타입
     */
    public void changeRankingType(RankingType newRankingType){
        this.rankingType=newRankingType;
    }

    /**
     * 랭킹 계산 후 최신 여부 판단 - 오늘 기준 최신 랭킹 데이터인지 확인
     * @param now 현재 시간
     * @return 최신이면 true, 아니면 false
     */
    public boolean isUpToDate(LocalDateTime now){
        return this.calculatedAt != null &&
                this.calculatedAt.toLocalDate().equals(now.toLocalDate());
    }


}