
package com.example.ecommerce.presentation.product;

import com.example.ecommerce.domain.catalog.Product;

/*
   상품 정보 응답용
 */
public class ProductDto {
    private Long pro_Id; //상품 ID
    private String productName; //상품명
    private int quantity; //상품수량
    private Long price; //상품 가격
    private String specification;//상품 규격
    private String unit;//상품 단위
    private String description; //상품 설명
    private String thumbnailImageUrl;//상품목록용이미지(imageUrl)

    public ProductDto(Product product){
        this.pro_Id=product.getPro_Id();
        this.productName=product.getProductName();
        this.quantity=product.getQuantity();
        this.price=product.getPrice();
        this.specification=product.getSpecification();
        this.unit=product.getUnit();
        this.description=product.getDescription();
        this.thumbnailImageUrl= product.getThumbnailImageUrl();
    }
}
