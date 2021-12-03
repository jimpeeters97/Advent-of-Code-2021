import java.io.File
import kotlin.math.pow

fun main() {
    val numbers = arrayListOf<String>()

    File("src/files/day3.txt").forEachLine {
        numbers.add(it)
    }

    println("The result of part 1 is: " + countPowerConsumption(numbers))
    println("The result of part 2 is: " + countLifeSupportRating(numbers))
}

fun countPowerConsumption(numbers: List<String>): Int {
    val size = numbers[0].length
    var commonBits = ""


    for (x in 0 until size) {
        var zeroBit = 0
        var oneBit = 0
        for (element in numbers) {
            if (element[x] == '0') {
                zeroBit++
            } else {
                oneBit++
            }
        }

        commonBits += when {
            zeroBit > oneBit -> {
                "0"
            }
            else -> {
                "1"
            }
        }
    }

    var turnaroundBits = ""
    for (i in commonBits.indices) {
        turnaroundBits += if (commonBits[i] == '0') {
            "1"
        } else {
            "0"
        }
    }

    return bitsToInt(commonBits) * bitsToInt(turnaroundBits)
}

fun countLifeSupportRating(numbers: List<String>): Int {
    val size = numbers[0].length
    val oxygenNumbers = ArrayList(numbers)
    val co2Numbers = ArrayList(numbers)
    val oxygenBits: String
    val co2Bits: String

    for (x in 0 until size) {
        var zeroBit = 0
        var oneBit = 0

        for (element in oxygenNumbers) {
            if (element[x] == '0') {
                zeroBit++
            } else {
                oneBit++
            }
        }

        if (zeroBit > oneBit) {
            oxygenNumbers.filter { it[x] == '1' }
                    .forEach { oxygenNumbers.remove(it) }
        } else {
            oxygenNumbers.filter { it[x] == '0' }
                    .forEach { oxygenNumbers.remove(it) }
        }

        if(oxygenNumbers.size == 1) {
            break
        }
    }

    for (x in 0 until size) {
        var zeroBit = 0
        var oneBit = 0

        for (element in co2Numbers) {
            if (element[x] == '0') {
                zeroBit++
            } else {
                oneBit++
            }
        }

        if (oneBit < zeroBit) {
            co2Numbers.filter { it[x] == '0' }
                    .forEach { co2Numbers.remove(it) }
        } else {
            co2Numbers.filter { it[x] == '1' }
                    .forEach { co2Numbers.remove(it) }
        }

        if(co2Numbers.size == 1) {
            break
        }
    }

    oxygenBits = oxygenNumbers[0]
    co2Bits = co2Numbers[0]

    return bitsToInt(oxygenBits) * bitsToInt(co2Bits)
}

fun bitsToInt(bits: String): Int {
    var result = 0
    for (i in bits.indices) {
        if (bits[i] == '1') {
            result += if (bits.length - i == 0) {
                1
            } else {
                2.0.pow((bits.length - i - 1).toDouble()).toInt()
            }
        }
    }
    return result
}