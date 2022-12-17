package algorithms.DFS와BFS

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList

private val sb = StringBuilder()

private fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m, v) = br.readLine().split(" ").map { it.toInt() }
    val adjList = Array(n + 1) { mutableListOf<Int>() }

    for (i in 0 until m) {
        val (s, e) = br.readLine().split(" ").map { it.toInt() }

        adjList[s].add(e)
        adjList[e].add(s)
    }

    for (list in adjList) {
        list.sort()
    }

    dfs(adjList,BooleanArray(n+1),v)

    sb.append('\n')

    bfs(adjList, v)

    println(sb.toString())
}

private fun dfs(adjList: Array<MutableList<Int>>, visit: BooleanArray, v: Int) {
    visit[v] = true

    sb.append(v).append(" ")

    for (next in adjList[v]) {
        if (visit[next]) continue

        dfs(adjList, visit, next)
    }
}

private fun bfs(adjList: Array<MutableList<Int>>, v: Int) {
    val que = LinkedList<Int>()
    val visit = BooleanArray(adjList.size)

    que.offer(v)
    visit[v] = true

    while(que.isNotEmpty()){
        val cur = que.poll()

        sb.append(cur).append(" ")

        for(next in adjList[cur]){
            if(visit[next]) continue

            visit[next] =true
            que.offer(next)
        }
    }
}