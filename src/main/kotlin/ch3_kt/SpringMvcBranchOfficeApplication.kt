package ch3_kt

import ch3_kt.domain.Book
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
open class SpringMvcBranchOfficeApplication {

    @Bean
    open fun bookMap(): Map<Long, Book> {
        val bookMap = mutableMapOf<Long, Book>()
        for (i in 1..2_000_000) {
            bookMap[i.toLong()] = Book(i.toLong(), "IT Book $i", 2000)
        }
        return bookMap
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(SpringMvcBranchOfficeApplication::class.java, *args)
}