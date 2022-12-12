package algorithms.부분수열의합

import java.io.BufferedReader
import java.io.InputStreamReader

//fun main() {
//    val br = BufferedReader(InputStreamReader(System.`in`))
//    val (n, target) = br.readLine().split(" ").map { it.toInt() }
//    val list = br.readLine().split(" ").map { it.toInt() }
//
//    var l = 0
//    var r = 0
//
//    var sum = 0
//    var ans = 0
//
//    while (l <= r) {
//        if (sum < target) {
//            sum += list[r++]
//
//        } else {
//            if (sum == target) {
//                ans++
//            }
//
//            sum -= list[l++]
//        }
//    }
//
//    println(ans)
//}


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (size, target) = br.readLine().split(" ").map { it.toInt() }
    val list = br.readLine().split(" ").map { it.toInt() }
    var ans = 0

    for (i in 1..(1 shl size)) {
        var sum = 0

        for (j in 0 until size) {
            if (i and (1 shl j) >= 1) {
                sum += list[j]
            }
        }

        if (sum == target) {
            ans++
        }
    }

    if(target ==0){
        ans--
    }

    println(ans)
}

