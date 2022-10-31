package algorithms.두수의합

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val list = br.readLine().split(" ").map { it.toInt() }.sorted()
    var target = br.readLine().toInt()

    var l = 0
    var r = list.size - 1
    var count = 0

    while (l < r) {
        val sum = list[l] + list[r]

        if (sum <= target) {
            if (sum == target) {
                count++
            }

            l++
        } else {
            r--
        }
    }

    println(count)
}