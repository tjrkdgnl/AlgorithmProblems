package algorithms.두동전

import java.io.BufferedReader
import java.io.InputStreamReader

private val dy = intArrayOf(0, -1, 0, 1)
private val dx = intArrayOf(-1, 0, 1, 0)

private var min = 987654321

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val matrix = Array(n) { CharArray(m) }

    val yPos = mutableListOf<Int>()
    val xPos = mutableListOf<Int>()

    for (i in 0 until n) {
        val input = br.readLine()

        for (j in 0 until input.length) {
            matrix[i][j] = input[j]

            if (input[j] == 'o') {
                yPos.add(i)
                xPos.add(j)
            }
        }
    }


    val size = if (n > m) n else m

    bruteForce(matrix, IntArray(size), yPos, xPos, 0)

    if (min == 987654321) {
        println(-1)
    } else {
        println(min)
    }
}

fun bruteForce(
    matrix: Array<CharArray>,
    direction: IntArray,
    yPos: MutableList<Int>,
    xPos: MutableList<Int>,
    cnt: Int
) {
    if (cnt == direction.size) {
        var count = 0
        var fy = yPos[0]
        var fx = xPos[0]
        var sy = yPos[1]
        var sx = xPos[1]
        var fall = false

        for (j in direction) {
            val fNy = fy + dy[j]
            val fNx = fx + dx[j]
            val sNy = sy + dy[j]
            val sNx = sx + dx[j]

            if ((fNy < 0 || fNy >= matrix.size || fNx < 0 || fNx >= matrix[0].size)) {
                if ((sNy >= 0 && sNy < matrix.size && sNx >= 0 && sNx < matrix[0].size)) {
                    count++
                    fall = true
                    break
                }

                count++
                continue
            }
            if ((sNy < 0 || sNy >= matrix.size || sNx < 0 || sNx >= matrix[0].size)) {
                count++
                fall = true
                break
            }
            if (matrix[fNy][fNx] != '#') {
                fy = fNy
                fx = fNx
            }
            if (matrix[sNy][sNx] != '#') {
                sy = sNy
                sx = sNx
            }

            count++
        }

        if (fall) {
            min = if (min > count) count else min
        }

        return
    }


    for (i in 0 until 4) {


        direction[cnt] = i
        bruteForce(matrix, direction, yPos, xPos, cnt + 1)
    }
}
