package algorithms.치킨배달

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs
import kotlin.math.min

private val INF = 987654321
private var ans = INF

private fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }

    val matrix = Array(n) { IntArray(n) }
    val store = mutableListOf<Int>()
    var idx = 0

    for (i in 0 until n) {
        val inputs = br.readLine().split(" ").map { it.toInt() }

        for (j in 0 until n) {
            matrix[i][j] = inputs[j]

            if (inputs[j] == 2) {
                store.add(idx)
            }

            idx++
        }
    }

    val visit = BooleanArray(store.size)

    permutation(matrix, visit, store.toIntArray(), m, IntArray(m), 0, 0)

    if (ans == INF) {
        println(0)
    } else {
        println(ans)
    }
}

private fun permutation(
    matrix: Array<IntArray>,
    visit: BooleanArray,
    store: IntArray,
    m: Int,
    arr: IntArray,
    idx: Int,
    cnt: Int
) {
    if (cnt == m) {
        ans = min(ans, calculate(matrix, arr))
        return
    }

    for (i in idx until store.size) {
        if (visit[i]) continue

        visit[i] = true
        arr[cnt] = store[i]
        permutation(matrix, visit, store, m, arr, i + 1, cnt + 1)
        visit[i] = false
    }
}

private fun calculate(matrix: Array<IntArray>, arr: IntArray): Int {
    var result = 0

    for (i in 0 until matrix.size) {
        for (j in 0 until matrix.size) {
            if (matrix[i][j] == 1) {
                var dist = INF

                for (data in arr) {
                    val y = data / matrix.size
                    val x = data % matrix.size

                    dist = min(dist, abs(y - i) + abs(x - j))
                }

                result += dist
            }
        }
    }

    return result
}