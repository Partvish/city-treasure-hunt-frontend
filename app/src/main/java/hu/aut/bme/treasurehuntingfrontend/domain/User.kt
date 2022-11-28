package hu.aut.bme.treasurehuntingfrontend.domain

data class User(
    val id: Long,
    val name: String,
    val password: String,
    val email: String,
    val points: Int
){
    companion object{
        public var ID:Long =0
    }
}