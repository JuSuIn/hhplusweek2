<!--
  이커머스 시스템 설계 
-->
# 제목 이커머스 시스템
<!--
  (Optional: 참고 자료가 없는 작업 - 단순 버그 픽스 등 의 경우엔 해당 란을 제거해주세요 !)
  작업에 대한 참고자료(PR, 피그마, 슬랙 등)가 있는 경우 링크를 참고 자료에 같이 추가해주세요.
  히스토리나 정책, 특정 기술 등에 대한 이해가 필요한 작업일 때 참고자료가 있다면 리뷰어에게 큰 도움이 됩니다!
-->
-----------------------------------------------------------------
## PR
### 7주차

## ☔︎ 현재 커밋 링크 
* Redis 실시간 기반 인기 상품 조회 클래스 작성 : [10150c8](https://github.com/JuSuIn/hhplusweek2/commit/10150c820f6fdbcab68e4a0a2f2bd1e0ea16bbe6)
* Redis 구현체 작성 및 인프라 계층 구현체 작성 : [03320ca](https://github.com/JuSuIn/hhplusweek2/commit/03320ca716942db24cb77355adce766e7698c617)


## 미완성 되었던 부분 구현 Unit Test
* PRODUCT

-----------------------------------------------------------------
## 현재 완료 된 부분


### 2) APPRICATION(SERVICE)
* PRODUCT
  * [상품 서비스 분리](https://github.com/JuSuIn/hhplusweek2/tree/WEEK5-1/server-java/src/main/java/com/example/ecommerce/application/catalog)
  * ProductQueryService - 조회
  * ProductService -등록,수정,삭제
* COUPON
  * [쿠폰 서비스 분리](https://github.com/JuSuIn/hhplusweek2/tree/WEEK5-1/server-java/src/main/java/com/example/ecommerce/application/coupon)
  * ProductQueryService - 조회
  * ProductService -등록,수정,삭제
* ORDER
  * [주문 서비스](https://github.com/JuSuIn/hhplusweek2/tree/WEEK5-1/server-java/src/main/java/com/example/ecommerce/application/order)
  * OrderService -주문생성,주문완료,주문취소,사용자주문내역조회
* PAYMENT
  * [결제 서비스](https://github.com/JuSuIn/hhplusweek2/tree/WEEK5-1/server-java/src/main/java/com/example/ecommerce/application/payment)
  * PaymentService -결제 생성 (결제 요청),결제 완료,결제 실패
* WALLET
  * [지갑 서비스](https://github.com/JuSuIn/hhplusweek2/tree/WEEK5-1/server-java/src/main/java/com/example/ecommerce/application/wallet)
  * WalletService -지갑 잔액 충전,자갑 잔액 출금,지갑 잔액 조회

-----------------------------------------------------------------

## PR
### 7주차
### 1) 아직 미구현된 로직 구현 중입니다.
### 2) FACADE 로직 구현 했습니다.

-----------------------------------------------------------------

## 리뷰 포인트
## 현재 구현 된 부분

### 1) DOMAIN
* RANKING
  * [인기상품랭킹 도메인 구현](https://github.com/JuSuIn/hhplusweek2/tree/WEEK6-1/server-java/src/main/java/com/example/ecommerce/domain/ranking)
  * [인기상품랭킹 Repository](https://github.com/JuSuIn/hhplusweek2/tree/WEEK6-1/server-java/src/main/java/com/example/ecommerce/domain/ranking)

