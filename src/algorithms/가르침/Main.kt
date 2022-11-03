package algorithms.가르침

import java.io.BufferedReader
import java.io.InputStreamReader

var max = -987654321
val alphabet = ('a'..'z').toList()

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, k) = br.readLine().split(" ").map { it.toInt() }

    val wordList = mutableListOf<String>()
    val wordCount = Array(n) { 0 }
    val possible = HashSet<Char>()


    for (i in 0 until n) {
        val word = br.readLine()
            .removePrefix("anta")
            .removeSuffix("tica")

        wordList.add(word)

        for (cha in word) {
            possible.add(cha)
            wordCount[i]++
        }
    }

    if (k < 5) {
        println(0)
        return
    }

    if (k == 26) {
        println(n)
        return
    }

    val known = charArrayOf('a', 'n', 't', 'c', 'i')
    val visit = BooleanArray(26)

    for (cha in known) {
        visit[cha - 'a'] = true
    }

    recursive(
        possible.toList(),
        wordCount,
        wordList,
        visit,
        k - 5,
        0,
        0
    )

    println(max)
}

fun recursive(
    possible: List<Char>,
    wordCount: Array<Int>,
    wordList: MutableList<String>,
    visit: BooleanArray,
    k: Int,
    cur: Int,
    cnt: Int
) {
    if (cnt == k) {

        var read = 0

        for ((idx, word) in wordList.withIndex()) {
            var count = 0

            for (i in 0 until visit.size) {
                if (visit[i] && word.contains(alphabet[i])) {
                    count++
                }
            }

            if (count == wordCount[idx]) {
                read++
            }
        }

        max = if (max < read) read else max
        return
    }

    for (cha in possible) {
        val idx = cha - 'a'

        if (visit[idx]) continue

        visit[idx] = true

        recursive(possible, wordCount, wordList, visit, k, cur + 1, cnt + 1)

        visit[idx] = false
    }
}