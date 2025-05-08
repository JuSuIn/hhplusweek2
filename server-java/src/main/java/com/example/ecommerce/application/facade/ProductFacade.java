package com.example.ecommerce.application.facade;

import com.example.ecommerce.application.catalog.ProductService;
import com.example.ecommerce.application.ranking.RankingService;
import com.example.ecommerce.domain.catalog.Product;
import com.example.ecommerce.domain.ranking.ProductRanking;
import com.example.ecommerce.domain.ranking.RankingType;
import com.example.ecommerce.presentation.product.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  상품 관련 파사드
 */
@Service
@RequiredArgsConstructor
public class ProductFacade {

    private final ProductService productService;
    private final RankingService rankingService;

    //상품 등록
    public ProductDto registerProduct(ProductDto productDto){
       Product product = productDto.toEntityForCreate(); // DTO → Entity
       Product saveProduct = productService.registerProduct(product);
       return new ProductDto(saveProduct);
    }

    //상품 수정
    public ProductDto updateProduct(Long id,ProductDto productDto){
        Product product=productDto.toEntityForUpdate();
        Product updated=productService.updateProduct(id,product);
        return new ProductDto(updated);
    }

    //상품 삭제
    public void deleteProduct(Long id){
        productService.deleteProduct(id);
    }

    //인기상품조회 ( 랭킹 타입별 상위 랭킹 리스트 조회)
    public List<ProductDto> getTopRankings(RankingType rankingType, int limit){
        List<ProductRanking> rankings = rankingService.getTopRankings(rankingType,limit);

        // Product 엔티티로 변환 (예: ProductId로 Product 조회 → ProductDto로 변환)
        return rankings.stream()
                .map(r -> new ProductDto(r.getProduct()))
                .collect(Collectors.toList());
    }



}
