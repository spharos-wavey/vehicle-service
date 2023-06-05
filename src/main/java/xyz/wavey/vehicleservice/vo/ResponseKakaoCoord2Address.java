package xyz.wavey.vehicleservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseKakaoCoord2Address {

    private Meta meta;
    private List<Documents> documents;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Meta {
        Integer total_count;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Documents {
        Address address;
        RoadAddress road_address;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Address {
            String address_name;
            String region_1depth_name;
            String region_2depth_name;
            String region_3depth_name;
            String mountain_yn;
            String main_address_no;
            String sub_address_no;
        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class RoadAddress {
            String address_name;
            String region_1depth_name;
            String region_2depth_name;
            String region_3depth_name;
            String road_name;
            String underground_yn;
            String main_building_no;
            String sub_building_no;
            String building_name;
            String zone_no;
        }
    }

}
