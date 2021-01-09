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

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val input = scanner.next()

    println(checkColor(input))
}

fun checkColor(input: String): Boolean {
    var boolean = false
    for (color in Rainbow.values()) {
        if (input.toUpperCase() == color.name) boolean = true
    }
    return boolean
}