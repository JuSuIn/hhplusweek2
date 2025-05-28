package com.example.ecommerce.application.order;

import com.example.ecommerce.application.ranking.RedisRankingService;
import com.example.ecommerce.domain.catalog.Product;
import com.example.ecommerce.domain.catalog.ProductRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import redis.embedded.RedisServer;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/***
 *  주문생성시 인기상품 관련 통합테스트 진행
 */
@SpringBootTest(classes =com.example.ecommerce.EcommerceApplication.class)
@AutoConfigureMockMvc
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class OrderServiceIntegrationTest {
    private RedisServer redisServer; //test 할 redisServer 생성

    @BeforeAll
    void startRedis() throws IOException {
        redisServer = new RedisServer(6379);
        redisServer.start();
    }

    @AfterAll
    void stopRedis() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }
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
        Product saved= productRepository.save(product);
        System.out.println("Saved ID: " + product.getPro_Id());
        Long productId = saved.getPro_Id();

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
