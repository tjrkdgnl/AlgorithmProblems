package algorithms.`123더하기`

import java.io.BufferedReader
import java.io.InputStreamReader

private fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val sb = StringBuilder()

    for (i in 0 until n) {
        val target = br.readLine().toInt()
        val dp = IntArray(target + 1) { -1 }

        dp[0] = 1
        dp[target] = find(dp, target)

        sb.append(dp[target]).append('\n')
    }

    println(sb.toString())
}

private fun find(dp: IntArray, n: Int): Int {
    if (n < 0) {
        return 0
    }

    if (n == 0) {
        return 1
    }

    if (dp[n] != -1) {
        return dp[n]
    }

    dp[n] = find(dp, n - 1) + find(dp, n - 2) + find(dp, n - 3)

    return dp[n]
}