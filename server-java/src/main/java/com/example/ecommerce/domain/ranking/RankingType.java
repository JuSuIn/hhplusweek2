package com.example.ecommerce.domain.ranking;// 랭킹 종류 enum (판매량, 조회수 등)

/*
* 판매량 랭킹 종류
*
* */
public enum RankingType {
    SALES,      // 판매량 기준
    VIEWS,      // 조회수 기준
    REVIEWS,     // 리뷰 수 기준
    LIKES //좋아요 기준
}
