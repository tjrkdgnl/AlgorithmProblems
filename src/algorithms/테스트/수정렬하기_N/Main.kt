package algorithms.테스트.수정렬하기_N


/**
 * 배열 A를 내림차순 정렬하려고 한다. 단, 배열은 중복된 데이터가 들어 갈 수 있다.
 * 때문에 가장 먼저 정렬해야할 부분은 중복된 데이터 개수가 많은 것부터 정렬 후, 내림차순으로 정렬하려고 한다.
 * 만약 중복된 데이터가 같다면 수가 큰 값부터 오도록 한다.
 *
 * 예로 배열 A가 [1,2,2,3,3,3,4,5,5]를 주어진 정렬 기준대로 적용 시, A = [3,3,3,5,5,2,2,4,1]이 되어야 한다.
 *
 * 배열 A가 주어졌을 때 조건대로 정렬하는 로직을 만들어보시오.
 */

private data class Node(
    val key: Int,
    val count: Int
)

fun main() {
    val A = intArrayOf(1, 2, 2, 3, 3, 3, 4, 5, 5)
    val map = HashMap<Int, Int>()
    val list = mutableListOf<Node>()

    for (data in A) {
        map[data] = map.getOrDefault(data, 0) + 1
    }

    val keySet = map.keys

    for (key in keySet) {
        map[key]?.let { count ->
            list.add(Node(key, count))
        }
    }

    list.sortWith(compareBy({ node -> node.count }, { node -> node.key }))

    val sb = StringBuilder()

    for (i in list.size - 1 downTo 0) {
        val (key, count) = list[i]

        for (j in 0 until count) {
            sb.append(key).append(' ')
        }
    }

    println(sb.toString())
}

