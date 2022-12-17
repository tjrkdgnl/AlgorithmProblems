package algorithms.게임맵최단거리

import java.util.LinkedList
import kotlin.math.min

private val dy = intArrayOf(0, 0, -1, 1)
private val dx = intArrayOf(-1, 1, 0, 0)

private val INF = 987654321
private val dp = Array(100) { IntArray(100) { INF } }
private val visit = Array(100) { BooleanArray(100) }

private data class Node(
    val y: Int,
    val x: Int,
    val count: Int
)

private fun main() {
    val matrix = arrayOf(
        intArrayOf(1, 0, 1, 1, 1),
        intArrayOf(1, 0, 1, 0, 1),
        intArrayOf(1, 0, 1, 1, 1),
        intArrayOf(1, 1, 1, 0, 1),
        intArrayOf(0, 0, 0, 0, 1)
    )


    visit[0][0] = true

    val result = bfs(matrix)

    if (result == INF) {
        println(-1)
    } else {
        println(result + 1)
    }
}

private fun dfs(matrix: Array<IntArray>, visit: Array<BooleanArray>, y: Int, x: Int): Int {
    if (y == matrix.size - 1 && x == matrix[0].size - 1) {
        return 0
    }

    if (dp[y][x] != INF) {
        return dp[y][x]
    }

    for (i in 0 until 4) {
        val ny = y + dy[i]
        val nx = x + dx[i]

        if (ny < 0 || ny >= matrix.size || nx < 0 || nx >= matrix[0].size) continue

        if (matrix[ny][nx] == 0) continue

        if (visit[ny][nx]) continue

        visit[ny][nx] = true
        dp[y][x] = min(dp[y][x], dfs(matrix, visit, ny, nx) + 1)
        visit[ny][nx] = false
    }

    return dp[y][x]
}

private fun bfs(matrix: Array<IntArray>): Int {
    val que = LinkedList<Node>()
    val visit = Array(matrix.size) { BooleanArray(matrix[0].size) }
    var count = 987654321

    que.offer(Node(0, 0, 0))
    visit[0][0] = true

    while (que.isNotEmpty()) {
        val cur = que.poll()

        if (cur.y == matrix.size - 1 && cur.x == matrix[0].size - 1) {
            count = min(count, cur.count)
            return count
        }

        visit[cur.y][cur.x] = true

        for (i in 0 until 4) {
            val ny = cur.y + dy[i]
            val nx = cur.x + dx[i]

            if (ny < 0 || ny >= matrix.size || nx < 0 || nx >= matrix[0].size) continue

            if (matrix[ny][nx] == 0) continue

            if (visit[ny][nx]) continue


            que.offer(Node(ny, nx, cur.count + 1))
        }
    }

    return count
}