package xyz.wavey.vehicleservice.base.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class KakaoOpenFeignConfig {

    @Value("${kakao.key.rest-api}")
    private String kakaoPayApiKey;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new KakaoOpenFeignHeaderInterceptor(kakaoPayApiKey);
    }
}