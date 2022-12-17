package algorithms.패션왕신해빈

import java.io.BufferedReader
import java.io.InputStreamReader

//
// 시간 초과가 나는 코드
// private fun main() {
//    val br = BufferedReader(InputStreamReader(System.`in`))
//    val n = br.readLine().toInt()
//    val sb =StringBuilder()
//
//    for (i in 0 until n) {
//        val input = br.readLine().toInt()
//        val typeList = mutableListOf<String>()
//        val map = HashMap<String, MutableList<String>>()
//
//        for (j in 0 until input) {
//            val (clothes, type) = br.readLine().split(" ")
//
//            if (!typeList.contains(type)) {
//                typeList.add(type)
//            }
//
//            if (!map.contains(type)) {
//                map.computeIfAbsent(type) {
//                    val list = mutableListOf<String>()
//                    list.add(clothes)
//                    list
//                }
//            } else {
//                val list = map.getOrDefault(type, mutableListOf<String>())
//                list.add(clothes)
//            }
//        }
//
//        var result = 0
//
//        for (size in 1 .. typeList.size) {
//            result += combination(typeList, map, size, 0, 0)
//        }
//
//        sb.append(result).append('\n')
//    }
//
//    println(sb.toString())
//}
//
//private fun combination(
//    typeList: MutableList<String>,
//    map: HashMap<String, MutableList<String>>,
//    size: Int,
//    idx: Int,
//    cnt: Int
//): Int {
//    if (cnt == size) {
//        return 1
//    }
//    var ret = 0
//
//    for (i in idx until typeList.size) {
//        val list = map.getOrDefault(typeList[i], mutableListOf())
//
//        for (j in 0 until list.size) {
//            ret += combination(typeList, map, size, i + 1, cnt + 1)
//        }
//    }
//
//    return ret
//}

private fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val sb =StringBuilder()

    for (i in 0 until n) {
        val input = br.readLine().toInt()
        val map = HashMap<String, Int>()

        for (j in 0 until input) {
            val (clothes, type) = br.readLine().split(" ")
            map[type] = map.getOrDefault(type, 0) + 1
        }

        var result = 1

        for (size in map.values) {
            result *= size + 1
        }

        sb.append(result-1).append('\n')
    }

    print(sb.toString())
}