package xyz.wavey.vehicleservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;
import xyz.wavey.vehicleservice.base.config.KakaoOpenFeignConfig;
import xyz.wavey.vehicleservice.vo.RequestKakaoCoord2Address;
import xyz.wavey.vehicleservice.vo.ResponseKakaoCoord2Address;

@FeignClient(name = "kakao-service", url = "https://dapi.kakao.com", configuration = KakaoOpenFeignConfig.class)
public interface KakaoOpenFeign {

    @PostMapping(value = "/v2/local/geo/coord2address")
    ResponseKakaoCoord2Address KakaoCoord2Address(@SpringQueryMap RequestKakaoCoord2Address requestKakaoCoord2RegionCode);

}
