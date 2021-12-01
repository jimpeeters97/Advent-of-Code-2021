import java.io.File

fun main() {
    val depths = arrayListOf<Int>()

    File("src/files/day1.txt").forEachLine {
        depths.add(it.toInt())
    }

    println("The result of part 1 is: " + countIncreased(depths))
    println("The result of part 2 is: " + countMeasurements(depths))
}

fun countIncreased(depths: List<Int>): Int {
    var last = 0
    var result = 0
    for (i in 1 until depths.size) {
        val newDepth = depths[i - 1]
        if(last != 0 && last < newDepth) {
            result++
        }
        last = newDepth
    }
    return result
}

fun countMeasurements(depths: List<Int>) : Int {
    var lastWindow = 0
    var result = 0
    for (i in 2 until depths.size) {
        val newWindow = depths[i - 2] + depths[i - 1] + depths[i]
        if(lastWindow != 0 && lastWindow < newWindow) {
            result++
        }
        lastWindow = newWindow
    }
    return result
}