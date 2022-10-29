import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, s) = br.readLine().split(" ").map { it.toInt() }
    val list = br.readLine().split(" ").map { it.toInt() }

    var slow = 0
    var fast = 1
    var sum = list[slow]
    var len = 1
    var min = 987654321

    if (sum == s) {
        println(1)
        return
    }

    while (slow <= fast) {
        if (sum > s) {
            sum -= list[slow++]
            len -= 1
        } else {
            if (fast >= list.size) break

            sum += list[fast++]
            len += 1
        }

        if (sum >= s) {
            if (min > len) {
                min = len
            }
        }
    }

    if (min == 987654321) {
        println(0)
    } else {
        println(min)
    }
}