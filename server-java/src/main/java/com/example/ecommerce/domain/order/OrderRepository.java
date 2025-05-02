// JPA 인터페이스로, 도메인 입장에서 데이터를 저장/조회하는 행위 추상화
// JpaRepository를 구현하거나 커스텀 메서드를 정의

package com.example.ecommerce.domain.order;

import com.example.ecommerce.domain.order.Order;
import com.example.ecommerce.domain.order.OrderStatus;

import java.util.List;
import java.util.Optional;
/**
 * 주문에 대한 데이터 접근을 담당하는 Repository 인터페이스
 * (Persistence 기술과 무관하게 도메인 순수성 유지)
 *
 *  주문 저장
 *  주문 ID로 주문 조회
 *  특정 사용자 ID로 주문 목록 조회
 *  주문 상태별로 주문 목록 조회
 *  특정 사용자 ID로 최근 주문 목록 조회
 */
public interface OrderRepository {

    /**
     * 주문 저장
     * @param order 저장할 주문 객체
     * @return 저장된 주문 객체
     */
    Order save(Order order);//주문 저장

    /**
     * 주문 ID로 주문 조회
     * @param id 주문 ID
     * @return 주문(Optional)
     */
    Optional<Order> findById(Long id); //주문 ID로 주문 조회

    /**
     * 특정 사용자 ID로 주문 목록 조회
     * @param userId 사용자 ID
     * @return 주문 리스트
     */
    List<Order> findByUserId(Long userId);//특정 사용자 ID로 주문 목록 조회

    /**
     * 주문 상태별로 주문 목록 조회
     * @param status 주문 상태 (CREATED, PAID, CANCELLED 등)
     * @return 주문 리스트
     */
    List<Order> findByStatus(OrderStatus status);//주문 상태별로 주문 목록 조회

    /**
     * 특정 사용자 ID로 최근 주문 목록 조회
     * @param userId 사용자 ID
     * @param limit 조회할 주문 개수 제한
     * @return 최근 주문 리스트
     */
    List<Order> findRecentOrdersByUserId(Long userId,int limit);//특정 사용자 ID로 최근 주문 목록 조회
}
