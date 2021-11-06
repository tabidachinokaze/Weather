package cn.tabidachinokaze.weather.logic.model

data class Assistant(
    val status: String,
    val info: String,
    val infocode: String,
    val count: String,
    val tips: List<Tips>
) {
    class Tips(
        val id: Any,
        val name: String,
        val district: String,
        val adcode: String,
        val location: Any,
        val address: Any,
        val typecode: String,
        val city: Any
    )
}
