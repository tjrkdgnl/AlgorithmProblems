package algorithms.체스판다시칠하기

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Integer.min

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }

    val matrix = Array(n) { CharArray(m) }

    for (i in 0 until n) {
        val inputs = br.readLine()

        for (j in 0 until inputs.length) {
            matrix[i][j] = inputs[j]
        }
    }

    var ans = 987654321

    for (i in 0 until n - 7) {
        for (j in 0 until m - 7) {
            ans = min(ans, find(matrix, i, j))
        }
    }

    println(ans)
}

private fun find(matrix: Array<CharArray>, row: Int, col: Int): Int {
    var target = matrix[row][col]
    var count = 0

    for (y in row until row + 8) {
        for (x in col until col + 8) {
            if (target != matrix[y][x]) {
                count++
            }

            target = if (target == 'W') 'B' else 'W'
        }
        target = if (target == 'W') 'B' else 'W'
    }

    return min(count, 64 - count)
}