package xyz.wavey.vehicleservice.BookList.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.wavey.vehicleservice.BookList.service.BookListService;
import xyz.wavey.vehicleservice.BookList.vo.RequestBookList;
import xyz.wavey.vehicleservice.BookList.vo.ResponseBookList;

@RestController
@RequestMapping("/booklist")
@RequiredArgsConstructor
public class BookListController {
  private final BookListService bookListService;

  @PostMapping()
  public ResponseEntity<Object> addBook(@RequestBody RequestBookList requestBookList){
    return bookListService.addBook(requestBookList);
  }

  @GetMapping("/{id}")
  public ResponseBookList getBook(@PathVariable Long id){
    return bookListService.getBook(id);
  }

}
