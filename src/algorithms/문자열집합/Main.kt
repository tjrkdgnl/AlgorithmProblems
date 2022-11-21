package algorithms.문자열집합

import java.io.BufferedReader
import java.io.InputStreamReader


class Trie {

    val dumy = Node()

    data class Node(
        val alphabet: Array<Node?> = Array(26) { null },
        var next: Node? = null,
        var isLast: Boolean = false
    )

    fun add(s: String) {
        var tail: Node? = dumy

        for (cha in s) {
            tail = if (tail?.alphabet?.get(cha - 'a') == null) {
                val newNode = Node(isLast = true)

                tail?.alphabet?.set(cha - 'a', newNode)
                newNode
            } else {
                tail.alphabet[cha - 'a']
            }
        }
    }

    fun checkWord(s: String): Boolean {
        var tail: Node? = dumy

        for (cha in s) {
            if (tail?.alphabet?.get(cha - 'a') == null) {
                return false
            } else {
                tail = tail.alphabet[cha - 'a']
            }
        }

        return true
    }

}


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
//    val (n, m) = br.readLine().split(" ").map { it.toInt() }

//    val sList = mutableListOf<String>()
//    val mString = StringBuilder()
//
//    for (i in 0 until n) {
//        sList.add(br.readLine())
//    }
//    for (i in 0 until m) {
//        mString.append(br.readLine())
//    }

    val trie = Trie()

    val word = "baekjoon"

    trie.add(word)

    println(trie.checkWord(word))

}