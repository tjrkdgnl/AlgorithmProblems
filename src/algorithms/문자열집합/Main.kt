package algorithms.문자열집합

import java.io.BufferedReader
import java.io.InputStreamReader


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }

    val set = HashSet<String>()

    for (i in 0 until n) {
        val input = br.readLine()
        set.add(input)
    }

    var result = 0

    for (i in 0 until m) {
        val input = br.readLine()

        if(set.contains(input)){
            result ++
        }
    }

    val str  ="bca"


    println(result)
}
