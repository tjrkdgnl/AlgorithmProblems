package algorithms.외판원순회

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min

private lateinit var dp: Array<IntArray>

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val matrix = Array(n) { IntArray(n) }

    dp = Array(n) { IntArray((1 shl n)) { 987654321 } }

    for (i in 0 until n) {
        val inputs = br.readLine().split(" ").map { it.toInt() }

        for (j in 0 until inputs.size) {
            matrix[i][j] = inputs[j]
        }
    }

    tsp(matrix, 1, 0, 0)

    var ans = 987654321

    for (i in 0 until matrix.size) {
        ans = min(ans, dp[i][1])
    }

    println(ans)
}

private fun tsp(matrix: Array<IntArray>, visit: Int, now: Int, cnt: Int): Int {
    if (cnt == matrix.size - 1) {
        if (matrix[now][0] != 0) {
            return matrix[now][0]
        }
        return 987654321
    }

    if (dp[now][visit] == 987654321) {
        for (next in 0 until matrix.size) {
            if (matrix[now][next] != 0 && visit and (1 shl next) == 0) {
                dp[now][visit] =
                    min(dp[now][visit], tsp(matrix, visit or (1 shl next), next, cnt + 1) + matrix[now][next])
            }
        }
    }

    return dp[now][visit]
}
