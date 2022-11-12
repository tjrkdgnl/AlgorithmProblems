package algorithms.늑대와양

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList

private data class Node(
    val y: Int,
    val x: Int
)

val dy = intArrayOf(0, -1, 0, 1)
val dx = intArrayOf(-1, 0, 1, 0)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (R, C) = br.readLine().split(" ").map { it.toInt() }

    val matrix = Array(R) { CharArray(C) }

    for (i in 0 until R) {
        val inputs = br.readLine()

        for (j in 0 until inputs.length) {
            matrix[i][j] = inputs[j]
        }
    }

    if (bfs(matrix)) {
        println(1)
        print(matrix)

    } else {
        println(0)
    }
}

private fun bfs(matrix: Array<CharArray>): Boolean {
    val que = LinkedList<Node>()

    for (y in 0 until matrix.size) {
        for (x in 0 until matrix[0].size) {
            if (matrix[y][x] == 'W') {
                que.offer(Node(y, x))
            }
        }
    }

    while (que.isNotEmpty()) {
        val cur = que.poll()

        for (i in 0 until 4) {
            val ny = cur.y + dy[i]
            val nx = cur.x + dx[i]

            if (ny < 0 || ny >= matrix.size || nx < 0 || nx >= matrix[0].size) continue

            if (matrix[ny][nx] == 'S') {
                return false
            }

            if (matrix[ny][nx] == '.') {
                matrix[ny][nx] = 'D'
            }
        }
    }

    return true
}

private fun print(matrix: Array<CharArray>) {
    val sb = java.lang.StringBuilder()

    for (y in 0 until matrix.size) {
        for (x in 0 until matrix[0].size) {
            sb.append(matrix[y][x])
        }
        sb.append('\n')
    }

    println(sb.toString())
}