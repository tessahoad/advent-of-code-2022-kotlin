package dayone

object App {
    @JvmStatic
    fun main(args: Array<String>) {
        val fileContent = App::class.java.getResource("/dayone/input.txt").readText()

        val unparsedElves = fileContent.split("\n\n")

        val elves = unparsedElves.map { unparsedElf ->
            val calories =  unparsedElf.split("\n").map{unparsedCalories -> unparsedCalories.toInt() }
            Elf(calories)
        }

        println(partOne(elves))
    }

    private fun partOne(elves: List<Elf>): Int {
        return elves.maxBy { elf -> elf.calories.sum() }!!.calories.sum()
    }
}