package dec1

import java.io.File


fun dec1() {
    var firstDigit: Int
    var lastDigit: Int
    var runningTotal = 0
    val valueMap: HashMap<String, Int> = hashMapOf(
        Pair("one", 1),
        Pair("two", 2),
        Pair("three", 3),
        Pair("four", 4),
        Pair("five", 5),
        Pair("six", 6),
        Pair("seven", 7),
        Pair("eight", 8),
        Pair("nine", 9),
    )
    val wordDigits = WordTrie(valueMap.keys.toList())

    val valueMapBackwards: HashMap<String, Int> = hashMapOf(
        Pair("eno", 1),
        Pair("owt", 2),
        Pair("eerht", 3),
        Pair("ruof", 4),
        Pair("evif", 5),
        Pair("xis", 6),
        Pair("neves", 7),
        Pair("thgie", 8),
        Pair("enin", 9),
    )
    val wordDigitsBackwards = WordTrie(valueMapBackwards.keys.toList())

    File("src/main/kotlin/dec1/input.txt").forEachLine { line ->
        firstDigit = getFirstNumber(line, valueMap, wordDigits)!!
        lastDigit = getFirstNumber(line.reversed(), valueMapBackwards, wordDigitsBackwards)!!
        runningTotal += (firstDigit * 10) + lastDigit
    }

    println(runningTotal)
}

fun numericalValue(toCompute: String, wordValueMap: HashMap<String, Int>): Int? {
    return if (toCompute.length == 1 && toCompute[0] in '1'..'9') {
        toCompute[0] - '0'
    } else if (toCompute[toCompute.length - 1] in '1'..'9') {
        toCompute[toCompute.length - 1] - '0'
    } else {
        wordValueMap[toCompute]
    }
}

fun getFirstNumber(line: String, valueMap: HashMap<String, Int>, wordDigits: WordTrie): Int? {
    var possibleDigitWord = ""
    for (c in line) {
        possibleDigitWord += c
        val currentValue = numericalValue(possibleDigitWord, valueMap)
        if (currentValue != null) {
            return currentValue
        }
        if (!wordDigits.containsWord(possibleDigitWord)) {
            var promisingWord = ""
            for (cutoffPosition in possibleDigitWord.indices) {
                val possibleSubstring = possibleDigitWord.substring(cutoffPosition)
                if (wordDigits.containsWord(possibleSubstring)) {
                    promisingWord = possibleSubstring
                    continue
                }
            }
            possibleDigitWord = promisingWord
        }
    }
    return null
}

