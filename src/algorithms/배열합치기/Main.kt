package algorithms.배열합치기

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n,m) = br.readLine().split(" ").map { it.toInt() }
    val first = br.readLine().split(" ").map { it.toInt() }
    val second = br.readLine().split(" ").map { it.toInt() }

    val result = merge(first,second)

    val sb =StringBuilder()

    for (data in result) {
        sb.append(data).append(' ')
    }

    println(sb.toString())
}

fun merge(first: List<Int>, second: List<Int>): IntArray {
    val result = IntArray(first.size + second.size)

    var left = 0
    var right = 0
    var idx = 0

    while (left < first.size && right < second.size) {

        if (first[left] < second[right]) {
            result[idx++] = first[left++]
        } else {
            result[idx++] = second[right++]
        }
    }

    if (left >= first.size) {
        while (right < second.size) {
            result[idx++] = second[right++]
        }
    } else {
        while (left < first.size){
            result[idx++] = first[left++]
        }
    }

    return result
}