package algorithms.집합

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val sb = StringBuilder()
    val num = br.readLine().toInt()

    var S = 0

    for (i in 0 until num) {
        val inputs = br.readLine().split(" ")

        val cal = inputs[0]
        val number = if (inputs.size > 1) inputs[1].toInt() else -1

        when (cal) {
            "add" -> {
                S = S on number
            }
            "remove" -> {
                S = S off number
            }
            "check" -> {
                sb.append(S.check(number)).append('\n')
            }
            "toggle" -> {
                S = S toggle number
            }
            "all" -> {
                S = S or 0.inv()
            }
            "empty" -> {
                S = 0
            }
        }
    }

    println(sb.toString())
}


infix fun Int.on(i: Int) = this or (1 shl i - 1)

infix fun Int.off(i: Int) = this and (1 shl i - 1).inv()

infix fun Int.check(i: Int) = if (this and (1 shl i - 1) >= 1) 1 else 0

infix fun Int.toggle(i: Int) = this xor (1 shl i - 1)

