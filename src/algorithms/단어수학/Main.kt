package algorithms.단어수학

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val alphabet = IntArray(26)

    for (i in 0 until n) {
        val input = br.readLine()
        var carry = 1

        repeat(input.length - 1) {
            carry *= 10
        }

        for (alpha in input) {
            val idx = alpha - 'A'

            alphabet[idx] += carry
            carry /= 10
        }
    }

    alphabet.sortDescending()

    var num = 9
    var answer = 0

    alphabet.forEach {
        if (it != 0) {
            answer += it * num--
        }
    }

    println(answer)
}

//fun main() {
//    val br = BufferedReader(InputStreamReader(System.`in`))
//    val n = br.readLine().toInt()
//    val matrix = mutableListOf<String>()
//
//    var inputs = ""
//
//    for (i in 0 until n) {
//        val input = br.readLine()
//        matrix.add(input)
//
//        inputs += input
//    }
//    var alphabets = inputs.toCharArray().distinct()
//
//    alphabets =alphabets.sorted()
//
//    println(alphabets)
//
//    backtracking(matrix, alphabets, BooleanArray(26), IntArray(26), alphabets.size, 0)
//
//    println(ans)
//}
//
//private fun backtracking(
//    matrix: List<String>,
//    alphabets: List<Char>,
//    visit: BooleanArray,
//    matching: IntArray,
//    size: Int,
//    cnt: Int
//) {
//    if (cnt == size) {
//        var num = 0
//
//        for (alphabet in matrix) {
//            var carry = 1
//
//            repeat(alphabet.length - 1) {
//                carry *= 10
//            }
//
//            for (i in 0 until alphabet.length) {
//                num += matching[alphabet[i] - 'A'] * carry
//                carry /= 10
//            }
//        }
//
//        ans = if (ans < num) num else ans
//        return
//    }
//
//    for (i in 0..9) {
//        if (visit[i]) continue
//
//        visit[i] = true
//
//        matching[alphabets[cnt] - 'A'] = i
//        backtracking(matrix, alphabets, visit, matching, size, cnt + 1)
//
//        visit[i] = false
//    }
//}