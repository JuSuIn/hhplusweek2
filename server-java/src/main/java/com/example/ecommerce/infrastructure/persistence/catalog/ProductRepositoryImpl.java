package com.example.ecommerce.infrastructure.persistence.catalog;//  JPA를 사용한 구현체 (@Repository)
//ProductRepository를 implements

import com.example.ecommerce.domain.catalog.Product;
import com.example.ecommerce.domain.catalog.ProductRepository;
import com.example.ecommerce.domain.catalog.ProductSearchType;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 *  상품에대해
 *  //ProductRepository를 implements
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

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final EntityManager em;

    /**
     *  전체 상품 목록 조회 (수천개 페이징 처리도 고려)
     * */
    @Override
    public List<Product> findAll(Pageable pageable) {
        return em.createQuery("SELECT p FROM Product p", Product.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }

    /**
     *  1) 특정 상품 조회 (상품 ID ,상품명등)
     * */
    @Override
    public List<Product> findByKeyword(String keyword, ProductSearchType productSearchType) {
        if(productSearchType == ProductSearchType.PRODUCT_ID){
            return findBypro_IdContaining(keyword);
        }else if(productSearchType == ProductSearchType.PRODUCT_NAME){
            return findByproductNameContaining(keyword);
        }

        return List.of();
    }

    /**
     *  2) 특정 상품 조회 (상품 ID ,상품명등)
     * */
    @Override
    public List<Product> findBypro_IdContaining(String keyword) {
        return em.createQuery("SELECT p FROM Product p WHERE str(p.pro_Id) LIKE :keyword",Product.class)
                .setParameter("keyword,","%"+ keyword+"%")
                .getResultList();
    }

    /**
     *  3) 특정 상품 조회 (상품 ID ,상품명등)
     * */
    @Override
    public List<Product> findByproductNameContaining(String keyword) {
        return em.createQuery("SELECT p FROM Product p WHERE p.productName LIKE :keyword", Product.class)
                .setParameter("keyword", "%" + keyword + "%")
                .getResultList();
    }

    /**
     *  상세 내역 상품 조회
     * */
    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(em.find(Product.class,id));
    }

    /**
     *  최근 상품 들만 조회
     * */
    @Override
    public List<Product> findRecentProducts() {
        return em.createQuery("SELECT p FROM Product p ORDER BY p.createdAt DESC",Product.class)
                .setMaxResults(10)
                .getResultList();
    }

    /**
     *  카테고리 조회
     * */
    @Override
    public List<Product> findByCategoryId(Long categoryId) {
        return em.createQuery("SELECT p FROM Product p WHERE p.category.id = :id",Product.class)
                .setParameter("id",categoryId)
                .getResultList();
    }

    /**
     *  테그 네임 조회
     * */
    @Override
    public List<Product> findByTagName(String tagName) {
        return em.createQuery("SELECT DISTINCT p FROM Product p JOIN p.tags t WHERE t.name = :tagName",Product.class)
                .setParameter("tagName",tagName)
                .getResultList();
    }

    /**
     *  테그 아이디 조회
     * */
    @Override
    public List<Product> findByTagIds(List<Long> tagIds) {
        return em.createQuery("SELECT DISTINCT p FROM Product p JOIN p.tags t WHERE t.id IN :ids",Product.class)
                .setParameter("ids",tagIds)
                .getResultList();
    }

    /**
     *  상품 저장
     *  등록 또는 수정
     * */
    @Override
    public Product save(Product product) {
        if(product.getPro_Id() == null){
            em.persist(product);
            return product;
        }else {
            return em.merge(product);
        }
    }

    /**
     *  상품 삭제
     *  실제 삭제 또는 soft delete 용도
     * */
    @Override
    public void delete(Product product) {
        em.remove(em.contains(product) ? product : em.merge(product));
    }

    /**
     * 등록 시 유효성 검사용
     */
    @Override
    public boolean existsByName(String name) {
        Long count = em.createQuery("SELECT COUNT(p) FROM Product p WHERE p.productName = :name",Long.class)
                .setParameter("name",name)
                .getSingleResult();
        return count > 0;

    }
}