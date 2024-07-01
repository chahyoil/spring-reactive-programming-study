package ch3_kt.domain

import lombok.Data

@Data
data class BookHead(
    val name: String,
    val price: Int
)