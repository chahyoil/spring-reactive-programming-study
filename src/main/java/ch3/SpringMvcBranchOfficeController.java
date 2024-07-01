package ch3;

import ch3.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/v1/books")
public class SpringMvcBranchOfficeController {
    private Map<Long, Book> bookMap;

    @Autowired
    public SpringMvcBranchOfficeController(Map<Long, Book> bookMap) {
        this.bookMap = bookMap;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{book-id}")
    public ResponseEntity<Book> getBook(@PathVariable("book-id") long bookId)
            throws InterruptedException {
        Thread.sleep(5000);

        Book book = bookMap.get(bookId);

        return ResponseEntity.ok(book);
    }
}