package ch3_kt.domain

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
data class Book(
    val bookId: Long,
    val name: String,
    val price: Int
)