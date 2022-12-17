package algorithms.바이러스

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min

private val INF = 987654321

private fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val m = br.readLine().toInt()

    val dist = Array(n + 1) { IntArray(n + 1) { INF } }

    for (i in 0 until m) {
        val (s, e) = br.readLine().split(" ").map { it.toInt() }

        dist[s][e] = 1
        dist[e][s] = 1
    }

    for (i in 0 until n) {
        dist[i][i] = 0
    }

    for (k in 1..n) {
        for (i in 1..n) {
            for (j in 1..n) {
                dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])
            }
        }
    }

    var count = 0

    for (data in dist[1]) {
        if (data != INF && data != 0) {
            count++
        }
    }

    dist.forEach {
        println(it.contentToString())
    }

    println(count)
}