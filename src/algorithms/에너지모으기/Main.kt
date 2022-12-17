package algorithms.에너지모으기

import java.io.BufferedReader
import java.io.InputStreamReader

private var max = -987654321

private fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val list = br.readLine().split(" ").map { it.toInt() }

    permutation(list, BooleanArray(list.size), IntArray(list.size - 2), 0)

    println(max)
}

private fun permutation(matrix: List<Int>, visit: BooleanArray, arr: IntArray, cnt: Int) {
    if (cnt == matrix.size - 2) {
        val checks = BooleanArray(matrix.size)
        var sum = 0

        for (idx in arr) {
            checks[idx] = true

            var i = idx - 1
            var j = idx + 1

            while (i > 0 && checks[i]) {
                i--
            }

            while (j < matrix.size - 1 && checks[j]) {
                j++
            }

            sum += matrix[i] * matrix[j]
        }

        max = if (max < sum) sum else max
        return
    }

    for (i in 1 until matrix.size - 1) {
        if (visit[i]) continue

        visit[i] = true
        arr[cnt] = i
        permutation(matrix, visit, arr, cnt + 1)
        visit[i] = false
    }
}