package algorithms.스타트링크

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList

private data class Node(
    val pos: Int,
    val count: Int,
)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (F, S, G, U, D) = br.readLine().split(" ").map { it.toInt() }

    val result = bfs(F, S, G, U, D)

    if (result == 987654321) {
        println("use the stairs")
    } else {
        println(result)
    }
}

private fun bfs(total: Int, current: Int, target: Int, up: Int, down: Int): Int {
    val visit = BooleanArray(1000001)
    val que = LinkedList<Node>()

    val buttons = intArrayOf(up, -down)

    que.offer(Node(current, 0))

    var min = 987654321

    while (que.isNotEmpty()) {
        val cur = que.poll()

        if (cur.pos == target) {
            min = if (min > cur.count) cur.count else min
            continue
        }

        for (button in buttons) {
            val next = cur.pos + button

            if (next < 1 || next > total) continue

            if (visit[next]) continue

            visit[next] = true
            que.offer(Node(next, cur.count + 1))
        }
    }

    return min
}

