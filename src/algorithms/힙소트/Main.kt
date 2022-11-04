package algorithms.힙소트

private class Heap(private val size: Int) {
    private var idx = 0
    private var arr = IntArray(size + 1)
    var currentSize = 0

    fun add(data: Int) {
        idx = ++currentSize

        while (idx != 1) {
            if (arr[idx / 2] > data) {
                val tmp = arr[idx / 2]
                arr[idx / 2] = data
                arr[idx] = tmp
                idx /= 2
            } else {
                break
            }
        }

        arr[idx] = data
    }

    private fun heapify(parentIdx: Int) {
        val left = parentIdx * 2
        val right = left + 1
        var idx = parentIdx

        if (left <= currentSize && arr[left] < arr[idx]) {
            idx = left
        }

        if (right <= currentSize && arr[right] < arr[idx]) {
            idx = right
        }

        if (idx != parentIdx) {
            val tmp = arr[idx]
            arr[idx] = arr[parentIdx]
            arr[parentIdx] = tmp

            heapify(idx)
        }
    }

    private fun remove(): Int {
        val head = arr[1]
        arr[1] = arr[currentSize--]

        heapify(1)
        return head
    }

    fun display() {
        val sb = StringBuilder()

        for (i in 1 until arr.size) {
            sb.append(remove()).append(' ')
        }

        println(sb.toString())
    }
}


fun main() {

    val list = intArrayOf(5, 2, 3, 1, 4)
    val heap = Heap(list.size)

    for (data in list) {
        heap.add(data)
    }

    heap.display()
}