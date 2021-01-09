import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    val number = scanner.nextInt()
    val values = IntArray(number)
    for (i in values.indices) {
        values[i] = scanner.nextInt()
    }

    if (number == 1) {
        println(values.first())
    } else {
        var max = 0
        for (i in values.indices) {
            for (k in values.indices) {
                if (i != k && values[i] * values[k] > max) {
                    max = values[i] * values[k]
                }
            }
        }
        println(max)
    }
}