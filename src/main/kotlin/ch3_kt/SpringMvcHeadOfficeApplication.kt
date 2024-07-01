package ch3_kt

import ch3_kt.domain.BookHead
import mu.KotlinLogging
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI
import java.time.LocalTime

private val log = KotlinLogging.logger {}

@SpringBootApplication
open class SpringMvcHeadOfficeApplication {
    private val baseUri: URI = UriComponentsBuilder.newInstance().scheme("http")
        .host("localhost")
        .port(8080)
        .path("/v1/books")
        .build()
        .encode()
        .toUri()

    @Bean
    open fun restTemplate(): RestTemplateBuilder {
        return RestTemplateBuilder()
    }

    @Bean
    open fun run(): CommandLineRunner {
        return CommandLineRunner {
            log.info("# 요청 시작 시간: {}", LocalTime.now())

            for (i in 1..5) {
                val book = getBook(i.toLong())
                log.info("{}: book name: {}", LocalTime.now(), book.name)
            }
        }
    }

    private fun getBook(bookId: Long): BookHead {
        val restTemplate = RestTemplate()
        val getBooksUri = UriComponentsBuilder.fromUri(baseUri)
            .path("/{book-id}")
            .build()
            .expand(bookId)
            .encode()
            .toUri() // http://localhost:8080/v1/books/{book-id}

        val response: ResponseEntity<BookHead> =
            restTemplate.getForEntity(getBooksUri, BookHead::class.java)
        return response.body!!
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(SpringMvcHeadOfficeApplication::class.java, *args)
}