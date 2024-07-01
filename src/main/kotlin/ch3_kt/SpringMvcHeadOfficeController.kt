package ch3_kt

import ch3_kt.domain.BookHead
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

/**
 * Blocking I/O 예시
 * Spring MVC 기반의 코드
 */
@RestController
@RequestMapping
class SpringMvcHeadOfficeController(restTemplateBuilder: RestTemplateBuilder) {
    private val restTemplate: RestTemplate = restTemplateBuilder.build()
    private val baseUri: URI = UriComponentsBuilder.newInstance().scheme("http")
        .host("localhost")
        .port(7070)
        .path("/v1/books")
        .build()
        .encode()
        .toUri()

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{book-id}")
    fun getBook(@PathVariable("book-id") bookId: Long): ResponseEntity<BookHead> {
        val getBookUri = UriComponentsBuilder.fromUri(baseUri)
            .path("/{book-id}")
            .build()
            .expand(bookId)
            .encode()
            .toUri() // http://localhost:7070/v1/books/{book-id}

        val response = restTemplate.getForEntity(getBookUri, BookHead::class.java)
        val book = response.body

        return ResponseEntity.ok(book)
    }
}