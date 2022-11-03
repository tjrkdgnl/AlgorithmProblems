package algorithms.테스트.리스트와머지소트_S


private class Node(
    var value: Int = -1,
    var next: Node? = null
) {
    private var head: Node? = null
    private var tail: Node? = null

    fun add(value: Int) {
        val newNode = Node(value)

        if (head == null) {
            head = newNode
            tail = newNode
        } else {

            tail?.next = newNode
            tail = newNode
        }
    }

    fun display() {
        var current = head

        val sb = StringBuilder()

        while (current != null) {
            sb.append(current.value).append(' ')
            current = current.next
        }

        println(sb.toString())
    }

    private fun getMiddle(node: Node?): Node? {
        var slow: Node? = node
        var fast: Node? = node

        while (slow?.next != null && fast?.next?.next != null) {
            slow = slow.next
            fast = fast.next?.next
        }

        return slow
    }

    fun sort() {
        val result = mergeSort(head)
        head = result
    }

    private fun mergeSort(node: Node?): Node? {
        if (node?.next == null) return node

        val mid = getMiddle(node)
        val nextMidNode = mid?.next

        mid?.next = null

        val leftNode = mergeSort(node)
        val rightNode = mergeSort(nextMidNode)

        return merge(leftNode, rightNode)
    }

    private fun merge(left: Node?, right: Node?): Node? {
        val result: Node?

        if (left == null) {
            return right
        }
        if (right == null) {
            return left
        }

        if (left.value < right.value) {
            result = left
            result.next = merge(left.next, right)
        } else {
            result = right
            result.next = merge(left, right.next)
        }

        return result
    }
}

fun main() {

    val linkedList = Node()

    linkedList.add(3)
    linkedList.add(2)
    linkedList.add(5)
    linkedList.add(1)
    linkedList.add(4)

    linkedList.sort()

    linkedList.display()
}