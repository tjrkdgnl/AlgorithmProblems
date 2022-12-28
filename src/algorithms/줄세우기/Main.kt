package algorithms.줄세우기

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.HashSet


private fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }

    val adjList = Array(n + 1) { mutableListOf<Int>() }
    val edgeCount = IntArray(n + 1)


    val ans = intArrayOf()

    val s= HashSet<Int>()

    s.sorted().toIntArray()


    for (i in 0 until m) {
        val (s, e) = br.readLine().split(" ").map { it.toInt() }

        adjList[s].add(e)

        edgeCount[e]++
    }

    val answer = topologySort(adjList, edgeCount)

    answer.forEach {
        print("$it ")
    }

}

private fun topologySort(adjList: Array<MutableList<Int>>, edgeCount: IntArray): MutableList<Int> {
    val que = LinkedList<Int>()
    val answer = mutableListOf<Int>()

    for (i in 1 until edgeCount.size) {
        if (edgeCount[i] == 0) {
            que.offer(i)
        }
    }

    for (i in 1 until adjList.size) {

        if (que.isEmpty()) {
            return mutableListOf()
        }

        val cur = que.poll()

        answer.add(cur)

        for (next in adjList[cur]) {
            if(--edgeCount[next] ==0) {
                que.offer(next)
            }
        }
    }

    return answer
}