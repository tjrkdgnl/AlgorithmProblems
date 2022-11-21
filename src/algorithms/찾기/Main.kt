package algorithms.찾기

import java.io.BufferedReader
import java.io.InputStreamReader

private fun getPi(p: String): IntArray {
    val pi = IntArray(p.length)
    var j = 0

    for (i in 1 until p.length) {
        while (j > 0 && p[i] != p[j]) {
            j = pi[j - 1]
        }

        if (p[i] == p[j]) {
            pi[i] = ++j
        }
    }

    return pi
}

private fun kmp(t: String, p: String): List<Int> {
    val find = mutableListOf<Int>()
    val pi = getPi(p)
    var j = 0

    for (i in 0 until t.length) {
        while (j > 0 && t[i] != p[j]) {
            j = pi[j - 1]
        }

        if (t[i] == p[j]) {
            if (j == p.length - 1) {
                find.add(i - (p.length - 1) + 1)
                j = pi[j]
            } else {
                j++
            }
        }
    }

    return find
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val sb = StringBuilder()
    val T = br.readLine()
    val P = br.readLine()

    val answer = kmp(T, P)

    sb.append(answer.size).append('\n')

    for (ans in answer) {
        sb.append(ans).append('\n')
    }

    print(sb.toString())
}

