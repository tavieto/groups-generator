package br.com.tavieto.groupsgenerator

data class Group(
    val id: Int = 1,
    val total: Int = 2,
    val numbers: List<Int> = listOf(30, 7)
)
