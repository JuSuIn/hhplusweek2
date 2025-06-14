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
### 10주차

## ☔︎ 현재 커밋 & 작업물 링크 
* 1) 이커머스 테스트 시나리오 작성(현재pr에 있음)  [82abc9d](https://github.com/JuSuIn/hhplusweek2/commit/82abc9d71c3e524e0a8f15bf83ecbc85f519aebd)
* 2) 장애 대응 시나리오 작성                   [b30513b](https://github.com/JuSuIn/hhplusweek2/commit/b30513bbff259c82804481cf9db620b1302ecb02)
* 3) 상품주문,쿠폰발급처리 k6 test 용 작성      [17d2e9c](https://github.com/JuSuIn/hhplusweek2/commit/17d2e9cc95c523860488b21870e1a2a80c99f544)

-----------------------------------------------------------------

---------------------------------------------------------
## 📈 E-commerce Test Scenario Document


## 🧩 1. 테스트 목적

* 이커머스 시스템의 주문 생성, 쿠폰 발급, 재고 차감 기능에 대해 고부하 상황에서도 안정적으로 작동하는지 확인한다.
* 이벤트 시간대에 집중되는 사용자 트래픽을 시뮬레이션하여 병목 지점을 사전에 식별하고 대응 전략을 수립한다.



---



## 🔍 2. 테스트 대상

| 기능    | 설명              | 엔드포인트 예시                  |
| ----- | --------------- | ------------------------- |
| 주문 생성 | 상품 주문 생성 API    | `POST /api/orders`        |
| 쿠폰 발급 | 쿠폰 발급 처리 API    | `POST /api/coupons/issue` |
| 재고 차감 | 인기 상품 실시간 재고 반영 | 내부 도메인 로직 or Redis 연동     |



---



## 🎯 3. 테스트 시나리오 설계



### 🎪 시나리오 1: 이벤트 시간대 집중 트래픽

* 사용자는 1,000명이 동시에 접속하여 주문 요청을 보낸다
* 쿠폰도 함께 적용하는 복합 처리

> ✅ 검증 포인트:
>
> * 평균 응답시간 < 1.5초
> * 실패율 < 1%



### 🎟️ 시나리오 2: 쿠폰 발급 요청 폭주

* 제한 수량(예: 1,000장) 쿠폰을 동시 요청 (1만명 동시 접속)
* 중복 발급 방지 로직 검증

> ✅ 검증 포인트:
>
> * 한 유저 1회 발급 제한 동작 확인
> * Redis 락 또는 DB unique key 충돌 로그 확인



### 📦 시나리오 3: 인기 상품 재고 실시간 차감

* 동시 5천 요청으로 특정 상품 주문 (재고 100개)
* 실시간 재고 차감 → 품절 처리 확인

> ✅ 검증 포인트:
>
> * 재고 음수 방지 여부
> * 동시성 처리 (락 또는 Redis 선점락) 로직 안정성

---




## ⚙️ 4. 테스트 환경




* 도구: `k6` 
* 스케일: 로컬 컨테이너 또는 클라우드 스케일링 환경
* 리소스: Docker로 CPU/Memory 제한 설정한 상태에서 수행

```bash
# 예시 Docker 옵션
$ docker run -m 1g --cpus=1 ...
```




---




## 📎 5. 후속 작업

* 테스트 결과 수집 (`response time`, `error rate`, `TPS` 등)
* 병목 지점 분석
* `failure-response.md` 문서로 장애 시나리오 정리 예정
