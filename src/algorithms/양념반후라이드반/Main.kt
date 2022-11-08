package algorithms.양념반후라이드반

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Integer.min


/**
 * 이 문제에서 메모이제이션을 사용하면 10만개 * 10만개 = 100억개의 공간이 필요하므로 문제를 풀 수는 없지만
 * DP를 이용하여 풀 수 있는 방법을 작성해봄
 */
var ans = 987654321
val memo = Array(100001) { IntArray(100001) { 987654321 } }

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (A, B, C, X, Y) = br.readLine().split(" ").map { it.toInt() }

    memo[0][0] = 0
    memo[1][0] = A
    memo[0][1] = B
    memo[1][1] = C * 2

    println(find(A, B, C * 2, X, Y))

}

private fun find(A: Int, B: Int, C: Int, x: Int, y: Int): Int {
    if (x < 0 || y < 0) return 0

    if (x == 0 && y == 0) return memo[0][0]
    else if (x == 1 && y == 0) return memo[1][0]
    else if (x == 0 && y == 1) return memo[0][1]
    else if (x == 1 && y == 1) return memo[1][1]
    else if (memo[x][y] != 987654321) return memo[x][y]

    if (x - 1 >= 0 && y - 1 >= 0) {
        memo[x][y] = min(memo[x][y], find(A, B, C, x - 1, y - 1) + C)
    }
    if (x - 1 >= 0) {
        memo[x][y] = min(memo[x][y], find(A, B, C, x - 1, y) + A)
    }
    if (y - 1 >= 0) {
        memo[x][y] = min(memo[x][y], find(A, B, C, x, y - 1) + B)
    }

    return memo[x][y]
}

