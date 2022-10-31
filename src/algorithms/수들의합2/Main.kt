package algorithms.수들의합2

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val list = br.readLine().split(" ").map { it.toInt() }

    var l = 0
    var r = 0

    var sum = 0
    var ans = 0

    while (l <= r) {
        if (sum <= m) {
            if (sum == m) {
                ans++
            }

            if (r >= list.size) break

            sum += list[r++]
        } else {
            sum -= list[l++]
        }
    }

    println(ans)
}