// Product.java  상품 정보 (이름, 가격, 설명 등)
package com.example.ecommerce.domain.catalog;
/*
   상품 정보 도메인
 */


import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import jakarta.persistence.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
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
    private Category category; //카테고리

    @ManyToMany
    private List<Tag> tags = new ArrayList<>(); //테그관련

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @CreatedDate
    private LocalDateTime createdAt;//상품등록일
    @LastModifiedDate
    private LocalDateTime updatedAt;//상품수정일

    //soft delete  여부 확인용 필드 추가
    private boolean deleted = false;

    public Product(Long pro_Id,
                   String productName,
                   int quantity,
                   Long price,
                   String specification,
                   String unit,
                   String description,
                   String thumbnailImageUrl
                   )
    {
        this.pro_Id=pro_Id;
        this.productName=productName;
        this.quantity=quantity;
        this.price=price;
        this.specification=specification;
        this.unit=unit;
        this.description=description;
        this.thumbnailImageUrl=thumbnailImageUrl;
    }

    public Product(String productName,
                   int quantity,
                   Long price,
                   String specification,
                   String unit,
                   String description,
                   String thumbnailImageUrl
    )
    {
        this.productName=productName;
        this.quantity=quantity;
        this.price=price;
        this.specification=specification;
        this.unit=unit;
        this.description=description;
        this.thumbnailImageUrl=thumbnailImageUrl;
    }


    // 상품 정보 수정용 도메인 메서드
    public void update(Product product
                       //String name,int quantity,Long price,
                       //String spec, String unit,
                       //String description, String thumbnailImageUrl,
                       //Category category, List<Tag> tags
                       )
    {
        this.productName=product.getProductName();
        this.quantity=product.getQuantity();
        this.price=product.getPrice();
        this.specification=product.getSpecification();
        this.unit=product.getUnit();
        this.description=product.getDescription();
        this.thumbnailImageUrl=product.getThumbnailImageUrl();
        this.category=product.getCategory();
        this.tags=product.getTags();
    }

    //soft delete 도메인 메서드
    public void markAsDeleted(){
        this.deleted=true;
    }

}