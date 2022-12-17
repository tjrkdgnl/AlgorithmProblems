package algorithms.부분합

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min

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
//            var ac: Long = 0
//            var bc: Long = 0
//            while (l < front.size && leftValue == front[l]) {
//                ac++
//                l++
//            }
//            while (r > -1 && rightValue == end[r]) {
//                bc++
//                r--
//            }
//            cnt += ac * bc
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
    val (n, s) = br.readLine().split(" ").map { it.toInt() }
    val list = br.readLine().split(" ").map { it.toLong() }

    var slow = 0
    var fast = 1
    var sum = list[slow]
    var ans = Int.MAX_VALUE

    while (fast < list.size) {
        if (sum < s) {
            sum += list[fast++]
        } else {
            ans = min(ans, fast - slow)

            sum -= list[slow++]
        }
    }

    while (slow < fast) {
        if (sum >= s) {
            ans = min(ans, fast - slow)
        }

        sum -= list[slow++]
    }

    if (ans == Int.MAX_VALUE) {
        println(0)
    } else {
        println(ans)
    }
}