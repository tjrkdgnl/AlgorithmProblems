package algorithms.나무재테크

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private data class Tree(
    val y: Int,
    val x: Int,
    var age: Int = 1
) : Comparable<Tree> {
    override fun compareTo(other: Tree): Int {
        return this.age - other.age
    }
}

private val dy = intArrayOf(0, -1, -1, -1, 0, 1, 1, 1)
private val dx = intArrayOf(-1, -1, 0, 1, 1, 1, 0, -1)

private fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m, year) = br.readLine().split(" ").map { it.toInt() }
    val A = Array(n) { IntArray(n) }
    val matrix = Array(n) { IntArray(n) }
    val deadTree = LinkedList<Tree>()
    val pq = ArrayDeque<Tree>()

    for (i in 0 until n) {
        val inputs = br.readLine().split(" ").map { it.toInt() }

        for (j in 0 until inputs.size) {
            A[i][j] = inputs[j]
            matrix[i][j] = 5
        }
    }

    for (i in 0 until m) {
        val (y, x, age) = br.readLine().split(" ").map { it.toInt() }
        pq.offer(Tree(y - 1, x - 1, age))
    }

    for (i in 0 until year) {
        spring(matrix, pq, deadTree)
        summer(matrix, deadTree)
        fall(matrix, pq)
        winter(matrix, A)
    }

    println(pq.size)
}


private fun spring(matrix: Array<IntArray>, pq: ArrayDeque<Tree>, deadTree: LinkedList<Tree>) {
    val size = pq.size

    for (i in 0 until size) {
        val tree = pq.poll()

        val y = tree.y
        val x = tree.x

        if (matrix[y][x] - tree.age >= 0) {
            matrix[y][x] -= tree.age
            tree.age++
            pq.offerLast(tree)
        } else {
            deadTree.offer(tree)
        }
    }
}

private fun summer(matrix: Array<IntArray>, deadTree: LinkedList<Tree>) {
    while(deadTree.isNotEmpty()){
        val tree = deadTree.poll()
        val y = tree.y
        val x = tree.x

        matrix[y][x] += tree.age / 2
    }
}

private fun fall(matrix: Array<IntArray>, pq: ArrayDeque<Tree>) {
    val save = mutableListOf<Tree>()

    for (tree in pq) {
        val y = tree.y
        val x = tree.x

        if (tree.age % 5 == 0) {
            for (i in 0 until 8) {
                val ny = y + dy[i]
                val nx = x + dx[i]

                if (ny < 0 || ny >= matrix.size || nx < 0 || nx >= matrix[0].size) continue

                save.add(Tree(ny, nx))
            }
        }
    }

    for (tree in save) {
        pq.offerFirst(tree)
    }
}

private fun winter(matrix: Array<IntArray>, A: Array<IntArray>) {
    for (i in 0 until A.size) {
        for (j in 0 until A[0].size) {
            matrix[i][j] += A[i][j]
        }
    }
}