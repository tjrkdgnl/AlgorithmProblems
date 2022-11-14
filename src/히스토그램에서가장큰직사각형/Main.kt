package 히스토그램에서가장큰직사각형

import java.io.BufferedReader
import java.io.InputStreamReader

private fun divide(arr: LongArray, start: Int, end: Int): Long {
    if (start >= end) return arr[start]

    val mid = (start + end) / 2

    val leftArea = divide(arr, start, mid)
    val rightArea = divide(arr, mid + 1, end)

    var max = if (leftArea > rightArea) leftArea else rightArea

    val area = getMaxArea(arr, start, mid, end)

    max = if (max > area) max else area

    return max
}

private fun getMaxArea(arr: LongArray, start: Int, mid: Int, end: Int): Long {
    var left = mid
    var right = mid
    var height = arr[mid]
    var maxArea = height

    while (start < left && right < end) {
        height = if (arr[left - 1] < arr[right + 1]) {
            if (height > arr[++right]) arr[right] else height
        } else {
            if (height > arr[--left]) arr[left] else height
        }

        val area = (right - left + 1) * height
        maxArea = if (maxArea < area) area else maxArea
    }

    if (start < left) {
        while (start < left) {
            height = if (height > arr[--left]) arr[left] else height

            val area = (right - left + 1) * height
            maxArea = if (maxArea < area) area else maxArea
        }
    } else {
        while (right < end) {
            height = if (height > arr[++right]) arr[right] else height

            val area = (right - left + 1) * height
            maxArea = if (maxArea < area) area else maxArea
        }
    }

    return maxArea
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val sb = StringBuilder()

    while (true) {
        var height = 0L
        val input = br.readLine().split(" ").map { it.toLong() }.toLongArray()

        if (input.size == 1 && input[0] == 0L) {
            print(sb.toString())
            break
        }

        val area = divide(input, 0, input.size-1)

        height = if (height > area) height else area

        sb.append(height).append('\n')
    }
}