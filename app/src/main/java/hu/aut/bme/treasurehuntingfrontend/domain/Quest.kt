package hu.aut.bme.treasurehuntingfrontend.domain

data class Quest (
    val  id: Long,
    val  point: Int,
    val  name: String,
    val  description: String,
    val  options: String,
    val  longitude: Float,
    val  latitude: Float,
){
    companion object{
        public val STARTED = 0
        public val ABANDONED = 1
        public val FINISHED = 2
    }
}