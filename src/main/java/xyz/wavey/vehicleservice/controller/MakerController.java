package xyz.wavey.vehicleservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.wavey.vehicleservice.service.MakerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/maker")
public class MakerController {

    private final MakerService makerService;

    @GetMapping()
    public ResponseEntity<Object> getAllBrands() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(makerService.getAllBrands());
    }
}
