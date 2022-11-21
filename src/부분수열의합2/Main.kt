package 부분수열의합2

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine()
    val matrix = br.readLine().split(" ").map { it.toInt() }.toIntArray()

    val arr = BooleanArray(2000001)

    bruteForce(matrix, arr, 0, 0)

    for (i in 1 until arr.size ) {
        if (!arr[i]) {
            println(i)
            return
        }
    }

}

private fun bruteForce(matrix: IntArray, arr: BooleanArray, idx: Int, sum: Int) {
    if (idx == matrix.size) {
        return
    }

    arr[sum] = true
    bruteForce(matrix, arr, idx + 1, sum)
    arr[sum + matrix[idx]] = true
    bruteForce(matrix, arr, idx + 1, sum + matrix[idx])
}