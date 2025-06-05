//주문 생성 테스트..
import http from 'k6/http';
import { check , sleep } from 'k6';

//test option definition
export const options = {
    vus : 50, //가상 사용자수
    duration : '1m' //테스트 실행 시간
};

//주문 API 앤드포인트
const BASE_URL = 'http://localhost:8080/'; //서버 주소
const API_ENDPOINT = 'api/orders'; // 주문 생성 API 앤드 포인트

export default function () {
    //주문에 사용할 데이터
    const payload = JSON.stringify({
        userId : '1',
        productId : [101,102], //존재하는 상품 ID
        quantity : [1 ,2] ,//수량 지정
        couponId : null
    });

    //요청 헤더 설정
    const params = {
        headers : {
            'Content-Type' : 'application/json',
        },
    };

    //post 요청 전송
    const res = http.post(`${BASE_URL}${API_ENDPOINT}`, payload, params);

    //응답 체크 (성공 여부 )
    check(res , {
        'status is 201' : (r) => r.status === 201,
        'response time < 1500ms' : (r) => r.timings.duration < 1500,
    });

    sleep(1);//사용자 간 대기 시간
};
