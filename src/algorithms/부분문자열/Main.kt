package algorithms.부분문자열

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val S = br.readLine()
    val P = br.readLine()

    val ans = kmp(S, P)

    if (ans.size != 0) {
        println(1)
    } else {
        println(0)
    }
}

private fun getPi(p: String): IntArray {
    val m = p.length
    var j = 0
    val pi = IntArray(m) { 0 }

    for (i in 1 until m) {
        while (j > 0 && p[i] != p[j]) {
            j = pi[j - 1]
        }

        if (p[i] == p[j]) {
            pi[i] = ++j
        }
    }

    return pi
}

private fun kmp(s: String, p: String): MutableList<Int> {
    val ans = mutableListOf<Int>()
    val pi = getPi(p)

    val n = s.length
    val m = p.length
    var j = 0

    for (i in 0 until n) {
        while (j > 0 && s[i] != p[j]) {
            j = pi[j - 1]
        }

        if (s[i] == p[j]) {
            if (j == m - 1) {
                ans.add(i - m + 1)
                j = pi[j]
            } else {
                j++
            }
        }
    }

    return ans
}