package xyz.wavey.vehicleservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.wavey.vehicleservice.model.Maker;
import xyz.wavey.vehicleservice.repository.MakerRepo;
import xyz.wavey.vehicleservice.vo.ResponseGetAllMakers;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MakerServiceImpl implements MakerService {

    private final MakerRepo makerRepo;

    @Override
    public List<ResponseGetAllMakers> getAllBrands() {
        List<ResponseGetAllMakers> returnValue = new ArrayList<>();

        for (Maker maker : makerRepo.findAll()) {
            returnValue.add(ResponseGetAllMakers.builder()
                    .id(maker.getId())
                    .name(maker.getName())
                    .build());
        }
        return returnValue;
    }
}
