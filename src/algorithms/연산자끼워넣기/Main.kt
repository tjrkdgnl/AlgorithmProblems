package algorithms.연산자끼워넣기

import java.io.BufferedReader
import java.io.InputStreamReader

private var max = -987654321
private var min = 987654321

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val n = br.readLine().toInt()
    val numbers = br.readLine().split(" ").map { it.toInt() }
    val operators = br.readLine().split(" ").map { it.toInt() }.toIntArray()

    val size = operators.sum()

    backtracking(numbers, operators, size, IntArray(size), 0)

    println(max)
    println(min)
}

private fun backtracking(numbers: List<Int>, operators: IntArray, size: Int, arr: IntArray, cnt: Int) {
    if (cnt == size) {
        var idx = 0

        val result = numbers.reduce { acc, num ->
            when (arr[idx++]) {
                0 -> acc + num
                1 -> acc - num
                2 -> acc * num
                3 -> acc / num
                else -> acc
            }
        }

        max = if (max < result) result else max
        min = if (min > result) result else min
        return
    }

    for (i in 0 until 4) {
        if (operators[i] == 0) continue

        operators[i]--
        arr[cnt] = i
        backtracking(numbers, operators, size, arr, cnt + 1)
        operators[i]++
    }
}
