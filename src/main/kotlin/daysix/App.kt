package daysix

object App {
    @JvmStatic
    fun main(args: Array<String>) {
        val signal = App::class.java.getResource("/daysix/input.txt").readText()

        println("Part one answer: ${getNumberOfProcessedCharacters(signal, 4)}")
        println("Part Two answer: ${getNumberOfProcessedCharacters(signal, 14)}")
    }

    private fun getNumberOfProcessedCharacters(signal: String, windowSize: Int): Int {
        val windows = signal.windowed(windowSize).map{ it.toSet() }
        val startOfPacketMarker = windows.first{ it.size == windowSize }.joinToString("")
        return signal.indexOf(startOfPacketMarker) + windowSize
    }
}