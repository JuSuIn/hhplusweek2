// 주문 도메인 관련 호출,트랜잭션 제어
package com.example.ecommerce.application.order;


import com.example.ecommerce.application.ranking.RedisRankingService;
import com.example.ecommerce.domain.catalog.Product;
import com.example.ecommerce.domain.catalog.ProductRepository;
import com.example.ecommerce.domain.coupon.Coupon;
import com.example.ecommerce.domain.coupon.CouponRepository;
import com.example.ecommerce.domain.order.Order;
import com.example.ecommerce.domain.order.OrderItem;
import com.example.ecommerce.domain.order.OrderRepository;
import com.example.ecommerce.domain.order.OrderStatus;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CouponRepository couponRepository;
    private final RedisRankingService redisRankingService;

    /**
     * 주문 생성
     */
    @Transactional
    public Order createOrder(Long userId, List<Long> productIds,
                             List<Integer> quantities, Long couponId)
    {
        if (productIds.size() != quantities.size()) {
            throw new IllegalArgumentException("상품의 수량의 개수가 일치하지 않습니다.");
        }

        Order order = new Order();
        order.setStatus(OrderStatus.CREATED);//주문 상태 변경
        order.setUserId(userId);//주문 ID 로직생성
        order.setOrderedAt(LocalDateTime.now());

        int totalPrice = 0;

        for (int i = 0; i < productIds.size(); i++) {
            Product product = productRepository.findById(productIds.get(i))
                    .orElseThrow(() -> new EntityNotFoundException("상품을 찾을 수 없습니다."));
            OrderItem item = OrderItem.createOrderItem(product, quantities.get(i));
            order.addOrderItem(item);
            totalPrice += item.calculateTotalPrice();

            //인기상품 점수 증가
            redisRankingService.increaseScore(product.getPro_Id(),quantities.get(i));
        }



        //쿠폰 적용(선택적)
        if (couponId != null) {
            Coupon coupon = couponRepository.findById(couponId)
                    .orElseThrow(() -> new EntityNotFoundException("쿠폰을 찾을 수 없습니다."));

            if (!coupon.isAvailable(LocalDateTime.now(), (long) totalPrice)) {
                throw new IllegalArgumentException("사용할 수 없는 쿠폰입니다.");
            }

            if (coupon.getType().isAmountDiscount()) {
                //쿠폰이 정액제이면 차감...................
                totalPrice -= coupon.getDiscountAmount();
            } else {
                //쿠폰이 정률제이면 차감...................
                totalPrice -= (int) (totalPrice * coupon.getDiscountRate());
            }

            coupon.markAsUsed(); //쿠폰 사용 처리
            order.setCoupon(coupon);
        }

        order.setTotalPrice((long)totalPrice);
        return orderRepository.save(order);
    }

    /*
    * 주문 취소
    *
    * */
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow( () -> new EntityNotFoundException("주문을 찾을 수 없습니다."));

        order.cancel();
    }

    /**
     * 주문 완료 처리
     * */
    @Transactional
    public void completeOrder(Long orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("주문을 찾을 수 없습니다."));
        order.complete();;
    }

    /*
    *  사용자 주문 내역 조회
    * */
    public List<Order> getOrdersByUserId(Long userId){
        return orderRepository.findByUserId(userId);
    }

    /**
     * 주문 ID로 주문 조회
     *
     * @param orderId 주문 ID
     * @return Order (없으면 예외 발생)
     */
    public Order getOrder(Long orderId){
        return orderRepository.findById(orderId)
                .orElseThrow( () -> new NoSuchElementException("주문을 찾을 수 없습니다. ID: " + orderId));
    }
}
