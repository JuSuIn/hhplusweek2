// 상품 조회 관련 분리 시 -> 기본 상품 내역 조회, 상세내역조회 ,최근 상품내역 조회
package com.example.ecommerce.application.catalog;

import com.example.ecommerce.domain.catalog.Product;
import com.example.ecommerce.domain.catalog.ProductRepository;
import com.example.ecommerce.domain.catalog.ProductSearchType;
import com.example.ecommerce.presentation.product.ProductDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/** 상품 조회 서비스
=======
/** 상품 조회 전용 서비스
 *  1.전체 상품 목록 조회
 *  2.특정 상품 조회 (상품 ID ,상품명등)
 *  3.상세 내역 상품 조회
 *  4.최근 상품 들만 조회
 *  5. 특정 카테고리 상품 조회
 *  6. 특정 태그 상품 조회
 *  7. 태그 ID 목록으로 상품 조회
 * */
@Service
@RequiredArgsConstructor
public class ProductQueryService {

    private final ProductRepository productRepository;

    // 1.전체 상품 목록 조회
    public List<Product> findAll(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    //2.특정 상품 조회 (상품 ID ,상품명등)
    public List<Product> findByKeyword(String keyword, ProductSearchType productSearchType){
        if(productSearchType == ProductSearchType.PRODUCT_ID)
            return productRepository.findBypro_IdContaining(keyword);
        else if(productSearchType == ProductSearchType.PRODUCT_NAME)
            return productRepository.findByproductNameContaining(keyword);

        return Collections.emptyList();
    }

    //3.상세 내역 상품 조회
    public Optional<Product> findById(Long id){
       return Optional.ofNullable(productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("상품을 찾을 수 없습니다.")));
    }

    //4.최근 상품 들만 조회
    public List<Product> findRecentProducts(){
        return productRepository.findRecentProducts();
    }

    // 5. 특정 카테고리 상품 조회
    public List<Product> findByCategoryId(Long categoryId){
        return productRepository.findByCategoryId(categoryId);
    }

    // 6. 특정 태그 상품 조회
    public List<Product> findByTagName(String tagName){
        return productRepository.findByTagName(tagName);
    }

    // 7. 태그 ID 목록으로 상품 조회
    public List<Product> findByTagIds(List<Long> tabIds){
        if(tabIds == null || tabIds.isEmpty()){
            throw new IllegalArgumentException("태그 ID 리스트가 비어 있습니다.");
        }
        return productRepository.findByTagIds(tabIds);
    }

}


