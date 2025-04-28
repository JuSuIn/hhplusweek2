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

## PR
### 이번 5주차에서는 낙관적 락,비관적 락에 대해 구현을 작성해야 했으나 3주차 부터 과제가 계속 밀려 어쩔 수 없이 도메인 단 로직과 서비스 로직 구현을 작성했습니다.(죄송합니다 ㅠ)
<!-- 해당 PR이 왜 발생했고, 어떤부분에 대한 작업인지 작성해주세요. -->

-----------------------------------------------------------------

## 리뷰 포인트

### 현재 구현된 부분 
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


<!-- TOC -->

<!-- 
    리뷰어가 함께 고민해주었으면 하는 내용을 간략하게 기재해주세요.
    커밋 링크가 포함되면, 더욱이 효과적일 거예요! 
-->

<!-- Definition of Done (DoD)
    DOD 란 해당 작업을 완료했다고 간주하기 위해 충족해야 하는 기준을 의미합니다.
    어떤 기능을 위해 어떤 요구사항을 만족하였으며, 어떤 테스트를 수행했는지 등을 명확하게 체크리스트로 기재해 주세요.
    리뷰어 입장에서, 모든 맥락을 파악하기 이전에 작업의 성숙도/완성도를 파악하는 데에 도움이 됩니다.
    만약 계획되거나 연관 작업이나 파생 작업이 존재하는데, 이후로 미뤄지는 경우 TODO -, 사유와 함께 적어주세요.

    ex:
    - [x] 상품 도메인 모델 구조 설계 완료 ( [정책 참고자료](관련 문서 링크) )
    - [x] 상품 재고 차감 로직 유닛/통합 테스트 완료
    - [ ] TODO - 상품 주문 로직 개발 ( 정책 미수립으로 인해 후속 작업에서 진행 )
-->