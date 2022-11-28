package hu.aut.bme.treasurehuntingfrontend.domain

data class Suggestion(
    val userId: Long,
    val questId: Long,
    val description: String,
)