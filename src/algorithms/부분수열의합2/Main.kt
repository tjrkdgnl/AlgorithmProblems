package algorithms.부분수열의합2

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    val n = st.nextToken().toInt()
    val s = st.nextToken().toInt()

    val arr = IntArray(n)
    st = StringTokenizer(br.readLine())
    for (i in 0 until n) {
        arr[i] = st.nextToken().toInt()
    }

    val a = mutableListOf<Int>()
    val b = mutableListOf<Int>()

    recursive(arr, a, 0, n / 2, 0)
    recursive(arr, b, n / 2, n, 0)

    a.sort()
    b.sort()

    println(a)
    println(b)

    search(a,b, s)
}

fun search(front: MutableList<Int>, end: MutableList<Int>, target: Int) {
    var l = 0
    var r = end.size - 1
    var cnt: Long = 0

    while (l < front.size && r >= 0) {
        val leftValue = front[l]
        val rightValue = end[r]

        if (leftValue + rightValue == target) {
            var frontTmp: Long = 0
            var lastTmp: Long = 0
            while (l < front.size && leftValue == front[l]) {
                frontTmp++
                l++
            }
            while (r > -1 && rightValue == end[r]) {
                lastTmp++
                r--
            }
            cnt += frontTmp * lastTmp
        }
        if (leftValue + rightValue < target) {
            l++
        } else if (leftValue + rightValue > target) {
            r--
        }
    }

    if (target == 0) cnt--
    println(cnt)
}

private fun recursive(arr: IntArray, subList: MutableList<Int>, start: Int, end: Int, sum: Int) {

    if (start == end) {
        subList.add(sum)
        return
    }

    recursive(arr, subList, start + 1, end, sum)
    recursive(arr, subList, start + 1, end, sum + arr[start])
}