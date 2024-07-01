package ch3;

import ch3.domain.BookHead;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ch3.domain.Book;
import java.net.URI;

/**
 * Blocking I/O 예시
 * Spring MVC 기반의 코드
 */

@Slf4j
@RestController
@RequestMapping
public class SpringMvcHeadOfficeController {
    private final RestTemplate restTemplate;
    URI baseUri = UriComponentsBuilder.newInstance().scheme("http")
            .host("localhost")
            .port(7070)
            .path("/v1/books")
            .build()
            .encode()
            .toUri();

    public SpringMvcHeadOfficeController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{book-id}")
    public ResponseEntity<BookHead> getBook(@PathVariable("book-id") long bookId) {
        URI getBookUri = UriComponentsBuilder.fromUri(baseUri)
                .path("/{book-id}")
                .build()
                .expand(bookId)
                .encode()
                .toUri(); // http://localhost:7070/v1/books/{book-id}

        ResponseEntity<BookHead> response = restTemplate.getForEntity(getBookUri, BookHead.class);
        BookHead book = response.getBody();

        return ResponseEntity.ok(book);
    }
}
