fun main(args: Array<String>) {
    val input = readLine()!!.toInt()
    println(input / 100 + input % 100 / 10 + input % 10)
}