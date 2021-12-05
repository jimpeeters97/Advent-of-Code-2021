import java.io.File

fun main() {
    val bingoNumbers = readBingoNumbers()
    val bingoBoards = readBingoBoards()

    println("The result of part 1 is: " + bingo(bingoNumbers, bingoBoards))
}

fun readBingoNumbers(): List<Int> {
    return File("src/files/day4.txt")
            .bufferedReader().readLine().split(",").map { it.toInt() }
}

fun readBingoBoards(): List<List<List<Int>>> {
    val result = mutableListOf<List<List<Int>>>()
    var bingoBoard = mutableListOf<List<Int>>()
    var idx = 0
    var boardIdx = 0
    File("src/files/day4.txt").forEachLine {
        if (idx > 1) {
            val bingoNumbersLine = it.chunked(3).map { str ->
                str.removePrefix(" ").removeSuffix(" ").toInt()
            }

            if (it.isNotEmpty()) {
                bingoBoard.add(bingoNumbersLine)

                boardIdx++

                if (boardIdx % 5 == 0) {
                    result.add(bingoBoard)
                    bingoBoard = mutableListOf()
                    boardIdx = 0
                }
            }
        }
        idx++
    }
    return result
}

fun bingo(numbers: List<Int>, boards: List<List<List<Int>>>): Int {
    var winningBoard = Triple(0, 0, mutableListOf<List<Int>>())
    val scrappedNumbers = mutableMapOf<Int, MutableMap<Int, MutableList<Int>>>()
    val doneNumbers = arrayListOf<Int>()

    outerloop@ for (n in numbers.indices) {
        doneNumbers.add(numbers[n])
        for (x in boards.indices) {
            for (y in boards[x].indices) {
                for (z in boards[x][y].indices) {
                    if (boards[x][y][z] == numbers[n]) {
                        if (scrappedNumbers[x] == null) {
                            val yMap = mutableMapOf<Int, MutableList<Int>>()
                            yMap[y] = mutableListOf()
                            scrappedNumbers[x] = yMap
                        }

                        if(scrappedNumbers[x]?.get(y) == null) {
                            scrappedNumbers[x]?.put(y, mutableListOf())
                        }

                        scrappedNumbers[x]?.get(y)?.add(numbers[n])

                        if (scrappedNumbers[x]?.get(y)?.size == 5) {
                            winningBoard = Triple(x, y, boards[x] as MutableList<List<Int>>)
                            break@outerloop
                        }
                    }
                }
            }
        }
    }

    var result = 0

    for (i in winningBoard.third.indices) {
        for (j in winningBoard.third[i].indices) {
            if(!doneNumbers.contains(winningBoard.third[i][j])) {
                result += winningBoard.third[i][j]
            }
        }
    }
    return result * doneNumbers.last()
}