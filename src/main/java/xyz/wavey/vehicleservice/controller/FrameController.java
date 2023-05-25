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
import xyz.wavey.vehicleservice.service.FrameService;
import xyz.wavey.vehicleservice.vo.RequestFrame;

@RestController
@RequestMapping("/frame")
@RequiredArgsConstructor
public class FrameController {
    private final FrameService frameService;

    @PostMapping()
    public ResponseEntity<Object> addFrame(@RequestBody RequestFrame requestFrame) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(frameService.addFrame(requestFrame));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getFrame(@PathVariable Long id) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(frameService.getFrame(id));
    }
}
