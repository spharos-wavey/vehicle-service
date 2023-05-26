package xyz.wavey.vehicleservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.wavey.vehicleservice.service.BookListService;
import xyz.wavey.vehicleservice.vo.RequestBookList;
import xyz.wavey.vehicleservice.vo.RequestCheckLicense;

@RestController
@RequestMapping("/booklist")
@RequiredArgsConstructor
public class BookListController {
    private final BookListService bookListService;

    @PostMapping()
    public ResponseEntity<Object> addBook(@RequestBody RequestBookList requestBookList) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(bookListService.addBook(requestBookList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBook(@PathVariable Long id) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(bookListService.getBook(id));
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id){
        bookListService.deleteBook(id);
    }

    @GetMapping("/information/{id}")
    public ResponseEntity<Object> getBookAboutVehicle(@PathVariable Long id){
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(bookListService.getBookAboutVehicle(id));
    }

    @GetMapping("/summary/{vehicleId}")
    public ResponseEntity<Object> getSummary(@PathVariable Long vehicleId){
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(bookListService.getSummary(vehicleId));
    }

    @GetMapping("/check/license")
    public ResponseEntity<Object> checkLicense(@RequestBody RequestCheckLicense requestCheckLicense) {
        if (bookListService.checkLicense(requestCheckLicense)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
}
