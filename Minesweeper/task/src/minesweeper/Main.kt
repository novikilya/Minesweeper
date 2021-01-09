package minesweeper

import java.util.*
import kotlin.math.min

fun main() {
    println()
    val scanner = Scanner(System.`in`)

    println("How many mines do you want on the field?")
    val number = scanner.nextInt()
    val mines = Array(9) { Array(9) { "." } }
    val hints = Array(9) { Array(9) { "." } }
    makeMines(mines, number)


    var x: Int
    var y: Int
    var cmd: String
    var rightAnswers = 0
    var move = 0
    while (rightAnswers != number) {
        printMap(hints)
        println("Set/unset mines marks or claim a cell as free:")
        y = scanner.nextInt() - 1
        x = scanner.nextInt() - 1
        cmd = scanner.next()


        if (cmd == "free") {
            if (move == 0 && mines[x][y] == "X") {
                var counter = 0
                for (i in 0..8) {
                    for (k in 0..8) {
                        if (mines[i][k] != "X" && counter == 0) {
                            mines[i][k] = "X"
                            counter = 1
                        }
                    }
                }
                mines[x][y] = "."
                move++
            }

            if (mines[x][y] != "X") {
                hints[x][y] = if (checkMinesAround(mines, x, y) != 0) checkMinesAround(mines, x, y).toString()
                else "/"
                if (checkMinesAround(mines, x, y) == 0) {
                    for (n in 1..12) {
                        for (i in 0..8) {
                            for (k in 0..8) {
                                if (checkMinesAround(mines, i, k) == 0 && hints[i][k] == "/") {
                                    markMinesAround(hints, mines, i, k)
                                }
                            }
                        }
                    }
                }
            } else {
                break
            }
            move++
        }

        if (cmd == "mine") {
            if (hints[x][y] == ".") {
                hints[x][y] = "*"
                if (mines[x][y] == "X") {
                    rightAnswers++
                }
            } else {
                hints[x][y] = "."
                if (mines[x][y] == "X") {
                    rightAnswers--
                }
            }
        }
    }
    if (rightAnswers == number) {
        printMap(hints)
        println("Congratulations! You found all the mines!")
    } else {
        for (i in 0..8) {
            for (k in 0..8) {
                if (mines[i][k] == "X") {
                    hints[i][k] = "X"
                }
            }
        }
        printMap(hints)
        println("You stepped on a mine and failed!")
    }
}


fun makeMines(array: Array<Array<String>>, number: Int) {
    var a = 1
    while (a <= number) {
        val randomNumber1 = (0..8).random()
        val randomNumber2 = (0..8).random()
        if (array[randomNumber1][randomNumber2] == ".") {
            array[randomNumber1][randomNumber2] = "X"
            a++
        }
    }
}

fun printMap(map: Array<Array<String>>) {
    println(
        " │123456789│\n" +
                "—│—————————│"
    )
    for (i in 0..8) {
        print("${i + 1}│")
        for (k in 0..8) {
            print(map[i][k])
        }
        println("│")
    }
    println("—│—————————│")
}

fun checkMinesAround(
    mines: Array<Array<String>>,
    i: Int,
    k: Int
): Int {
    var n = 0
    if (i >= 1 && k >= 1) {
        if (mines[i - 1][k - 1] == "X") n++
    }
    if (i >= 1) {
        if (mines[i - 1][k] == "X") n++
    }
    if (i >= 1 && k <= 7) {
        if (mines[i - 1][k + 1] == "X") n++
    }
    if (k <= 7) {
        if (mines[i][k + 1] == "X") n++
    }
    if (i <= 7 && k <= 7) {
        if (mines[i + 1][k + 1] == "X") n++
    }
    if (i <= 7) {
        if (mines[i + 1][k] == "X") n++
    }
    if (i <= 7 && k >= 1) {
        if (mines[i + 1][k - 1] == "X") n++
    }
    if (k >= 1) {
        if (mines[i][k - 1] == "X") n++
    }
    return n
}

fun markMinesAround(
    hints: Array<Array<String>>,
    mines: Array<Array<String>>,
    i: Int,
    k: Int
) {
    if (i >= 1 && k >= 1) {
        hints[i - 1][k - 1] = if (checkMinesAround(mines, i - 1, k - 1) != 0) checkMinesAround(mines, i - 1, k - 1).toString()
        else "/"
    }
    if (i >= 1) {
        hints[i - 1][k] = if (checkMinesAround(mines, i - 1, k) != 0) checkMinesAround(mines, i - 1, k).toString()
        else "/"
    }
    if (i >= 1 && k <= 7) {
        hints[i - 1][k + 1] =
            if (checkMinesAround(mines, i - 1, k + 1) != 0) checkMinesAround(mines, i - 1, k + 1).toString()
            else "/"
    }
    if (k <= 7) {
        hints[i][k + 1] = if (checkMinesAround(mines, i, k + 1) != 0) checkMinesAround(mines, i, k + 1).toString()
        else "/"
    }
    if (i <= 7 && k <= 7) {
        hints[i + 1][k + 1] =
            if (checkMinesAround(mines, i + 1, k + 1) != 0) checkMinesAround(mines, i + 1, k + 1).toString()
            else "/"
    }
    if (i <= 7) {
        hints[i + 1][k] = if (checkMinesAround(mines, i + 1, k) != 0) checkMinesAround(mines, i + 1, k).toString()
        else "/"
    }
    if (i <= 7 && k >= 1) {
        hints[i + 1][k - 1] =
            if (checkMinesAround(mines, i + 1, k - 1) != 0) checkMinesAround(mines, i + 1, k - 1).toString()
            else "/"
    }
    if (k >= 1) {
        hints[i][k - 1] = if (checkMinesAround(mines, i, k - 1) != 0) checkMinesAround(mines, i, k - 1).toString()
        else "/"
    }
}
