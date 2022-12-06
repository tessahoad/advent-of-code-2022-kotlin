package daysix

object App {
    @JvmStatic
    fun main(args: Array<String>) {
        val signal = App::class.java.getResource("/daysix/input.txt").readText()

        val windows = signal.windowed(4).map{ it.toSet() }
        val startOfPacketMarker = windows.first{ it.size == 4 }.joinToString("")

        val processedCharacters = signal.indexOf(startOfPacketMarker) + 4
        println("Part one answer: $processedCharacters")
    }
}