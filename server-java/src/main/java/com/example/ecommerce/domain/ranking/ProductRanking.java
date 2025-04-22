package com.example.ecommerce.domain.ranking;// 인기 상품 랭킹 도메인 객체


import com.example.ecommerce.domain.catalog.Product;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

/*
  인기 상품 관련 도메인 처리
 */
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
    LocalDateTime calculatedAt) {
        this.product = product;
        this.rankingType = rankingType;
        this.rank = rank;
        this.calculatedAt = calculatedAt;
    }


}