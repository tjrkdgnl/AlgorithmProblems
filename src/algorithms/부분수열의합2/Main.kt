package algorithms.부분수열의합2

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

//fun main() {
//    val br = BufferedReader(InputStreamReader(System.`in`))
//    var st = StringTokenizer(br.readLine())
//
//    val n = st.nextToken().toInt()
//    val s = st.nextToken().toInt()
//
//    val arr = IntArray(n)
//    st = StringTokenizer(br.readLine())
//    for (i in 0 until n) {
//        arr[i] = st.nextToken().toInt()
//    }
//
//    val a = mutableListOf<Int>()
//    val b = mutableListOf<Int>()
//
//    recursive(arr, a, 0, n / 2, 0)
//    recursive(arr, b, n / 2, n, 0)
//
//    a.sort()
//    b.sort()
//
//    search(a,b, s)
//}
//
//private fun search(front: MutableList<Int>, end: MutableList<Int>, target: Int) {
//    var l = 0
//    var r = end.size - 1
//    var cnt: Long = 0
//
//    while (l < front.size && r >= 0) {
//        val leftValue = front[l]
//        val rightValue = end[r]
//
//        if (leftValue + rightValue == target) {
//            var frontTmp: Long = 0
//            var lastTmp: Long = 0
//            while (l < front.size && leftValue == front[l]) {
//                frontTmp++
//                l++
//            }
//            while (r > -1 && rightValue == end[r]) {
//                lastTmp++
//                r--
//            }
//            cnt += frontTmp * lastTmp
//        }
//        if (leftValue + rightValue < target) {
//            l++
//        } else if (leftValue + rightValue > target) {
//            r--
//        }
//    }
//
//    if (target == 0) cnt--
//    println(cnt)
//}
//
//private fun recursive(arr: IntArray, subList: MutableList<Int>, start: Int, end: Int, sum: Int) {
//
//    if (start == end) {
//        subList.add(sum)
//        return
//    }
//
//    recursive(arr, subList, start + 1, end, sum)
//    recursive(arr, subList, start + 1, end, sum + arr[start])
//}


private fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, target) = br.readLine().split(" ").map { it.toInt() }
    val list = br.readLine().split(" ").map { it.toInt() }

    val map = HashMap<Long, Int>()

    val aList = mutableListOf<Int>()
    val bList = mutableListOf<Int>()

    recursive(map, list, aList, list.size / 2, 0, 0)
    recursive(map, list, bList, n, list.size / 2, 0)

    aList.sort()
    bList.sort()

    println(findTarget(aList, bList, target))
}

private fun findTarget(aList: List<Int>, bList: List<Int>, target: Int): Long {
    var count = 0L
    var start = 0
    var end = bList.size - 1

    while (start < aList.size && end >= 0) {
        val sum = aList[start] + bList[end]

        if (target == sum) {
            var leftCount = 0L
            val lTemp = aList[start]

            while (start < aList.size && aList[start] == lTemp) {
                start++
                leftCount++
            }

            var rightCount = 0L
            val rTemp = bList[end]

            while (end >= 0 && bList[end] == rTemp) {
                end--
                rightCount++
            }

            count += leftCount * rightCount
        } else if (sum > target) {
            end--
        } else {
            start++
        }
    }

    if(target ==0) {
        count--
    }

    return count
}

private fun recursive(
    map: HashMap<Long, Int>,
    list: List<Int>,
    container: MutableList<Int>,
    size: Int,
    idx: Int,
    sum: Int
) {
    if (idx == size) {
        container.add(sum)
        return
    }

    recursive(map, list, container, size, idx + 1, sum)
    recursive(map, list, container, size, idx + 1, sum + list[idx])
}

