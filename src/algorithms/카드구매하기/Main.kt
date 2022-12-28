package algorithms.카드구매하기

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

private fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val pi = br.readLine().split(" ").map { it.toInt() }.toIntArray()

    val dp = IntArray(n + 1) { -1 }

    dp[0] = 0

    for (i in 1..n) {
        for (j in 1..i) {
            dp[i] = max(dp[i], dp[i - j] + pi[j - 1])
        }
    }

    println(dp[n])

}

private fun topDown(dp: IntArray, pi: IntArray, n: Int): Int {
    if (n == 0) return 0

    if (dp[n] != -1) return dp[n]

    for (i in 1..pi.size) {
        if (n - i >= 0) {
            dp[n] = max(dp[n], topDown(dp, pi, n - i) + pi[i - 1])
        }
    }

    return dp[n]
}
