package com.example.ecommerce.application.facade;// 주문과 관련된 쿠폰 사용, 결제, 잔액 충전 차감 등 조합

import com.example.ecommerce.application.coupon.CouponService;
import com.example.ecommerce.application.order.OrderService;
import com.example.ecommerce.domain.order.Order;
import com.example.ecommerce.domain.order.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  주문 파사드
 */
@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderService orderService;
    private final CouponService couponService;


    @Transactional
    // 쿠폰 사용 처리한 뒤 -> 주문 생성
    public Order placeOrder(Long userId, List<OrderItem> items,Long couponId){
        // 쿠폰 사용 처리
        couponService.useCoupon(userId,couponId);

        //주문 생성
        //1. OrderItem -> productIds,quantities 분리
        List<Long> productIds = items.stream()
                .map( item -> item.getProduct().getPro_Id())
                .collect(Collectors.toList());

        List<Integer> quantities = items.stream()
                .map(OrderItem::getQuantity)
                .collect(Collectors.toList());

        return orderService.createOrder(userId,productIds,quantities,couponId);
    }

    //주문 취소
    @Transactional
    public void cancelOrder(Long orderId){
        orderService.cancelOrder(orderId);
    }



}