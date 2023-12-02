package dec2

import java.io.File

data class Game(val id: Int, var turns: MutableList<Turn>)
data class Turn(var r: Int = 0, var g: Int = 0, var b: Int = 0)

fun dec2() {
    val games: MutableList<Game> = mutableListOf()

    File("src/main/kotlin/dec2/input.txt").forEachLine{ line->
        games.add(toGame(line))
    }

    var runningTotal = 0

    for(game in games) {
        var highestRed = 0
        var highestGreen = 0
        var highestBlue = 0
        for(turn in game.turns) {
            if(turn.r > highestRed) {
                highestRed = turn.r
            }
            if(turn.g > highestGreen) {
                highestGreen = turn.g
            }
            if(turn.b > highestBlue) {
                highestBlue = turn.b
            }
        }
        // part 1
//        if(highestRed <= 12 && highestGreen <= 13 && highestBlue <= 14) {
//            runningTotal += game.id
//        }
        runningTotal += highestRed * highestBlue * highestGreen
    }

    println(runningTotal)
}

fun toGame(gameString: String): Game {
    val colonSplit = gameString.split(':')
    val id: Int = colonSplit[0].split(' ')[1].toInt()
    val turnStrings = colonSplit[1].split(';')
    val game = Game(id, mutableListOf())
    for(turnString in turnStrings) {
        game.turns.add(toTurn(turnString))
    }
    return game;
}

fun toTurn(turnString: String): Turn {
    val rawColors = turnString.split(',')
    val returnTurn = Turn()
    for(rawColor in rawColors) {
        addColorPair(rawColor, returnTurn)
    }
    return returnTurn
}

fun addColorPair(input: String, turn: Turn) {
    val split = input.trim().split(' ')
    val nameOfColor: String = split[1].trim()
    val amount: Int = split[0].trim().toInt()
    when(nameOfColor) {
        "red" -> turn.r += amount
        "green" -> turn.g += amount
        "blue" -> turn.b += amount
    }
}
