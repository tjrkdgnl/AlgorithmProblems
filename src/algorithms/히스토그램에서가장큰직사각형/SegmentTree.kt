package algorithms.히스토그램에서가장큰직사각형

private fun init(tree: LongArray, arr: LongArray, node: Int, start: Int, end: Int): Long {
    if (start == end) {
        tree[node] = arr[start]
        return tree[node]
    }

    val mid = (start + end) / 2
    tree[node] = init(tree, arr, node * 2, start, mid) + init(tree, arr, (node * 2) + 1, mid + 1, end)

    return tree[node]
}

private fun sum(tree: LongArray, node: Int, left: Int, right: Int, from: Int, to: Int): Long {
    if (right < from || to < left) return 0L

    if (from <= left && right <= to) return tree[node]

    val mid = (left + right) / 2

    return sum(tree, node * 2, left, mid, from, to) + sum(tree, node * 2 + 1, mid + 1, right, from, to)
}


fun main() {
    val arr = longArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val tree = Array(arr.size * 4) { 0L }.toLongArray()

    init(tree, arr, 1, 0, arr.size - 1)

    println(tree.contentToString())

    val result = sum(tree, 1, 0, arr.size - 1, 3, 8)

    println(result)
}