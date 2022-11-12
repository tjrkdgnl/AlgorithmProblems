package algorithms.탈옥

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList

private data class Node(
    val y: Int,
    val x: Int,
    val open: Int
)

val dx = intArrayOf(-1, 0, 1, 0)
val dy = intArrayOf(0, -1, 0, 1)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val sb = StringBuilder()

    for (i in 0 until n) {
        val (h, w) = br.readLine().split(" ").map { it.toInt() }
        val matrix = Array(h + 2) { CharArray(w + 2) }
        val prisons = Array(2) { Node(0, 0, 0) }
        var prisonIdx = 0

        for (y in 0 until h) {
            val input = br.readLine()

            for (x in 0 until w) {
                matrix[y + 1][x + 1] = input[x]

                if (matrix[y + 1][x + 1] == '$') {
                    prisons[prisonIdx++] = Node(y + 1, x + 1, 0)
                }
            }
        }

        val helper = bfs(matrix, 0, 0)
        val prison1 = bfs(matrix, prisons[0].y, prisons[0].x)
        val prison2 = bfs(matrix, prisons[1].y, prisons[1].x)

        sb.append(findMinimum(matrix, helper, prison1, prison2)).append('\n')
    }

    print(sb.toString())
}

private fun findMinimum(
    matrix: Array<CharArray>,
    helper: Array<IntArray>,
    prison1: Array<IntArray>,
    prison2: Array<IntArray>
): Int {
    var min = 987654321

    for (i in 0 until matrix.size ) {
        for (j in 0 until matrix[1].size ) {
            if(matrix[i][j] == '*') continue
            var sum = 0

            sum = if (matrix[i][j] == '#') {
                helper[i][j] + prison1[i][j] + prison2[i][j] - 2
            } else {
                helper[i][j] + prison1[i][j] + prison2[i][j]
            }

            min = if (min > sum) sum else min
        }
    }

    return min
}

private fun bfs(matrix: Array<CharArray>, y: Int, x: Int): Array<IntArray> {
    val history = Array(matrix.size) { IntArray(matrix[0].size) }
    val visit = Array(matrix.size) { BooleanArray(matrix[0].size) }
    val que = LinkedList<Node>()

    visit[y][x] = true
    que.offer(Node(y, x, 0))

    while (que.isNotEmpty()) {
        val cur = que.poll()

        history[cur.y][cur.x] = cur.open

        for (i in 0 until 4) {
            val ny = cur.y + dy[i]
            val nx = cur.x + dx[i]

            if (ny < 0 || ny >= matrix.size  || nx < 0 || nx >= matrix[0].size ) continue

            if (visit[ny][nx]) continue

            if (matrix[ny][nx] == '*') continue

            if (matrix[ny][nx] == '#') {
                que.offerLast(Node(ny, nx, cur.open + 1))
            } else {
                que.offerFirst(Node(ny, nx, cur.open))
            }

            visit[ny][nx] = true
        }
    }

    return history
}