package com.example.ecommerce.domain.catalog;// enum (판매중, 품절 등)

/*
   상품 정보 상태 지정 : 판매중,품절,판매중지
 */
public enum ProductStatus {
    AVAILABLE, // 판매중
    OUT_OF_STOCK, // 품절
    DISCONTINUED // 판매중지
}