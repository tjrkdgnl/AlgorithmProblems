package algorithms.스타트와링크

import java.io.BufferedReader
import java.io.InputStreamReader

private var min = 987654321

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val n = br.readLine().toInt()

    val matrix = Array(n) { IntArray(n) }

    for (i in 0 until n) {
        val input = br.readLine().split(" ").map { it.toInt() }

        for (j in 0 until input.size) {
            matrix[i][j] = input[j]
        }
    }

    combination(matrix, BooleanArray(n), IntArray(n / 2), 0, 0)

    println(min)
}

private fun combination(matrix: Array<IntArray>, visit: BooleanArray, start: IntArray, cnt: Int, idx: Int) {
    if (cnt == start.size) {
        val link = mutableListOf<Int>()
        var sResult = 0
        var lResult = 0

        for (i in 0 until matrix.size) {
            if (!start.contains(i)) {
                link.add(i)
            }
        }

        for (i in 0 until matrix.size / 2) {
            for (j in 0 until matrix.size / 2) {
                sResult += matrix[start[i]][start[j]]
                lResult += matrix[link[i]][link[j]]
            }
        }
        val result = if (sResult - lResult < 0) lResult - sResult else sResult - lResult

        min = if (min > result) result else min
        return
    }

    for (i in idx until matrix.size) {
        start[cnt] = i
        combination(matrix, visit, start, cnt + 1, i + 1)
    }
}