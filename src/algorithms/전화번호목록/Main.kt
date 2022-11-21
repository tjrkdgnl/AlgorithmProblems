package algorithms.전화번호목록

import java.util.*


fun main() {

    val clothes: Array<Array<String>> = arrayOf(
        arrayOf("yellow_hat", "face"),
        arrayOf("blue_sunglasses", "face"),
        arrayOf("green_turban", "face"),
        arrayOf("yellow_hat", "headgear"),
        arrayOf("blue_sunglasses", "eyewear"),
        arrayOf("green_turban", "headgear")
    )

    var answer = 0
    val map = HashMap<String, MutableList<String>>()

    for (clothe in clothes) {
        val type = clothe[clothe.size - 1]
        val list = map.get(type) ?: mutableListOf<String>()

        for (i in 0 until clothe.size - 1) {
            val passion = clothe[i]
            list.add(passion)
        }

        map[type] = list
    }

    val matrix = Array<MutableList<String>>(map.size) { mutableListOf() }

    for ((idx, entry) in map.entries.withIndex()) {
        val (type, list) = entry

        for (i in 0 until list.size) {
            matrix[idx].add(list[i])
        }

        answer += list.size
    }

    for (size in 0 until matrix.size-1) {
        answer += dfs(matrix, Array(matrix.size) { BooleanArray(31) }, Array(size + 1) { "" }, size +1, 0, 0)
    }

    println(answer)
}

fun dfs(
    matrix: Array<MutableList<String>>,
    visit: Array<BooleanArray>,
    arr: Array<String>,
    limit: Int,
    next: Int,
    cnt: Int
): Int {
    if (cnt == limit) {
        return 1
    }

    var ret = 0

    for (i in 0 until (matrix.size * matrix[next].size)) {
        val y = i / matrix[next].size
        val x = i % matrix[next].size

        if (visit[y][x]) continue

        visit[y][x] = true

        arr[cnt] = matrix[y][x]
        ret += dfs(matrix, visit, arr, limit, i, cnt + 1)

        visit[y][x] = false
    }

    return ret

}

