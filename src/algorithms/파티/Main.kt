package algorithms.파티

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue
import kotlin.math.max

private data class Node(
    val idx: Int,
    val w: Int
)

private val INF = 987654321

private fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m, x) = br.readLine().split(" ").map { it.toInt() }
    val adjList = Array<MutableList<Node>>(n + 1) { mutableListOf() }

    for (i in 0 until m) {
        val (s, e, w) = br.readLine().split(" ").map { it.toInt() }

        adjList[s].add(Node(e, w))
    }

    var ans = 0

    for (start in 1..n) {
        ans = max(ans, dijkstra(adjList, start, x) + dijkstra(adjList, x, start))
    }

    println(ans)
}

private fun dijkstra(adjList: Array<MutableList<Node>>, start: Int, x: Int): Int {
    val dist = IntArray(adjList.size) { INF }
    val pq = PriorityQueue<Node> { o1, o2 -> o1.w - o2.w }

    dist[start] = 0
    pq.offer(Node(start, 0))

    while (pq.isNotEmpty()) {
        val cur = pq.poll()

        if (cur.w > dist[cur.idx]) continue

        for (next in adjList[cur.idx]) {
            val newDist = dist[cur.idx] + next.w

            if (dist[next.idx] > newDist) {
                dist[next.idx] = newDist
                pq.offer(Node(next.idx, newDist))
            }
        }
    }

    return dist[x]
}