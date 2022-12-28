package algorithms.차량번호판1

import java.io.BufferedReader
import java.io.InputStreamReader

private fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val type = br.readLine()

    var answer =0

    if (type[0] == 'c') {
        for (cha in 'a'..'z') {
           answer+= find(type, cha.toString(), 1)
        }
    } else {
        for (cha in '0'..'9') {
           answer+= find(type, cha.toString(), 1)
        }
    }

    println(answer)
}

private fun find(type: String, ans: String, idx: Int): Int {
    if (ans.length == type.length) {
        return 1
    }

    var ret = 0

    if (type[idx] == 'c') {
        for (cha in 'a'..'z') {
            if (type[idx - 1] == 'c' && ans[idx - 1] == cha) continue

           ret += find(type, ans + cha, idx + 1)
        }
    } else {
        for (cha in '0'..'9') {
            if (type[idx - 1] == 'd' && ans[idx - 1] == cha) continue

           ret += find(type, ans + cha, idx + 1)
        }
    }

    return ret
}