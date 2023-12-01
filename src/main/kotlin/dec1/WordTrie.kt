package dec1

class TrieNode<T>() {
    private var nodes: HashMap<T, TrieNode<T>> = hashMapOf()
    fun step(next: T) : TrieNode<T>? {
        if(nodes.contains(next)) {
            return nodes[next]
        }
        return null
    }
    fun add(toAdd: T): TrieNode<T> {
        return if(!nodes.contains(toAdd)) {
            val newNode = TrieNode<T>()
            nodes[toAdd] = newNode
            newNode
        } else {
            nodes[toAdd]!!
        }
    }
}

class WordTrie() {
    private val rootNode = TrieNode<Char>();

    constructor(initWords: List<String>) : this() {
        for(word in initWords) {
            addWord(word)
        }
    }

    fun addWord(toAdd: String) {
        var currentNode: TrieNode<Char> = rootNode
        for(i in toAdd.indices) {
            currentNode = currentNode.add(toAdd[i])
        }
    }

    fun containsWord(toCheck: String): Boolean {
        var currentNode: TrieNode<Char>? = rootNode
        for(i in toCheck.indices) {
            currentNode = currentNode?.step(toCheck[i])
            if(currentNode == null) {
                return false;
            }
        }
        return true;
    }
}
