package ch3_kt

import ch3_kt.domain.Book
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/books")
class SpringMvcBranchOfficeController(private val bookMap: Map<Long, Book>) {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{book-id}")
    @Throws(InterruptedException::class)
    fun getBook(@PathVariable("book-id") bookId: Long): ResponseEntity<Book> {
        Thread.sleep(5000)
        val book = bookMap[bookId]
        return ResponseEntity.ok(book)
    }
}