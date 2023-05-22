package xyz.wavey.vehicleservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.wavey.vehicleservice.service.MakerService;
import xyz.wavey.vehicleservice.vo.RequestMaker;
import xyz.wavey.vehicleservice.vo.ResponseMaker;

@RestController
@RequiredArgsConstructor
@RequestMapping("/maker")
public class MakerController {

    private final MakerService makerService;

    @PostMapping()
    public ResponseEntity<Object> addMaker(@RequestBody RequestMaker requestMaker) {
        return makerService.addMaker(requestMaker);
    }

    @GetMapping("/{id}")
    public ResponseMaker getMaker(@PathVariable Integer id) {
        return makerService.getMaker(id);
    }

    @GetMapping()
    public ResponseEntity<Object> getAllBrands() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(makerService.getAllBrands());
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<Object> getAllVehicleByMaker(@PathVariable Integer id){
        return makerService.getAllVehicleByMaker(id);
    }
}
