package hu.aut.bme.treasurehuntingfrontend.helper

import java.util.*

class TokenGenerator {
    fun createToken(username: String, password: String): String {
        val pass = "${username}:${password}"
        return Base64.getEncoder().encodeToString(pass.toByteArray())
    }
}