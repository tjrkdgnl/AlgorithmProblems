package algorithms.두동전

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min

private val dy = intArrayOf(0, -1, 0, 1)
private val dx = intArrayOf(-1, 0, 1, 0)
private var ans = 987654321

private data class Node(val y: Int, val x: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val matrix = Array(n) { CharArray(m) }

    val yList = mutableListOf<Int>()
    val xList = mutableListOf<Int>()

    for (y in 0 until n) {
        val inputs = br.readLine()

        for (x in 0 until inputs.length) {
            matrix[y][x] = inputs[x]

            if (inputs[x] == 'o') {
                yList.add(y)
                xList.add(x)
            }
        }
    }

    for (i in 0 until 4) {
        dfs(matrix, Node(yList[0], xList[0]), Node(yList[1], xList[1]), 1, i)
    }


    if (ans == 987654321 || ans > 10) {
        println(-1)
        return
    }

    println(ans)
}

private fun dfs(matrix: Array<CharArray>, n1: Node, n2: Node, click: Int, dir: Int) {
    if (click > 10) {
        ans = min(ans, click)
        return
    }

    var n1Y = n1.y + dy[dir]
    var n1X = n1.x + dx[dir]

    var n2Y = n2.y + dy[dir]
    var n2X = n2.x + dx[dir]

    val n1Range = range(n1Y, matrix.size, n1X, matrix[0].size)
    val n2Range = range(n2Y, matrix.size, n2X, matrix[0].size)

    if (!n1Range && !n2Range) {
        return
    } else if (n1Range && !n2Range) {
        ans = min(ans, click)
        return
    } else if (!n1Range && n2Range) {
        ans = min(ans, click)
        return
    }

    if (matrix[n1Y][n1X] == '#') {
        n1Y = n1.y
        n1X = n1.x
    }

    if (matrix[n2Y][n2X] == '#') {
        n2Y = n2.y
        n2X = n2.x
    }

    for (i in 0 until 4) {
        dfs(matrix, Node(n1Y, n1X), Node(n2Y, n2X), click + 1, i)
    }
}

private fun range(y: Int, yLength: Int, x: Int, xLength: Int) =
    (y >= 0 && y < yLength && x >= 0 && x < xLength)




