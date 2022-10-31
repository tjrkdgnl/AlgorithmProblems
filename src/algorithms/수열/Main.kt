package algorithms.수열

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, k) = br.readLine().split(" ").map { it.toInt() }

    /**
     * 새로운 방법
     * windowed 확장함수 이용하기
     * size      :윈도우 사이즈
     * transform :윈도우에 구성된 요소의 합으로 리턴
     * maxOf     :인자로 주어진 값들 중 최고로 큰 값 리턴
     * todo val answer = br.readLine().split(" ").map { it.toInt() }.windowed(k) { it.sum() }.maxOf { it }
     */

    val list = br.readLine().split(" ").map { it.toInt() }

    var l = 0
    var r = 0
    var c = k
    var sum = 0
    var m = -987654321

    while (l <= r && r <= list.size) {

        if (c >= 0) {
            if (c == 0) {
                m = max(m, sum)
            }

            if(r == list.size) break

            sum += list[r++]
            c--
        } else {
            c = 0
            sum -= list[l++]
        }
    }

    println(m)
}