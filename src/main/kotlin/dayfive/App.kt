package dayfive

object App {
    @JvmStatic
    fun main(args: Array<String>) {
        val fileContent = App::class.java.getResource("/dayfive/input.txt").readText()

        val (unparsedArrangement, procedure) = fileContent.split("\n\n")

        val unparsedInstructions = procedure.split("\n")
        val instructions = unparsedInstructions.map{ unparsedInstruction -> Instruction.fromString(unparsedInstruction) }

        val initialArrangement = Arrangement.fromString(unparsedArrangement)

        println("Part one answer: ${partOne(initialArrangement, instructions)}")
        println("Part two answer: ${partTwo(initialArrangement, instructions)}")
    }

    private fun partOne(initialArrangement: Arrangement, instructions: List<Instruction>): String {
        val finalArrangement = instructions.fold(initialArrangement) { arrangement, instruction -> arrangement.moveCrates(instruction) }

        return finalArrangement.getTopOfStacks()
    }

    private fun partTwo(initialArrangement: Arrangement, instructions: List<Instruction>): String {
        val finalArrangement = instructions.fold(initialArrangement) { arrangement, instruction -> arrangement.moveCrates(instruction, true) }

        return finalArrangement.getTopOfStacks()
    }
}
class Arrangement(private val stackToCrates: Map<Int, List<Char>>) {

    fun getTopOfStacks(): String {
        return stackToCrates.toSortedMap().values.map{it[0]}.joinToString("")
    }

    fun moveCrates(instruction: Instruction, canPickUpManyCrates: Boolean = false): Arrangement {
        val originList = stackToCrates[instruction.origin]
        val destinationList = stackToCrates[instruction.destination]
        val cratesToMove = if (canPickUpManyCrates) originList!!.take(instruction.quantity) else originList!!.take(instruction.quantity).reversed()

        val newOriginList = originList.subList(instruction.quantity, originList.size)
        val newDestinationList = cratesToMove + destinationList!!
        val newStackToCrates = stackToCrates + (instruction.origin to newOriginList) + (instruction.destination to newDestinationList)
        return Arrangement(newStackToCrates)
    }

    companion object {
        fun fromString(string: String): Arrangement {
            val x = string.split("\n")

            val stackContents = x.subList(0, x.size - 1)
                .map{ it.replace("    ", " [] ") }
                .map{ it.split(" ") }
                .map { it.filter { string -> string.isNotEmpty() } }
                .map { it.map{ string -> string[1] } }
                .flatMap { it.withIndex() }
                .filter{ it.value != ']' }
                .groupBy { it.index }
                .map{ it.key + 1 to it.value.map { thing -> thing.value } }
                .toMap()

            return Arrangement(stackContents)
        }
    }
}

class Instruction(val origin: Int, val destination: Int, val quantity: Int) {

    companion object {
        fun fromString(string: String): Instruction {
            val (move, from, to) = string.split(" ").chunked(2)
            val origin = from[1].toInt()
            val destination = to[1].toInt()
            val quantity = move[1].toInt()

            return Instruction(origin, destination, quantity)
        }
    }
}