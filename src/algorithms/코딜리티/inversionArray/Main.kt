package algorithms.코딜리티.inversionArray

import java.util.*

var ans =0

fun merge(arr: IntArray, start: Int, mid: Int, end: Int) {
    var l = start
    var r = mid +1
    var idx = start
    val copyArr = arr.copyOfRange(0, arr.size)

    val entracne = "10:00".split(":").map { it.toInt() }.toTypedArray()

    while (l <= mid && r <= end) {
        if (arr[l] <= arr[r]) {
            copyArr[idx++] = arr[l++]
        } else {
            copyArr[idx++] = arr[r++]
            ans++
        }
    }

    if (l > mid) {
        while (r <= end) {
            copyArr[idx++] = arr[r++]
        }
    } else {
        while (l <= mid) {
            copyArr[idx++] = arr[l++]
        }
    }

    for (i in 0 until copyArr.size) {
        arr[i] = copyArr[i]
    }

}

fun mergeSort(arr: IntArray, start: Int, end: Int) {
    if (start >= end) return

    val mid = (start + end) / 2

    mergeSort(arr, start, mid)
    mergeSort(arr, mid + 1, end)

    merge(arr, start, mid, end)
}


fun main() {
    val arr = intArrayOf(-1, 6, 3, 4, 7, 4)
    mergeSort(arr, 0, arr.size-1)
    println(ans)

}