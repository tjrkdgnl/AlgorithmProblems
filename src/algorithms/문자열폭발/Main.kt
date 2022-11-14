package algorithms.문자열폭발

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Stack

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val str = br.readLine()
    val fire = br.readLine()
    val stack = Stack<Char>()
    var idx = 0

    for (s in str) {
        idx = fire.length - 1

        if (fire[idx] == s) {
            val save = Stack<Char>()

            while (stack.isNotEmpty() && idx > 0) {
                if (stack.peek() != fire[--idx]) break
                save.push(stack.pop())
            }

            if (save.size != fire.length - 1) {
                while (save.isNotEmpty()) {
                    stack.push(save.pop())
                }

                stack.push(s)
            }

        } else {
            stack.push(s)
        }
    }

    if (stack.isNotEmpty()) {
        val sb = StringBuilder()

        for (data in stack) {
            sb.append(data)
        }

        println(sb.toString())
    } else {
        println("FRULA")
    }
}