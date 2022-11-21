package algorithms.부등호

import java.io.BufferedReader
import java.io.InputStreamReader

private val answer = mutableListOf<String>()

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val matrix = br.readLine().split(" ").toTypedArray()

    combination(matrix, BooleanArray(10), 0, -1, "")

    answer.sort()

    println(answer[answer.size - 1])
    println(answer[0])
}

private fun combination(matrix: Array<String>, visit: BooleanArray, cnt: Int, idx: Int, str: String) {
    if (idx == matrix.size) {
        answer.add(str)
        return
    }

    for (i in 0..9) {
        if (visit[i]) continue

        if (idx == -1) {
            visit[i] = true
            combination(matrix, visit, cnt + 1, 0, str + i)
            visit[i] = false
        } else {
            val prev = str[cnt - 1] - '0'

            if (matrix[idx] == "<" && prev < i) {
                visit[i] = true
                combination(matrix, visit, cnt + 1, idx + 1, str + i)
                visit[i] = false
            } else if (matrix[idx] == ">" && prev > i) {
                visit[i] = true
                combination(matrix, visit, cnt + 1, idx + 1, str + i)
                visit[i] = false
            }
        }
    }
}