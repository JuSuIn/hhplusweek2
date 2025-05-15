package com.example.ecommerce.application.order;

import com.example.ecommerce.application.ranking.RedisRankingService;
import com.example.ecommerce.domain.catalog.Product;
import com.example.ecommerce.domain.catalog.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/***
 *  주문생성시 인기상품 관련 통합테스트 진행
 */
@SpringBootTest(classes =com.example.ecommerce.EcommerceApplication.class)
@AutoConfigureMockMvc
@Transactional
public class OrderServiceIntegrationTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RedisRankingService redisRankingService;

    @Autowired
    private ProductRepository productRepository;

    /** 주문 생성시 인기상품 점수 증가
     * 통합테스트
    *
    * */
    @Test
    void increaseScore_whenOrderPlaced(){
        //given

        Product product = new Product("상품1",10,1000L,"SPEC","UNIT","상품입니다.","이미지");
        productRepository.save(product);
        Long productId = product.getPro_Id();

        List<Long> productIds = List.of(productId);
        List<Integer> quantities = List.of(3);

        Long userId = 1L;

        //when
        orderService.createOrder(userId,productIds,quantities,null);

        //then
        Double score = redisRankingService.getScore(productId);
        assertThat(score).isEqualTo(3.0);
    }

}
