package com.example.ecommerce.application.ranking;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/***
 * 실시간 기반 인기 상품 조회 (redis 기반)
 */
@Service
@RequiredArgsConstructor
public class RedisRankingService {

    private final RedisTemplate<String,String> redisTemplate;
    private static final String DAILY_RANKING_KEY="ranking:product:daily";


    /**
     * 실시간으로 상품이 얼마나 많이 팔렸는지 수치를 계속 누적시켜서 랭킹 점수로 사용
     * 상품이 주문이 될 때마다 해당 상품의 점수를 redis Sorted set에 증가
     * @param productId 상품 ID
     * @param score     증가할 점수 (주문 수량 등)
     */
    public void increaseScore(Long productId,int score){
        redisTemplate.opsForZSet().incrementScore(DAILY_RANKING_KEY,productId.toString(),score);
    }

    /**
     * 인기 상품 목록 화면에 실시간 인기 TOP 10을 보여줌 (조회)
     * Redis Sorted Set에서 score가 높은 순으로 TOP N개 상품의 ID를 가져옴
     * @param limit 조회할 상품 개수 (ex. TOP 10)
     * @return 상품 ID 목록
     */

    public List<Long> getTopRankedProductIds(int limit){
        Set<String> ids=redisTemplate.opsForZSet().reverseRange(DAILY_RANKING_KEY,0,limit-1);
        return ids.stream().map(Long::valueOf).collect(Collectors.toList());
    }


    /**
     * 하루 랭킹 데이터 초기화
     * 자정에 랭킹 초기화 스케줄러에서 호출
     */
    public void clearRanking(){
        redisTemplate.delete(DAILY_RANKING_KEY);
    }

    /**
     * 테스트용 Redis 에서 특정 상품의 Score 조회 가능 메서드
     * */
    public Double getScore(Long productId){
        return redisTemplate.opsForZSet().score("ranking:product:daily",productId.toString());
    }

}
