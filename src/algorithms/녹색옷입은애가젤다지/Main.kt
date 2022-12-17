package algorithms.녹색옷입은애가젤다지

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue

private data class Node(
    val y: Int,
    val x: Int,
    val w: Int
)

private val dy = intArrayOf(0, 0, -1, 1)
private val dx = intArrayOf(-1, 1, 0, 0)
private val INF = 987654321

private fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val sb = StringBuilder()
    var idx = 1

    while (true) {
        val n = br.readLine().toInt()

        if (n == 0) {
            break
        }

        val matrix = Array(n) { IntArray(n) }

        for (i in 0 until n) {
            val inputs = br.readLine().split(" ").map { it.toInt() }
            for (j in 0 until inputs.size) {
                matrix[i][j] = inputs[j]
            }
        }

        sb.append("Problem ${idx++}: ${dijkstra(matrix)}").append('\n')
    }

    println(sb.toString())
}

private fun dijkstra(matrix: Array<IntArray>): Int {
    val dist = Array(matrix.size) { IntArray(matrix[0].size) { INF } }
    val pq = PriorityQueue<Node> { o1, o2 ->
        o1.w - o2.w
    }

    pq.offer(Node(0, 0, 0))
    dist[0][0] = matrix[0][0]

    while (pq.isNotEmpty()) {
        val cur = pq.poll()

        for (i in 0 until 4) {
            val ny = cur.y + dy[i]
            val nx = cur.x + dx[i]

            if (ny < 0 || ny >= matrix.size || nx < 0 || nx >= matrix[0].size) continue

            val newDist = dist[cur.y][cur.x] + matrix[ny][nx]

            if (dist[ny][nx] > newDist) {
                dist[ny][nx] = newDist
                pq.offer(Node(ny, nx, newDist))
            }
        }
    }

    return dist[matrix.size - 1][matrix[0].size - 1]
}
