package algorithms.로마숫자만들기

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * 문제 조건은 최대 N자리가 20개까지 허용이다.
 * 또한 각 자리에 올 수 있는 수가 4개 이기에 O(4^20) = O(2^40)으로 연산해야하는 개수가 억단위이다
 * 하지만 중복된 수를 제외한 서로다른 수를 체크하는 것이기 때문에 백트래킹을 적용하면 충분히 가능한 연산이 될 수 있다.
 * 따라서 모든 경우의 수를 구한 뒤 중복되는지 확인하는 것이 아니라, 애초에 탐색하는 범위를 줄여나간다.
 * 첫 idx는 0~N까지 모두 훑겠지만, 이후부터는 n-1,n-2,n-3....1로 점차 탐색하는 범위가 줄어든다.
 * 따라서 해당 시간복잡도는 O(N^4)정도로 줄어들게 된다.
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()

    val nums = intArrayOf(1, 5, 10, 50)
    val visit = BooleanArray(1001)

    println(find(n, nums, visit, 0, 0, 0))
}

fun find(n: Int, nums: IntArray, visit: BooleanArray, idx: Int, cnt: Int, sum: Int): Int {
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