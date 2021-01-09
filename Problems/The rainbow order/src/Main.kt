import java.util.Scanner

enum class Rainbow(val color: String) {
    RED("Red"),
    ORANGE("Orange"),
    YELLOW("Yellow"),
    GREEN("Green"),
    BLUE("Blue"),
    INDIGO("Indigo"),
    VIOLET("Violet")
}

fun main() {
    val scanner = Scanner(System.`in`)
    val input = scanner.next()

    println(colorNumber(input))
}

fun colorNumber(input: String): Int {
    var n = 0
    for (color in Rainbow.values()) {
        if (input.toUpperCase() == color.name) n = color.ordinal + 1
    }
    return n
}