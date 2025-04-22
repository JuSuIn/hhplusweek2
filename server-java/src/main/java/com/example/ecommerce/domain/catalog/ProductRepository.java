// 인터페이스에 있음 (비즈니스 입장에서의 계약)
//순수 비즈니스 인터페이스

package com.example.ecommerce.domain.catalog;
import com.example.ecommerce.domain.catalog.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 *  상품에 대한 순수 비즈니스만 넣음
 *  전체 상품 목록 조회
 *  특정 상품 조회 (상품 ID ,상품명등)
 *  상세 내역 상품 조회
 *  최근 상품 들만 조회
 * */

public interface ProductRepository {
    List<Product> findAll(Pageable pageable); //전체 상품 목록 조회 (수천개 페이징 처리도 고려)

    //특정 상품 조회 (상품 ID ,상품명등)
    List<Product> findByKeyword(String keyword, ProductSearchType productSearchType);
    List<Product> findBypro_IdContaining(String keyword);
    List<Product> findByproductNameContaining(String keyword);

    Optional<Product> findById(Long id); //상세 내역 상품 조회
    List<Product> findRecentProducts(); //최근 상품 들만 조회

}