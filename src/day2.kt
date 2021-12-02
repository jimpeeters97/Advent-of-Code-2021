import java.io.File

fun main() {
    val positions = arrayListOf<Pair<String, Int>>()

    File("src/files/day2.txt").forEachLine {
        val dir = it.substringBefore(" ")
        val number = it.substringAfter(" ").toInt()

        positions.add(Pair(dir, number))
    }

    println("The result is: " + countPositions(positions))
}

fun countPositions(positions: List<Pair<String, Int>>): Int {
    var posX = 0
    var posY = 0
    var aim = 0

    positions.forEach {
        when (it.first) {
            "forward" -> {
                posX += it.second
                posY += (aim * it.second)
            }
            "down" -> {
                //posY += it.second
                aim += it.second
            }
            "up" -> {
                //posY -= it.second
                aim -= it.second
            }
            else -> {
                //posX -= it.second
            }
        }
    }
    return posX * posY
}