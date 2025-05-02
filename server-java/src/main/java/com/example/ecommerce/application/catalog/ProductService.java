// 상품의 도메인 관련 호출,트랜잭션 제어
package com.example.ecommerce.application.catalog;
/*
*  상품의 도메인 관련 호출
* 1. 상품을 등록함
* 2. 상품을 수정함
* 3. 상품을 삭제함
*
* */

import com.example.ecommerce.domain.catalog.Product;
import com.example.ecommerce.domain.catalog.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    //1.상품을 등록함
    public Product registerProduct(Product product) {
        if (productRepository.existsByName(product.getProductName())) {//유효성 검사
            throw new IllegalArgumentException("이미 존재하는 상품이름 입니다.");
        }
        return productRepository.save(product);
    }

    //2.상품을 수정함
    public Product updateProduct(Long id,Product updated){
        Product product  = productRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("상품이 존재하지 않습니다. "));
        product.update(updated); //product 도메인
        return productRepository.save(product);
    }

    //3.상품을 삭제함
    public void deleteProduct(Long id){
        Product product =productRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("상품이 존재하지 않습니다."));

        product.markAsDeleted();
        productRepository.delete(product);
    }
}
