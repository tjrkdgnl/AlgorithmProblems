package 부분수열의합

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, s) = br.readLine().split(" ").map { it.toInt() }

    val matrix = br.readLine().split(" ").map { it.toInt() }.toIntArray()

    var result = recursive(matrix, 0, 0, s)

    if (s == 0) {
        result--
    }

    println(result)
}

fun recursive(matrix: IntArray, idx: Int, sum: Int, s: Int): Int {
    if (idx == matrix.size) {
        if (sum == s) {
            return 1
        }
        return 0
    }

    var ret = 0

    ret += recursive(matrix, idx + 1, sum, s)
    ret += recursive(matrix, idx + 1, sum + matrix[idx], s)

    return ret
}