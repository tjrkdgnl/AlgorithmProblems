package algorithms.테스트.연관검색어_V

import java.util.*

/**
 * 단어 셋이 주어졌을 때, 사용자가 입력한 문자열을 내포하는 연관 단어를 추천하기 위해 필요한 알고리즘을 구현해봐라
 * 모든 단어는 소문자로 주어진다.
 */

private class Trie {
    private inner class Node(
        val char: String? = null,
        val alphabetNodes: Array<Node?>,
        var isEnd: Boolean = false
    )

    private var dummy: Node = Node(null, Array(26) { null })

    fun add(word: String) {
        var tail: Node? = null

        for (cha in word) {
            val idx = cha - 'a'
            val newNode = Node(cha.toString(), Array(26) { null })

            if (tail == null) { //첫 시작은 더미 노드에서 저장된 알파벳으로 시작해야 한다.
                if (dummy.alphabetNodes[idx] == null) {
                    dummy.alphabetNodes[idx] = newNode
                    tail = newNode
                } else {
                    tail = dummy.alphabetNodes[idx]
                }
            } else {
                tail = if (tail.alphabetNodes[idx] == null) { // 다음 번 노드가 존재하지 않으면 이어준다.
                    tail.alphabetNodes[idx] = newNode
                    newNode
                } else { // 기존의 단어로 인해 더하고자 하는 노드가 이미 있을 수 있다.
                    tail.alphabetNodes[idx]
                }
            }
        }
        // 단어의 노드 연결이 끝났으면 tail은 그 단어의 끝 알파벳을 가르키므로
        // 이후로는 단어가 연결되어 있지 않다는 플래그를 설정한다.
        tail?.isEnd = true
    }

    //관련 단어 검색 시, 일부 단어의 끝에서부터 찾아야하므로 word의 끝 알파벳을 찾는다.
    private fun searchEndOfWord(word: String): Node? {
        //첫번째 알파벳은 반드시 더미노드에 저장되므로 더미노드로부터 불러와야한다.
        var tail: Node? = dummy.alphabetNodes[word[0] - 'a'] ?: return null

        for (i in 1 until word.length) { //2번째 알파벳은 tail의 alphabetNodes를 살펴봐야 한다.
            val idx = word[i] - 'a'
            tail = tail?.alphabetNodes?.get(idx)
        }

        return tail
    }

    //BFS 알고리즘을 통해 word와 관련있는 단어를 만들어 리스트로 리턴한다.
    fun relativeWords(word: String): MutableList<String> {
        if (word.isEmpty()) return mutableListOf()

        val endNode: Node = searchEndOfWord(word) ?: return mutableListOf()

        val node = Node(
            word,
            alphabetNodes = endNode.alphabetNodes,
            isEnd = endNode.isEnd
        )

        val relatives = mutableListOf<String>()
        val queue = LinkedList<Node>()

        queue.offer(node)

        while (queue.isNotEmpty()) {
            val current = queue.poll()

            if (current.isEnd) {
                current.char?.let { string ->
                    relatives.add(string)
                }
                continue
            }

            for (next in current.alphabetNodes) {
                if (next != null) {
                    val newNode = Node(current.char + next.char, next.alphabetNodes, next.isEnd)
                    queue.offer(newNode)
                }
            }
        }

        return relatives
    }
}

fun main() {
    val trie = Trie()

    trie.add("apple")
    trie.add("appointment")
    trie.add("application")
    trie.add("trie")
    trie.add("triple")

    println(trie.relativeWords("app"))
    println(trie.relativeWords("tri"))

    println("-----------------------------")

    println(trie.relativeWords("as"))
    println(trie.relativeWords("ts"))

}