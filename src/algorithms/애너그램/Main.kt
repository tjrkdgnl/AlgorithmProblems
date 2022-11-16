package algorithms.애너그램

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Math.abs

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val first = br.readLine()
    val second = br.readLine()

    val firstAlphabet = Array(26) { 0 }
    val secondAlphabet = Array(26) { 0 }

    for (cha in first) {
        val idx = cha - 'a'

        firstAlphabet[idx]++
    }

    for (cha in second) {
        val idx = cha - 'a'

        secondAlphabet[idx]++
    }

    var count = 0

    for (i in 0 until 26) {
        if (firstAlphabet[i] != secondAlphabet[i]) {
            count += abs(firstAlphabet[i] - secondAlphabet[i])
        }
    }

    println(count)
}