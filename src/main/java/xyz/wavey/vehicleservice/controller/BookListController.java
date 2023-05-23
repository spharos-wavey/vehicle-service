package xyz.wavey.vehicleservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.wavey.vehicleservice.service.BookListService;
import xyz.wavey.vehicleservice.vo.RequestBookList;
import xyz.wavey.vehicleservice.vo.ResponseBookList;
import xyz.wavey.vehicleservice.vo.ResponseSummary;

@RestController
@RequestMapping("/booklist")
@RequiredArgsConstructor
public class BookListController {
    private final BookListService bookListService;

    @PostMapping()
    public ResponseEntity<Object> addBook(@RequestBody RequestBookList requestBookList) {
        return bookListService.addBook(requestBookList);
    }

    @GetMapping("/{id}")
    public ResponseBookList getBook(@PathVariable Long id) {
        return bookListService.getBook(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id){
        bookListService.deleteBook(id);
    }

    @GetMapping("/information/{id}")
    public ResponseEntity<Object> getBookAboutVehicle(@PathVariable Long id){
        return bookListService.getBookAboutVehicle(id);
    }

    @GetMapping("/summary/{vehicleId}")
    public ResponseEntity<Object> getSummary(@PathVariable Long vehicleId){
        ResponseSummary responseSummary = bookListService.getSummary(vehicleId);
        return ResponseEntity.ok(responseSummary);
    }

}
