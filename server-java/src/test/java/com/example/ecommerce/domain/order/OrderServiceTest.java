package com.example.ecommerce.domain.order;

import com.example.ecommerce.application.order.OrderService;
import com.example.ecommerce.application.ranking.RedisRankingService;
import com.example.ecommerce.domain.catalog.Product;
import com.example.ecommerce.domain.catalog.ProductRepository;
import com.example.ecommerce.domain.coupon.Coupon;
import com.example.ecommerce.domain.coupon.CouponRepository;
import com.example.ecommerce.domain.coupon.CouponStatus;
import com.example.ecommerce.domain.coupon.CouponType;
import org.assertj.core.api.AbstractBigDecimalAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/*
   상품 서비스에 대한 Unit Test 작성
 */
@SpringBootTest
public class OrderServiceTest {

    @Mock private OrderRepository orderRepository;
    @Mock private ProductRepository productRepository;
    @Mock private CouponRepository couponRepository;
    @Mock private RedisRankingService redisRankingService;

    @InjectMocks
    private OrderService orderService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void 주문생성(){
        //given
        Long userId=10000L;
        List<Long> productIds=List.of(101L,102L);
        List<Integer> quantities=List.of(2,1);
        Long couponId=100L;

        //상품 및 쿠폰 가져오기
        Product product1 = new Product("상품1",2,1000L, "SPEC", "KG", "관련자료1", "이미지");
        product1.setPro_Id(101L);
        Product product2 = new Product("상품2",1,5000L, "SPEC", "KG", "관련자료2", "이미지");
        product2.setPro_Id(102L);

        Coupon coupon = Coupon.create(
                "5천원할인",5000L,null,
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().minusDays(1),
                CouponType.AMOUNT,0L);

        when(productRepository.findById(101L)).thenReturn(Optional.of(product1));
        when(productRepository.findById(102L)).thenReturn(Optional.of(product2));
        when(couponRepository.findById(couponId)).thenReturn(Optional.of(coupon));
        // 저장된 객체 리턴
        when(orderRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        //when
        Order result = orderService.createOrder(userId,productIds,quantities,couponId);

        //then
        assertThat(result.getUserId()).isEqualTo(userId);
        assertThat(result.getOrderItems()).hasSize(2);
        assertThat(result.getTotalPrice()).isEqualTo(1000 * 2 + 5000 * 1 - 5000);
        assertThat(result.getCoupon()).isEqualTo(coupon);
        assertThat(result.getStatus()).isEqualTo(OrderStatus.CREATED);
    }
}
