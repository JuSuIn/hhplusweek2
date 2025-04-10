package com.example.ecommerce.domain.catalog;// Product.java  상품 정보 (이름, 가격, 설명 등)
/*
   상품 정보 도메인
 */


import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id @GeneratedValue
    private Long pro_Id; //상품 ID
    private String productName; //상품명
    private int quantity; //상품수량
    private Long price; //상품 가격
    private String specification;//상품 규격
    private String unit;//상품 단위
    private String description; //상품 설명
    private String thumbnailImageUrl;//상품목록용이미지(imageUrl)

    @ManyToOne(fetch = FetchType.LAZY)

    //@JoinColumn(name = "category_id")
    //private Category category;      //카테코리 조회

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @CreatedDate
    private LocalDateTime createdAt;//상품등록일
    @LastModifiedDate
    private LocalDateTime updatedAt;//상품수정일

}