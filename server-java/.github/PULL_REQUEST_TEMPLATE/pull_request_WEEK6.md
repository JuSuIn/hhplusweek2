<!--
  제목 이커머스 시스템 설계 
-->
## 제목 이커머스 시스템 설계
<!--
  (Optional: 참고 자료가 없는 작업 - 단순 버그 픽스 등 의 경우엔 해당 란을 제거해주세요 !)
  작업에 대한 참고자료(PR, 피그마, 슬랙 등)가 있는 경우 링크를 참고 자료에 같이 추가해주세요.
  히스토리나 정책, 특정 기술 등에 대한 이해가 필요한 작업일 때 참고자료가 있다면 리뷰어에게 큰 도움이 됩니다!
-->
-----------------------------------------------------------------
## 현재 완료 된 부분

### 1) DOMAIN
* PRODUCT
    * [상품 도메인 구현](https://github.com/JuSuIn/hhplusweek2/tree/WEEK5-1/server-java/src/main/java/com/example/ecommerce/domain/catalog)
    * [상품 Repository](https://github.com/JuSuIn/hhplusweek2/tree/WEEK5-1/server-java/src/main/java/com/example/ecommerce/domain/catalog)
* COUPON
    * [쿠폰 도메인 구현](https://github.com/JuSuIn/hhplusweek2/tree/WEEK5-1/server-java/src/main/java/com/example/ecommerce/domain/coupon)
    * [쿠폰 Repository](https://github.com/JuSuIn/hhplusweek2/tree/WEEK5-1/server-java/src/main/java/com/example/ecommerce/domain/coupon)
* ORDER
    * [주문 도메인 구현](https://github.com/JuSuIn/hhplusweek2/tree/WEEK5-1/server-java/src/main/java/com/example/ecommerce/domain/order)
    * [주문 Repository](https://github.com/JuSuIn/hhplusweek2/tree/WEEK5-1/server-java/src/main/java/com/example/ecommerce/domain/order)
* PAYMENT
    * [결제 도메인 구현](https://github.com/JuSuIn/hhplusweek2/tree/WEEK5-1/server-java/src/main/java/com/example/ecommerce/domain/payment)
    * [결제 Repository](https://github.com/JuSuIn/hhplusweek2/tree/WEEK5-1/server-java/src/main/java/com/example/ecommerce/domain/payment)
* WALLET
    * [지갑 도메인 구현](https://github.com/JuSuIn/hhplusweek2/tree/WEEK5-1/server-java/src/main/java/com/example/ecommerce/domain/wallet)
    * [지갑 Repository](https://github.com/JuSuIn/hhplusweek2/tree/WEEK5-1/server-java/src/main/java/com/example/ecommerce/domain/wallet)


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
### 6주차
### 1) 아직 미구현된 로직 구현 중입니다.
### 2) FACADE 로직 구현 했습니다. 

-----------------------------------------------------------------

## 리뷰 포인트
## 현재 구현 된 부분

### 1) DOMAIN
* RANKING
    * [인기상품랭킹 도메인 구현](https://github.com/JuSuIn/hhplusweek2/tree/WEEK6-1/server-java/src/main/java/com/example/ecommerce/domain/ranking)
    * [인기상품랭킹 Repository](https://github.com/JuSuIn/hhplusweek2/tree/WEEK6-1/server-java/src/main/java/com/example/ecommerce/domain/ranking)

### 2) APPRICATION(SERVICE)
* RANKING
    * [인기상품랭킹 서비스](https://github.com/JuSuIn/hhplusweek2/tree/WEEK6-1/server-java/src/main/java/com/example/ecommerce/application/ranking)
    * RankingService -랭킹 타입별 상위 랭킹 리스트 조회,특정 상품의 랭킹 조회,오래된 랭킹 삭제,랭킹 업데이트

* FACADE
  * [파사드 로직 구현](https://github.com/JuSuIn/hhplusweek2/tree/WEEK6-1/server-java/src/main/java/com/example/ecommerce/application/facade)
  * ProductFacade - 상품 관련 파사드 , 상품 등록.수정.삭제 , 인기상품조회 ( 랭킹 타입별 상위 랭킹 리스트 조회)
  * CouponIssueFacade -쿠폰 관련 정책 파사드 , 쿠폰 발급 , 사용자가 사용할 수 있는 쿠폰 조회
  * OrderFacade -  주문 파사드 ,  쿠폰 사용 처리한 뒤 -> 주문 생성 , 주문 취소
  * PaymentFacade - 결제 관련 파사드 , 주문 상태 확인 -> 결제 처리 -> 주문 완료 처리