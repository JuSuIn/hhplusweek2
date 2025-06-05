import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    vus: 10, // 가상 사용자 수
    duration: '5s', // 테스트 시간
};

export default function () {
    const url = 'http://localhost:8080/coupon/issue';

    const payload = JSON.stringify({
        userId: 1,
        couponCode: 'WELCOME100',
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    const res = http.post(url, payload, params);

    check(res, {
        'status is 200': (r) => r.status === 200,
        'response has couponId': (r) => r.body.includes('couponId'),
    });

    sleep(1);
}