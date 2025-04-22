
package com.example.ecommerce.presentation.dto;

import com.example.ecommerce.domain.catalog.Product;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

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

    // 상품 등록
    @Getter
    @Setter
    public static class Create {
        @NotNull
        private Long pro_Id; //상품 ID
        @NotNull
        private String productName; //상품명
        @NotNull
        private int quantity; //상품수량
        private Long price; //상품 가격
        private String specification;//상품 규격
        private String unit;//상품 단위
        private String description; //상품 설명
        private String thumbnailImageUrl;//상품목록용이미지(imageUrl)

        public Create() {}
        public Create(ProductDto dto) {}
    }

    // 상품 수정

    public static class Update extends Create{
        @NotNull
        private Long pro_Id; //상품 ID
    }


    //상품 삭제
    @Getter
    public static class Delete {
        @NotNull
        private Long pro_Id; //상품 ID

        public Delete() {}
        public Delete(ProductDto dto) {}
    }

}
