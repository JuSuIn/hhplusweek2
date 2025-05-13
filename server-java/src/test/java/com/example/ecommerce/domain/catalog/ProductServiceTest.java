package com.example.ecommerce.domain.catalog;

import com.example.ecommerce.application.catalog.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

/*
   상품 서비스에 대한 Unit Test 작성
 */
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository; //의존성 주입 목객체 생성

    @InjectMocks
    private ProductService productService; //목 주입된 서비스 객체 생성

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); //목 초기화
    }

    @Test
    void 상품등록_성공(){
        //given
        Product product = new Product("상품1",10,
                5000L, "SPEC", "KG", "관련자료 테스트 중입니다.", "이미지");
        when(productRepository.existsByName("상품1")).thenReturn(false);
        when(productRepository.save(product)).thenReturn(product);

        //when
        Product savedProduct = productService.registerProduct(product);

        //then
        assertThat(savedProduct).isEqualTo(product);
        verify(productRepository).save(product);
    }

    @Test
    void 상품등록_실패_이미존재하는상품(){
        //given
        Product product = new Product("상품1",10,
                5000L, "SPEC", "KG", "관련자료 테스트 중입니다.", "이미지");
        when(productRepository.existsByName("상품1")).thenReturn(true);

        // when & then
        assertThatThrownBy( () -> productService.registerProduct(product))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이미 존재하는 상품이름 입니다.");

        verify(productRepository,never()).save(any());

    }


}
