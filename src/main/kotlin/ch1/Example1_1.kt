package ch1

fun main() {
    val numbers = listOf(1, 3, 21, 10, 8, 11)
    val sum = numbers.filter { it > 6 && it % 2 != 0 }.sum()

    println("합계 : $sum")
}