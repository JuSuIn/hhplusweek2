// 인터페이스에 있음 (비즈니스 입장에서의 계약)
//순수 비즈니스 인터페이스

package com.example.ecommerce.domain.catalog;
import com.example.ecommerce.domain.catalog.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 *  상품에 대한 순수 비즈니스만 넣음
 *
 *  --- 조회 관련 -----
 *  1.전체 상품 목록 조회
 *  2.특정 상품 조회 (상품 ID ,상품명등)
 *  3.상세 내역 상품 조회
 *  4.최근 상품 들만 조회
 *  5. 특정 카테고리 상품 조회
 *  6. 특정 태그 상품 조회
 *
 *  -- 등록 ,수정 ,삭제 -----
 *  1. 등록 또는 수정
 *  2. 실페 삭제 또는 soft delete 용도
 *  3. 등록시 유효성 검사용
 * */

public interface ProductRepository {
    List<Product> findAll(Pageable pageable); //전체 상품 목록 조회 (수천개 페이징 처리도 고려)

    //1) 특정 상품 조회 (상품 ID ,상품명등)
    List<Product> findByKeyword(String keyword, ProductSearchType productSearchType);
    List<Product> findBypro_IdContaining(String keyword);
    List<Product> findByproductNameContaining(String keyword);

    Optional<Product> findById(Long id); //상세 내역 상품 조회
    List<Product> findRecentProducts(); //최근 상품 들만 조회

    List<Product> findByCategoryId(Long categoryId); //카테고리 조회
    List<Product> findByTagName(String tagName); //테그 네임 조회
    List<Product> findByTagIds(@Param("tagIds") List<Long> tagIds); //테그 ID 기반 조회

    //2) 상품  등록, 수정 ,삭제
    Product save(Product product); // 등록 또는 수정
    void delete(Product product); //실제 삭제 또는 soft delete 용도
    boolean existsByName(String name);//등록 시 유효성 검사용
}