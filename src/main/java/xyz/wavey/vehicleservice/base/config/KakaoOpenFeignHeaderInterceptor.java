package xyz.wavey.vehicleservice.base.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class KakaoOpenFeignHeaderInterceptor implements RequestInterceptor {

    private final String KAKAO_PAY_AUTHORIZATION = "Authorization";
    private final String KAKAO_PAY_AUTHORIZATION_PREFIX = "KakaoAK ";

    private String kakaoPayApiKey;

    public KakaoOpenFeignHeaderInterceptor(String kakaoPayApiKey) {
        this.kakaoPayApiKey = kakaoPayApiKey;
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header(KAKAO_PAY_AUTHORIZATION, KAKAO_PAY_AUTHORIZATION_PREFIX + kakaoPayApiKey);
    }
}
