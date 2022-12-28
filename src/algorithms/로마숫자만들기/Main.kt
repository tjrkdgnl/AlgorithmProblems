package algorithms.로마숫자만들기

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * 문제 조건은 최대 N자리가 20개까지 허용이다.
 * 또한 각 자리에는 중복적으로 값이 올 수 있기때문에 O(4^20)정도의 시간복잡도가 걸린다.
 * 하지만 연산 결과를 중복적으로 허용하지 않기 때문에 1,2,3으로 6을 만들던 1,3,2 / 3,2,1로 6을 만들던 상관없다.
 * 따라서 이 문제를 조합으로 변경하여 풀게 되면 O(2^N)정도의 시간으로 변경할 수 있다.
 * 조합으로 변경하기 위해 인덱스 범위를 좁혀 나간다.
 */
private fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()

    val nums = intArrayOf(1, 5, 10, 50)
    val visit = BooleanArray(1001)

    println(find(n, nums, visit, 0, 0, 0))
}

private fun find(n: Int, nums: IntArray, visit: BooleanArray, idx: Int, cnt: Int, sum: Int): Int {
    if (n == cnt) {
        return if (!visit[sum]) {
            visit[sum] = true
            1
        } else {
            0
        }
    }

    var ret = 0

    for (i in idx until nums.size) {
        ret += find(n, nums, visit, i, cnt + 1, sum + nums[i])
    }

    return ret
}