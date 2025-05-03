package com.example.ecommerce.application.ranking;

import com.example.ecommerce.domain.catalog.Product;
import com.example.ecommerce.domain.ranking.ProductRanking;
import com.example.ecommerce.domain.ranking.ProductRankingRepository;
import com.example.ecommerce.domain.ranking.RankingType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/***
 * 인기 상품 랭킹 서비스단 로직 구현
 *
 */

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RankingService {

    private final ProductRankingRepository productRankingRepository;

    /*
    * 랭킹 타입별 상위 랭킹 리스트 조회
    * */
    public List<ProductRanking> getTopRankings(RankingType rankingType,int limit){
        return productRankingRepository.findTopRankingByType(rankingType,limit);
    }

    /**
     *  특정 상품의 랭킹 조회
     * */
    public Optional<ProductRanking> getRankingByProduct(Long productId,RankingType rankingType){
        return productRankingRepository.findByProductIdAndType(productId,rankingType);
    }

    /**
     *  오래된 랭킹 삭제
     * */
    public void cleanOldRankings(LocalDateTime beforeDateTime){
        List<ProductRanking> oldRankings = productRankingRepository.findAllCalculatedBefore(beforeDateTime);
        oldRankings.forEach(productRankingRepository::delete);
    }

    /**
     * 랭킹 업데이트 로직( 예 : 매일 밤 배치로 실행)
     * */
    public void updateRankings(List<Product> topProducts,RankingType rankType){
        int rank = 1;
        LocalDateTime now = LocalDateTime.now();
        for(Product product : topProducts){
            ProductRanking ranking = new ProductRanking(product,rankType,rank++,now);
            productRankingRepository.save(ranking);
        }
    }

}