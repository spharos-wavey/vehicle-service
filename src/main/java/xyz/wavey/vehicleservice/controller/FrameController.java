package xyz.wavey.vehicleservice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.wavey.vehicleservice.service.FrameService;
import xyz.wavey.vehicleservice.vo.RequestFrame;
import xyz.wavey.vehicleservice.vo.ResponseFrame;

@RestController
@RequestMapping("/frame")
@RequiredArgsConstructor
public class FrameController {
    private final FrameService frameService;

    @PostMapping()
    public ResponseEntity<Object> addFrame(@RequestBody RequestFrame requestFrame) {
        return frameService.addFrame(requestFrame);
    }

    @GetMapping("/{id}")
    public ResponseFrame getFrame(@PathVariable Long id) {
        return frameService.getFrame(id);
    }
}
