package ch3;

import ch3.domain.Book;
import ch3.domain.BookHead;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalTime;

@Slf4j
@SpringBootApplication
public class SpringMvcHeadOfficeApplication {
    private URI baseUri = UriComponentsBuilder.newInstance().scheme("http")
            .host("localhost")
            .port(8080)
            .path("/v1/books")
            .build()
            .encode()
            .toUri();

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcHeadOfficeApplication.class, args);
    }

    @Bean
    public RestTemplateBuilder restTemplate() {
        return new RestTemplateBuilder();
    }

    @Bean
    public CommandLineRunner run() {
        return (String... args) -> {
            log.info("# 요청 시작 시간: {}", LocalTime.now());

            for (int i = 1; i <= 5; i++) {
                BookHead book = this.getBook(i);
                log.info("{}: book name: {}", LocalTime.now(), book.getName());
            }
        };
    }

    private BookHead getBook(long bookId) {
        RestTemplate restTemplate = new RestTemplate();

        URI getBooksUri = UriComponentsBuilder.fromUri(baseUri)
                .path("/{book-id}")
                .build()
                .expand(bookId)
                .encode()
                .toUri(); // http://localhost:8080/v1/books/{book-id}

        ResponseEntity<BookHead> response =
                restTemplate.getForEntity(getBooksUri, BookHead.class);
        BookHead book = response.getBody();

        return book;
    }
}
